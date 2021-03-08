package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.WorkerType;
import com.meowlomo.ems.core.model.WorkerTypeExample;

public interface WorkerTypeService {
    // mapper methods
    long countByExample(WorkerTypeExample example);

    long insert(WorkerType record);

    long insertSelective(WorkerType record);

    int updateByExampleSelective(WorkerType record, WorkerTypeExample example);

    int updateByExample(WorkerType record, WorkerTypeExample example);

    int updateByPrimaryKeySelective(WorkerType record);

    int updateByPrimaryKey(WorkerType record);

    List<WorkerType> selectByExample(WorkerTypeExample example);

    List<WorkerType> selectByExampleWithRowbounds(WorkerTypeExample example, RowBounds rowBounds);

    WorkerType selectByPrimaryKey(Long id);

    int deleteByExample(WorkerTypeExample example);

    int deleteByPrimaryKey(Long id);
}
