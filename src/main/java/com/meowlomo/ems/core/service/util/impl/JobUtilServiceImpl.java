package com.meowlomo.ems.core.service.util.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.config.RuntimeVariables;
import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.JobLog;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExample;
import com.meowlomo.ems.core.service.base.JobLogService;
import com.meowlomo.ems.core.service.base.JobService;
import com.meowlomo.ems.core.service.base.TaskService;
import com.meowlomo.ems.core.service.util.JobUtilService;
import com.meowlomo.ems.core.service.util.TaskUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class JobUtilServiceImpl implements JobUtilService {

    static final Logger logger = LoggerFactory.getLogger(JobUtilServiceImpl.class);

    @Autowired
    private JobService jobService;

    @Autowired
    private JobLogService jobLogService;

    @Autowired
    private TaskUtilService taskUtilService;

    @Autowired
    private TaskService taskService;

    @Override
    public boolean killJob(Job job) {
        // get the information from the job to restart the worker
        if (job == null) {
            throw new NullPointerException();
        }

        if (job.getId() == null || job.getUuid() == null) {
            logger.error("Job is missing information to be terminatied. ID[" + job.getId() + "], UUID[" + job.getUuid() + "]");
            return false;
        }
        Long jobId = job.getId();
        // check if we need to reboot the worker
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andStatusNotIn(RuntimeVariables.TASK_FINAL_STATUSES).andJobIdEqualTo(jobId);
        List<Task> targetTasks = taskService.selectByExample(taskExample);
        boolean killTasksResult = true;
        if (targetTasks.isEmpty()) {
            // could not find the worker. we just do nothing.
            logger.debug("Not task is linked for TERMINATION job {}", job.getUuid());
        } else {
            // we just need to loop through the task to terminate it
            for (Task task : targetTasks) {
                /*
                 * we need to check the task status for the following 1: if the the task is
                 * working, we need to kill it 2: if the task is in the final status, we don't
                 * need to do any thing 3: if the task is has not been touched, we set it to
                 * final status
                 * 
                 */
                String taskStatus = task.getStatus();
                if (taskStatus.equalsIgnoreCase("WIP")) {
                    logger.debug("kill task status:{}, uuid:{} for TERMINATION job {}", task.getStatus(), task.getUuid(), job.getUuid());
                    if (!taskUtilService.killTask(task, job)) {
                        killTasksResult = killTasksResult ? false : killTasksResult;
                    }
                } else if (RuntimeVariables.TASK_FINAL_STATUSES.contains(taskStatus)) {
                    // we don't need to do any thing.
                } else {
                    logger.debug("termination task status:{}, uuid:{} for TERMINATION job {}", task.getStatus(), task.getUuid(), job.getUuid());
                    // default action
                    if (!taskUtilService.terminateTask(task, job)) {
                        killTasksResult = killTasksResult ? false : killTasksResult;
                    }
                }
            }
        }

        if (!killTasksResult) {
            logger.error("Failed to reboot worker by JOB --> Task termination. Job {}" + job.getUuid());
            return false;
        } else {
            logger.debug("termination tasks done for TERMINATION job {}", job.getUuid());
        }

        // set the status for the job, set the job to error
        Job updateJob = new Job();
        updateJob.setStatus("TERMINATED");
        updateJob.setId(jobId);
        int jobUpdateresult = jobService.updateByPrimaryKeySelective(updateJob);
        if (jobUpdateresult == 1) {
            JobLog jobLog = new JobLog();
            jobLog.setJobId(jobId);
            jobLog.setMessage("Status has been set to TERMINATED due to TERMINATION signal.");
            jobLogService.insertSelective(jobLog);
            logger.debug("Job has been updated for TERMINATION {}", job.getUuid());
        } else {
            logger.debug("Error on updating TERMINATION job {}", job.getUuid());
            return false;
        }
        return true;
    }

}
