package com.meowlomo.ems.core.service.util.impl;

import java.net.URL;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerLog;
import com.meowlomo.ems.core.resource.model.MeowlomoResponse;
import com.meowlomo.ems.core.service.base.WorkerLogService;
import com.meowlomo.ems.core.service.util.WorkerUtilService;
import com.meowlomo.ems.external.vmc.api.VMCApiService;

import okhttp3.HttpUrl;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkerUtilServiceImpl implements WorkerUtilService {

    static final Logger logger = LoggerFactory.getLogger(WorkerUtilServiceImpl.class);

    @Autowired
    private WorkerLogService workerLogService;
    @Autowired
    private VMCApiService vmcApiService;

    @Override
    public boolean isNonDownWorkerValid(Worker workerFromDb, String currentStatus, Boolean fileSystemOK, List<Task> currentTasks) {
        // status check
        if (currentStatus != null) {
            // no expected status
            if (workerFromDb.getStatus().equalsIgnoreCase("RESERVED")) {
                if (workerFromDb.getTaskId() != null) {
                    logger.debug("Worker [" + workerFromDb.getUuid() + "] failed validity check on status in db [" + workerFromDb.getStatus() + "] current [" + currentStatus + "], taks id in db is " + workerFromDb.getTaskId());
                    return false;
                }

                if (currentStatus.equalsIgnoreCase("FREE") || currentStatus.equalsIgnoreCase("WORKING")) {

                } else {
                    logger.debug("Worker [" + workerFromDb.getUuid() + "] failed validity check on status in db [" + workerFromDb.getStatus() + "] current [" + currentStatus + "]");
                    return false;
                }
            } else if (!workerFromDb.getStatus().equalsIgnoreCase(currentStatus)) {
                logger.debug("Worker [" + workerFromDb.getUuid() + "] failed validity check on status in db [" + workerFromDb.getStatus() + "] current [" + currentStatus + "]");
                return false;
            }
        } else {
            logger.error("Worker [" + workerFromDb.getUuid() + "] failed validity check on status expected [" + workerFromDb.getStatus() + "] current [" + currentStatus + "]");
            return false;
        }
        // file system check
        if (fileSystemOK != null) {
            if (!fileSystemOK) {
                logger.debug("Worker failed validity check on file system");
                return false;
            }
        }
        // linked tasks check
        // the worker should not have any tasks on them
        // worker can have FREE WORKING HOLD

        if (workerFromDb.getStatus().equalsIgnoreCase("FREE")) {
            Long taskInDb = workerFromDb.getTaskId();
            // must be null
            if (taskInDb != null) {
                logger.debug("Worker failed validity check on linked task check.status:{} db task:{} real task:{}", workerFromDb.getStatus(), taskInDb, currentTasks.toString());
                return false;
            }

            // must have non NULL empty task list
            if (currentTasks == null || !currentTasks.isEmpty()) {
                logger.debug("Worker failed validity check on linked task check.status:{} db task:{} real task:{}", workerFromDb.getStatus(), taskInDb, currentTasks.toString());
                return false;
            }
        } else if (workerFromDb.getStatus().equalsIgnoreCase("WORKING")) {
            Long taskInDb = workerFromDb.getTaskId();
            // must not be null
            if (taskInDb == null) {
                logger.debug("Worker failed validity check on linked task check.status===>" + workerFromDb.getStatus() + " db task is NULL real task===>" + currentTasks.toString());
                return false;
            }

            // must have non NULL task list and only one task
            if (currentTasks == null || currentTasks.size() != 1) {
                logger.debug("Worker failed validity check on linked task check.status===>" + workerFromDb.getStatus() + " db task===>" + taskInDb + " real task===>" + currentTasks.toString());
                return false;
            }
        } else {// HOLD,same as FREE
            Long taskInDb = workerFromDb.getTaskId();
            // must be null
            if (taskInDb != null) {
                logger.debug("Worker failed validity check on linked task check.status===>" + workerFromDb.getStatus() + " db task===>" + taskInDb + " real task===>" + currentTasks.toString());
                return false;
            }

            // must have non NULL empty task list
            if (currentTasks == null || !currentTasks.isEmpty()) {
                logger.debug("Worker failed validity check on linked task check.status===>" + workerFromDb.getStatus() + " db task===>" + taskInDb + " real task===>" + currentTasks.toString());
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isDownWorkerRecyclable(Worker workerFromDb, String currentStatus, Boolean fileSystemOK, List<Task> currentTasks) {
        // file system check
        if (fileSystemOK != null) {
            if (!fileSystemOK) {
                logger.debug("Worker failed validity check on file system");
                return false;
            }
        }

        // chec kif it has any task on it, set it to the black list and return false;
        if (currentTasks != null && !currentTasks.isEmpty()) {
            logger.debug("DOWN worker has tasks on it. will set it to black list from updating task.");
//            String ip = workerFromDb.getIpAddress();
            // RuntimeVariables.blackListWorkers.add(ip);
        }

        // current status must be FREE
        if (!currentStatus.equalsIgnoreCase("FREE")) {
            logger.debug("DOWN worker is not recyclabe with FREE status");
            return false;
        }
        return true;
    }

    @Override
    public String getBaseUrl(Worker worker) {
        String ip = worker.getIpAddress();
        String protocol = worker.getProtocol();
        Integer port = worker.getPort();
        return protocol + "://" + ip + ":" + port;
    }

    @Override
    public Worker generateWorkerFieldsForUpdate(Worker newWorker, Worker oriWorker) {
        Worker updateWorker = new Worker();
        if (newWorker.getId() != null) {
            updateWorker.setId(newWorker.getId());
        } else {
            updateWorker.setId(oriWorker.getId());
        }
        // =================
        if (newWorker.getBandwidth() != null) {
            updateWorker.setBandwidth(newWorker.getBandwidth());
        } else {
            updateWorker.setBandwidth(oriWorker.getBandwidth());
        }
        // =================
        if (newWorker.getCpuCore() != null) {
            updateWorker.setCpuCore(newWorker.getCpuCore());
        } else {
            updateWorker.setCpuCore(oriWorker.getCpuCore());
        }
        // =================
        if (newWorker.getArchitecture() != null) {
            updateWorker.setArchitecture(newWorker.getArchitecture());
        } else {
            updateWorker.setArchitecture(oriWorker.getArchitecture());
        }
        // =================
        if (newWorker.getGroup() != null) {
            updateWorker.setGroup(newWorker.getGroup());
        } else {
            updateWorker.setGroup(oriWorker.getGroup());
        }
        // =================
        if (newWorker.getHostname() != null) {
            updateWorker.setHostname(newWorker.getHostname());
        } else {
            updateWorker.setHostname(oriWorker.getHostname());
        }
        // =================
        if (newWorker.getIpAddress() != null) {
            updateWorker.setIpAddress(newWorker.getIpAddress());
        } else {
            updateWorker.setIpAddress(oriWorker.getIpAddress());
        }
        // =================
        if (newWorker.getOperatingSystem() != null) {
            updateWorker.setOperatingSystem(newWorker.getOperatingSystem());
        } else {
            updateWorker.setOperatingSystem(oriWorker.getOperatingSystem());
        }
        // =================
        if (newWorker.getPort() != null) {
            updateWorker.setPort(newWorker.getPort());
        } else {
            updateWorker.setPort(oriWorker.getPort());
        }
        // =================
        if (newWorker.getProtocol() != null) {
            updateWorker.setProtocol(newWorker.getProtocol());
        } else {
            updateWorker.setProtocol(oriWorker.getProtocol());
        }
        // =================
        if (newWorker.getType() != null) {
            updateWorker.setType(newWorker.getType());
        } else {
            updateWorker.setType(oriWorker.getType());
        }
        // =================
        if (newWorker.getRam() != null) {
            updateWorker.setRam(newWorker.getRam());
        } else {
            updateWorker.setRam(oriWorker.getRam());
        }
        // =================
        if (newWorker.getName() != null) {
            updateWorker.setName(newWorker.getName());
        } else {
            updateWorker.setName(oriWorker.getName());
        }
        // =================
        if (newWorker.getMacAddress() != null) {
            updateWorker.setMacAddress(newWorker.getMacAddress());
        } else {
            updateWorker.setMacAddress(oriWorker.getMacAddress());
        }
        // =================
        return updateWorker;
    }

    @Override
    public boolean checkWorkerAssignmentResponse(MeowlomoResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode metadata = response.getMetadata();
        if (metadata.has("validToken")) {
            boolean validToken = metadata.get("validToken").asBoolean();
            if (!validToken) {
                logger.debug("Trying to assign to a worker with an invalid token fomr the response");
                return false;
            }
        }
        if (metadata.has("reject")) {
            // get the worker status
            String workerReponseStatus = metadata.get("workerStatus").asText();
            List<Task> workerCurrentTasks = mapper.convertValue(response.getData(), new TypeReference<List<Task>>() {
            });
            if (workerReponseStatus.equalsIgnoreCase("WORKING") && !workerCurrentTasks.isEmpty()) {
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean rebootWorker(Worker worker) {
        if (worker == null) {
            logger.error("The worker object is NUL for reboot");
            throw new NullPointerException();
        }

        if (worker.getId() == null || worker.getProtocol() == null || worker.getPort() == null || worker.getIpAddress() == null) {
            logger.error("Worker is missing information for reboot. " + "ID[" + worker.getId() + "]" + "Protocol[" + worker.getProtocol() + "], " + "IP Address[" + worker.getIpAddress() + "], " + "Port[" + worker.getPort() + "]");
            return false;
        }

        // get the information to reboot the worker
        Long wokerId = worker.getId();
        String workerIp = worker.getIpAddress();
        Integer workerPort = worker.getPort();
        String workerProtocol = worker.getProtocol();
        URL baseUrl = new HttpUrl.Builder().scheme(workerProtocol).host(workerIp).port(workerPort).build().url();
        logger.debug("Goting to reboot worker with url " + baseUrl.toString());
        MeowlomoResponse response = vmcApiService.rebootWorker(baseUrl.toString());
        if (response != null) {
            logger.debug("worker {} received reboot", worker.getUuid());
            // return true here
            logger.debug("Worker " + worker.getUuid() + " has been rebooted.");
            WorkerLog workerLog = new WorkerLog();
            workerLog.setId(wokerId);
            workerLog.setMessage("received Reboot signal and successfully executed.");
            workerLogService.insertSelective(workerLog);
            return true;
        } else {// Response not OK
            logger.error("Error response from Worker {} from reboot request", worker.getUuid());
            return false;
        }
    }

    @Override
    public boolean workerTokenIsValid(String tokenString, UUID workerToken) {
        // check the id
        if (tokenString == null) {
            logger.error("Worker token check the input worker token string is null.");
            return false;
        } else if (workerToken == null) {
            logger.error("Worker token check the input worker token is null.");
            return false;
        } else {
//            //check the token from the worker
//            Worker worker = workerService.selectByPrimaryKey(workerId);
//            UUID workerCurrentToken = worker.getToken();
//            if (workerCurrentToken == null) {
//                logger.error("Worker token in database is null, it is not registered properly.");
//                return false;
//            }
//            else 
            if (workerToken.compareTo(UUID.fromString(tokenString)) == 0) {
                return true;
            } else {
                logger.debug("Worker token check no valid. input token {}  , actual token {}", workerToken, tokenString);
                return false;
            }
        }
    }

}
