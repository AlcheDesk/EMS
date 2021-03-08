package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;

public interface WorkerReferenceService {
    // mapper methods
    long countByExample(WorkerExample example);

    List<Worker> selectByExample(WorkerExample example);

    List<Worker> selectByExampleWithRowbounds(WorkerExample example, RowBounds rowBounds);

    Worker selectByPrimaryKey(Long id);
}
