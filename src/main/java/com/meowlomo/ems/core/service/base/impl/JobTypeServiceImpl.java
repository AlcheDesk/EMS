package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.JobTypeMapper;
import com.meowlomo.ems.core.model.JobType;
import com.meowlomo.ems.core.model.JobTypeExample;
import com.meowlomo.ems.core.service.base.JobTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class JobTypeServiceImpl implements JobTypeService {

    @Autowired
    private JobTypeMapper jobTypeMapper;

    @Override
    public long countByExample(JobTypeExample example) {
        return jobTypeMapper.countByExample(example);
    }

    @Override
    public long insert(JobType record) {
        return jobTypeMapper.insert(record);
    }

    @Override
    public long insertSelective(JobType record) {
        return jobTypeMapper.insertSelective(record);
    }

    @Override
    public int updateByExampleSelective(JobType record, JobTypeExample example) {
        return jobTypeMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(JobType record, JobTypeExample example) {
        return jobTypeMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(JobType record) {
        return jobTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(JobType record) {
        return jobTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<JobType> selectByExample(JobTypeExample example) {
        return jobTypeMapper.selectByExample(example);
    }

    @Override
    public List<JobType> selectByExampleWithRowbounds(JobTypeExample example, RowBounds rowBounds) {
        return jobTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public JobType selectByPrimaryKey(Long id) {
        return jobTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(JobTypeExample example) {
        return jobTypeMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return jobTypeMapper.deleteByPrimaryKey(id);
    }
}
