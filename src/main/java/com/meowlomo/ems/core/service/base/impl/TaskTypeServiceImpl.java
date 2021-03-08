package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.TaskTypeMapper;
import com.meowlomo.ems.core.model.TaskType;
import com.meowlomo.ems.core.model.TaskTypeExample;
import com.meowlomo.ems.core.service.base.TaskTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TaskTypeServiceImpl implements TaskTypeService {

    @Autowired
    private TaskTypeMapper taskTypeMapper;

    @Override
    public long countByExample(TaskTypeExample example) {
        return taskTypeMapper.countByExample(example);
    }

    @Override
    public long insert(TaskType record) {
        return taskTypeMapper.insert(record);
    }

    @Override
    public long insertSelective(TaskType record) {
        return taskTypeMapper.insertSelective(record);
    }

    @Override
    public int updateByExampleSelective(TaskType record, TaskTypeExample example) {
        return taskTypeMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(TaskType record, TaskTypeExample example) {
        return taskTypeMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(TaskType record) {
        return taskTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TaskType record) {
        return taskTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TaskType> selectByExample(TaskTypeExample example) {
        return taskTypeMapper.selectByExample(example);
    }

    @Override
    public List<TaskType> selectByExampleWithRowbounds(TaskTypeExample example, RowBounds rowBounds) {
        return taskTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public TaskType selectByPrimaryKey(Long id) {
        return taskTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(TaskTypeExample example) {
        return taskTypeMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return taskTypeMapper.deleteByPrimaryKey(id);
    }
}
