package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.JobLog;
import com.meowlomo.ems.core.model.JobLogExample;

public interface JobLogService {
    // mapper methods
    long countByExample(JobLogExample example);

    int deleteByExample(JobLogExample example);

    int insert(JobLog record);

    int insertSelective(JobLog record);

    List<JobLog> selectByExampleWithRowbounds(JobLogExample example, RowBounds rowBounds);

    List<JobLog> selectByExample(JobLogExample example);

    int updateByExampleSelective(JobLog record, JobLogExample example);

    int updateByExample(JobLog record, JobLogExample example);
}
