package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.TaskLogMapper;
import com.meowlomo.ems.core.model.TaskLog;
import com.meowlomo.ems.core.model.TaskLogExample;
import com.meowlomo.ems.core.service.base.TaskLogService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TaskLogServiceImpl implements TaskLogService {

    @Autowired
    private  TaskLogMapper jobLogMapper;
    
    @Override
    public long countByExample(TaskLogExample example) {
        return jobLogMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TaskLogExample example) {
        return jobLogMapper.deleteByExample(example);
    }

    @Override
    public int insert(TaskLog record) {
        return jobLogMapper.insert(record);
    }

    @Override
    public int insertSelective(TaskLog record) {
        return jobLogMapper.insertSelective(record);
    }

    @Override
    public List<TaskLog> selectByExampleWithRowbounds(TaskLogExample example, RowBounds rowBounds) {
        return jobLogMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public List<TaskLog> selectByExample(TaskLogExample example) {
        return jobLogMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(TaskLog record, TaskLogExample example) {
        return jobLogMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(TaskLog record, TaskLogExample example) {
        return jobLogMapper.updateByExample(record, example);
    }

}
