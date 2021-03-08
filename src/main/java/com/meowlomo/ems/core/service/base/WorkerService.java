package com.meowlomo.ems.core.service.base;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;

public interface WorkerService {
    // mapper methods
    long countByExample(WorkerExample example);

    long insert(Worker record);

    long insertSelective(Worker record);

    int updateByExampleSelective(Worker record, WorkerExample example);

    int updateByExample(Worker record, WorkerExample example);

    int updateByPrimaryKeySelective(Worker record);

    int updateByPrimaryKey(Worker record);

    List<Worker> selectByExample(WorkerExample example);

    List<Worker> selectByExampleWithRowbounds(WorkerExample example, RowBounds rowBounds);

    Worker selectByPrimaryKey(Long id);

    int deleteByExample(WorkerExample example);

    int deleteByPrimaryKey(Long id);

    // non mapper methods
    Worker selectByUuid(UUID workerUuid);
}
