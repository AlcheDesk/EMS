package com.meowlomo.ems.core.service.base;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.JobExample;

public interface JobReferenceService {
    // mapper methods
    long countByExample(JobExample example);

    long insert(Job record);

    long insertSelective(Job record);

    int updateByExampleSelective(Job record, JobExample example);

    int updateByExample(Job record, JobExample example);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

    List<Job> selectByExample(JobExample example);

    List<Job> selectByExampleWithRowbounds(JobExample example, RowBounds rowBounds);

    Job selectByPrimaryKey(Long id);

    int deleteByExample(JobExample example);

    int deleteByPrimaryKey(Long id);

    // non mapper methods
    Job selectByUuid(UUID jobUuid);
}
