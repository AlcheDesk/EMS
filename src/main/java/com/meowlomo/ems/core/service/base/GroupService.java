package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.Group;
import com.meowlomo.ems.core.model.GroupExample;

public interface GroupService {
    // mapper methods
    long countByExample(GroupExample example);

    long insert(Group record);

    long insertSelective(Group record);

    int updateByExampleSelective(Group record, GroupExample example);

    int updateByExample(Group record, GroupExample example);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    List<Group> selectByExample(GroupExample example);

    List<Group> selectByExampleWithRowbounds(GroupExample example, RowBounds rowBounds);

    Group selectByPrimaryKey(Long id);

    int deleteByExample(GroupExample example);

    int deleteByPrimaryKey(Long id);
}
