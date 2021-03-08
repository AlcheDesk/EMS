package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.ExecutionControlTypeMapper;
import com.meowlomo.ems.core.model.ExecutionControlType;
import com.meowlomo.ems.core.model.ExecutionControlType.Column;
import com.meowlomo.ems.core.model.ExecutionControlTypeExample;
import com.meowlomo.ems.core.service.base.ExecutionControlTypeService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ExecutionControlTypeServiceImpl implements ExecutionControlTypeService {

    @Autowired
    private ExecutionControlTypeMapper executionControlTypeMapper;

    @Override
    public long countByExample(ExecutionControlTypeExample example) {
        return executionControlTypeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ExecutionControlTypeExample example) {
        return executionControlTypeMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return executionControlTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ExecutionControlType record) {
        return executionControlTypeMapper.insert(record);
    }

    @Override
    public int insertSelective(ExecutionControlType record, Column... selective) {
        return executionControlTypeMapper.insertSelective(record, selective);
    }

    @Override
    public List<ExecutionControlType> selectByExampleWithRowbounds(ExecutionControlTypeExample example,
            RowBounds rowBounds) {
        return executionControlTypeMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public ExecutionControlType selectOneByExample(ExecutionControlTypeExample example) {
        return executionControlTypeMapper.selectOneByExample(example);
    }

    @Override
    public ExecutionControlType selectOneByExampleSelective(ExecutionControlTypeExample example, Column... selective) {
        return executionControlTypeMapper.selectOneByExampleSelective(example, selective);
    }

    @Override
    public List<ExecutionControlType> selectByExampleSelective(ExecutionControlTypeExample example,
            Column... selective) {
        return executionControlTypeMapper.selectByExampleSelective(example, selective);
    }

    @Override
    public List<ExecutionControlType> selectByExample(ExecutionControlTypeExample example) {
        return executionControlTypeMapper.selectByExample(example);
    }

    @Override
    public ExecutionControlType selectByPrimaryKeySelective(Long id, Column... selective) {
        return executionControlTypeMapper.selectByPrimaryKeySelective(id, selective);
    }

    @Override
    public ExecutionControlType selectByPrimaryKey(Long id) {
        return executionControlTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(ExecutionControlType record, ExecutionControlTypeExample example,
            Column... selective) {
        return executionControlTypeMapper.updateByExampleSelective(record, example, selective);
    }

    @Override
    public int updateByExample(ExecutionControlType record, ExecutionControlTypeExample example) {
        return executionControlTypeMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(ExecutionControlType record, Column... selective) {
        return executionControlTypeMapper.updateByPrimaryKeySelective(record, selective);
    }

    @Override
    public int updateByPrimaryKey(ExecutionControlType record) {
        return executionControlTypeMapper.updateByPrimaryKey(record);
    }


}
