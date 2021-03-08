package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.WorkerLog;
import com.meowlomo.ems.core.model.WorkerLogExample;

public interface WorkerLogService {
    // mapper methods
    long countByExample(WorkerLogExample example);

    int deleteByExample(WorkerLogExample example);

    int insert(WorkerLog record);

    int insertSelective(WorkerLog record);

    List<WorkerLog> selectByExampleWithRowbounds(WorkerLogExample example, RowBounds rowBounds);

    List<WorkerLog> selectByExample(WorkerLogExample example);

    int updateByExampleSelective(WorkerLog record, WorkerLogExample example);

    int updateByExample(WorkerLog record, WorkerLogExample example);
}
