package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.WorkerLog;
import com.meowlomo.ems.core.model.WorkerLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WorkerLogMapper {
    long countByExample(WorkerLogExample example);

    int deleteByExample(WorkerLogExample example);

    int insert(WorkerLog record);

    int insertSelective(@Param("record") WorkerLog record, @Param("selective") WorkerLog.Column... selective);

    List<WorkerLog> selectByExampleWithRowbounds(WorkerLogExample example, RowBounds rowBounds);

    WorkerLog selectOneByExample(WorkerLogExample example);

    WorkerLog selectOneByExampleSelective(@Param("example") WorkerLogExample example,
            @Param("selective") WorkerLog.Column... selective);

    List<WorkerLog> selectByExampleSelective(@Param("example") WorkerLogExample example,
            @Param("selective") WorkerLog.Column... selective);

    List<WorkerLog> selectByExample(WorkerLogExample example);

    int updateByExampleSelective(@Param("record") WorkerLog record, @Param("example") WorkerLogExample example,
            @Param("selective") WorkerLog.Column... selective);

    int updateByExample(@Param("record") WorkerLog record, @Param("example") WorkerLogExample example);
}