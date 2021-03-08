package com.meowlomo.ems.core.service.base;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExample;

public interface TaskService {
    // mapper methods
    long countByExample(TaskExample example);

    long insert(Task record);

    long insertSelective(Task record);

    int updateByExampleSelective(Task record, TaskExample example);

    int updateByExample(Task record, TaskExample example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    List<Task> selectByExample(TaskExample example);

    List<Task> selectByExampleWithRowbounds(TaskExample example, RowBounds rowBounds);

    Task selectByPrimaryKey(Long id);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(Long id);

    // non mapper methods
    Task selectByUuid(UUID taskUuid);
//
//    void updateLinkedWorkerStatus(Task record);
}
