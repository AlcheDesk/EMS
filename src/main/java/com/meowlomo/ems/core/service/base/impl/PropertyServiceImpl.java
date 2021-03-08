package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.PropertyMapper;
import com.meowlomo.ems.core.model.Property;
import com.meowlomo.ems.core.model.PropertyExample;
import com.meowlomo.ems.core.service.base.PropertyService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private  PropertyMapper jobLogMapper;
    
    @Override
    public long countByExample(PropertyExample example) {
        return jobLogMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(PropertyExample example) {
        return jobLogMapper.deleteByExample(example);
    }

    @Override
    public int insert(Property record) {
        return jobLogMapper.insert(record);
    }

    @Override
    public int insertSelective(Property record) {
        return jobLogMapper.insertSelective(record);
    }

    @Override
    public List<Property> selectByExampleWithRowbounds(PropertyExample example, RowBounds rowBounds) {
        return jobLogMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public List<Property> selectByExample(PropertyExample example) {
        return jobLogMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(Property record, PropertyExample example) {
        return jobLogMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Property record, PropertyExample example) {
        return jobLogMapper.updateByExample(record, example);
    }

}
