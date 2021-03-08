package com.meowlomo.ems.core.service.base.impl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.TaskReferenceMapper;
import com.meowlomo.ems.core.model.Task;
import com.meowlomo.ems.core.model.TaskExample;
import com.meowlomo.ems.core.service.base.TaskReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TaskReferenceServiceImpl implements TaskReferenceService {

    @Autowired
    private TaskReferenceMapper taskReferenceMapper;

    @Override
    public long countByExample(TaskExample example) {
        return taskReferenceMapper.countByExample(example);
    }

    @Override
    public long insert(Task record) {
        return taskReferenceMapper.insert(record);
    }

    @Override
    public long insertSelective(Task record) {
        return taskReferenceMapper.insertSelective(record);
    }

    @Override
    public int updateByExampleSelective(Task record, TaskExample example) {
        return taskReferenceMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Task record, TaskExample example) {
        return taskReferenceMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Task record) {
        return taskReferenceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Task record) {
        return taskReferenceMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Task> selectByExample(TaskExample example) {
        return taskReferenceMapper.selectByExample(example);
    }

    @Override
    public List<Task> selectByExampleWithRowbounds(TaskExample example, RowBounds rowBounds) {
        return taskReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public Task selectByPrimaryKey(Long id) {
        return taskReferenceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(TaskExample example) {
        return taskReferenceMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return taskReferenceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Task selectByUuid(UUID taskUuid) {
        TaskExample example = new TaskExample();
        example.createCriteria().andUuidEqualTo(taskUuid);
        return taskReferenceMapper.selectByExample(example).get(0);
    }

}
