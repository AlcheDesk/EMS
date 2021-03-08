package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.WorkerLogMapper;
import com.meowlomo.ems.core.model.WorkerLog;
import com.meowlomo.ems.core.model.WorkerLogExample;
import com.meowlomo.ems.core.service.base.WorkerLogService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkerLogServiceImpl implements WorkerLogService {

    @Autowired
    private  WorkerLogMapper jobLogMapper;
    
    @Override
    public long countByExample(WorkerLogExample example) {
        return jobLogMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(WorkerLogExample example) {
        return jobLogMapper.deleteByExample(example);
    }

    @Override
    public int insert(WorkerLog record) {
        return jobLogMapper.insert(record);
    }

    @Override
    public int insertSelective(WorkerLog record) {
        return jobLogMapper.insertSelective(record);
    }

    @Override
    public List<WorkerLog> selectByExampleWithRowbounds(WorkerLogExample example, RowBounds rowBounds) {
        return jobLogMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public List<WorkerLog> selectByExample(WorkerLogExample example) {
        return jobLogMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(WorkerLog record, WorkerLogExample example) {
        return jobLogMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(WorkerLog record, WorkerLogExample example) {
        return jobLogMapper.updateByExample(record, example);
    }

}
