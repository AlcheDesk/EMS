package com.meowlomo.ems.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meowlomo.ems.core.service.background.WorkerHousekeeperService;

@Component
public class WorkerHousekeeper {

	static final Logger logger = LoggerFactory.getLogger(WorkerHousekeeper.class);

	@Autowired
	private WorkerHousekeeperService workerHousekeeperService;

	@Scheduled(fixedDelay = 60000, initialDelay = 29000) // every 1 minutes,init delay 29 second
	private void recycleWorkers() {
		Thread.currentThread().setName("WorkerRecycler");
		logger.debug("start recycling workers");
		workerHousekeeperService.recycleWorkers();
		logger.debug("finish recycling workers");
	}

	@Scheduled(fixedDelay = 60000, initialDelay = 17000) // every 1 second,init delay 17 second
	private void checkWorkers() {
		Thread.currentThread().setName("WorkerChecker");
		logger.debug("start checking workers");
		workerHousekeeperService.checkWorkers();
		logger.debug("finish checking workers");
	}

	@Scheduled(fixedDelay = 60000, initialDelay = 8000) // every 1 second,init delay 10 mins
	private void cleanAbnormalWorkers() {
		Thread.currentThread().setName("WorkerCleaner");
		logger.debug("start clean abnormal workers");
		workerHousekeeperService.cleanAbnormalWorkers();
		logger.debug("finish cleaning abnormal workers");
	}
	
//	@Scheduled(fixedDelay = 10000, initialDelay = 8000) // every 1 second,init delay 10 mins
//	private void waitupWorkers() {
//		Thread.currentThread().setName("WorkerCleaner");
//		logger.debug("start wating up workers");
//		workerHousekeeperService.wakeupOnHoldWorker();
//		logger.debug("finish wating up workers");
//	}
}
