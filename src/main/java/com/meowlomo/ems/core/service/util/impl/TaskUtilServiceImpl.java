package com.meowlomo.ems.core.service.util.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.JobExample;
import com.meowlomo.ems.core.model.JobLog;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExecutionControl;
import com.meowlomo.ems.core.model.TaskLog;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;
import com.meowlomo.ems.core.service.base.JobLogService;
import com.meowlomo.ems.core.service.base.JobService;
import com.meowlomo.ems.core.service.base.TaskExecutionControlService;
import com.meowlomo.ems.core.service.base.TaskLogService;
import com.meowlomo.ems.core.service.base.TaskService;
import com.meowlomo.ems.core.service.base.WorkerService;
import com.meowlomo.ems.core.service.util.TaskUtilService;
import com.meowlomo.ems.core.service.util.WorkerUtilService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class TaskUtilServiceImpl implements TaskUtilService {

    private static final Logger logger = LoggerFactory.getLogger(TaskUtilServiceImpl.class);

    @Autowired
    private JobService jobService;

    @Autowired
    private JobLogService jobLogService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private WorkerUtilService workerUtilService;

    @Autowired
    private TaskLogService taskLogService;
    
    @Autowired
    private TaskExecutionControlService taskExecutionControlService;

    @Override
    public void updateJobStatusByTask(Task task, Task oldTask) {
        Long jobId = task.getJobId();
        Long taskId = task.getId();
        String taskStatus = task.getStatus();
        if (jobId != null && taskId != null && taskStatus != null) {
            // get the old task first
            if (oldTask.getStatus().equalsIgnoreCase("NEW") && taskStatus.equalsIgnoreCase("WIP")) {
                // update the job
                Job updateJob = new Job();
                updateJob.setId(jobId);
                updateJob.setStatus("WIP");
                JobExample jobExample = new JobExample();
                jobExample.or().andIdEqualTo(jobId).andStatusEqualTo("NEW");
                int updateResult = jobService.updateByExampleSelective(updateJob, jobExample);
                if (updateResult == 1) {
                    // insert job log
                    JobLog jobLog = new JobLog();
                    jobLog.setJobId(jobId);
                    jobLog.setMessage("Update status from NEW to WIP due to Task " + task.getUuid());
                    int insertResult = jobLogService.insertSelective(jobLog);
                    if (insertResult != 1) {
                        logger.error("can not insert job log by task triggering NEW to WIP staus update");
                        try {
                            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        } catch (Exception e) {
                            logger.error("error on inserting job log by task triggering NEW to WIP staus update", e);
                        }
                    }
                } else {
                    logger.error("can not update job by task triggering NEW to WIP staus update");
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    } catch (Exception e) {
                        logger.error("error on updating by task triggering NEW to WIP staus update", e);
                    }
                }
            } else if (oldTask.getStatus().equalsIgnoreCase("NEW") && taskStatus.equalsIgnoreCase("ERROR")) {
                // update the job
                Job updateJob = new Job();
                updateJob.setId(jobId);
                updateJob.setStatus("ERROR");
                JobExample jobExample = new JobExample();
                jobExample.or().andIdEqualTo(jobId).andStatusEqualTo("NEW");
                int updateResult = jobService.updateByExampleSelective(updateJob, jobExample);
                if (updateResult == 1) {
                    // insert job log
                    JobLog jobLog = new JobLog();
                    jobLog.setJobId(jobId);
                    jobLog.setMessage("Update status from NEW to ERROR due to Task " + task.getUuid());
                    int insertResult = jobLogService.insertSelective(jobLog);
                    if (insertResult != 1) {
                        logger.error("can not insert job log by task triggering NEW to ERROR staus update");
                        try {
                            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        } catch (Exception e) {
                            logger.error("error on inserting job log by task triggering NEW to ERROR staus update", e);
                        }
                    }
                } else {
                    logger.error("can not update job by task triggering NEW to ERROR staus update");
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    } catch (Exception e) {
                        logger.error("error on updating by task triggering NEW to ERROR staus update", e);
                    }
                }
            } else if (oldTask.getStatus().equalsIgnoreCase("WIP") && taskStatus.equalsIgnoreCase("ERROR")) {
                // update the job
                Job updateJob = new Job();
                updateJob.setId(jobId);
                updateJob.setStatus("ERROR");
                JobExample jobExample = new JobExample();
                jobExample.or().andIdEqualTo(jobId).andStatusEqualTo("WIP");
                int updateResult = jobService.updateByExampleSelective(updateJob, jobExample);
                if (updateResult == 1) {
                    // insert job log
                    JobLog jobLog = new JobLog();
                    jobLog.setJobId(jobId);
                    jobLog.setMessage("Update status from WIP to ERROR due to Task " + task.getUuid());
                    int insertResult = jobLogService.insertSelective(jobLog);
                    if (insertResult != 1) {
                        logger.error("can not insert job log by task triggering WIP to ERROR staus update");
                        try {
                            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        } catch (Exception e) {
                            logger.error("error on inserting job log by task triggering WIP to ERROR staus update", e);
                        }
                    }
                } else {
                    logger.error("can not update job by task triggering WIP to ERROR staus update");
                    try {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    } catch (Exception e) {
                        logger.error("error on updating by task triggering WIP to ERROR staus update", e);
                    }
                }
            }
        }
    }

    @Override
    public void updateJobStatusByTaskWithExample(Task record, List<Task> oldTasks) {
        // get all task first by the example
//        List<Task> records = taskService.selectByExample(example);
        // check the task input first
        String taskStatus = record.getStatus();
        if (taskStatus != null) {
            for (Task task : oldTasks) {
                // Assemble the task to update job status
                Task triggerTask = new Task();
                triggerTask.setStatus(taskStatus);
                triggerTask.setJobId(task.getJobId());
                triggerTask.setId(task.getId());
                this.updateJobStatusByTask(triggerTask, task);
            }
        }
    }

    @Override
    public boolean updateLinkedWorkerStatus(Task newRecord, Task oldTask) {
        // get the worker ids from the the task
        // ERROR or DONE update
        logger.debug("check task is linked to worker or not.");
        String taskStatus = newRecord.getStatus();
        if (taskStatus != null && (taskStatus.equalsIgnoreCase("ERROR") || taskStatus.equalsIgnoreCase("DONE"))) {
//            Task task = taskService.selectByPrimaryKey(newRecord.getId());
            Long workerId = oldTask.getWorkerId();
            logger.debug("the worker id {} from the task.", workerId);
            // only one worker for the task
            if (workerId != null) {
                logger.debug("Update worker [" + workerId + "] to free by task [" + oldTask.getUuid() + "]");
                Worker currentWorker = workerService.selectByPrimaryKey(workerId);
                logger.debug("current linked worker ===>" + currentWorker.toString());
                WorkerExample updateWorkerExample = new WorkerExample();
                updateWorkerExample.or().andTaskIdEqualTo(oldTask.getId()).andStatusEqualTo("WORKING").andIdEqualTo(workerId);
                List<Worker> workers = workerService.selectByExample(updateWorkerExample);
                for (Worker worker : workers) {
                    worker.setStatus("FREE");
                    worker.setTaskId(null);
                    workerService.updateByPrimaryKey(worker);
                }
                Worker afterWorker = workerService.selectByPrimaryKey(workerId);
                logger.debug("after linked worker ===>" + afterWorker.toString());
                return true;
            } else {
                logger.debug("task status {} is not valid for termination", taskStatus);
                return false;
            }
        } else {
            logger.debug("the worker id is null from the task {}", newRecord.getUuid());
            return false;
        }
    }

    @Override
    public void updateLinkedWorkerStatus(Task record, List<Task> oldTasks) {
        String taskStatus = record.getStatus();
        if (taskStatus != null && (taskStatus.equalsIgnoreCase("ERROR") || taskStatus.equalsIgnoreCase("DONE"))) {
//            List<Task> tasks = taskService.selectByExample(example);
            for (Task task : oldTasks) {
                Long workerId = task.getWorkerId();
                // only one worker for the task
                if (workerId != null) {
                    logger.debug("Update worker [" + workerId + "] to free by task [" + task.getUuid() + "]");
                    Worker currentWorker = workerService.selectByPrimaryKey(workerId);
                    logger.debug("current linked worker ===>" + currentWorker.toString());
                    WorkerExample updateWorkerExample = new WorkerExample();
                    updateWorkerExample.or().andTaskIdEqualTo(task.getId()).andStatusEqualTo("WORKING").andIdEqualTo(workerId);
                    List<Worker> workers = workerService.selectByExample(updateWorkerExample);
                    for (Worker worker : workers) {
                        worker.setStatus("FREE");
                        worker.setTaskId(null);
                        workerService.updateByPrimaryKey(worker);
                    }
                    Worker afterWorker = workerService.selectByPrimaryKey(workerId);
                    logger.debug("after linked worker ===>" + afterWorker.toString());
                }
            }
        }
    }

    @Override
    public boolean killTask(Task task, Job job) {
        // get the information from the task to restart the worker
        if (task == null) {
            logger.error("The task object is NULL for TERMINATION for job {}", job.getUuid());
            throw new NullPointerException();
        }
        if (task.getId() == null || task.getWorkerId() == null) {
            logger.error("Task is missing information to be terminatied. task {}, job {}", task.getUuid(), job.getUuid());
            return false;
        }
        Long workerId = task.getWorkerId();
        // check if we need to reboot the worker
        WorkerExample workerExample = new WorkerExample();
        workerExample.createCriteria().andIdEqualTo(workerId);
        List<Worker> workers = workerService.selectByExample(workerExample);
        boolean workerRebootResult = true;
        if (workers.isEmpty()) {
            // could not find the worker. we just do nothing.
            workerRebootResult = true;
            logger.debug("Cloud not find worker to reboot for TERMINATION job {}", job.getUuid());
        } else {
            // we just sent the reboot signal to the worker
            Worker targetWorker = workers.get(0);
            workerRebootResult = workerUtilService.rebootWorker(targetWorker);
            UUID targetUUID = targetWorker != null ? targetWorker.getUuid() : null;
            UUID taskUUID = task != null ? task.getUuid() : null;
            UUID jobUUID = job != null ? job.getUuid() : null;
            if (workerRebootResult) {
                
                logger.debug("Sent Reboot signal to worker {}, task {}, job {}", targetUUID, taskUUID, jobUUID);
            } else {
                logger.error("Error sending reboot signal to worker {}, task {}, job {}", targetUUID, taskUUID, jobUUID);
            }

        }

        if (!workerRebootResult) {
            logger.error("Failed to reboot worker by Task termination. task {}, job {}", task.getUuid(), job.getUuid());
            return false;
        }
        // set the status for the task, set the task to error
        return this.terminateTask(task, job);
    }

    @Override
    public boolean terminateTask(Task task, Job job) {
        if (task.getId() == null || task.getId() < 0) {
            logger.error("The task id is null or the id is not valid for TERMINATION job {}", job.getUuid());
            return false;
        }
        Long taskId = task.getId();
        Task updateTask = new Task();
        updateTask.setStatus("TERMINATED");
        updateTask.setId(taskId);
        int taskUpdateresult = taskService.updateByPrimaryKeySelective(updateTask);
        if (taskUpdateresult == 1) {
            TaskLog taskLog = new TaskLog();
            taskLog.setTaskId(taskId);
            if (job == null || job.getUuid() == null) {
                taskLog.setMessage("Status has been set to TERMINATED due to TERMINATION signal..");
            } else {
                taskLog.setMessage("Status has been set to TERMINATED due to TERMINATION signal. by job [" + job.getUuid() + "].");
            }
            taskLogService.insertSelective(taskLog);
            return true;
        } else {
            logger.error("Error on update task status and log for TERMINATION job {}", job.getUuid());
            return false;
        }

    }
    
    @Override
    public void recordTaskExecutionControl(Task task) {
        //check if the task is set to singleton
        if (task.getSingleton() != null && task.getSingleton()) {
            /*
             * we need to get the following information to set the singleton execution logic
             * task uuid
             * singleton uuid
             */
            UUID taskUuid = task.getUuid();
            UUID singletonUuid = task.getSingletonUuid();
            if (taskUuid == null || singletonUuid == null) {
                logger.error("Found a task uuid:{} is singleton but the singleton uuid is null", taskUuid.toString());
            }
            // insert the logic to the exectuon control table
            TaskExecutionControl taskExecutionControl = new TaskExecutionControl();
            taskExecutionControl.setPrimaryTaskUuid(taskUuid);
            taskExecutionControl.setType("Singleton");
            taskExecutionControl.setSingletonUuid(singletonUuid);
            int insertReturn = taskExecutionControlService.insertSelective(taskExecutionControl);
            if (insertReturn != 1) {
                logger.error("Error on inserting singleton task execution logic task uuid:{}  singleton uuid: {}", taskUuid.toString(), singletonUuid.toString());
            }
        }
    }

}
