package com.meowlomo.ems.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.meowlomo.ems.config.RuntimeVariables;
import com.meowlomo.ems.scheduler.DataFetcher;

@Component
public class DataMappingInitListener {

    static final Logger logger = LoggerFactory.getLogger(DataMappingInitListener.class);

    @Autowired
    DataFetcher scheduler = new DataFetcher();

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        
        logger.info("Initializing Database Data Mapping");

        this.scheduler.fetchGroup();
        this.scheduler.fetchStatus();
        this.scheduler.fetchJobType();
        this.scheduler.fetchTaskType();
        this.scheduler.fetchOperatingSystem();
        this.scheduler.fetchWorkerType();
        this.scheduler.fetchVendor();
        this.scheduler.fetchExecutionControlType();

        logger.info("Id to Status Map =>" + RuntimeVariables.getIdToStatusMap().toString());
        logger.info("Id to Operating System Map =>" + RuntimeVariables.getIdToOperatingSystemMap().toString());
        logger.info("Id to Job Type Map =>" + RuntimeVariables.getIdToJobTypeMap().toString());
        logger.info("Id to Task Type Map =>" + RuntimeVariables.getIdToTaskTypeMap().toString());
        logger.info("Id to Group Map =>" + RuntimeVariables.getIdToGroupMap().toString());
        logger.info("Id to Worker Type Map =>" + RuntimeVariables.getIdToWorkerTypeMap().toString());
        logger.info("Id to Vendor Map =>" + RuntimeVariables.getIdToVendor().toString());
        logger.info("Id to Execution Control Type Map =>" + RuntimeVariables.getIdToExecutionControlTypeMap().toString());
    }

}
