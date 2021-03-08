package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.OperatingSystem;
import com.meowlomo.ems.core.model.OperatingSystemExample;

public interface OperatingSystemService {
    // mapper methods
    long countByExample(OperatingSystemExample example);

    long insert(OperatingSystem record);

    long insertSelective(OperatingSystem record);

    int updateByExampleSelective(OperatingSystem record, OperatingSystemExample example);

    int updateByExample(OperatingSystem record, OperatingSystemExample example);

    int updateByPrimaryKeySelective(OperatingSystem record);

    int updateByPrimaryKey(OperatingSystem record);

    List<OperatingSystem> selectByExample(OperatingSystemExample example);

    List<OperatingSystem> selectByExampleWithRowbounds(OperatingSystemExample example, RowBounds rowBounds);

    OperatingSystem selectByPrimaryKey(Long id);

    int deleteByExample(OperatingSystemExample example);

    int deleteByPrimaryKey(Long id);
}
