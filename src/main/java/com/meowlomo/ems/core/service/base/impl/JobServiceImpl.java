package com.meowlomo.ems.core.service.base.impl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.JobMapper;
import com.meowlomo.ems.core.model.Job;
import com.meowlomo.ems.core.model.JobExample;
import com.meowlomo.ems.core.service.base.JobService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public long countByExample(JobExample example) {
        return jobMapper.countByExample(example);
    }

    @Override
    public long insert(Job record) {
        return jobMapper.insert(record);
    }

    @Override
    public long insertSelective(Job record) {
        return jobMapper.insertSelective(record);
    }

    @Override
    public int updateByExampleSelective(Job record, JobExample example) {
        return jobMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Job record, JobExample example) {
        return jobMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Job record) {
        return jobMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Job record) {
        return jobMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Job> selectByExample(JobExample example) {
        return jobMapper.selectByExample(example);
    }

    @Override
    public List<Job> selectByExampleWithRowbounds(JobExample example, RowBounds rowBounds) {
        return jobMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public Job selectByPrimaryKey(Long id) {
        return jobMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(JobExample example) {
        return jobMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return jobMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Job selectByUuid(UUID jobUuid) {
        JobExample example = new JobExample();
        example.createCriteria().andUuidEqualTo(jobUuid);
        return jobMapper.selectByExample(example).get(0);
    }

}
