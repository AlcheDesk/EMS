package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.VendorMapper;
import com.meowlomo.ems.core.model.Vendor;
import com.meowlomo.ems.core.model.VendorExample;
import com.meowlomo.ems.core.service.base.VendorService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorMapper vendorMapper;

    @Override
    public long countByExample(VendorExample example) {
        return vendorMapper.countByExample(example);
    }

    @Override
    public long insert(Vendor record) {
        return vendorMapper.insert(record);
    }

    @Override
    public long insertSelective(Vendor record) {
        return vendorMapper.insertSelective(record);
    }

    @Override
    public int updateByExampleSelective(Vendor record, VendorExample example) {
        return vendorMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Vendor record, VendorExample example) {
        return vendorMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Vendor record) {
        return vendorMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Vendor record) {
        return vendorMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Vendor> selectByExample(VendorExample example) {
        return vendorMapper.selectByExample(example);
    }

    @Override
    public List<Vendor> selectByExampleWithRowbounds(VendorExample example, RowBounds rowBounds) {
        return vendorMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public Vendor selectByPrimaryKey(Long id) {
        return vendorMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(VendorExample example) {
        return vendorMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return vendorMapper.deleteByPrimaryKey(id);
    }
}
