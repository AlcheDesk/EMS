package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.Vendor;
import com.meowlomo.ems.core.model.VendorExample;

public interface VendorService {
    // mapper methods
    long countByExample(VendorExample example);

    long insert(Vendor record);

    long insertSelective(Vendor record);

    int updateByExampleSelective(Vendor record, VendorExample example);

    int updateByExample(Vendor record, VendorExample example);

    int updateByPrimaryKeySelective(Vendor record);

    int updateByPrimaryKey(Vendor record);

    List<Vendor> selectByExample(VendorExample example);

    List<Vendor> selectByExampleWithRowbounds(VendorExample example, RowBounds rowBounds);

    Vendor selectByPrimaryKey(Long id);

    int deleteByExample(VendorExample example);

    int deleteByPrimaryKey(Long id);
}
