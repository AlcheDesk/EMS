package com.meowlomo.ems.core.mapper;

import com.meowlomo.ems.core.model.ExecutionControlType;
import com.meowlomo.ems.core.model.ExecutionControlTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ExecutionControlTypeMapper {
    long countByExample(ExecutionControlTypeExample example);

    int deleteByExample(ExecutionControlTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExecutionControlType record);

    int insertSelective(@Param("record") ExecutionControlType record,
            @Param("selective") ExecutionControlType.Column... selective);

    List<ExecutionControlType> selectByExampleWithRowbounds(ExecutionControlTypeExample example, RowBounds rowBounds);

    ExecutionControlType selectOneByExample(ExecutionControlTypeExample example);

    ExecutionControlType selectOneByExampleSelective(@Param("example") ExecutionControlTypeExample example,
            @Param("selective") ExecutionControlType.Column... selective);

    List<ExecutionControlType> selectByExampleSelective(@Param("example") ExecutionControlTypeExample example,
            @Param("selective") ExecutionControlType.Column... selective);

    List<ExecutionControlType> selectByExample(ExecutionControlTypeExample example);

    ExecutionControlType selectByPrimaryKeySelective(@Param("id") Long id,
            @Param("selective") ExecutionControlType.Column... selective);

    ExecutionControlType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExecutionControlType record,
            @Param("example") ExecutionControlTypeExample example,
            @Param("selective") ExecutionControlType.Column... selective);

    int updateByExample(@Param("record") ExecutionControlType record,
            @Param("example") ExecutionControlTypeExample example);

    int updateByPrimaryKeySelective(@Param("record") ExecutionControlType record,
            @Param("selective") ExecutionControlType.Column... selective);

    int updateByPrimaryKey(ExecutionControlType record);
}