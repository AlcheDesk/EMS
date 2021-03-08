package com.meowlomo.ems.core.service.custom;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.WorkerExample;
import com.meowlomo.ems.core.model.custom.WorkerStatus;

public interface WorkerStatusService {
    // mapper methods
    long countByExample(WorkerExample example);

    List<WorkerStatus> selectByExample(WorkerExample example);

    List<WorkerStatus> selectByExampleWithRowbounds(WorkerExample example, RowBounds rowBounds);

    WorkerStatus selectByUuid(UUID workerUuid);
}
