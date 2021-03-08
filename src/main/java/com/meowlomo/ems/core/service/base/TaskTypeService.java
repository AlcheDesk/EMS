package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.TaskType;
import com.meowlomo.ems.core.model.TaskTypeExample;

public interface TaskTypeService {
    // mapper methods
    long countByExample(TaskTypeExample example);

    long insert(TaskType record);

    long insertSelective(TaskType record);

    int updateByExampleSelective(TaskType record, TaskTypeExample example);

    int updateByExample(TaskType record, TaskTypeExample example);

    int updateByPrimaryKeySelective(TaskType record);

    int updateByPrimaryKey(TaskType record);

    List<TaskType> selectByExample(TaskTypeExample example);

    List<TaskType> selectByExampleWithRowbounds(TaskTypeExample example, RowBounds rowBounds);

    TaskType selectByPrimaryKey(Long id);

    int deleteByExample(TaskTypeExample example);

    int deleteByPrimaryKey(Long id);
}
