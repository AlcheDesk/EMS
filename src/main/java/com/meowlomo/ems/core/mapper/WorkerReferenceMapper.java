package com.meowlomo.ems.core.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;

public interface WorkerReferenceMapper {
    long countByExample(WorkerExample example);

    List<Worker> selectByExampleWithRowbounds(WorkerExample example, RowBounds rowBounds);

    List<Worker> selectByExample(WorkerExample example);

    Worker selectByPrimaryKey(Long id);
}