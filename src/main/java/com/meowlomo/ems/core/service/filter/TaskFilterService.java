package com.meowlomo.ems.core.service.filter;

import java.util.List;

import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.Task;

public interface TaskFilterService {
    
    Task processTaskFromJob(Task task, Job job);

    Task generateExecutionStartAndExecutionEndAndFinished(Task record, Task oldTask);

    List<Task> generateExecutionStartAndExecutionEndAndFinished(Task record, List<Task> oldTasks);

}
