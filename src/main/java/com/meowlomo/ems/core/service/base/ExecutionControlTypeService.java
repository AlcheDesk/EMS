package com.meowlomo.ems.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.ems.core.model.ExecutionControlType;
import com.meowlomo.ems.core.model.ExecutionControlTypeExample;

public interface ExecutionControlTypeService {
    // mapper methods
    long countByExample(ExecutionControlTypeExample example);

    int deleteByExample(ExecutionControlTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExecutionControlType record);

    int insertSelective(ExecutionControlType record, ExecutionControlType.Column... selective);

    List<ExecutionControlType> selectByExampleWithRowbounds(ExecutionControlTypeExample example, RowBounds rowBounds);

    ExecutionControlType selectOneByExample(ExecutionControlTypeExample example);

    ExecutionControlType selectOneByExampleSelective(ExecutionControlTypeExample example, ExecutionControlType.Column... selective);

    List<ExecutionControlType> selectByExampleSelective(ExecutionControlTypeExample example, ExecutionControlType.Column... selective);

    List<ExecutionControlType> selectByExample(ExecutionControlTypeExample example);

    ExecutionControlType selectByPrimaryKeySelective(Long id, ExecutionControlType.Column... selective);

    ExecutionControlType selectByPrimaryKey(Long id);

    int updateByExampleSelective(ExecutionControlType record, ExecutionControlTypeExample example, ExecutionControlType.Column... selective);

    int updateByExample(ExecutionControlType record, ExecutionControlTypeExample example);

    int updateByPrimaryKeySelective(ExecutionControlType record, ExecutionControlType.Column... selective);

    int updateByPrimaryKey(ExecutionControlType record);
}
