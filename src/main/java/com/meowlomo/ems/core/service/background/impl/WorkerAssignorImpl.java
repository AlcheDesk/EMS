package com.meowlomo.ems.core.service.background.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.meowlomo.ems.core.mapper.TaskAssignmentMapper;
import com.meowlomo.ems.core.mapper.WorkerControlMapper;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExample;
import com.meowlomo.ems.core.model.TaskExample.Criteria;
import com.meowlomo.ems.core.model.TaskLog;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;
import com.meowlomo.ems.core.resource.model.MeowlomoResponse;
import com.meowlomo.ems.core.service.background.WorkerAssignorService;
import com.meowlomo.ems.core.service.base.TaskLogService;
import com.meowlomo.ems.core.service.base.TaskService;
import com.meowlomo.ems.core.service.base.WorkerService;
import com.meowlomo.ems.core.service.util.WorkerUtilService;
import com.meowlomo.ems.external.vmc.api.VMCApiService;

@Service
public class WorkerAssignorImpl implements WorkerAssignorService {

    private static final Logger logger = LoggerFactory.getLogger(TaskWorkerServiceImpl.class);

    private volatile int MAX_FETCH_SIZE = 20;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskLogService taskLogService;

    @Autowired
    private WorkerService workerService;

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
        // get free worker first, we use the same transaction to fetch the workers
        List<Worker> freeWorkers = this.getFreeWorkers(maxTaskNumber);
        logger.info("found " + freeWorkers.size() + " free worker to assign");

