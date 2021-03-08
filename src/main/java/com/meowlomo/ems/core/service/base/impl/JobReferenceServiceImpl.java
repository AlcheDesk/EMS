package com.meowlomo.ems.core.service.base.impl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.JobReferenceMapper;
import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.JobExample;
import com.meowlomo.ems.core.service.base.JobReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class JobReferenceServiceImpl implements JobReferenceService {

    @Autowired
    private JobReferenceMapper jobReferenceMapper;

    @Override
    public long countByExample(JobExample example) {
        return jobReferenceMapper.countByExample(example);
    }

    @Override
    public long insert(Job record) {
        return jobReferenceMapper.insert(record);
    }

    @Override
    public long insertSelective(Job record) {
        return jobReferenceMapper.insertSelective(record);
    }

    @Override
    public int updateByExampleSelective(Job record, JobExample example) {
        return jobReferenceMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Job record, JobExample example) {
        return jobReferenceMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Job record) {
        return jobReferenceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Job record) {
        return jobReferenceMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Job> selectByExample(JobExample example) {
        return jobReferenceMapper.selectByExample(example);
    }

    @Override
    public List<Job> selectByExampleWithRowbounds(JobExample example, RowBounds rowBounds) {
        return jobReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public Job selectByPrimaryKey(Long id) {
        return jobReferenceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(JobExample example) {
        return jobReferenceMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return jobReferenceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Job selectByUuid(UUID jobUuid) {
        JobExample example = new JobExample();
        example.createCriteria().andUuidEqualTo(jobUuid);
        return jobReferenceMapper.selectByExample(example).get(0);
    }

}
