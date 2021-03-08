package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.JobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface JobMapper {
    long countByExample(JobExample example);

    int deleteByExample(JobExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Job record);

    int insertSelective(@Param("record") Job record, @Param("selective") Job.Column... selective);

    List<Job> selectByExampleWithRowbounds(JobExample example, RowBounds rowBounds);

    Job selectOneByExample(JobExample example);

    Job selectOneByExampleSelective(@Param("example") JobExample example, @Param("selective") Job.Column... selective);

    List<Job> selectByExampleSelective(@Param("example") JobExample example,
            @Param("selective") Job.Column... selective);

    List<Job> selectByExample(JobExample example);

    Job selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Job.Column... selective);

    Job selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Job record, @Param("example") JobExample example,
            @Param("selective") Job.Column... selective);

    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    int updateByPrimaryKeySelective(@Param("record") Job record, @Param("selective") Job.Column... selective);

    int updateByPrimaryKey(Job record);
}