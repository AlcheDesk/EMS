package com.meowlomo.ems.scheduler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meowlomo.ems.config.RuntimeVariables;
import com.meowlomo.ems.core.model.ExecutionControlType;
import com.meowlomo.ems.core.model.ExecutionControlTypeExample;
import com.meowlomo.ems.core.model.Group;
import com.meowlomo.ems.core.model.GroupExample;
import com.meowlomo.ems.core.model.JobType;
import com.meowlomo.ems.core.model.JobTypeExample;
import com.meowlomo.ems.core.model.OperatingSystem;
import com.meowlomo.ems.core.model.OperatingSystemExample;
import com.meowlomo.ems.core.model.Status;
import com.meowlomo.ems.core.model.StatusExample;
import com.meowlomo.ems.core.model.TaskType;
import com.meowlomo.ems.core.model.TaskTypeExample;
import com.meowlomo.ems.core.model.Vendor;
import com.meowlomo.ems.core.model.VendorExample;
import com.meowlomo.ems.core.model.WorkerType;
import com.meowlomo.ems.core.model.WorkerTypeExample;
import com.meowlomo.ems.core.service.base.ExecutionControlTypeService;
import com.meowlomo.ems.core.service.base.GroupService;
import com.meowlomo.ems.core.service.base.JobTypeService;
import com.meowlomo.ems.core.service.base.OperatingSystemService;
import com.meowlomo.ems.core.service.base.StatusService;
import com.meowlomo.ems.core.service.base.TaskTypeService;
import com.meowlomo.ems.core.service.base.VendorService;
import com.meowlomo.ems.core.service.base.WorkerTypeService;

@Component
public class DataFetcher {

    static final Logger logger = LoggerFactory.getLogger(DataFetcher.class);

    @Autowired
    private StatusService statusService;
    @Autowired
    private OperatingSystemService operatingSystemService;
    @Autowired
    private JobTypeService jobTypeService;
    @Autowired
    private TaskTypeService taskTypeService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private WorkerTypeService workerTypeService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private ExecutionControlTypeService executionControlTypeService;

    /*
     * cron format seconds,minutes,hours,day,month,weekday
     */

