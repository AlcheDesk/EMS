package com.meowlomo.ems.core.service.background.impl;

import java.util.List;
import java.util.Random;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.meowlomo.ems.core.mapper.TaskAssignmentMapper;
import com.meowlomo.ems.core.mapper.TaskLogMapper;
import com.meowlomo.ems.core.mapper.WorkerControlMapper;
import com.meowlomo.ems.core.mapper.WorkerMapper;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExample;
import com.meowlomo.ems.core.model.TaskLog;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;
import com.meowlomo.ems.core.model.WorkerExample.Criteria;
import com.meowlomo.ems.core.resource.model.MeowlomoResponse;
import com.meowlomo.ems.core.service.background.TaskWorkerService;
import com.meowlomo.ems.core.service.util.WorkerUtilService;
import com.meowlomo.ems.external.vmc.api.VMCApiService;

@Service
public class TaskWorkerServiceImpl implements TaskWorkerService {

    private static final Logger logger = LoggerFactory.getLogger(TaskWorkerServiceImpl.class);

    private volatile int MAX_FETCH_SIZE = 20;

    @Autowired
    private TaskLogMapper taskLogMapper;

    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private TaskAssignmentMapper taskAssignmentMapper;

    @Autowired
    private WorkerControlMapper workerControlMapper;

    @Autowired
    private WorkerUtilService workerUtilService;
    
    @Autowired
    private VMCApiService vmcApiService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public int assignTasksToWorkers(Integer maxTaskNumber) {

        // get all new tasks first

        // this query will lock all new task rows, the row are not locked. this used a
        // operated sql transaction
        List<Task> newTasks = this.getFreeTasks(maxTaskNumber);
        logger.debug("found " + newTasks.size() + " new task to assign");

        // start to process the task
        // we need to get the conditions that we need to think of
        /*
         * 1: operating system 2: interactive 3: group 4: component list 5: cpu core 6:
         * ram
         */
        int totalAssignNumber = 0;
        // loop the task
        for (int taskCount = 0; taskCount < newTasks.size(); taskCount++) {
            Task targetTask = newTasks.get(taskCount);
            // we need have a need sql transaction to do this, os we use another method.
            if (this.assignTargetTaskToWorker(targetTask)) {
                totalAssignNumber = totalAssignNumber++;
            }
        }
        return totalAssignNumber;
    }

    /**
     * Gets the free tasks.
     *
     * @return the free tasks
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private List<Task> getFreeTasks(int fetchSize) {
        // get all new tasks first
        TaskExample newTaskExample = new TaskExample();
        newTaskExample.or().andStatusEqualTo("NEW");
        newTaskExample.setOrderByClause(" priority asc , created_at asc");
        RowBounds rowBounds = new RowBounds();
        if (MAX_FETCH_SIZE != 0) {
            rowBounds = new RowBounds(0, fetchSize);
        }
//        List<Task> newTasks = taskMapper.selectByExampleWithRowbounds(newTaskExample, rowBounds);
        List<Task> newTasks = taskAssignmentMapper.getTasksForAssignment(rowBounds);
        return newTasks;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private boolean assignTargetTaskToWorker(Task targetTask) {
        /*
         * we use the following logic to assign a task to a worker 1: we update the task
         * first which proper where conditions. 2: if the task is updated, we commit the
         * database transaction and this acts like a lock. 3: try to find a worker, if
         * not found, update the task back to the original status. return false. 4: if
         * found. we update the worker first and commit 5: send the task to the worker
         * by http 6: if fail. then update the worker and task back to the originals.
         */
        logger.debug("starting assigning task id=>" + targetTask.getId() + " uuid=>" + targetTask.getUuid());
        // ==================logic to find match worker
        WorkerExample tempWorkerExample = this.getTargetWorkerExampleByTask(targetTask);
        List<Worker> matchedWorkers = workerMapper.selectByExample(tempWorkerExample);
        Worker targetWorker = null;
        if (matchedWorkers.isEmpty()) {
            logger.debug("not worker matched for task " + targetTask.getUuid());
            return false;
        } else {
            // update a worker status for assignment
            // select a random worker
            Random random = new Random();
            targetWorker = matchedWorkers.get(random.nextInt(matchedWorkers.size()));
            // lock the worker by setting its status
            targetWorker.setTaskId(targetTask.getId());
            int workerToWorkingResult = this.rollbackWorkerToFreeFromReservedForAssignment(targetWorker);
            if (workerToWorkingResult != 1) {
                logger.debug("worker " + targetWorker.getUuid() + " status could not be updated and locked for assignment");
                try {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                } catch (Exception ex) {

                }
                // roll back the worker
                this.rollbackWorkerToFreeFromReservedForAssignment(targetWorker);
                return false;
            } else {
                logger.debug("worker " + targetWorker.getUuid() + " status updated to WORKING for assignment");
            }

            // we update and lock the task
            targetTask.setWorkerId(targetWorker.getId());
            int taskToWIPResult = this.updateTaskToWIPByPrimaryKeyForAssignment(targetTask);
            if (taskToWIPResult != 1) {
                logger.debug("task " + targetTask.getUuid() + " status could not be updated and locked for assignment");
                try {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                } catch (Exception ex) {

                }
                // roll back the worker
                this.rollbackTaskByPrimaryKeyFromAssignment(targetTask.getId());
                return false;
            }
        }

