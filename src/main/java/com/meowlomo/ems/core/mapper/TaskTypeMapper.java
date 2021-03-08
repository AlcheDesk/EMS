package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.TaskType;
import com.meowlomo.ems.core.model.TaskTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TaskTypeMapper {
    long countByExample(TaskTypeExample example);

    int deleteByExample(TaskTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskType record);

    int insertSelective(@Param("record") TaskType record, @Param("selective") TaskType.Column... selective);

    List<TaskType> selectByExampleWithRowbounds(TaskTypeExample example, RowBounds rowBounds);

    TaskType selectOneByExample(TaskTypeExample example);

    TaskType selectOneByExampleSelective(@Param("example") TaskTypeExample example,
            @Param("selective") TaskType.Column... selective);

    List<TaskType> selectByExampleSelective(@Param("example") TaskTypeExample example,
            @Param("selective") TaskType.Column... selective);

    List<TaskType> selectByExample(TaskTypeExample example);

    TaskType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TaskType.Column... selective);

    TaskType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TaskType record, @Param("example") TaskTypeExample example,
            @Param("selective") TaskType.Column... selective);

    int updateByExample(@Param("record") TaskType record, @Param("example") TaskTypeExample example);

    int updateByPrimaryKeySelective(@Param("record") TaskType record, @Param("selective") TaskType.Column... selective);

    int updateByPrimaryKey(TaskType record);
}