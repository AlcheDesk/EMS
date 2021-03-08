package com.meowlomo.ems.core.service.background.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meowlomo.ems.config.RuntimeVariables;
import com.meowlomo.ems.core.mapper.WorkerControlMapper;
import com.meowlomo.ems.core.mapper.WorkerLogMapper;
import com.meowlomo.ems.core.mapper.WorkerMapper;
import com.meowlomo.ems.core.mapper.WorkerReferenceMapper;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;
import com.meowlomo.ems.core.model.WorkerLog;
import com.meowlomo.ems.core.resource.model.MeowlomoResponse;
import com.meowlomo.ems.core.service.background.WorkerHousekeeperService;
import com.meowlomo.ems.core.service.util.WorkerUtilService;
import com.meowlomo.ems.core.service.util.impl.JWTUtil;
import com.meowlomo.ems.external.vmc.api.VMCApiService;

@Service
public class WorkerHousekeeperImpl implements WorkerHousekeeperService {

    static final Logger logger = LoggerFactory.getLogger(WorkerHousekeeperImpl.class);

    private static ConcurrentSkipListSet<Long> firstTimeDownQueue = new ConcurrentSkipListSet<Long>();

    private static ConcurrentSkipListSet<Long> thirdTimeDownQueue = new ConcurrentSkipListSet<Long>();

    private static ConcurrentSkipListSet<Long> secondTimeDownQueue = new ConcurrentSkipListSet<Long>();

    private static ConcurrentSkipListSet<Long> firstTimeUpQueue = new ConcurrentSkipListSet<Long>();

    private static ConcurrentSkipListSet<Long> secondTimeUpQueue = new ConcurrentSkipListSet<Long>();

    @Autowired
    private WorkerReferenceMapper workerReferenceMapper;

    @Autowired
    private WorkerControlMapper workerControlMapper;

    @Autowired
    private WorkerMapper workerMapper;
    
    @Autowired
    private WorkerLogMapper workerLogMapper;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    private WorkerUtilService workerUtilService;
    
    @Autowired
    private VMCApiService vmcApiService;

    @Override
    public void checkWorkers() {
        // get all workers first
        WorkerExample allWorkerExample = new WorkerExample();
        allWorkerExample.or().andStatusNotEqualTo("DOWN").andManageableEqualTo(true);
        List<Worker> workers = workerReferenceMapper.selectByExample(allWorkerExample);
        // check worker one by one
        if (workers.isEmpty()) {
            logger.info("All workers are DOWN, Clear all down queue. Let Worker Resycler do its job.");
            WorkerHousekeeperImpl.firstTimeDownQueue.clear();
            WorkerHousekeeperImpl.secondTimeDownQueue.clear();
            WorkerHousekeeperImpl.thirdTimeDownQueue.clear();
        } else {
            // check non down worker one by one
            for (Worker worker : workers) {
                Long workerId = worker.getId();
                MeowlomoResponse response = this.checkWorkerByAPI(worker, 1000);
                // cehck result
                boolean finalResult = false;
                // only has response and valid status will pass
                if (response != null) {
                    logger.debug("Worker reponse ===>" + response.toString());
                    ObjectNode metadata = response.getMetadata();
                    String statusFromAPI = metadata.get("status").asText();
                    Boolean fileSystemAvaliable = metadata.get("fileSystemOK").asBoolean();
                    String tokenStringFromWorker = metadata.has("token") ? metadata.get("token").asText() : null;

                    // check the token from the worker first
                    UUID tokenFromWorker = worker.getToken();
                    // check the token
                    if (workerUtilService.workerTokenIsValid(tokenStringFromWorker, tokenFromWorker)) {

                    } else {
                        logger.debug("Wokre {} has token {} in the DB, but response with token {}. Will put it to unmanageable mode.", worker.getUuid(), worker.getToken(), tokenStringFromWorker);
                        this.updateWorkerToUnmanageable(worker);
                        // remove from all list, if in.
                        this.removeIDFromAllList(workerId);
                        continue;
                    }

                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    List<Task> linkedTasksFromAPI = mapper.convertValue(response.getData(), new TypeReference<List<Task>>() {
                    });
                    boolean verifyResult = workerUtilService.isNonDownWorkerValid(worker, statusFromAPI, fileSystemAvaliable, linkedTasksFromAPI);
                    if (verifyResult) {
                        if (WorkerHousekeeperImpl.firstTimeDownQueue.contains(workerId)) {
                            WorkerHousekeeperImpl.firstTimeDownQueue.remove(workerId);
                        }
                        if (WorkerHousekeeperImpl.secondTimeDownQueue.contains(workerId)) {
                            WorkerHousekeeperImpl.secondTimeDownQueue.remove(workerId);
                        }
                        if (WorkerHousekeeperImpl.thirdTimeDownQueue.contains(workerId)) {
                            WorkerHousekeeperImpl.thirdTimeDownQueue.remove(workerId);
                        }
                        finalResult = true;
                    }
                }
                if (!finalResult) {
                    if (WorkerHousekeeperImpl.thirdTimeDownQueue.contains(workerId)) {
                        logger.debug("Bringing worker [" + worker.getUuid() + "] to down");
                        this.updateWorkerStatus(worker, "DOWN");
                    } else if (WorkerHousekeeperImpl.secondTimeDownQueue.contains(workerId)) {
                        logger.debug("Move worker [" + worker.getUuid() + "] to third down queue.");
                        WorkerHousekeeperImpl.thirdTimeDownQueue.add(workerId);
                        WorkerHousekeeperImpl.secondTimeDownQueue.remove(workerId);
                    } else if (WorkerHousekeeperImpl.firstTimeDownQueue.contains(workerId)) {
                        logger.debug("Move worker [" + worker.getUuid() + "] to second down queue.");
                        WorkerHousekeeperImpl.secondTimeDownQueue.add(workerId);
                        WorkerHousekeeperImpl.firstTimeDownQueue.remove(workerId);
                    } else {
                        logger.debug("Put worker [" + worker.getUuid() + "] to first down queue.");
                        WorkerHousekeeperImpl.firstTimeDownQueue.add(workerId);
                    }
                }
            }
        }
    }

