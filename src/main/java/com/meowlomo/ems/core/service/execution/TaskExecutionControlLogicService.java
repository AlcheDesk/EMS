package com.meowlomo.ems.core.service.execution;

import java.util.List;

import com.meowlomo.ems.core.model.Task;

public interface TaskExecutionControlLogicService {
    
    List<Task> applySingletonLogicControl(List<Task> tasks);   
    void triggerSingletonFinished(Task taskUpdateRecord, Task oldTask);
    void triggerSingletonFinished(Task taskUpdateRecord, List<Task> oldTasks);
    
}
