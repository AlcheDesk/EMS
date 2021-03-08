package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.OperatingSystemMapper;
import com.meowlomo.ems.core.model.OperatingSystem;
import com.meowlomo.ems.core.model.OperatingSystemExample;
import com.meowlomo.ems.core.service.base.OperatingSystemService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class OperatingSystemServiceImpl implements OperatingSystemService {

    @Autowired
    private OperatingSystemMapper operatingSystemServiceMapper;

    @Override
    public long countByExample(OperatingSystemExample example) {
        return operatingSystemServiceMapper.countByExample(example);
    }

    @Override
    public long insert(OperatingSystem record) {
        return operatingSystemServiceMapper.insert(record);
    }

    @Override
    public long insertSelective(OperatingSystem record) {
        return operatingSystemServiceMapper.insertSelective(record);
    }

    @Override
    public int updateByExampleSelective(OperatingSystem record, OperatingSystemExample example) {
        return operatingSystemServiceMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(OperatingSystem record, OperatingSystemExample example) {
        return operatingSystemServiceMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(OperatingSystem record) {
        return operatingSystemServiceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OperatingSystem record) {
        return operatingSystemServiceMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<OperatingSystem> selectByExample(OperatingSystemExample example) {
        return operatingSystemServiceMapper.selectByExample(example);
    }

    @Override
    public List<OperatingSystem> selectByExampleWithRowbounds(OperatingSystemExample example, RowBounds rowBounds) {
        return operatingSystemServiceMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public OperatingSystem selectByPrimaryKey(Long id) {
        return operatingSystemServiceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(OperatingSystemExample example) {
        return operatingSystemServiceMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return operatingSystemServiceMapper.deleteByPrimaryKey(id);
    }
}
