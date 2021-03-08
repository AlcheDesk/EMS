package com.meowlomo.ems.core.service.custom.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meowlomo.ems.core.mapper.WorkerReferenceMapper;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;
import com.meowlomo.ems.core.model.custom.WorkerStatus;
import com.meowlomo.ems.core.service.custom.WorkerStatusService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkerStatusServiceImpl implements WorkerStatusService{
    
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;
    
    @Autowired
    private WorkerReferenceMapper workerReferenceMapper;

    @Override
    public long countByExample(WorkerExample example) {
        return workerReferenceMapper.countByExample(example);
    }
    
    @Override
    public List<WorkerStatus> selectByExample(WorkerExample example) {        
        return this.convertWorkersToWorkerStatus(workerReferenceMapper.selectByExample(example));
    }
    
    private List<WorkerStatus> convertWorkersToWorkerStatus(List<Worker> workers){

        List<WorkerStatus> returnList = new ArrayList<WorkerStatus>();
        for (int i = 0; i < workers.size(); i++) {
            Worker workerEntry = workers.get(i);
            WorkerStatus workerStatusEntry = this.convertWorkerToWorkerStatus(workerEntry);
            if (workerStatusEntry != null) {
                returnList.add(workerStatusEntry);
            }
        }
        return returnList;
    }
    
    private WorkerStatus convertWorkerToWorkerStatus (Worker worker) {
        ObjectMapper mapper = jacksonConverter.getObjectMapper();
        Task taskEntry = worker.getTask();
        JsonNode taskData = taskEntry.getData();
        worker.setLogs(null);
        worker.setTask(null);
        worker.setVendors(null);
        try {
            
            String workerString = mapper.writeValueAsString(worker);
            WorkerStatus workerStatusEntry = mapper.readValue(workerString, WorkerStatus.class);
            workerStatusEntry.setTaskData(taskData);
            return workerStatusEntry;
        }
        catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<WorkerStatus> selectByExampleWithRowbounds(WorkerExample example, RowBounds rowBounds) {
        return this.convertWorkersToWorkerStatus(workerReferenceMapper.selectByExampleWithRowbounds(example, rowBounds));
    }

    @Override
    public WorkerStatus selectByUuid(UUID workerUuid) {
        WorkerExample example = new WorkerExample();
        example.createCriteria().andUuidEqualTo(workerUuid);
        return this.convertWorkerToWorkerStatus(workerReferenceMapper.selectByExample(example).get(0));
    }
}
