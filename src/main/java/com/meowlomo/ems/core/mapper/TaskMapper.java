package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TaskMapper {
    long countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Task record);

    int insertSelective(@Param("record") Task record, @Param("selective") Task.Column... selective);

    List<Task> selectByExampleWithRowbounds(TaskExample example, RowBounds rowBounds);

    Task selectOneByExample(TaskExample example);

    Task selectOneByExampleSelective(@Param("example") TaskExample example,
            @Param("selective") Task.Column... selective);

    List<Task> selectByExampleSelective(@Param("example") TaskExample example,
            @Param("selective") Task.Column... selective);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Task.Column... selective);

    Task selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example,
            @Param("selective") Task.Column... selective);

    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(@Param("record") Task record, @Param("selective") Task.Column... selective);

    int updateByPrimaryKey(Task record);
}