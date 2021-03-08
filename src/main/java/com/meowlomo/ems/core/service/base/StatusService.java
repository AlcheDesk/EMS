package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.Status;
import com.meowlomo.ems.core.model.StatusExample;

public interface StatusService {
    // mapper methods
    long countByExample(StatusExample example);

    long insert(Status record);

    long insertSelective(Status record);

    int updateByExampleSelective(Status record, StatusExample example);

    int updateByExample(Status record, StatusExample example);

    int updateByPrimaryKeySelective(Status record);

    int updateByPrimaryKey(Status record);

    List<Status> selectByExample(StatusExample example);

    List<Status> selectByExampleWithRowbounds(StatusExample example, RowBounds rowBounds);

    Status selectByPrimaryKey(Long id);

    int deleteByExample(StatusExample example);

    int deleteByPrimaryKey(Long id);
}
