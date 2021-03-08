package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.WorkerType;
import com.meowlomo.ems.core.model.WorkerTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WorkerTypeMapper {
    long countByExample(WorkerTypeExample example);

    int deleteByExample(WorkerTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WorkerType record);

    int insertSelective(@Param("record") WorkerType record, @Param("selective") WorkerType.Column... selective);

    List<WorkerType> selectByExampleWithRowbounds(WorkerTypeExample example, RowBounds rowBounds);

    WorkerType selectOneByExample(WorkerTypeExample example);

    WorkerType selectOneByExampleSelective(@Param("example") WorkerTypeExample example,
            @Param("selective") WorkerType.Column... selective);

    List<WorkerType> selectByExampleSelective(@Param("example") WorkerTypeExample example,
            @Param("selective") WorkerType.Column... selective);

    List<WorkerType> selectByExample(WorkerTypeExample example);

    WorkerType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") WorkerType.Column... selective);

    WorkerType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WorkerType record, @Param("example") WorkerTypeExample example,
            @Param("selective") WorkerType.Column... selective);

    int updateByExample(@Param("record") WorkerType record, @Param("example") WorkerTypeExample example);

    int updateByPrimaryKeySelective(@Param("record") WorkerType record,
            @Param("selective") WorkerType.Column... selective);

    int updateByPrimaryKey(WorkerType record);
}