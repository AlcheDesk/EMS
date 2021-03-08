package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.JobType;
import com.meowlomo.ems.core.model.JobTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface JobTypeMapper {
    long countByExample(JobTypeExample example);

    int deleteByExample(JobTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(JobType record);

    int insertSelective(@Param("record") JobType record, @Param("selective") JobType.Column... selective);

    List<JobType> selectByExampleWithRowbounds(JobTypeExample example, RowBounds rowBounds);

    JobType selectOneByExample(JobTypeExample example);

    JobType selectOneByExampleSelective(@Param("example") JobTypeExample example,
            @Param("selective") JobType.Column... selective);

    List<JobType> selectByExampleSelective(@Param("example") JobTypeExample example,
            @Param("selective") JobType.Column... selective);

    List<JobType> selectByExample(JobTypeExample example);

    JobType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") JobType.Column... selective);

    JobType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") JobType record, @Param("example") JobTypeExample example,
            @Param("selective") JobType.Column... selective);

    int updateByExample(@Param("record") JobType record, @Param("example") JobTypeExample example);

    int updateByPrimaryKeySelective(@Param("record") JobType record, @Param("selective") JobType.Column... selective);

    int updateByPrimaryKey(JobType record);
}