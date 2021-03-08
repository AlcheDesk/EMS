package com.meowlomo.ems.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import com.meowlomo.ems.config.RuntimeVariables;

@MappedJdbcTypes(JdbcType.OTHER)
public class OperatingSystemTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Map<String, Long> osMap = RuntimeVariables.getOperatingSystemToIdMap();
        Set<Long> matchedOSIds = osMap.entrySet().stream()
                .filter(entry -> parameter.toLowerCase().contains(entry.getKey().toLowerCase()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
        if (matchedOSIds.isEmpty()) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, matchedOSIds.iterator().next());
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long statusId = rs.getLong(columnName);
        if (statusId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToOperatingSystemMap().get(statusId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long statusId = rs.getLong(columnIndex);
        if (statusId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToOperatingSystemMap().get(statusId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long statusId = cs.getLong(columnIndex);
        if (statusId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToOperatingSystemMap().get(statusId);
        }
    }

}
