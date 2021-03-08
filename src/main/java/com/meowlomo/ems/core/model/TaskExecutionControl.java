package com.meowlomo.ems.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class TaskExecutionControl implements Serializable {
    private Long id;

    private Date createdAt;

    private Date updatedAt;

    private UUID primaryTaskUuid;

    private String type;

    private UUID singletonUuid;

    private UUID secondaryTaskUuid;

    private Date syncTime;

    private Boolean finished;

    private Date timeToRelease;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getPrimaryTaskUuid() {
        return primaryTaskUuid;
    }

    public void setPrimaryTaskUuid(UUID primaryTaskUuid) {
        this.primaryTaskUuid = primaryTaskUuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public UUID getSingletonUuid() {
        return singletonUuid;
    }

    public void setSingletonUuid(UUID singletonUuid) {
        this.singletonUuid = singletonUuid;
    }

    public UUID getSecondaryTaskUuid() {
        return secondaryTaskUuid;
    }

    public void setSecondaryTaskUuid(UUID secondaryTaskUuid) {
        this.secondaryTaskUuid = secondaryTaskUuid;
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Date getTimeToRelease() {
        return timeToRelease;
    }

    public void setTimeToRelease(Date timeToRelease) {
        this.timeToRelease = timeToRelease;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }
        TaskExecutionControl other = (TaskExecutionControl) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null
                        : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null
                        : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getPrimaryTaskUuid() == null ? other.getPrimaryTaskUuid() == null
                        : this.getPrimaryTaskUuid().equals(other.getPrimaryTaskUuid()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getSingletonUuid() == null ? other.getSingletonUuid() == null
                        : this.getSingletonUuid().equals(other.getSingletonUuid()))
                && (this.getSecondaryTaskUuid() == null ? other.getSecondaryTaskUuid() == null
                        : this.getSecondaryTaskUuid().equals(other.getSecondaryTaskUuid()))
                && (this.getSyncTime() == null ? other.getSyncTime() == null
                        : this.getSyncTime().equals(other.getSyncTime()))
                && (this.getFinished() == null ? other.getFinished() == null
                        : this.getFinished().equals(other.getFinished()))
                && (this.getTimeToRelease() == null ? other.getTimeToRelease() == null
                        : this.getTimeToRelease().equals(other.getTimeToRelease()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getPrimaryTaskUuid() == null) ? 0 : getPrimaryTaskUuid().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getSingletonUuid() == null) ? 0 : getSingletonUuid().hashCode());
        result = prime * result + ((getSecondaryTaskUuid() == null) ? 0 : getSecondaryTaskUuid().hashCode());
        result = prime * result + ((getSyncTime() == null) ? 0 : getSyncTime().hashCode());
        result = prime * result + ((getFinished() == null) ? 0 : getFinished().hashCode());
        result = prime * result + ((getTimeToRelease() == null) ? 0 : getTimeToRelease().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", primaryTaskUuid=").append(primaryTaskUuid);
        sb.append(", type=").append(type);
        sb.append(", singletonUuid=").append(singletonUuid);
        sb.append(", secondaryTaskUuid=").append(secondaryTaskUuid);
        sb.append(", syncTime=").append(syncTime);
        sb.append(", finished=").append(finished);
        sb.append(", timeToRelease=").append(timeToRelease);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id", "id", "BIGINT", false), createdAt("created_at", "createdAt", "TIMESTAMP", false), updatedAt(
                "updated_at", "updatedAt", "TIMESTAMP",
                false), primaryTaskUuid("primary_task_uuid", "primaryTaskUuid", "OTHER", false), type(
                        "execution_control_type_id", "type", "OTHER",
                        false), singletonUuid("singleton_uuid", "singletonUuid", "OTHER", false), secondaryTaskUuid(
                                "secondary_task_uuid", "secondaryTaskUuid", "BIGINT",
                                false), syncTime("sync_time", "syncTime", "TIMESTAMP", false), finished("is_finished",
                                        "finished", "BIT",
                                        false), timeToRelease("time_to_release", "timeToRelease", "TIMESTAMP", false);

        private static final String BEGINNING_DELIMITER = "\"";

        private static final String ENDING_DELIMITER = "\"";

        private final String column;

        private final boolean isColumnNameDelimited;

        private final String javaProperty;

        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[] {});
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER)
                        .toString();
            }
            else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}