        // random the worker order
        long seed = System.nanoTime();
        Collections.shuffle(freeWorkers, new Random(seed));
        int totalAssignNumber = 0;
        ArrayList<CompletableFuture<Boolean>> assignThreads = new ArrayList<CompletableFuture<Boolean>>();
        for (int workerCount = 0; workerCount < freeWorkers.size(); workerCount++) {
            Worker targetWorker = freeWorkers.get(workerCount);
            // we need have a need sql transaction to do this, os we use another method.
            assignThreads.add(this.assignTaskToWorker(targetWorker));

        }
        while (!assignThreads.isEmpty()) {
            CompletableFuture<Boolean> assignThread = assignThreads.get(0);
            if (assignThread.isDone()) {
                try {
                    Boolean result = assignThread.get();
                    if (result) {
                        totalAssignNumber = totalAssignNumber++;
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                assignThreads.remove(0);
            }
        }
        return totalAssignNumber;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private List<Worker> getFreeWorkers(int fetchSize) {
        // get all new tasks first
        WorkerExample example = new WorkerExample();
        example.or().andStatusEqualTo("FREE").andTaskIdIsNull().andManageableEqualTo(true);
        RowBounds rowBounds = new RowBounds();
        if (MAX_FETCH_SIZE != 0) {
            rowBounds = new RowBounds(0, fetchSize);
        }
        // we dont lock the worker here
        List<Worker> freeWorkers = workerService.selectByExampleWithRowbounds(example, rowBounds);
        return freeWorkers;
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private CompletableFuture<Boolean> assignTaskToWorker(Worker targetWorker) {
        Thread.currentThread().setName("WA=====>" + targetWorker.getId());
        /*
         * we use the following logic to assign a task to a worker 1: we update the task
         * first which proper where conditions. 2: if the task is updated, we commit the
         * database transaction and this acts like a lock. 3: try to find a worker, if
         * not found, update the task back to the original status. return false. 4: if
         * found. we update the worker first and commit 5: send the task to the worker
         * by http 6: if fail. then update the worker and task back to the originals.
         */
        logger.debug("starting find task to assign to worker id=>" + targetWorker.getId() + " uuid=>" + targetWorker.getUuid());
        // lock down the worker first
        int lockWorkerResult = this.setWorkerToResevedFromFreeForAssignment(targetWorker);
        if (lockWorkerResult != 1) {
            logger.debug("failed to reserve worker id=>" + targetWorker.getId() + " uuid=>" + targetWorker.getUuid() + " will skipped");
            return CompletableFuture.completedFuture(false);
        }
        // ==================logic to find match task
        TaskExample targetTaskExample = this.getTargetTaskExampleByWorker(targetWorker);
        List<Task> matchedTasks = taskService.selectByExample(targetTaskExample);
        if (matchedTasks.isEmpty()) {
            logger.debug("not task matched for worker " + targetWorker.getUuid() + " will skipped");
            // roll back the worker
            this.rollbackWorkerToFreeFromReservedForAssignment(targetWorker);
            return CompletableFuture.completedFuture(false);
        } else {
            // loop the task to lock the first available one
            Task targetTask = null;
            for (Task task : matchedTasks) {
                // try to lock the task
                int lockTaskResult = this.updateTaskToWIPWithWorkerIDByPrimaryKeyForAssignment(task, targetWorker);
                if (lockTaskResult == 1) {
                    targetTask = task;
                    logger.debug("task => id" + targetTask.getId() + " uuid =>" + targetTask.getUuid() + " is lock to assign");
                    break;
                }
            }

            // check final result
            if (targetTask == null) {
                logger.debug("could not lock a task for worker " + targetWorker.getUuid() + " will skipped");
                return CompletableFuture.completedFuture(false);
            }

            // start sending action
            // task and worker are locked and updated in place
            String baseUrl = workerUtilService.getBaseUrl(targetWorker);
            String tokenString = targetWorker.getToken() == null ? null : targetWorker.getToken().toString();
            logger.info("trying to assign task {} to worker with uuid {}, ip {}, token {}", targetTask.getUuid(), targetWorker.getUuid(), targetWorker.getIpAddress(), targetWorker.getToken());
            MeowlomoResponse response = vmcApiService.sendTaskToWorker(baseUrl, tokenString, targetTask);
            if (response != null) {
                // check reject and working and with task
                // get the meta data from the body
                if (!workerUtilService.checkWorkerAssignmentResponse(response)) { // false the reject check
                    logger.error(" worker reponse has logic error. will skip this worker.");
                    String message = " Task [" + targetTask.getUuid() + "] has been rejected by execution unit [" + targetWorker.getUuid() + "] without reasonable conditions, unit will be set to DOWN";
                    this.addLogToTask(targetTask, message);
                    // set the worker back to down
                    this.setWorkerToDownFromReservedForFailedAssignment(targetWorker);
                    // rollback the task
                    this.rollbackTaskByPrimaryKeyFromAssignment(targetTask);
                } else {// Response OK
                        // the task is assigned, we need to update the task and add log
                        // =========set log
                    String message = " Task [" + targetTask.getUuid() + "] has been assigned to execution unit [" + targetWorker.getUuid() + "]";
                    this.addLogToTask(targetTask, message);
                    // set worker to working
                    this.updateWorkerToWorkingByFinishedAssignment(targetWorker, targetTask);

                }
            } else {
                // failed to send the task to the worker
                try {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                } catch (Exception ex) {

                }
                // 1: we roll back the task,but set the worker to down
                this.rollbackTaskByPrimaryKeyFromAssignment(targetTask);
                logger.error("task" + targetWorker.getUuid() + " could not be sended to worker " + targetWorker.getUuid() + " will set the worker to down.");
                this.setWorkerToDownFromReservedForFailedAssignment(targetWorker);
                return CompletableFuture.completedFuture(false);
            }
            // every thing is done
            logger.info("task " + targetTask.getUuid() + " has been assigned to worker id=>" + targetWorker.getId() + " uuid=>" + targetWorker.getUuid());
            return CompletableFuture.completedFuture(true);
        }
    }

    private TaskExample getTargetTaskExampleByWorker(Worker worker) {
        TaskExample example = new TaskExample();
        Criteria criteria = example.or().andStatusEqualTo("NEW").andWorkerIdIsNull();
        // group
        if (worker.getGroup() != null && !worker.getGroup().equalsIgnoreCase("*")) {
            criteria.andGroupEqualTo(worker.getGroup());
        }

        // system
        if (worker.getOperatingSystem() != null && !worker.getOperatingSystem().equalsIgnoreCase("*")) {
            List<String> osList = new ArrayList<String>();
            osList.add("*");
            osList.add(worker.getOperatingSystem());
            criteria.andOperatingSystemIn(osList);
        }

        // ram
        if (worker.getRam() != null && !worker.getRam().equals(Integer.getInteger("0"))) {
            criteria.andRamRequiredLessThanOrEqualTo(worker.getRam());
        }

        // CPU
        if (worker.getCpuCore() != null && !worker.getCpuCore().equals(Integer.getInteger("0"))) {
            criteria.andCpuCoreRequiredLessThanOrEqualTo(worker.getCpuCore());
        }

        // bandwidth
        if (worker.getBandwidth() != null && !worker.getBandwidth().equals(Integer.getInteger("0"))) {
            criteria.andBandwidthRequiredLessThanOrEqualTo(worker.getBandwidth());
        }

        example.or(criteria);
        example.setOrderByClause(" priority asc , created_at asc ");
        return example;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int updateTaskToWIPByPrimaryKeyForAssignment(Task task) {
        task.setStatus("WIP");
        return taskAssignmentMapper.updateTaskToWIPByPrimaryKeyForAssignment(task);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int updateTaskToWIPWithWorkerIDByPrimaryKeyForAssignment(Task task, Worker worker) {
        Task updateTask = new Task();
        updateTask.setId(task.getId());
        updateTask.setWorkerId(worker.getId());
        updateTask.setStatus("WIP");
        updateTask.setExecutionStartAt(new Date());
        return taskService.updateByPrimaryKeySelective(updateTask);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int rollbackTaskByPrimaryKeyFromAssignment(Task task) {
        return taskAssignmentMapper.rollbackTaskByPrimaryKeyFromAssignment(task.getId());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int setWorkerToWorkingFromFreeWithTaskIdForAssignment(Worker worker) {
        worker.setStatus("WORKING");
        return workerControlMapper.setWorkerToWorkingFromFreeWithTaskIdForAssignment(worker);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int setWorkerToResevedFromFreeForAssignment(Worker worker) {
        return workerControlMapper.setWorkerToResevedFromFreeForAssignment(worker);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int updateWorkerToWorkingByFinishedAssignment(Worker worker, Task task) {
        Worker updateWorker = new Worker();
        updateWorker.setStatus("WORKING");
        updateWorker.setId(worker.getId());
        updateWorker.setTaskId(task.getId());
        return workerService.updateByPrimaryKeySelective(updateWorker);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private int updateWorkerTaskIDForAssignment(Worker worker, Task task) {
        Worker updateWorker = new Worker();
        updateWorker.setId(worker.getId());
        updateWorker.setTaskId(task.getId());
        return workerService.updateByPrimaryKeySelective(updateWorker);
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
    private int addLogToTask(Task task, String message) {
        TaskLog log = new TaskLog();
        log.setMessage(message);
        log.setTaskId(task.getId());
        return taskLogService.insert(log);
    }

}
