package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.TaskExecutionControlMapper;
import com.meowlomo.ems.core.model.TaskExecutionControl;
import com.meowlomo.ems.core.model.TaskExecutionControl.Column;
import com.meowlomo.ems.core.model.TaskExecutionControlExample;
import com.meowlomo.ems.core.service.base.TaskExecutionControlService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TaskExecutionControlServiceImpl implements TaskExecutionControlService {

    @Autowired
    private TaskExecutionControlMapper taskExecutionControlMapper;

    @Override
    public long countByExample(TaskExecutionControlExample example) {
        return taskExecutionControlMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TaskExecutionControlExample example) {
        return taskExecutionControlMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return taskExecutionControlMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TaskExecutionControl record) {
        return taskExecutionControlMapper.insert(record);
    }

    @Override
    public int insertSelective(TaskExecutionControl record, Column... selective) {
        return taskExecutionControlMapper.insertSelective(record, selective);
    }

    @Override
    public List<TaskExecutionControl> selectByExampleWithRowbounds(TaskExecutionControlExample example,
            RowBounds rowBounds) {
        return taskExecutionControlMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public TaskExecutionControl selectOneByExample(TaskExecutionControlExample example) {
        return taskExecutionControlMapper.selectOneByExample(example);
    }

    @Override
    public TaskExecutionControl selectOneByExampleSelective(TaskExecutionControlExample example, Column... selective) {
        return taskExecutionControlMapper.selectOneByExampleSelective(example, selective);
    }

    @Override
    public List<TaskExecutionControl> selectByExampleSelective(TaskExecutionControlExample example,
            Column... selective) {
        return taskExecutionControlMapper.selectByExampleSelective(example, selective);
    }

    @Override
    public List<TaskExecutionControl> selectByExample(TaskExecutionControlExample example) {
        return taskExecutionControlMapper.selectByExample(example);
    }

    @Override
    public TaskExecutionControl selectByPrimaryKeySelective(Long id, Column... selective) {
        return taskExecutionControlMapper.selectByPrimaryKeySelective(id, selective);
    }

    @Override
    public TaskExecutionControl selectByPrimaryKey(Long id) {
        return taskExecutionControlMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(TaskExecutionControl record, TaskExecutionControlExample example,
            Column... selective) {
        return taskExecutionControlMapper.updateByExampleSelective(record, example, selective);
    }

    @Override
    public int updateByExample(TaskExecutionControl record, TaskExecutionControlExample example) {
        return taskExecutionControlMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(TaskExecutionControl record, Column... selective) {
        return taskExecutionControlMapper.updateByPrimaryKeySelective(record, selective);
    }

    @Override
    public int updateByPrimaryKey(TaskExecutionControl record) {
        return taskExecutionControlMapper.updateByPrimaryKey(record);
    }


}
