package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.Group;
import com.meowlomo.ems.core.model.GroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GroupMapper {
    long countByExample(GroupExample example);

    int deleteByExample(GroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    int insertSelective(@Param("record") Group record, @Param("selective") Group.Column... selective);

    List<Group> selectByExampleWithRowbounds(GroupExample example, RowBounds rowBounds);

    Group selectOneByExample(GroupExample example);

    Group selectOneByExampleSelective(@Param("example") GroupExample example,
            @Param("selective") Group.Column... selective);

    List<Group> selectByExampleSelective(@Param("example") GroupExample example,
            @Param("selective") Group.Column... selective);

    List<Group> selectByExample(GroupExample example);

    Group selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Group.Column... selective);

    Group selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example,
            @Param("selective") Group.Column... selective);

    int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

    int updateByPrimaryKeySelective(@Param("record") Group record, @Param("selective") Group.Column... selective);

    int updateByPrimaryKey(Group record);
}