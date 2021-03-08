package com.meowlomo.ems.core.service.util;

import java.util.List;
import java.util.UUID;

import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.resource.model.MeowlomoResponse;

public interface WorkerUtilService {

    boolean isNonDownWorkerValid(Worker workerFromDb, String currentStatus, Boolean fileSystemOK,List<Task> currentTasks);

    boolean isDownWorkerRecyclable(Worker workerFromDb, String currentStatus, Boolean fileSystemOK, List<Task> currentTasks);

    String getBaseUrl(Worker worker);

    Worker generateWorkerFieldsForUpdate(Worker newWorker, Worker oriWorker);

    boolean checkWorkerAssignmentResponse(MeowlomoResponse response);

    boolean rebootWorker(Worker worker);

    boolean workerTokenIsValid(String tokenString, UUID workerToken);

}
