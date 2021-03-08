package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.JobLogMapper;
import com.meowlomo.ems.core.model.JobLog;
import com.meowlomo.ems.core.model.JobLogExample;
import com.meowlomo.ems.core.service.base.JobLogService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class JobLogServiceImpl implements JobLogService {

    @Autowired
    private  JobLogMapper jobLogMapper;
    
    @Override
    public long countByExample(JobLogExample example) {
        return jobLogMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(JobLogExample example) {
        return jobLogMapper.deleteByExample(example);
    }

    @Override
    public int insert(JobLog record) {
        return jobLogMapper.insert(record);
    }

    @Override
    public int insertSelective(JobLog record) {
        return jobLogMapper.insertSelective(record);
    }

    @Override
    public List<JobLog> selectByExampleWithRowbounds(JobLogExample example, RowBounds rowBounds) {
        return jobLogMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public List<JobLog> selectByExample(JobLogExample example) {
        return jobLogMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(JobLog record, JobLogExample example) {
        return jobLogMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(JobLog record, JobLogExample example) {
        return jobLogMapper.updateByExample(record, example);
    }

}
