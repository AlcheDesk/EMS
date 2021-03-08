package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.Vendor;
import com.meowlomo.ems.core.model.VendorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface VendorMapper {
    long countByExample(VendorExample example);

    int deleteByExample(VendorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Vendor record);

    int insertSelective(@Param("record") Vendor record, @Param("selective") Vendor.Column... selective);

    List<Vendor> selectByExampleWithRowbounds(VendorExample example, RowBounds rowBounds);

    Vendor selectOneByExample(VendorExample example);

    Vendor selectOneByExampleSelective(@Param("example") VendorExample example,
            @Param("selective") Vendor.Column... selective);

    List<Vendor> selectByExampleSelective(@Param("example") VendorExample example,
            @Param("selective") Vendor.Column... selective);

    List<Vendor> selectByExample(VendorExample example);

    Vendor selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Vendor.Column... selective);

    Vendor selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Vendor record, @Param("example") VendorExample example,
            @Param("selective") Vendor.Column... selective);

    int updateByExample(@Param("record") Vendor record, @Param("example") VendorExample example);

    int updateByPrimaryKeySelective(@Param("record") Vendor record, @Param("selective") Vendor.Column... selective);

    int updateByPrimaryKey(Vendor record);
}