package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.WorkerTypeMapper;
import com.meowlomo.ems.core.model.WorkerType;
import com.meowlomo.ems.core.model.WorkerTypeExample;
import com.meowlomo.ems.core.service.base.WorkerTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkerTypeServiceImpl implements WorkerTypeService {
    @Autowired
    private WorkerTypeMapper workerTypeMapper;

    @Override
    public long countByExample(WorkerTypeExample example) {
        return workerTypeMapper.countByExample(example);
    }

    @Override
    public long insert(WorkerType record) {
        return workerTypeMapper.insert(record);
    }

    @Override
    public long insertSelective(WorkerType record) {
        return workerTypeMapper.insertSelective(record);
    }

    @Override
    public int updateByExampleSelective(WorkerType record, WorkerTypeExample example) {
        return workerTypeMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(WorkerType record, WorkerTypeExample example) {
        return workerTypeMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(WorkerType record) {
        return workerTypeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WorkerType record) {
        return workerTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<WorkerType> selectByExample(WorkerTypeExample example) {
        return workerTypeMapper.selectByExample(example);
    }

    @Override
    public List<WorkerType> selectByExampleWithRowbounds(WorkerTypeExample example, RowBounds rowBounds) {
        return workerTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public WorkerType selectByPrimaryKey(Long id) {
        return workerTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(WorkerTypeExample example) {
        return workerTypeMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return workerTypeMapper.deleteByPrimaryKey(id);
    }
}
