package com.meowlomo.ems.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meowlomo.ems.core.service.background.JobHouseKeeperService;

@Component
public class JobHousekeeper {
    static final Logger logger = LoggerFactory.getLogger(TaskHousekeeper.class);

    @Autowired
    private JobHouseKeeperService jobHouseKeeperService;

    @Scheduled(fixedRate = 60000, initialDelay = 6000) // every 20 minutes,init delay 10 mins
    private void finishJobs() {
        Thread.currentThread().setName("JobFinisher");
        logger.debug("finishing jobs");
        long result = jobHouseKeeperService.finishJobs();
        logger.debug("finish job finishing, finished " + result + " jobs");
    }
}
