package com.meowlomo.ems.core.service.execution.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExecutionControl;
import com.meowlomo.ems.core.model.TaskExecutionControlExample;
import com.meowlomo.ems.core.service.base.TaskExecutionControlService;
import com.meowlomo.ems.core.service.execution.TaskExecutionControlLogicService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TaskExecutionControlLogicServiceImpl implements TaskExecutionControlLogicService {
    
    static final Logger logger = LoggerFactory.getLogger(TaskExecutionControlLogicServiceImpl.class);
    
    @Autowired
    private TaskExecutionControlService taskExecutionControlService;

    @Override
    public List<Task> applySingletonLogicControl(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(0);
            if (isTaskNeedToWaitFromSingletonLogic(task)) {
                tasks.remove(i);
                i--;
            }
        }
        return tasks;
    }
    
    private boolean isTaskNeedToWaitFromSingletonLogic(Task task) {
        //get useful information from the task    
        Boolean singleton = task.getSingleton();
        UUID singletonUuid = task.getSingletonUuid();
        if (singleton && singletonUuid != null) {
            //check is the ingleton uuid exsits and not finished
            TaskExecutionControlExample example = new TaskExecutionControlExample();
            example.createCriteria().andSingletonUuidEqualTo(singletonUuid).andFinishedEqualTo(false);
            TaskExecutionControl searchResult = taskExecutionControlService.selectOneByExample(example);
            if (searchResult != null) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Override
    public void triggerSingletonFinished(Task taskUpdateRecord, Task oldTask) {
        //get the id or the uuid from the task
        if (taskUpdateRecord.getId() != null) {
            //check is it is singleton task and finished.
            if (oldTask.getSingleton() && oldTask.getSingletonUuid() != null && taskUpdateRecord.getFinished()) {
                //get the singleton record and finished it
                UUID taskUuid = oldTask.getUuid();
                UUID singletonUuid = oldTask.getSingletonUuid();
                TaskExecutionControlExample example = new TaskExecutionControlExample();
                example.createCriteria().andPrimaryTaskUuidEqualTo(taskUuid).andSingletonUuidEqualTo(singletonUuid).andFinishedEqualTo(false);
                List<TaskExecutionControl> controlRecords = taskExecutionControlService.selectByExample(example);
                //finishe all
                for(TaskExecutionControl controlRecord : controlRecords){
                    TaskExecutionControl updateRecord = new TaskExecutionControl();
                    updateRecord.setId(controlRecord.getId());
                    updateRecord.setFinished(true);
                    taskExecutionControlService.updateByPrimaryKeySelective(updateRecord);
                }
            }
        }
    }

    @Override
    public void triggerSingletonFinished(Task taskUpdateRecord, List<Task> oldTasks) {
        for (Task oldTask : oldTasks) {
            this.triggerSingletonFinished(taskUpdateRecord, oldTask);
        }
        
    }
}
