package com.meowlomo.ems.core.service.base.impl;

import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.WorkerMapper;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;
import com.meowlomo.ems.core.service.base.WorkerService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public long countByExample(WorkerExample example) {
        return workerMapper.countByExample(example);
    }

    @Override
    public long insert(Worker record) {
        return workerMapper.insert(record);
    }

    @Override
    public long insertSelective(Worker record) {
        return workerMapper.insertSelective(record);
    }

    @Override
    public int updateByExampleSelective(Worker record, WorkerExample example) {
        return workerMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Worker record, WorkerExample example) {
        return workerMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Worker record) {
        return workerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Worker record) {
        return workerMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Worker> selectByExample(WorkerExample example) {
        return workerMapper.selectByExample(example);
    }

    @Override
    public List<Worker> selectByExampleWithRowbounds(WorkerExample example, RowBounds rowBounds) {
        return workerMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public Worker selectByPrimaryKey(Long id) {
        return workerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(WorkerExample example) {
        return workerMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return workerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Worker selectByUuid(UUID workerUuid) {
        WorkerExample example = new WorkerExample();
        example.createCriteria().andUuidEqualTo(workerUuid);
        return workerMapper.selectByExample(example).get(0);
    }

}
