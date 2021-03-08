package com.meowlomo.ems.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import com.meowlomo.ems.config.RuntimeVariables;

@MappedJdbcTypes(JdbcType.OTHER)
public class ExecutionControlTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long jobTypeId = RuntimeVariables.getExecutionControlTypeToIdMap().get(parameter);
        if (jobTypeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, jobTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long jobTypeId = rs.getLong(columnName);
        if (jobTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToExecutionControlTypeMap().get(jobTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long jobTypeId = rs.getLong(columnIndex);
        if (jobTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToExecutionControlTypeMap().get(jobTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long jobTypeId = cs.getLong(columnIndex);
        if (jobTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToExecutionControlTypeMap().get(jobTypeId);
        }
    }

}
