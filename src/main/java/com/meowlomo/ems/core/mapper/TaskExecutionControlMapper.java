package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.TaskExecutionControl;
import com.meowlomo.ems.core.model.TaskExecutionControlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TaskExecutionControlMapper {
    long countByExample(TaskExecutionControlExample example);

    int deleteByExample(TaskExecutionControlExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskExecutionControl record);

    int insertSelective(@Param("record") TaskExecutionControl record,
            @Param("selective") TaskExecutionControl.Column... selective);

    List<TaskExecutionControl> selectByExampleWithRowbounds(TaskExecutionControlExample example, RowBounds rowBounds);

    TaskExecutionControl selectOneByExample(TaskExecutionControlExample example);

    TaskExecutionControl selectOneByExampleSelective(@Param("example") TaskExecutionControlExample example,
            @Param("selective") TaskExecutionControl.Column... selective);

    List<TaskExecutionControl> selectByExampleSelective(@Param("example") TaskExecutionControlExample example,
            @Param("selective") TaskExecutionControl.Column... selective);

    List<TaskExecutionControl> selectByExample(TaskExecutionControlExample example);

    TaskExecutionControl selectByPrimaryKeySelective(@Param("id") Long id,
            @Param("selective") TaskExecutionControl.Column... selective);

    TaskExecutionControl selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TaskExecutionControl record,
            @Param("example") TaskExecutionControlExample example,
            @Param("selective") TaskExecutionControl.Column... selective);

    int updateByExample(@Param("record") TaskExecutionControl record,
            @Param("example") TaskExecutionControlExample example);

    int updateByPrimaryKeySelective(@Param("record") TaskExecutionControl record,
            @Param("selective") TaskExecutionControl.Column... selective);

    int updateByPrimaryKey(TaskExecutionControl record);
}