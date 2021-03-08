package com.meowlomo.ems.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class RuntimeVariables {
    
    public static int DEFAULT_LIMIT = 20;
    
    public static List<String> TASK_FINAL_STATUSES = Arrays.asList("PASS", "FAIL", "ERROR", "TERMINATED");

    public static ConcurrentSkipListSet <String> blackListWorkers = new ConcurrentSkipListSet<String>();
    
    private volatile static Set<String> statuses = new HashSet<String>();
    private volatile static Set<String> jobTypes = new HashSet<String>();
    private volatile static Set<String> executionControlTypes = new HashSet<String>();
    private volatile static Set<String> taskTypes = new HashSet<String>();
    private volatile static Set<String> workerTypes = new HashSet<String>();
    private volatile static Set<String> operatingSystems = new HashSet<String>();
    private volatile static Set<String> groups = new HashSet<String>();
    private volatile static Set<String> vendor = new HashSet<String>();

    private volatile static Map<String, Long> statusToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private volatile static Map<Long, String> IdToStatusMap = new HashMap<Long, String>();
    private volatile static Map<String, Long> operatingSystemToIdMap = new TreeMap<String, Long>(
            String.CASE_INSENSITIVE_ORDER);
    private volatile static Map<Long, String> IdToOperatingSystemMap = new HashMap<Long, String>();
    private volatile static Map<String, Long> jobTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private volatile static Map<Long, String> IdToJobTypeMap = new HashMap<Long, String>();
    private volatile static Map<String, Long> executionControlTypeToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private volatile static Map<Long, String> IdToExecutionControlTypeMap = new HashMap<Long, String>();
    private volatile static Map<String, Long> workerTypeToIdMap = new TreeMap<String, Long>(
            String.CASE_INSENSITIVE_ORDER);
    private volatile static Map<Long, String> IdToWorkerTypeMap = new HashMap<Long, String>();
    private volatile static Map<String, Long> taskTypeToIdMap = new TreeMap<String, Long>(
            String.CASE_INSENSITIVE_ORDER);
    private volatile static Map<Long, String> IdToTaskTypeMap = new HashMap<Long, String>();
    private volatile static Map<String, Long> groupToIdMap = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
    private volatile static Map<Long, String> IdToGroupMap = new HashMap<Long, String>();
    
    private volatile static Map<String, Long> vendorToIdMap = new TreeMap<String, Long>(
            String.CASE_INSENSITIVE_ORDER);
    private volatile static Map<Long, String> IdToVendor = new HashMap<Long, String>();

    public static Map<String, Long> getStatusToIdMap() {
        return statusToIdMap;
    }

    public static void setStatusToIdMap(Map<String, Long> statusToIdMap) {
        RuntimeVariables.statusToIdMap = statusToIdMap;
    }

    public static Map<Long, String> getIdToStatusMap() {
        return IdToStatusMap;
    }

    public static void setIdToStatusMap(Map<Long, String> idToStatusMap) {
        IdToStatusMap = idToStatusMap;
    }

    public static Map<String, Long> getOperatingSystemToIdMap() {
        return operatingSystemToIdMap;
    }

    public static void setOperatingSystemToIdMap(Map<String, Long> operatingSystemToIdMap) {
        RuntimeVariables.operatingSystemToIdMap = operatingSystemToIdMap;
    }

    public static Map<Long, String> getIdToOperatingSystemMap() {
        return IdToOperatingSystemMap;
    }

    public static void setIdToOperatingSystemMap(Map<Long, String> idToOperatingSystemMap) {
        IdToOperatingSystemMap = idToOperatingSystemMap;
    }

    public static Map<String, Long> getJobTypeToIdMap() {
        return jobTypeToIdMap;
    }

    public static void setJobTypeToIdMap(Map<String, Long> jobTypeToIdMap) {
        RuntimeVariables.jobTypeToIdMap = jobTypeToIdMap;
    }

    public static Map<Long, String> getIdToJobTypeMap() {
        return IdToJobTypeMap;
    }

    public static void setIdToJobTypeMap(Map<Long, String> idToJobTypeMap) {
        IdToJobTypeMap = idToJobTypeMap;
    }

    public static Map<String, Long> getTaskTypeToIdMap() {
        return taskTypeToIdMap;
    }

    public static void setTaskTypeToIdMap(Map<String, Long> taskTypeToIdMap) {
        RuntimeVariables.taskTypeToIdMap = taskTypeToIdMap;
    }

    public static Map<Long, String> getIdToTaskTypeMap() {
        return IdToTaskTypeMap;
    }

    public static void setIdToTaskTypeMap(Map<Long, String> idToTaskTypeMap) {
        IdToTaskTypeMap = idToTaskTypeMap;
    }

    public static Map<String, Long> getGroupToIdMap() {
        return groupToIdMap;
    }

    public static void setGroupToIdMap(Map<String, Long> groupToIdMap) {
        RuntimeVariables.groupToIdMap = groupToIdMap;
    }

    public static Map<Long, String> getIdToGroupMap() {
        return IdToGroupMap;
    }

    public static void setIdToGroupMap(Map<Long, String> idToGroupMap) {
        IdToGroupMap = idToGroupMap;
    }

    public static Set<String> getStatuses() {
        return statuses;
    }

    public static void setStatuses(Set<String> statuses) {
        RuntimeVariables.statuses = statuses;
    }

    public static Set<String> getJobTypes() {
        return jobTypes;
    }

    public static void setJobTypes(Set<String> jobTypes) {
        RuntimeVariables.jobTypes = jobTypes;
    }

    public static Set<String> getTaskTypes() {
        return taskTypes;
    }

    public static void setTaskTypes(Set<String> taskTypes) {
        RuntimeVariables.taskTypes = taskTypes;
    }

    public static Set<String> getOperatingSystems() {
        return operatingSystems;
    }

    public static void setOperatingSystems(Set<String> operatingSystems) {
        RuntimeVariables.operatingSystems = operatingSystems;
    }

    public static Set<String> getGroups() {
        return groups;
    }

    public static void setGroups(Set<String> groups) {
        RuntimeVariables.groups = groups;
    }

    public static Set<String> getWorkerTypes() {
        return workerTypes;
    }

    public static void setWorkerTypes(Set<String> workerTypes) {
        RuntimeVariables.workerTypes = workerTypes;
    }

    public static Map<String, Long> getWorkerTypeToIdMap() {
        return workerTypeToIdMap;
    }

    public static void setWorkerTypeToIdMap(Map<String, Long> workerTypeToIdMap) {
        RuntimeVariables.workerTypeToIdMap = workerTypeToIdMap;
    }

    public static Map<Long, String> getIdToWorkerTypeMap() {
        return IdToWorkerTypeMap;
    }

    public static void setIdToWorkerTypeMap(Map<Long, String> idToWorkerTypeMap) {
        IdToWorkerTypeMap = idToWorkerTypeMap;
    }

    public static Set<String> getVendor() {
        return vendor;
    }

    public static void setVendor(Set<String> vendor) {
        RuntimeVariables.vendor = vendor;
    }

    public static Map<String, Long> getVendorToIdMap() {
        return vendorToIdMap;
    }

    public static void setVendorToIdMap(Map<String, Long> vendorToIdMap) {
        RuntimeVariables.vendorToIdMap = vendorToIdMap;
    }

    public static Map<Long, String> getIdToVendor() {
        return IdToVendor;
    }

    public static void setIdToVendor(Map<Long, String> idToVendor) {
        IdToVendor = idToVendor;
    }

    public static Set<String> getExecutionControlTypes() {
        return executionControlTypes;
    }

    public static void setExecutionControlTypes(Set<String> executionControlTypes) {
        RuntimeVariables.executionControlTypes = executionControlTypes;
    }

    public static Map<String, Long> getExecutionControlTypeToIdMap() {
        return executionControlTypeToIdMap;
    }

    public static void setExecutionControlTypeToIdMap(Map<String, Long> executionControlTypeToIdMap) {
        RuntimeVariables.executionControlTypeToIdMap = executionControlTypeToIdMap;
    }

    public static Map<Long, String> getIdToExecutionControlTypeMap() {
        return IdToExecutionControlTypeMap;
    }

    public static void setIdToExecutionControlTypeMap(Map<Long, String> idToExecutionControlTypeMap) {
        IdToExecutionControlTypeMap = idToExecutionControlTypeMap;
    }
 
}
