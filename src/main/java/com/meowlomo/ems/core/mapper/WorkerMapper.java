package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WorkerMapper {
    long countByExample(WorkerExample example);

    int deleteByExample(WorkerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Worker record);

    int insertSelective(@Param("record") Worker record, @Param("selective") Worker.Column... selective);

    List<Worker> selectByExampleWithRowbounds(WorkerExample example, RowBounds rowBounds);

    Worker selectOneByExample(WorkerExample example);

    Worker selectOneByExampleSelective(@Param("example") WorkerExample example,
            @Param("selective") Worker.Column... selective);

    List<Worker> selectByExampleSelective(@Param("example") WorkerExample example,
            @Param("selective") Worker.Column... selective);

    List<Worker> selectByExample(WorkerExample example);

    Worker selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Worker.Column... selective);

    Worker selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Worker record, @Param("example") WorkerExample example,
            @Param("selective") Worker.Column... selective);

    int updateByExample(@Param("record") Worker record, @Param("example") WorkerExample example);

    int updateByPrimaryKeySelective(@Param("record") Worker record, @Param("selective") Worker.Column... selective);

    int updateByPrimaryKey(Worker record);
}