package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.WorkerReferenceMapper;
import com.meowlomo.ems.core.model.Worker;
import com.meowlomo.ems.core.model.WorkerExample;
import com.meowlomo.ems.core.service.base.WorkerReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkerReferenceServiceImpl implements WorkerReferenceService {
    @Autowired
    private WorkerReferenceMapper workerReferenceMapper;

    @Override
    public long countByExample(WorkerExample example) {
        return workerReferenceMapper.countByExample(example);
    }

    @Override
    public List<Worker> selectByExample(WorkerExample example) {
        return workerReferenceMapper.selectByExample(example);
    }

    @Override
    public List<Worker> selectByExampleWithRowbounds(WorkerExample example, RowBounds rowBounds) {
        return workerReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public Worker selectByPrimaryKey(Long id) {
        return workerReferenceMapper.selectByPrimaryKey(id);
    }
}
