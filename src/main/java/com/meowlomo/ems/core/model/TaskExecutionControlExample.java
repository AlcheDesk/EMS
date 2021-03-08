package com.meowlomo.ems.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskExecutionControlExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskExecutionControlExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public TaskExecutionControlExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public TaskExecutionControlExample orderBy(String... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public static Criteria newAndCreateCriteria() {
        TaskExecutionControlExample example = new TaskExecutionControlExample();
        return example.createCriteria();
    }

    public TaskExecutionControlExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public TaskExecutionControlExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        }
        else {
            otherwise.example(this);
        }
        return this;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> primaryTaskUuidCriteria;

        protected List<Criterion> typeCriteria;

        protected List<Criterion> singletonUuidCriteria;

        protected List<Criterion> secondaryTaskUuidCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            primaryTaskUuidCriteria = new ArrayList<Criterion>();
            typeCriteria = new ArrayList<Criterion>();
            singletonUuidCriteria = new ArrayList<Criterion>();
            secondaryTaskUuidCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getPrimaryTaskUuidCriteria() {
            return primaryTaskUuidCriteria;
        }

        protected void addPrimaryTaskUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            primaryTaskUuidCriteria
                    .add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addPrimaryTaskUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            primaryTaskUuidCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getTypeCriteria() {
            return typeCriteria;
        }

        protected void addTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            typeCriteria.add(
                    new Criterion(condition, value, "com.meowlomo.ems.typehandler.ExecutionControlTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            typeCriteria.add(new Criterion(condition, value1, value2,
                    "com.meowlomo.ems.typehandler.ExecutionControlTypeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getSingletonUuidCriteria() {
            return singletonUuidCriteria;
        }

        protected void addSingletonUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            singletonUuidCriteria.add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addSingletonUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            singletonUuidCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getSecondaryTaskUuidCriteria() {
            return secondaryTaskUuidCriteria;
        }

        protected void addSecondaryTaskUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            secondaryTaskUuidCriteria
                    .add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addSecondaryTaskUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            secondaryTaskUuidCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || primaryTaskUuidCriteria.size() > 0 || typeCriteria.size() > 0
                    || singletonUuidCriteria.size() > 0 || secondaryTaskUuidCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(primaryTaskUuidCriteria);
                allCriteria.addAll(typeCriteria);
                allCriteria.addAll(singletonUuidCriteria);
                allCriteria.addAll(secondaryTaskUuidCriteria);
            }
            return allCriteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) { throw new RuntimeException("Value for condition cannot be null"); }
            criteria.add(new Criterion(condition));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            criteria.add(new Criterion(condition, value));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
            allCriteria = null;
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("created_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("updated_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidIsNull() {
            addCriterion("primary_task_uuid is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidIsNotNull() {
            addCriterion("primary_task_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidEqualTo(UUID value) {
            addPrimaryTaskUuidCriterion("primary_task_uuid =", value, "primaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("primary_task_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidNotEqualTo(UUID value) {
            addPrimaryTaskUuidCriterion("primary_task_uuid <>", value, "primaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidNotEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("primary_task_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidGreaterThan(UUID value) {
            addPrimaryTaskUuidCriterion("primary_task_uuid >", value, "primaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidGreaterThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("primary_task_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidGreaterThanOrEqualTo(UUID value) {
            addPrimaryTaskUuidCriterion("primary_task_uuid >=", value, "primaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidGreaterThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("primary_task_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidLessThan(UUID value) {
            addPrimaryTaskUuidCriterion("primary_task_uuid <", value, "primaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidLessThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("primary_task_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidLessThanOrEqualTo(UUID value) {
            addPrimaryTaskUuidCriterion("primary_task_uuid <=", value, "primaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidLessThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("primary_task_uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidIn(List<UUID> values) {
            addPrimaryTaskUuidCriterion("primary_task_uuid in", values, "primaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidNotIn(List<UUID> values) {
            addPrimaryTaskUuidCriterion("primary_task_uuid not in", values, "primaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidBetween(UUID value1, UUID value2) {
            addPrimaryTaskUuidCriterion("primary_task_uuid between", value1, value2, "primaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andPrimaryTaskUuidNotBetween(UUID value1, UUID value2) {
            addPrimaryTaskUuidCriterion("primary_task_uuid not between", value1, value2, "primaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("execution_control_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("execution_control_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addTypeCriterion("execution_control_type_id =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(
                    new StringBuilder("execution_control_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addTypeCriterion("execution_control_type_id <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("execution_control_type_id <> ").append(column.getEscapedColumnName())
                    .toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addTypeCriterion("execution_control_type_id >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanColumn(TaskExecutionControl.Column column) {
            addCriterion(
                    new StringBuilder("execution_control_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addTypeCriterion("execution_control_type_id >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("execution_control_type_id >= ").append(column.getEscapedColumnName())
                    .toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addTypeCriterion("execution_control_type_id <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanColumn(TaskExecutionControl.Column column) {
            addCriterion(
                    new StringBuilder("execution_control_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addTypeCriterion("execution_control_type_id <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("execution_control_type_id <= ").append(column.getEscapedColumnName())
                    .toString());
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addTypeCriterion("execution_control_type_id in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addTypeCriterion("execution_control_type_id not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addTypeCriterion("execution_control_type_id between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addTypeCriterion("execution_control_type_id not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidIsNull() {
            addCriterion("singleton_uuid is null");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidIsNotNull() {
            addCriterion("singleton_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidEqualTo(UUID value) {
            addSingletonUuidCriterion("singleton_uuid =", value, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("singleton_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonUuidNotEqualTo(UUID value) {
            addSingletonUuidCriterion("singleton_uuid <>", value, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidNotEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("singleton_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonUuidGreaterThan(UUID value) {
            addSingletonUuidCriterion("singleton_uuid >", value, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidGreaterThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("singleton_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonUuidGreaterThanOrEqualTo(UUID value) {
            addSingletonUuidCriterion("singleton_uuid >=", value, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidGreaterThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("singleton_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonUuidLessThan(UUID value) {
            addSingletonUuidCriterion("singleton_uuid <", value, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidLessThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("singleton_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonUuidLessThanOrEqualTo(UUID value) {
            addSingletonUuidCriterion("singleton_uuid <=", value, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidLessThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("singleton_uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonUuidIn(List<UUID> values) {
            addSingletonUuidCriterion("singleton_uuid in", values, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidNotIn(List<UUID> values) {
            addSingletonUuidCriterion("singleton_uuid not in", values, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidBetween(UUID value1, UUID value2) {
            addSingletonUuidCriterion("singleton_uuid between", value1, value2, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidNotBetween(UUID value1, UUID value2) {
            addSingletonUuidCriterion("singleton_uuid not between", value1, value2, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidIsNull() {
            addCriterion("secondary_task_uuid is null");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidIsNotNull() {
            addCriterion("secondary_task_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidEqualTo(UUID value) {
            addSecondaryTaskUuidCriterion("secondary_task_uuid =", value, "secondaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("secondary_task_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidNotEqualTo(UUID value) {
            addSecondaryTaskUuidCriterion("secondary_task_uuid <>", value, "secondaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidNotEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("secondary_task_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidGreaterThan(UUID value) {
            addSecondaryTaskUuidCriterion("secondary_task_uuid >", value, "secondaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidGreaterThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("secondary_task_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidGreaterThanOrEqualTo(UUID value) {
            addSecondaryTaskUuidCriterion("secondary_task_uuid >=", value, "secondaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidGreaterThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("secondary_task_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidLessThan(UUID value) {
            addSecondaryTaskUuidCriterion("secondary_task_uuid <", value, "secondaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidLessThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("secondary_task_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidLessThanOrEqualTo(UUID value) {
            addSecondaryTaskUuidCriterion("secondary_task_uuid <=", value, "secondaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidLessThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("secondary_task_uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidIn(List<UUID> values) {
            addSecondaryTaskUuidCriterion("secondary_task_uuid in", values, "secondaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidNotIn(List<UUID> values) {
            addSecondaryTaskUuidCriterion("secondary_task_uuid not in", values, "secondaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidBetween(UUID value1, UUID value2) {
            addSecondaryTaskUuidCriterion("secondary_task_uuid between", value1, value2, "secondaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andSecondaryTaskUuidNotBetween(UUID value1, UUID value2) {
            addSecondaryTaskUuidCriterion("secondary_task_uuid not between", value1, value2, "secondaryTaskUuid");
            return (Criteria) this;
        }

        public Criteria andSyncTimeIsNull() {
            addCriterion("sync_time is null");
            return (Criteria) this;
        }

        public Criteria andSyncTimeIsNotNull() {
            addCriterion("sync_time is not null");
            return (Criteria) this;
        }

        public Criteria andSyncTimeEqualTo(Date value) {
            addCriterion("sync_time =", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("sync_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSyncTimeNotEqualTo(Date value) {
            addCriterion("sync_time <>", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeNotEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("sync_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSyncTimeGreaterThan(Date value) {
            addCriterion("sync_time >", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeGreaterThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("sync_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSyncTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sync_time >=", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeGreaterThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("sync_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSyncTimeLessThan(Date value) {
            addCriterion("sync_time <", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeLessThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("sync_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSyncTimeLessThanOrEqualTo(Date value) {
            addCriterion("sync_time <=", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeLessThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("sync_time <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSyncTimeIn(List<Date> values) {
            addCriterion("sync_time in", values, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeNotIn(List<Date> values) {
            addCriterion("sync_time not in", values, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeBetween(Date value1, Date value2) {
            addCriterion("sync_time between", value1, value2, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeNotBetween(Date value1, Date value2) {
            addCriterion("sync_time not between", value1, value2, "syncTime");
            return (Criteria) this;
        }

        public Criteria andFinishedIsNull() {
            addCriterion("is_finished is null");
            return (Criteria) this;
        }

        public Criteria andFinishedIsNotNull() {
            addCriterion("is_finished is not null");
            return (Criteria) this;
        }

        public Criteria andFinishedEqualTo(Boolean value) {
            addCriterion("is_finished =", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("is_finished = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualTo(Boolean value) {
            addCriterion("is_finished <>", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("is_finished <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThan(Boolean value) {
            addCriterion("is_finished >", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("is_finished > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_finished >=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("is_finished >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThan(Boolean value) {
            addCriterion("is_finished <", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("is_finished < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_finished <=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("is_finished <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedIn(List<Boolean> values) {
            addCriterion("is_finished in", values, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotIn(List<Boolean> values) {
            addCriterion("is_finished not in", values, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_finished between", value1, value2, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_finished not between", value1, value2, "finished");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseIsNull() {
            addCriterion("time_to_release is null");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseIsNotNull() {
            addCriterion("time_to_release is not null");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseEqualTo(Date value) {
            addCriterion("time_to_release =", value, "timeToRelease");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("time_to_release = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseNotEqualTo(Date value) {
            addCriterion("time_to_release <>", value, "timeToRelease");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseNotEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("time_to_release <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseGreaterThan(Date value) {
            addCriterion("time_to_release >", value, "timeToRelease");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseGreaterThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("time_to_release > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseGreaterThanOrEqualTo(Date value) {
            addCriterion("time_to_release >=", value, "timeToRelease");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseGreaterThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("time_to_release >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseLessThan(Date value) {
            addCriterion("time_to_release <", value, "timeToRelease");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseLessThanColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("time_to_release < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseLessThanOrEqualTo(Date value) {
            addCriterion("time_to_release <=", value, "timeToRelease");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseLessThanOrEqualToColumn(TaskExecutionControl.Column column) {
            addCriterion(new StringBuilder("time_to_release <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseIn(List<Date> values) {
            addCriterion("time_to_release in", values, "timeToRelease");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseNotIn(List<Date> values) {
            addCriterion("time_to_release not in", values, "timeToRelease");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseBetween(Date value1, Date value2) {
            addCriterion("time_to_release between", value1, value2, "timeToRelease");
            return (Criteria) this;
        }

        public Criteria andTimeToReleaseNotBetween(Date value1, Date value2) {
            addCriterion("time_to_release not between", value1, value2, "timeToRelease");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        private TaskExecutionControlExample example;

        protected Criteria(TaskExecutionControlExample example) {
            super();
            this.example = example;
        }

        public TaskExecutionControlExample example() {
            return this.example;
        }

        @Deprecated
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then) {
            if (condition) {
                then.criteria(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then, ICriteriaWhen otherwise) {
            if (condition) {
                then.criteria(this);
            }
            else {
                otherwise.criteria(this);
            }
            return this;
        }

        @Deprecated
        public interface ICriteriaAdd {

            Criteria add(Criteria add);
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            }
            else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    public interface ICriteriaWhen {

        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {

        void example(com.meowlomo.ems.core.model.TaskExecutionControlExample example);
    }
}