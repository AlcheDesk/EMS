package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.OperatingSystem;
import com.meowlomo.ems.core.model.OperatingSystemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OperatingSystemMapper {
    long countByExample(OperatingSystemExample example);

    int deleteByExample(OperatingSystemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OperatingSystem record);

    int insertSelective(@Param("record") OperatingSystem record,
            @Param("selective") OperatingSystem.Column... selective);

    List<OperatingSystem> selectByExampleWithRowbounds(OperatingSystemExample example, RowBounds rowBounds);

    OperatingSystem selectOneByExample(OperatingSystemExample example);

    OperatingSystem selectOneByExampleSelective(@Param("example") OperatingSystemExample example,
            @Param("selective") OperatingSystem.Column... selective);

    List<OperatingSystem> selectByExampleSelective(@Param("example") OperatingSystemExample example,
            @Param("selective") OperatingSystem.Column... selective);

    List<OperatingSystem> selectByExample(OperatingSystemExample example);

    OperatingSystem selectByPrimaryKeySelective(@Param("id") Long id,
            @Param("selective") OperatingSystem.Column... selective);

    OperatingSystem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OperatingSystem record,
            @Param("example") OperatingSystemExample example, @Param("selective") OperatingSystem.Column... selective);

    int updateByExample(@Param("record") OperatingSystem record, @Param("example") OperatingSystemExample example);

    int updateByPrimaryKeySelective(@Param("record") OperatingSystem record,
            @Param("selective") OperatingSystem.Column... selective);

    int updateByPrimaryKey(OperatingSystem record);
}