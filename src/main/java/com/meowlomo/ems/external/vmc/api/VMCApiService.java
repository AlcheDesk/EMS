package com.meowlomo.ems.external.vmc.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.resource.model.MeowlomoResponse;

public interface VMCApiService {

  MeowlomoResponse sendTaskToWorker(String workerBaseUrl, String token, Task task);

  MeowlomoResponse getTaskerFromWorker(String workerBaseUrl);

  MeowlomoResponse checkWorkerHealth(String workerBaseUrl);

  MeowlomoResponse endManagerRequest(String workerBaseUrl, String token, ObjectNode JsonContent);

  MeowlomoResponse rebootWorker(String workerBaseUrl);

  MeowlomoResponse restartWorker(String workerBaseUrl);

  MeowlomoResponse getWorkerStatus(String workerBaseUrl);

  MeowlomoResponse stopWorker(String workerBaseUrl, String token, ObjectNode JsonContent);
	
}
