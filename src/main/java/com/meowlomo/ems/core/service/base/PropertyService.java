package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.Property;
import com.meowlomo.ems.core.model.PropertyExample;

public interface PropertyService {
    // mapper methods
    long countByExample(PropertyExample example);

    int deleteByExample(PropertyExample example);

    int insert(Property record);

    int insertSelective(Property record);

    List<Property> selectByExampleWithRowbounds(PropertyExample example, RowBounds rowBounds);

    List<Property> selectByExample(PropertyExample example);

    int updateByExampleSelective(Property record, PropertyExample example);

    int updateByExample(Property record, PropertyExample example);
}