    @Override
    public void recycleWorkers() {
        // get all down workers
        WorkerExample example = new WorkerExample();
        example.or().andStatusEqualTo("DOWN");
        List<Worker> workers = workerReferenceMapper.selectByExample(example);
        if (workers.isEmpty()) {
            logger.info("No worker is DOWN, Clear all up queue");
            WorkerHousekeeperImpl.secondTimeUpQueue.clear();
            WorkerHousekeeperImpl.firstTimeUpQueue.clear();
        }
        for (Worker worker : workers) {
            long timeout = 300000 / workers.size();
            if (timeout > 1000) {
                timeout = 1000;
            }
            MeowlomoResponse response = this.checkWorkerByAPI(worker, timeout);
            Long workerId = worker.getId();
            // cehck result
            if (response != null) {
                logger.info("Worker reponse ===>" + response.toString());
                ObjectNode metadata = response.getMetadata();
                String statusFromAPI = metadata.get("status").asText();
                Boolean fileSystemAvaliable = metadata.get("fileSystemOK").asBoolean();
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                List<Task> linkedTasksFromAPI = mapper.convertValue(response.getData(), new TypeReference<List<Task>>() {
                });
                boolean verifyResult = workerUtilService.isDownWorkerRecyclable(worker, statusFromAPI, fileSystemAvaliable, linkedTasksFromAPI);
                if (verifyResult) {
                    if (WorkerHousekeeperImpl.secondTimeUpQueue.contains(workerId)) {
                        logger.info("Set DOWN worker [" + worker.getUuid() + "] back to FREE");
                        this.updateWorkerBackToFree(worker);
                        RuntimeVariables.blackListWorkers.remove(worker.getIpAddress());
                    } else if (WorkerHousekeeperImpl.firstTimeUpQueue.contains(workerId)) {
                        logger.info("Move worker [" + worker.getUuid() + "] to second up queue.");
                        WorkerHousekeeperImpl.secondTimeUpQueue.add(workerId);
                        WorkerHousekeeperImpl.firstTimeUpQueue.remove(workerId);
                    } else {
                        logger.info("Put worker [" + worker.getUuid() + "] to first up queue.");
                        WorkerHousekeeperImpl.firstTimeUpQueue.add(workerId);
                    }
                }
            }
        }
    }

