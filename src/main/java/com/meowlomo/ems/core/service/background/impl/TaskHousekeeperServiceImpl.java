package com.meowlomo.ems.core.service.background.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.meowlomo.ems.core.mapper.TaskHousekeeperMapper;
import com.meowlomo.ems.core.mapper.TaskLogMapper;
import com.meowlomo.ems.core.mapper.TaskMapper;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExample;
import com.meowlomo.ems.core.model.TaskLog;
import com.meowlomo.ems.core.service.background.TaskHousekeeperService;

@Service
public class TaskHousekeeperServiceImpl implements TaskHousekeeperService {

    static final Logger logger = LoggerFactory.getLogger(TaskHousekeeperServiceImpl.class);

    @Autowired
    private TaskHousekeeperMapper taskHousekeeperMapper;

    @Autowired
    private TaskLogMapper taskLogMapper;

    @Autowired
    private TaskMapper taskMapper;

    private List<Task> selectAbnormalTasks() {
        return taskHousekeeperMapper.selectAbnormalTasks();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private List<Task> selectRecyclableTasks() {
        return taskHousekeeperMapper.selectRecyclableTasks();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public long recycleTasks() {
        // get recyclable tasks first
        List<Task> tasks = this.selectRecyclableTasks();
        logger.info("found " + tasks.size() + " recyclable tasks.");
        long count = 0;
        for (int taskCount = 0; taskCount < tasks.size(); taskCount++) {
            if (this.recycleTask(tasks.get(taskCount))) {
                count = count + 1;
            }
        }
        return count;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private boolean recycleTask(Task task) {
        // add log
        TaskLog log = new TaskLog();
        log.setMessage("recycle the task and reset the status to NEW due to tryNumber " + task.getRetryNumber() + " and maxRetry " + task.getMaxRetry());
        log.setTaskId(task.getId());
        long logResult = taskLogMapper.insert(log);
        if (logResult == 1) {
            // update the task
            Task updateTask = new Task();
            updateTask.setStatus("NEW");
            updateTask.setRetryNumber(task.getRetryNumber() + 1);
            TaskExample example = new TaskExample();
            example.or().andIdEqualTo(task.getId());
            int updateResult = taskMapper.updateByExampleSelective(updateTask, example);
            if (updateResult == 1) {
                return true;
            } else {
                logger.error("Error on reset task to NEW by recycling for task " + task.getUuid());
                return false;
            }
        } else {
            logger.error("Error on adding log for recycling task " + task.getUuid());
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public long cleanAbnormalTasks() {
        List<Task> tasks = this.selectAbnormalTasks();
        long count = 0;
        for (int taskCount = 0; taskCount < tasks.size(); taskCount++) {
            if (this.processAbnormalTask(tasks.get(taskCount))) {
                count = count + 1;
            }
        }
        return count;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private boolean processAbnormalTask(Task task) {
        TaskLog log = new TaskLog();
        if (task.getStatus().equalsIgnoreCase("FREE")) {
            log.setMessage("the task [" + task.getId() + "] is FREE but record shown it is assigned to " + task.getWorkerId() + ", Set to ERROR.");
        } else if (task.getStatus().equalsIgnoreCase("WIP")) {
            log.setMessage("the task [" + task.getId() + "] is WIP but record shown it is not assigned any worker, Set to ERROR.");
        } else {
            log.setMessage("the task [" + task.getId() + "], the data has integrity problem, Set to ERROR.");
        }
        log.setTaskId(task.getId());
        long logResult = taskLogMapper.insert(log);
        if (logResult == 1) {
            // update the task
            Task updateTask = new Task();
            updateTask.setStatus("ERROR");
            TaskExample example = new TaskExample();
            example.or().andIdEqualTo(task.getId());
            int taskUpdateResult = taskMapper.updateByExampleSelective(updateTask, example);
            if (taskUpdateResult != 1) {
                logger.error("Error on udpate abnormal task " + task.getUuid() + " to status error");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
            return true;
        } else {
            logger.error("Error on adding log for cleaning abnormal task " + task.getUuid());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public long finishTasks() {
        // get the task which are not finished and
        List<Task> tasks = taskHousekeeperMapper.selectFinishableTasks();
        List<Long> ids = new ArrayList<Long>();
        for (Task task : tasks) {
            ids.add(task.getId());
        }
        Task updateTask = new Task();
        updateTask.setFinished(true);
        TaskExample updateExample = new TaskExample();
        updateExample.or().andIdIn(ids);
        return taskMapper.updateByExampleSelective(updateTask, updateExample);
    }
}
