package com.meowlomo.ems.core.mapper;

import java.util.List;

import com.meowlomo.ems.core.model.Worker;

public interface WorkerControlMapper {

    int setWorkerToWorkingFromFreeWithTaskIdForAssignment(Worker record);

    int setWorkerToResevedFromFreeForAssignment(Worker record);

    int setWorkerToFreeFromDownByPrimaryKey(Worker record);

    int rollbackWorkerToFreeFromReservedForAssignment(Worker record);

    int setWorkerToDownFromReservedForFailedAssignment(Worker record);

    int rollbackWorkerToFreeFromWorkingFromAssignment(Worker record);

    List<Worker> selectAbnormalWorkers();
}
