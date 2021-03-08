package com.meowlomo.ems.core.service.background;

public interface WorkerAssignorService {
    int assignTasksToWorkers(Integer maxTaskNumber);
}