    private void removeIDFromAllList(Long workerId) {
        if (workerId != null) {
            WorkerHousekeeperImpl.firstTimeDownQueue.remove(workerId);
            WorkerHousekeeperImpl.secondTimeDownQueue.remove(workerId);
            WorkerHousekeeperImpl.thirdTimeDownQueue.remove(workerId);
            WorkerHousekeeperImpl.firstTimeUpQueue.remove(workerId);
            WorkerHousekeeperImpl.secondTimeUpQueue.remove(workerId);
        }
    }

    private MeowlomoResponse checkWorkerByAPI(Worker worker, long timeout) {
        String baseUrl = worker.getProtocol() + "://" + worker.getIpAddress() + ":" + worker.getPort();
        try {
            // get the health status from the worker           
            logger.info("check worker by api. base url : {}  uuid : {}",baseUrl, worker.getUuid());

            logger.info("Checking worker UUID " + worker.getUuid());
            MeowlomoResponse response = vmcApiService.checkWorkerHealth(baseUrl);
            // check the response
            if (response != null) {
                return response;
            } else {
                return null;
            }
        } catch (IllegalArgumentException ex) {
            return null;
        }finally {
            logger.info("check worker by api. base url : {}  uuid : {}",baseUrl, worker.getUuid());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private void updateWorkerBackToFree(Worker worker) {
        Worker updateWorker = new Worker();
        updateWorker.setStatus("FREE");
        updateWorker.setId(worker.getId());
        workerControlMapper.setWorkerToFreeFromDownByPrimaryKey(worker);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private void updateWorkerStatus(Worker worker, String status) {
        WorkerExample example = new WorkerExample();
        example.or().andIdEqualTo(worker.getId());
        Worker updateWorker = new Worker();
        updateWorker.setStatus(status);
        int udpateresult = workerMapper.updateByExampleSelective(updateWorker, example);
        if (udpateresult != 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private void updateWorkerToUnmanageable(Worker worker) {
        Worker updateWorker = new Worker();
        updateWorker.setStatus("DOWN");
        updateWorker.setManageable(false);
        updateWorker.setId(worker.getId());
        workerControlMapper.setWorkerToFreeFromDownByPrimaryKey(worker);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public long cleanAbnormalWorkers() {
        List<Worker> workers = this.selectAbnormalWorkers();
        long count = 0;
        for (int taskCount = 0; taskCount < workers.size(); taskCount++) {
            if (this.processAbnormalWorker(workers.get(taskCount))) {
                count = count + 1;
            }
        }
        return count;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private boolean processAbnormalWorker(Worker worker) {
        WorkerLog log = new WorkerLog();
        log.setMessage("the worker [" + worker.getId() + "] is assigned to " + worker.getTaskId() +  " but record shown the task is finished, Set to DOWN.");
        log.setWorkerId(worker.getId());
        long logResult = workerLogMapper.insert(log);
        if (logResult == 1) {
            // update the task
            Worker updateWorker = new Worker();
            updateWorker.setStatus("DOWN");
            updateWorker.setId(worker.getId());
            updateWorker.setTaskId(worker.getTaskId());
            int workerUpdateResult = workerMapper.updateByPrimaryKey(updateWorker);
            if (workerUpdateResult != 1) {
                logger.error("Error on udpate abnormal worker "+ worker.getIpAddress()+" " + worker.getUuid() + " to status down");
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
            return true;
        } else {
            logger.error("Error on adding log for cleaning abnormal task " + worker.getUuid());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
    
    private List<Worker> selectAbnormalWorkers() {
        return workerControlMapper.selectAbnormalWorkers();
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void wakeupOnHoldWorker() {
    	WorkerExample workerExample = new WorkerExample();
    	workerExample.createCriteria().andStatusEqualTo("HOLD");
    	List<Worker> workers = workerMapper.selectByExample(workerExample);
    	for (int i = 0; i < workers.size(); i++) {
        	Worker updateWorker = workers.get(i);
            updateWorker.setStatus("FREE");
            updateWorker.setActive(true);
            updateWorker.setTask(null);
            WorkerLog log = new WorkerLog();
            log.setMessage(LocalDate.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME)+" ");
            workerMapper.updateByPrimaryKey(updateWorker);
    	}
    }
}
