package com.meowlomo.ems.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meowlomo.ems.core.service.background.WorkerAssignorService;

@Component
public class TaskDispatcher {
    private static final Logger logger = LoggerFactory.getLogger(TaskDispatcher.class);

    @Autowired
    private WorkerAssignorService workerAssignorService;

    private int initAssign = 20;
    private int previousAssign;

    @Scheduled(fixedRate = 3000, initialDelay = 10000) // every 10 seconds, init delay 5 mins
    private void dispatcherTasks() {
        Thread.currentThread().setName("TaskDispatcher");
        logger.debug("start trying to assign grep [" + initAssign + "] workers for dispaching ");
        previousAssign = workerAssignorService.assignTasksToWorkers(initAssign);
        if (initAssign < 5) {
            initAssign = 5;
        }
        else if (previousAssign >= initAssign * 0.8) {
            initAssign = (int) (initAssign * 1.2);
        }
        else if (previousAssign < initAssign * 0.8 && previousAssign >= initAssign * 0.5) {
//            initAssign = initAssign;
        }
        else if (previousAssign < initAssign * 0.5 && previousAssign >= initAssign * 0.3) {
            initAssign = (int) (initAssign * 0.8);
        }
        else {
            initAssign = (int) (initAssign * 0.5);
        }
        logger.debug("finished assigning workers, with [" + previousAssign + "] successful assignment.");
    }

}
