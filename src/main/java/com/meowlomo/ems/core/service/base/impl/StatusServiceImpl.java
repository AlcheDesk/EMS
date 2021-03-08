package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.StatusMapper;
import com.meowlomo.ems.core.model.Status;
import com.meowlomo.ems.core.model.StatusExample;
import com.meowlomo.ems.core.service.base.StatusService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusMapper statusMapper;

    @Override
    public long countByExample(StatusExample example) {
        return statusMapper.countByExample(example);
    }

    @Override
    public long insert(Status record) {
        return statusMapper.insert(record);
    }

    @Override
    public long insertSelective(Status record) {
        return statusMapper.insertSelective(record);
    }

    @Override
    public int updateByExampleSelective(Status record, StatusExample example) {
        return statusMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Status record, StatusExample example) {
        return statusMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Status record) {
        return statusMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Status record) {
        return statusMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Status> selectByExample(StatusExample example) {
        return statusMapper.selectByExample(example);
    }

    @Override
    public List<Status> selectByExampleWithRowbounds(StatusExample example, RowBounds rowBounds) {
        return statusMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public Status selectByPrimaryKey(Long id) {
        return statusMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(StatusExample example) {
        return statusMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return statusMapper.deleteByPrimaryKey(id);
    }
}
