package com.meowlomo.ems.listener;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;
import com.meowlomo.ems.core.resource.model.MeowlomoResponse;
import com.meowlomo.ems.core.service.base.WorkerService;
import com.meowlomo.ems.core.service.util.impl.JWTUtil;
import com.meowlomo.ems.external.vmc.api.VMCApiService;

@Component
public class WorkerInitializer {
    static final Logger logger = LoggerFactory.getLogger(WorkerInitializer.class);

    @Autowired
    WorkerService workerService;

    @Autowired
    private VMCApiService vmcApiService;

    @Autowired
    JWTUtil jwtUtil;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeWorkers() {
        // get all the workers in the DB
        WorkerExample example = new WorkerExample();
        example.or().andIdIsNotNull().andStatusNotEqualTo("DOWN");
        List<Worker> workers = workerService.selectByExample(example);
        // loop the workers
        for (int workerCount = 0; workerCount < workers.size(); workerCount++) {

            Worker targetWorker = workers.get(workerCount);
            UUID workerUuid = targetWorker.getUuid();
            try {
                // get the health status from the worker
                String baseUrl = targetWorker.getProtocol() + "://" + targetWorker.getIpAddress() + ":"+ targetWorker.getPort();
                logger.debug("Checking worker with UUID " + workerUuid.toString());
                MeowlomoResponse response = vmcApiService.checkWorkerHealth(baseUrl);
                // check the response
                if (response != null) {
                    /*
                     * check the following status 1: worker status 2: file system
                     */
                    ObjectNode metadata = response.getMetadata();
                    String statusFromWorker = metadata.get("status").asText();
                    Boolean fileSystemAvaliable = metadata.get("fileSystemOK").asBoolean();
                    if ((statusFromWorker != null && fileSystemAvaliable != null)&& (statusFromWorker.equalsIgnoreCase(targetWorker.getStatus()) && fileSystemAvaliable)) {
                        // //this means the worker is in good condition, we need to check the task list
                        // ObjectMapper mapper = new ObjectMapper();
                        // List<Task> workerTasks = mapper.convertValue(metadata.get("tasks"), new
                        // TypeReference<List<Task>>(){});
                    }
                    else {
                        logger.debug("Error update [" + targetWorker.getStatus() + "] worker [" + workerUuid.toString()
                                + "]  status to DOWN, health check from initializer got result " + response.toString());
                        // update the worker to down
                        Worker udpateWorker = new Worker();
                        udpateWorker.setStatus("DOWN");
                        udpateWorker.setId(targetWorker.getId());
                        workerService.updateByPrimaryKeySelective(udpateWorker);
                    }
                }
                else {
                    // update the worker to down
                    Worker udpateWorker = new Worker();
                    udpateWorker.setStatus("DOWN");
                    udpateWorker.setId(targetWorker.getId());
                    workerService.updateByPrimaryKeySelective(udpateWorker);
                }
            }
            catch (IllegalArgumentException ex) {
                logger.error("Missing info for comunicating the worker [" + workerUuid.toString()
                        + "], set worker status to DOWN.");
                Worker udpateWorker = new Worker();
                udpateWorker.setStatus("DOWN");
                udpateWorker.setId(targetWorker.getId());
                workerService.updateByPrimaryKeySelective(udpateWorker);
            }
        }

    }
}