    /**
     * Fetch status.
     */
    @Scheduled(cron = "0 0 2 * * *") // run on only at 2am every day
    public void fetchStatus() {
        logger.debug("fetching status");
        HashSet<String> names = new HashSet<String>();
        Map<String, Long> nameToIDMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        StatusExample example = new StatusExample();
        example.or().andIdIsNotNull();
        List<Status> recordList = statusService.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                Status record = recordList.get(count);
                nameToIDMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToStatusMap(idToNameMap);
            RuntimeVariables.setStatusToIdMap(nameToIDMap);
            RuntimeVariables.setStatuses(names);
        }
    }

    @Scheduled(cron = "0 * * * * 1-5") // run every 1 minutes from Monday to Friday
    public void fetchJobType() {
        logger.debug("fetching job type");
        HashSet<String> names = new HashSet<String>();
        Map<String, Long> nameToIDMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        JobTypeExample example = new JobTypeExample();
        example.or().andIdIsNotNull();
        List<JobType> recordList = jobTypeService.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                JobType record = recordList.get(count);
                nameToIDMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToJobTypeMap(idToNameMap);
            RuntimeVariables.setJobTypeToIdMap(nameToIDMap);
            RuntimeVariables.setJobTypes(names);
        }
    }

    @Scheduled(cron = "0 * * * * MON-FRI") // run every 1 minutes from Monday to Friday
    public void fetchTaskType() {
        logger.debug("fetching task type");
        HashSet<String> names = new HashSet<String>();
        Map<String, Long> nameToIDMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        TaskTypeExample example = new TaskTypeExample();
        example.or().andIdIsNotNull();
        List<TaskType> recordList = taskTypeService.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                TaskType record = recordList.get(count);
                nameToIDMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToTaskTypeMap(idToNameMap);
            RuntimeVariables.setTaskTypeToIdMap(nameToIDMap);
            RuntimeVariables.setTaskTypes(names);
        }
    }

    @Scheduled(cron = "0 */10 * * * MON-FRI") // run every 10 minutes from Monday to Friday
    public void fetchGroup() {
        logger.debug("fetching group");
        HashSet<String> names = new HashSet<String>();
        Map<String, Long> nameToIDMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        GroupExample example = new GroupExample();
        example.or().andIdIsNotNull();
        List<Group> recordList = groupService.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                Group record = recordList.get(count);
                nameToIDMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToGroupMap(idToNameMap);
            RuntimeVariables.setGroupToIdMap(nameToIDMap);
            RuntimeVariables.setGroups(names);
        }
    }

    @Scheduled(cron = "0 1 2 * * 6") // run on 2:01am Sunday
    public void fetchOperatingSystem() {
        logger.debug("fetching operating system");
        HashSet<String> names = new HashSet<String>();
        Map<String, Long> nameToIDMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        OperatingSystemExample example = new OperatingSystemExample();
        example.or().andIdIsNotNull();
        List<OperatingSystem> recordList = operatingSystemService.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                OperatingSystem record = recordList.get(count);
                nameToIDMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToOperatingSystemMap(idToNameMap);
            RuntimeVariables.setOperatingSystemToIdMap(nameToIDMap);
            RuntimeVariables.setOperatingSystems(names);
        }
    }

    @Scheduled(cron = "0 */10 * * * *") // run on only at 2am every day, 10mins
    public void fetchWorkerType() {
        logger.debug("fetching worker type");
        HashSet<String> names = new HashSet<String>();
        Map<String, Long> nameToIDMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        WorkerTypeExample example = new WorkerTypeExample();
        example.or().andIdIsNotNull();
        List<WorkerType> recordList = workerTypeService.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                WorkerType record = recordList.get(count);
                nameToIDMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToWorkerTypeMap(idToNameMap);
            RuntimeVariables.setWorkerTypeToIdMap(nameToIDMap);
            RuntimeVariables.setWorkerTypes(names);
        }
    }

    @Scheduled(cron = "0 */10 * * * *") // run 10 min
    public void fetchVendor() {
        logger.debug("fetching vendor");
        HashSet<String> names = new HashSet<String>();
        Map<String, Long> nameToIDMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        VendorExample example = new VendorExample();
        example.or().andIdIsNotNull();
        List<Vendor> recordList = vendorService.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                Vendor record = recordList.get(count);
                nameToIDMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToVendor(idToNameMap);
            RuntimeVariables.setVendorToIdMap(nameToIDMap);
            RuntimeVariables.setVendor(names);
        }
    }
    
    @Scheduled(cron = "0 * * * * 1-5") // run every 1 minutes from Monday to Friday
    public void fetchExecutionControlType() {
        logger.debug("fetching execution control type");
        HashSet<String> names = new HashSet<String>();
        Map<String, Long> nameToIDMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
        HashMap<Long, String> idToNameMap = new HashMap<Long, String>();
        ExecutionControlTypeExample example = new ExecutionControlTypeExample();
        example.or().andIdIsNotNull();
        List<ExecutionControlType> recordList = executionControlTypeService.selectByExample(example);
        if (!recordList.isEmpty()) {
            for (int count = 0; count < recordList.size(); count++) {
                ExecutionControlType record = recordList.get(count);
                nameToIDMap.put(record.getName(), record.getId());
                idToNameMap.put(record.getId(), record.getName());
                names.add(record.getName());
            }
            // put the map to the runtime variables
            RuntimeVariables.setIdToExecutionControlTypeMap(idToNameMap);
            RuntimeVariables.setExecutionControlTypeToIdMap(nameToIDMap);
            RuntimeVariables.setExecutionControlTypes(names);
        }
    }
}
