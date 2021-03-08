package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.TaskLog;
import com.meowlomo.ems.core.model.TaskLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TaskLogMapper {
    long countByExample(TaskLogExample example);

    int deleteByExample(TaskLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskLog record);

    int insertSelective(@Param("record") TaskLog record, @Param("selective") TaskLog.Column... selective);

    List<TaskLog> selectByExampleWithRowbounds(TaskLogExample example, RowBounds rowBounds);

    TaskLog selectOneByExample(TaskLogExample example);

    TaskLog selectOneByExampleSelective(@Param("example") TaskLogExample example,
            @Param("selective") TaskLog.Column... selective);

    List<TaskLog> selectByExampleSelective(@Param("example") TaskLogExample example,
            @Param("selective") TaskLog.Column... selective);

    List<TaskLog> selectByExample(TaskLogExample example);

    TaskLog selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TaskLog.Column... selective);

    TaskLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TaskLog record, @Param("example") TaskLogExample example,
            @Param("selective") TaskLog.Column... selective);

    int updateByExample(@Param("record") TaskLog record, @Param("example") TaskLogExample example);

    int updateByPrimaryKeySelective(@Param("record") TaskLog record, @Param("selective") TaskLog.Column... selective);

    int updateByPrimaryKey(TaskLog record);
}