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
public class TaskTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long taskTypeId = RuntimeVariables.getTaskTypeToIdMap().get(parameter);
        if (taskTypeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, taskTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long taskTypeId = rs.getLong(columnName);
        if (taskTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToTaskTypeMap().get(taskTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long taskTypeId = rs.getLong(columnIndex);
        if (taskTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToTaskTypeMap().get(taskTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long taskTypeId = cs.getLong(columnIndex);
        if (taskTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToTaskTypeMap().get(taskTypeId);
        }
    }

}
