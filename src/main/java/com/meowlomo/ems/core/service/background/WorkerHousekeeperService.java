package com.meowlomo.ems.core.service.background;

public interface WorkerHousekeeperService {

	void checkWorkers();

	void recycleWorkers();

	long cleanAbnormalWorkers();

	void wakeupOnHoldWorker();
}
