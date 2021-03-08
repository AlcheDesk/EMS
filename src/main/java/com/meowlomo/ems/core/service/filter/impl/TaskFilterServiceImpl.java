package com.meowlomo.ems.core.service.filter.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskLog;
import com.meowlomo.ems.core.service.base.TaskLogService;
import com.meowlomo.ems.core.service.filter.TaskFilterService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TaskFilterServiceImpl implements TaskFilterService{
    
    @Autowired
    private TaskLogService taskLogService;
    
    @Value("${spring.jackson.date-format}")
    private String dateFormat;

    @Override
    public Task processTaskFromJob(Task task, Job job) {
        task.setJobId(job.getId());
        task.setGroup(job.getGroup());
        task.setPriority(job.getPriority());
        return task;
    }

    @Override
    public Task generateExecutionStartAndExecutionEndAndFinished(Task record, Task oldTask) {
        //check first
        Long taskId = record.getId();
        String newTaskStatus = record.getStatus();
        if (taskId != null && newTaskStatus != null) {
            //get the old task back
//            Task oldTask = taskMapper.selectByPrimaryKey(taskId);
            return this.processTaskExecutionEndAndExectutionStartAndFinished(record, oldTask);

        }
        //return original task
        return record;
    }
    
    private Task processTaskExecutionEndAndExectutionStartAndFinished(Task newRecord, Task oldRecord) {        
        if (oldRecord != null && newRecord != null) {
            String newTaskStatus = newRecord.getStatus();
            Long taskId = newRecord.getId();
            if (newTaskStatus == null || taskId == null) {
                return newRecord;
            }
            String oldTaskStatus = oldRecord.getStatus();
            Integer oldRetryNumber = oldRecord.getRetryNumber();
            Integer oldMaxRetry = oldRecord.getMaxRetry();
            Date oldExecutionStartAt = oldRecord.getExecutionStartAt();
            //compare the status 
            if (oldTaskStatus.equalsIgnoreCase("NEW") && newTaskStatus.equalsIgnoreCase("WIP") && oldRetryNumber == 0 && oldExecutionStartAt == null) {
                //insert the log
                Date nowDate = new Date();
                TaskLog taskLog = new TaskLog();
                taskLog.setTaskId(taskId);
                taskLog.setMessage("Start execution set to WIP from NEW at "+ new SimpleDateFormat(this.dateFormat).format(nowDate));
                taskLogService.insertSelective(taskLog);
                //process the task
                newRecord.setExecutionStartAt(nowDate);
            }
            else if (oldTaskStatus.equalsIgnoreCase("NEW") && newTaskStatus.equalsIgnoreCase("ERROR") && oldRetryNumber == 0 && oldExecutionStartAt == null) {
                //insert the log
                Date nowDate = new Date();
                TaskLog taskLog = new TaskLog();
                taskLog.setTaskId(taskId);
                taskLog.setMessage("Start execution and set to ERROR from NEW at " + new SimpleDateFormat(this.dateFormat).format(nowDate) + " please contact system admin.");
                taskLogService.insertSelective(taskLog);
                //process the task
                newRecord.setExecutionStartAt(nowDate);
            }
            else if (oldTaskStatus.equalsIgnoreCase("NEW") && newTaskStatus.equalsIgnoreCase("ERROR") && oldRetryNumber == oldMaxRetry) {
                //insert the log
                Date nowDate = new Date();
                TaskLog taskLog = new TaskLog();
                taskLog.setTaskId(taskId);
                taskLog.setMessage("Set to ERROR from NEW and finished execution at " + new SimpleDateFormat(this.dateFormat).format(nowDate));
                taskLogService.insertSelective(taskLog);
                //process the task
                newRecord.setExecutionStartAt(nowDate);
                newRecord.setExecutionEndAt(nowDate);
                newRecord.setFinished(true);
            }
            else if (oldTaskStatus.equalsIgnoreCase("WIP") && newTaskStatus.equalsIgnoreCase("ERROR") && oldRetryNumber == oldMaxRetry) {
                //insert the log
                Date nowDate = new Date();
                TaskLog taskLog = new TaskLog();
                taskLog.setTaskId(taskId);
                taskLog.setMessage("Set to ERROR from WIP and finished execution at " + new SimpleDateFormat(this.dateFormat).format(nowDate));
                taskLogService.insertSelective(taskLog);
                //process the task
                newRecord.setExecutionEndAt(nowDate);
                newRecord.setFinished(true);
            }
            else if (oldTaskStatus.equalsIgnoreCase("WIP") && newTaskStatus.equalsIgnoreCase("DONE")) {
                //insert the log
                Date nowDate = new Date();
                TaskLog taskLog = new TaskLog();
                taskLog.setTaskId(taskId);
                taskLog.setMessage("Set to DONE from WIP and finished execution at " + new SimpleDateFormat(this.dateFormat).format(nowDate));
                taskLogService.insertSelective(taskLog);
                //process the task
                newRecord.setExecutionEndAt(nowDate);
                newRecord.setFinished(true);
            }                
        }
        //Return processed task
        return newRecord;
    }

    @Override
    public List<Task> generateExecutionStartAndExecutionEndAndFinished(Task record, List<Task> oldTasks) {
        //select the records first 
//        List<Task> records = taskMapper.selectByExample(example);
        List<Task> newReturnList = new ArrayList<Task>();
        for (int i = 0; i < oldTasks.size(); i++) {
            Task oldTask = oldTasks.get(i);
            record.setId(oldTasks.get(0).getId());
            newReturnList.add(this.generateExecutionStartAndExecutionEndAndFinished(record, oldTask));
        }
        return newReturnList;
    }
}
