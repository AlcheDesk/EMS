package com.meowlomo.ems.core.service.util;

import java.util.List;

import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.Task;

public interface TaskUtilService {
    
    boolean killTask(Task task, Job job);
    
    boolean terminateTask(Task task, Job job);

    void recordTaskExecutionControl(Task task);

    void updateJobStatusByTask(Task task, Task oldTask);

    boolean updateLinkedWorkerStatus(Task record, Task oldTask);

    void updateLinkedWorkerStatus(Task record, List<Task> oldTasks);

    void updateJobStatusByTaskWithExample(Task record, List<Task> oldTasks);
}
