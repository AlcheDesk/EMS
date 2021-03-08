package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.Property;
import com.meowlomo.ems.core.model.PropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PropertyMapper {
    long countByExample(PropertyExample example);

    int deleteByExample(PropertyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Property record);

    int insertSelective(@Param("record") Property record, @Param("selective") Property.Column... selective);

    List<Property> selectByExampleWithRowbounds(PropertyExample example, RowBounds rowBounds);

    Property selectOneByExample(PropertyExample example);

    Property selectOneByExampleSelective(@Param("example") PropertyExample example,
            @Param("selective") Property.Column... selective);

    List<Property> selectByExampleSelective(@Param("example") PropertyExample example,
            @Param("selective") Property.Column... selective);

    List<Property> selectByExample(PropertyExample example);

    Property selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Property.Column... selective);

    Property selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Property record, @Param("example") PropertyExample example,
            @Param("selective") Property.Column... selective);

    int updateByExample(@Param("record") Property record, @Param("example") PropertyExample example);

    int updateByPrimaryKeySelective(@Param("record") Property record, @Param("selective") Property.Column... selective);

    int updateByPrimaryKey(Property record);
}