package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.WorkerVendor;
import com.meowlomo.ems.core.model.WorkerVendorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WorkerVendorMapper {
    long countByExample(WorkerVendorExample example);

    int deleteByExample(WorkerVendorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WorkerVendor record);

    int insertSelective(@Param("record") WorkerVendor record, @Param("selective") WorkerVendor.Column... selective);

    List<WorkerVendor> selectByExampleWithRowbounds(WorkerVendorExample example, RowBounds rowBounds);

    WorkerVendor selectOneByExample(WorkerVendorExample example);

    WorkerVendor selectOneByExampleSelective(@Param("example") WorkerVendorExample example,
            @Param("selective") WorkerVendor.Column... selective);

    List<WorkerVendor> selectByExampleSelective(@Param("example") WorkerVendorExample example,
            @Param("selective") WorkerVendor.Column... selective);

    List<WorkerVendor> selectByExample(WorkerVendorExample example);

    WorkerVendor selectByPrimaryKeySelective(@Param("id") Long id,
            @Param("selective") WorkerVendor.Column... selective);

    WorkerVendor selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WorkerVendor record, @Param("example") WorkerVendorExample example,
            @Param("selective") WorkerVendor.Column... selective);

    int updateByExample(@Param("record") WorkerVendor record, @Param("example") WorkerVendorExample example);

    int updateByPrimaryKeySelective(@Param("record") WorkerVendor record,
            @Param("selective") WorkerVendor.Column... selective);

    int updateByPrimaryKey(WorkerVendor record);
}