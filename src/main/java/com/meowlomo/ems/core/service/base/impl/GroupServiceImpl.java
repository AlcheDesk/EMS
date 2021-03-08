package com.meowlomo.ems.core.service.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.ems.core.mapper.GroupMapper;
import com.meowlomo.ems.core.model.Group;
import com.meowlomo.ems.core.model.GroupExample;
import com.meowlomo.ems.core.service.base.GroupService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public long countByExample(GroupExample example) {
        return groupMapper.countByExample(example);
    }

    @Override
    public long insert(Group record) {
        return groupMapper.insert(record);
    }

    @Override
    public long insertSelective(Group record) {
        return groupMapper.insertSelective(record);
    }

    @Override
    public int updateByExampleSelective(Group record, GroupExample example) {
        return groupMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Group record, GroupExample example) {
        return groupMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Group record) {
        return groupMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Group record) {
        return groupMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Group> selectByExample(GroupExample example) {
        return groupMapper.selectByExample(example);
    }

    @Override
    public List<Group> selectByExampleWithRowbounds(GroupExample example, RowBounds rowBounds) {
        return groupMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

    @Override
    public Group selectByPrimaryKey(Long id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByExample(GroupExample example) {
        return groupMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return groupMapper.deleteByPrimaryKey(id);
    }
}