        // task and worker are locked and updated in place
        // we need to send the task to the worker
        String baseUrl = workerUtilService.getBaseUrl(targetWorker);
        String tokenString = targetWorker.getToken() == null ? null : targetWorker.getToken().toString();
        logger.debug("trying to assign task {} to worker with uuid {}, ip {}, token {}", targetTask.getUuid(), targetWorker.getUuid(), targetWorker.getIpAddress(), targetWorker.getToken());
        MeowlomoResponse response = vmcApiService.sendTaskToWorker(baseUrl, tokenString, targetTask);
        if (response != null) {
            // check reject and working and with task
            // get the meta data from the body
            if (!workerUtilService.checkWorkerAssignmentResponse(response)) { // false the reject check
                logger.error(" worker reponse has logic error. will skip this worker.");
                String message = " Task [" + targetTask.getUuid() + "] has been rejected by execution unit [" + targetWorker.getUuid() + "] without reasonable conditions, unit will be set to DOWN";
                this.addLogToTask(targetTask.getId(), message);
                // set the worker back to down
                this.setWorkerToDownFromReservedForFailedAssignment(targetWorker);
                // rollback the task
                this.rollbackTaskByPrimaryKeyFromAssignment(targetTask.getId());
            } else {// Response OK
                    // the task is assigned, we need to update the task and add log
                    // =========set log
                String message = " Task [" + targetTask.getUuid() + "] has been assigned to execution unit [" + targetWorker.getUuid() + "]";
                this.addLogToTask(targetTask.getId(), message);
            }
        } else {
            // failed to send the task to the worker
            try {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            } catch (Exception ex) {

            }
            // 1: we roll back the task,but set the worker to down
            this.rollbackTaskByPrimaryKeyFromAssignment(targetTask.getId());
            logger.error("task" + targetTask.getUuid() + " could not be sended to worker " + targetWorker.getUuid() + " will set the worker to down.");
            this.setWorkerToDownFromReservedForFailedAssignment(targetWorker);
            return false;
        }
        return true;
    }

    private WorkerExample getTargetWorkerExampleByTask(Task task) {
        WorkerExample workerExample = new WorkerExample();
        Criteria criteria = workerExample.or().andStatusEqualTo("FREE").andTaskIdIsNull();
        // group
        if (task.getGroup() != null && !task.getGroup().equalsIgnoreCase("*")) {
            criteria.andGroupEqualTo(task.getGroup());
        }

        // system
        if (task.getOperatingSystem() != null && !task.getOperatingSystem().equalsIgnoreCase("*")) {
            criteria.andOperatingSystemEqualTo(task.getOperatingSystem());
        }

        // ram
        if (task.getRamRequired() != null && !task.getRamRequired().equals(Integer.getInteger("0"))) {
            criteria.andRamGreaterThanOrEqualTo(task.getRamRequired());
        }

        // CPU
        if (task.getCpuCoreRequired() != null && !task.getCpuCoreRequired().equals(Integer.getInteger("0"))) {
            criteria.andCpuCoreGreaterThanOrEqualTo(task.getCpuCoreRequired());
        }

        // bandwidth
        if (task.getBandwidthRequired() != null && !task.getBandwidthRequired().equals(Integer.getInteger("0"))) {
            criteria.andBandwidthGreaterThan(task.getBandwidthRequired());
        }

        workerExample.or(criteria);
        return workerExample;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int updateTaskToWIPByPrimaryKeyForAssignment(Task task) {
        task.setStatus("WIP");
        return taskAssignmentMapper.updateTaskToWIPByPrimaryKeyForAssignment(task);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int rollbackTaskByPrimaryKeyFromAssignment(Long id) {
        return taskAssignmentMapper.rollbackTaskByPrimaryKeyFromAssignment(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int setWorkerToWorkingFromFreeWithTaskIdForAssignment(Worker worker) {
        return workerControlMapper.setWorkerToWorkingFromFreeWithTaskIdForAssignment(worker);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int rollbackWorkerToFreeFromReservedForAssignment(Worker worker) {
        return workerControlMapper.rollbackWorkerToFreeFromReservedForAssignment(worker);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int setWorkerToDownFromReservedForFailedAssignment(Worker worker) {
        return workerControlMapper.setWorkerToDownFromReservedForFailedAssignment(worker);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int addLogToTask(Long taskId, String message) {
        TaskLog log = new TaskLog();
        log.setMessage(message);
        log.setTaskId(taskId);
        return taskLogMapper.insert(log);
    }

}
