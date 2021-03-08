package com.meowlomo.ems.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meowlomo.ems.core.service.background.TaskHousekeeperService;

@Component
public class TaskHousekeeper {

    static final Logger logger = LoggerFactory.getLogger(TaskHousekeeper.class);

    @Autowired
    private TaskHousekeeperService taskHousekeeperService;

    // Replaced by database trigger
    @Scheduled(fixedDelay = 60000, initialDelay = 4000) // every 10 minutes, init delay 5 mins
    private void recycleTasks() {
        Thread.currentThread().setName("TaskRecycler");
        logger.debug("start recycling tasks");
        long result = taskHousekeeperService.recycleTasks();
        logger.debug("Recycled " + result + " tasks");
        logger.debug("finish recycling tasks");
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 8000) // every 20 minutes,init delay 10 mins
    private void cleanAbnormalTasks() {
        Thread.currentThread().setName("TaskCleaner");
        logger.debug("start clean abnormal tasks");
        taskHousekeeperService.cleanAbnormalTasks();
        logger.debug("finish cleaning abnormal tasks");
    }

    // Replaced by database trigger
    // @Scheduled(fixedRate = 10000, initialDelay = 6000) // every 20 minutes,init
    // delay 10 mins
    // private void finishTasks() {
    // logger.debug("finish tasks");
    // taskHousekeeperService.cleanAbnormalTasks();
    // logger.debug("finish cleaning abnormal tasks");
    // }

}
