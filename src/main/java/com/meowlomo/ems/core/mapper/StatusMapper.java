package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.Status;
import com.meowlomo.ems.core.model.StatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface StatusMapper {
    long countByExample(StatusExample example);

    int deleteByExample(StatusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Status record);

    int insertSelective(@Param("record") Status record, @Param("selective") Status.Column... selective);

    List<Status> selectByExampleWithRowbounds(StatusExample example, RowBounds rowBounds);

    Status selectOneByExample(StatusExample example);

    Status selectOneByExampleSelective(@Param("example") StatusExample example,
            @Param("selective") Status.Column... selective);

    List<Status> selectByExampleSelective(@Param("example") StatusExample example,
            @Param("selective") Status.Column... selective);

    List<Status> selectByExample(StatusExample example);

    Status selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Status.Column... selective);

    Status selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Status record, @Param("example") StatusExample example,
            @Param("selective") Status.Column... selective);

    int updateByExample(@Param("record") Status record, @Param("example") StatusExample example);

    int updateByPrimaryKeySelective(@Param("record") Status record, @Param("selective") Status.Column... selective);

    int updateByPrimaryKey(Status record);
}