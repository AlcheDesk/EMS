package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.JobType;
import com.meowlomo.ems.core.model.JobTypeExample;

public interface JobTypeService {
    // mapper methods
    long countByExample(JobTypeExample example);

    long insert(JobType record);

    long insertSelective(JobType record);

    int updateByExampleSelective(JobType record, JobTypeExample example);

    int updateByExample(JobType record, JobTypeExample example);

    int updateByPrimaryKeySelective(JobType record);

    int updateByPrimaryKey(JobType record);

    List<JobType> selectByExample(JobTypeExample example);

    List<JobType> selectByExampleWithRowbounds(JobTypeExample example, RowBounds rowBounds);

    JobType selectByPrimaryKey(Long id);

    int deleteByExample(JobTypeExample example);

    int deleteByPrimaryKey(Long id);
}
