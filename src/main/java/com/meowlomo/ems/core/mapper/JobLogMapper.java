package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.JobLog;
import com.meowlomo.ems.core.model.JobLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface JobLogMapper {
    long countByExample(JobLogExample example);

    int deleteByExample(JobLogExample example);

    int insert(JobLog record);

    int insertSelective(@Param("record") JobLog record, @Param("selective") JobLog.Column... selective);

    List<JobLog> selectByExampleWithRowbounds(JobLogExample example, RowBounds rowBounds);

    JobLog selectOneByExample(JobLogExample example);

    JobLog selectOneByExampleSelective(@Param("example") JobLogExample example,
            @Param("selective") JobLog.Column... selective);

    List<JobLog> selectByExampleSelective(@Param("example") JobLogExample example,
            @Param("selective") JobLog.Column... selective);

    List<JobLog> selectByExample(JobLogExample example);

    int updateByExampleSelective(@Param("record") JobLog record, @Param("example") JobLogExample example,
            @Param("selective") JobLog.Column... selective);

    int updateByExample(@Param("record") JobLog record, @Param("example") JobLogExample example);
}