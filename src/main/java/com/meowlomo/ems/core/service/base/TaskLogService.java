package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.TaskLog;
import com.meowlomo.ems.core.model.TaskLogExample;

public interface TaskLogService {
    // mapper methods
    long countByExample(TaskLogExample example);

    int deleteByExample(TaskLogExample example);

    int insert(TaskLog record);

    int insertSelective(TaskLog record);

    List<TaskLog> selectByExampleWithRowbounds(TaskLogExample example, RowBounds rowBounds);

    List<TaskLog> selectByExample(TaskLogExample example);

    int updateByExampleSelective(TaskLog record, TaskLogExample example);

    int updateByExample(TaskLog record, TaskLogExample example);
}
