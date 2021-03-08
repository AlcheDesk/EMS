package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.TaskExecutionControl;
import com.meowlomo.ems.core.model.TaskExecutionControlExample;

public interface TaskExecutionControlService {
    // mapper methods
    long countByExample(TaskExecutionControlExample example);

    int deleteByExample(TaskExecutionControlExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskExecutionControl record);

    int insertSelective(TaskExecutionControl record, TaskExecutionControl.Column... selective);

    List<TaskExecutionControl> selectByExampleWithRowbounds(TaskExecutionControlExample example, RowBounds rowBounds);

    TaskExecutionControl selectOneByExample(TaskExecutionControlExample example);

    TaskExecutionControl selectOneByExampleSelective(TaskExecutionControlExample example, TaskExecutionControl.Column... selective);

    List<TaskExecutionControl> selectByExampleSelective(TaskExecutionControlExample example, TaskExecutionControl.Column... selective);

    List<TaskExecutionControl> selectByExample(TaskExecutionControlExample example);

    TaskExecutionControl selectByPrimaryKeySelective(Long id, TaskExecutionControl.Column... selective);

    TaskExecutionControl selectByPrimaryKey(Long id);

    int updateByExampleSelective(TaskExecutionControl record, TaskExecutionControlExample example, TaskExecutionControl.Column... selective);

    int updateByExample(TaskExecutionControl record, TaskExecutionControlExample example);

    int updateByPrimaryKeySelective(TaskExecutionControl record, TaskExecutionControl.Column... selective);

    int updateByPrimaryKey(TaskExecutionControl record);
}
