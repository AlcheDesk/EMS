package com.meowlomo.ems.core.model;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskExample() {
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

    public TaskExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public TaskExample orderBy(String... orderByClauses) {
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
        TaskExample example = new TaskExample();
        return example.createCriteria();
    }

    public TaskExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public TaskExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        }
        else {
            otherwise.example(this);
        }
        return this;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> uuidCriteria;

        protected List<Criterion> typeCriteria;

        protected List<Criterion> groupCriteria;

        protected List<Criterion> statusCriteria;

        protected List<Criterion> parameterCriteria;

        protected List<Criterion> dataCriteria;

        protected List<Criterion> operatingSystemCriteria;

        protected List<Criterion> executionResultCriteria;

        protected List<Criterion> externalIdentifierCriteria;

        protected List<Criterion> singletonUuidCriteria;

        protected List<Criterion> synchronizedExecutionUuidCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            uuidCriteria = new ArrayList<Criterion>();
            typeCriteria = new ArrayList<Criterion>();
            groupCriteria = new ArrayList<Criterion>();
            statusCriteria = new ArrayList<Criterion>();
            parameterCriteria = new ArrayList<Criterion>();
            dataCriteria = new ArrayList<Criterion>();
            operatingSystemCriteria = new ArrayList<Criterion>();
            executionResultCriteria = new ArrayList<Criterion>();
            externalIdentifierCriteria = new ArrayList<Criterion>();
            singletonUuidCriteria = new ArrayList<Criterion>();
            synchronizedExecutionUuidCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getUuidCriteria() {
            return uuidCriteria;
        }

        protected void addUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            uuidCriteria.add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addUuidCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            uuidCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getTypeCriteria() {
            return typeCriteria;
        }

        protected void addTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.TaskTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            typeCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.TaskTypeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getGroupCriteria() {
            return groupCriteria;
        }

        protected void addGroupCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            groupCriteria.add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.GroupTypeHandler"));
            allCriteria = null;
        }

        protected void addGroupCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            groupCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.GroupTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getStatusCriteria() {
            return statusCriteria;
        }

        protected void addStatusCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            statusCriteria.add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.StatusTypeHandler"));
            allCriteria = null;
        }

        protected void addStatusCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            statusCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.StatusTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getParameterCriteria() {
            return parameterCriteria;
        }

        protected void addParameterCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            parameterCriteria.add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addParameterCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            parameterCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getDataCriteria() {
            return dataCriteria;
        }

        protected void addDataCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            dataCriteria.add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addDataCriterion(String condition, JsonNode value1, JsonNode value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            dataCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getOperatingSystemCriteria() {
            return operatingSystemCriteria;
        }

        protected void addOperatingSystemCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            operatingSystemCriteria
                    .add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.OperatingSystemTypeHandler"));
            allCriteria = null;
        }

        protected void addOperatingSystemCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            operatingSystemCriteria.add(new Criterion(condition, value1, value2,
                    "com.meowlomo.ems.typehandler.OperatingSystemTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getExecutionResultCriteria() {
            return executionResultCriteria;
        }

        protected void addExecutionResultCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            executionResultCriteria
                    .add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        protected void addExecutionResultCriterion(String condition, JsonNode value1, JsonNode value2,
                String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            executionResultCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.JsonNodeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getExternalIdentifierCriteria() {
            return externalIdentifierCriteria;
        }

        protected void addExternalIdentifierCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            externalIdentifierCriteria
                    .add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addExternalIdentifierCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            externalIdentifierCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
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

        public List<Criterion> getSynchronizedExecutionUuidCriteria() {
            return synchronizedExecutionUuidCriteria;
        }

        protected void addSynchronizedExecutionUuidCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            synchronizedExecutionUuidCriteria
                    .add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addSynchronizedExecutionUuidCriterion(String condition, UUID value1, UUID value2,
                String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            synchronizedExecutionUuidCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || uuidCriteria.size() > 0 || typeCriteria.size() > 0 || groupCriteria.size() > 0
                    || statusCriteria.size() > 0 || parameterCriteria.size() > 0 || dataCriteria.size() > 0
                    || operatingSystemCriteria.size() > 0 || executionResultCriteria.size() > 0
                    || externalIdentifierCriteria.size() > 0 || singletonUuidCriteria.size() > 0
                    || synchronizedExecutionUuidCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(uuidCriteria);
                allCriteria.addAll(typeCriteria);
                allCriteria.addAll(groupCriteria);
                allCriteria.addAll(statusCriteria);
                allCriteria.addAll(parameterCriteria);
                allCriteria.addAll(dataCriteria);
                allCriteria.addAll(operatingSystemCriteria);
                allCriteria.addAll(executionResultCriteria);
                allCriteria.addAll(externalIdentifierCriteria);
                allCriteria.addAll(singletonUuidCriteria);
                allCriteria.addAll(synchronizedExecutionUuidCriteria);
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

        public Criteria andIdEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(Task.Column column) {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("name <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(UUID value) {
            addUuidCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(UUID value) {
            addUuidCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(UUID value) {
            addUuidCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(UUID value) {
            addUuidCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(UUID value) {
            addUuidCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(UUID value) {
            addUuidCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("uuid <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<UUID> values) {
            addUuidCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<UUID> values) {
            addUuidCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(UUID value1, UUID value2) {
            addUuidCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(UUID value1, UUID value2) {
            addUuidCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("priority = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("priority <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("priority > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("priority >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("priority < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("priority <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("task_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("task_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addTypeCriterion("task_type_id =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("task_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addTypeCriterion("task_type_id <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("task_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addTypeCriterion("task_type_id >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("task_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addTypeCriterion("task_type_id >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("task_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addTypeCriterion("task_type_id <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("task_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addTypeCriterion("task_type_id <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("task_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addTypeCriterion("task_type_id in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addTypeCriterion("task_type_id not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addTypeCriterion("task_type_id between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addTypeCriterion("task_type_id not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andGroupIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupEqualTo(String value) {
            addGroupCriterion("group_id =", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("group_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualTo(String value) {
            addGroupCriterion("group_id <>", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("group_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThan(String value) {
            addGroupCriterion("group_id >", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("group_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualTo(String value) {
            addGroupCriterion("group_id >=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("group_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThan(String value) {
            addGroupCriterion("group_id <", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("group_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualTo(String value) {
            addGroupCriterion("group_id <=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("group_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupIn(List<String> values) {
            addGroupCriterion("group_id in", values, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotIn(List<String> values) {
            addGroupCriterion("group_id not in", values, "group");
            return (Criteria) this;
        }

        public Criteria andGroupBetween(String value1, String value2) {
            addGroupCriterion("group_id between", value1, value2, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotBetween(String value1, String value2) {
            addGroupCriterion("group_id not between", value1, value2, "group");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredIsNull() {
            addCriterion("cpu_core_required is null");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredIsNotNull() {
            addCriterion("cpu_core_required is not null");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredEqualTo(Integer value) {
            addCriterion("cpu_core_required =", value, "cpuCoreRequired");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("cpu_core_required = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredNotEqualTo(Integer value) {
            addCriterion("cpu_core_required <>", value, "cpuCoreRequired");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("cpu_core_required <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredGreaterThan(Integer value) {
            addCriterion("cpu_core_required >", value, "cpuCoreRequired");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("cpu_core_required > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredGreaterThanOrEqualTo(Integer value) {
            addCriterion("cpu_core_required >=", value, "cpuCoreRequired");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("cpu_core_required >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredLessThan(Integer value) {
            addCriterion("cpu_core_required <", value, "cpuCoreRequired");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("cpu_core_required < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredLessThanOrEqualTo(Integer value) {
            addCriterion("cpu_core_required <=", value, "cpuCoreRequired");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("cpu_core_required <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredIn(List<Integer> values) {
            addCriterion("cpu_core_required in", values, "cpuCoreRequired");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredNotIn(List<Integer> values) {
            addCriterion("cpu_core_required not in", values, "cpuCoreRequired");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredBetween(Integer value1, Integer value2) {
            addCriterion("cpu_core_required between", value1, value2, "cpuCoreRequired");
            return (Criteria) this;
        }

        public Criteria andCpuCoreRequiredNotBetween(Integer value1, Integer value2) {
            addCriterion("cpu_core_required not between", value1, value2, "cpuCoreRequired");
            return (Criteria) this;
        }

        public Criteria andRamRequiredIsNull() {
            addCriterion("ram_required is null");
            return (Criteria) this;
        }

        public Criteria andRamRequiredIsNotNull() {
            addCriterion("ram_required is not null");
            return (Criteria) this;
        }

        public Criteria andRamRequiredEqualTo(Integer value) {
            addCriterion("ram_required =", value, "ramRequired");
            return (Criteria) this;
        }

        public Criteria andRamRequiredEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("ram_required = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamRequiredNotEqualTo(Integer value) {
            addCriterion("ram_required <>", value, "ramRequired");
            return (Criteria) this;
        }

        public Criteria andRamRequiredNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("ram_required <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamRequiredGreaterThan(Integer value) {
            addCriterion("ram_required >", value, "ramRequired");
            return (Criteria) this;
        }

        public Criteria andRamRequiredGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("ram_required > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamRequiredGreaterThanOrEqualTo(Integer value) {
            addCriterion("ram_required >=", value, "ramRequired");
            return (Criteria) this;
        }

        public Criteria andRamRequiredGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("ram_required >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamRequiredLessThan(Integer value) {
            addCriterion("ram_required <", value, "ramRequired");
            return (Criteria) this;
        }

        public Criteria andRamRequiredLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("ram_required < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamRequiredLessThanOrEqualTo(Integer value) {
            addCriterion("ram_required <=", value, "ramRequired");
            return (Criteria) this;
        }

        public Criteria andRamRequiredLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("ram_required <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamRequiredIn(List<Integer> values) {
            addCriterion("ram_required in", values, "ramRequired");
            return (Criteria) this;
        }

        public Criteria andRamRequiredNotIn(List<Integer> values) {
            addCriterion("ram_required not in", values, "ramRequired");
            return (Criteria) this;
        }

        public Criteria andRamRequiredBetween(Integer value1, Integer value2) {
            addCriterion("ram_required between", value1, value2, "ramRequired");
            return (Criteria) this;
        }

        public Criteria andRamRequiredNotBetween(Integer value1, Integer value2) {
            addCriterion("ram_required not between", value1, value2, "ramRequired");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredIsNull() {
            addCriterion("bandwidth_required is null");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredIsNotNull() {
            addCriterion("bandwidth_required is not null");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredEqualTo(Integer value) {
            addCriterion("bandwidth_required =", value, "bandwidthRequired");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("bandwidth_required = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredNotEqualTo(Integer value) {
            addCriterion("bandwidth_required <>", value, "bandwidthRequired");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("bandwidth_required <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredGreaterThan(Integer value) {
            addCriterion("bandwidth_required >", value, "bandwidthRequired");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("bandwidth_required > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredGreaterThanOrEqualTo(Integer value) {
            addCriterion("bandwidth_required >=", value, "bandwidthRequired");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("bandwidth_required >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredLessThan(Integer value) {
            addCriterion("bandwidth_required <", value, "bandwidthRequired");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("bandwidth_required < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredLessThanOrEqualTo(Integer value) {
            addCriterion("bandwidth_required <=", value, "bandwidthRequired");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("bandwidth_required <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredIn(List<Integer> values) {
            addCriterion("bandwidth_required in", values, "bandwidthRequired");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredNotIn(List<Integer> values) {
            addCriterion("bandwidth_required not in", values, "bandwidthRequired");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredBetween(Integer value1, Integer value2) {
            addCriterion("bandwidth_required between", value1, value2, "bandwidthRequired");
            return (Criteria) this;
        }

        public Criteria andBandwidthRequiredNotBetween(Integer value1, Integer value2) {
            addCriterion("bandwidth_required not between", value1, value2, "bandwidthRequired");
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

        public Criteria andCreatedAtEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(Task.Column column) {
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

        public Criteria andUpdatedAtEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(Task.Column column) {
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

        public Criteria andInteractiveIsNull() {
            addCriterion("interactive is null");
            return (Criteria) this;
        }

        public Criteria andInteractiveIsNotNull() {
            addCriterion("interactive is not null");
            return (Criteria) this;
        }

        public Criteria andInteractiveEqualTo(Boolean value) {
            addCriterion("interactive =", value, "interactive");
            return (Criteria) this;
        }

        public Criteria andInteractiveEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("interactive = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInteractiveNotEqualTo(Boolean value) {
            addCriterion("interactive <>", value, "interactive");
            return (Criteria) this;
        }

        public Criteria andInteractiveNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("interactive <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInteractiveGreaterThan(Boolean value) {
            addCriterion("interactive >", value, "interactive");
            return (Criteria) this;
        }

        public Criteria andInteractiveGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("interactive > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInteractiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("interactive >=", value, "interactive");
            return (Criteria) this;
        }

        public Criteria andInteractiveGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("interactive >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInteractiveLessThan(Boolean value) {
            addCriterion("interactive <", value, "interactive");
            return (Criteria) this;
        }

        public Criteria andInteractiveLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("interactive < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInteractiveLessThanOrEqualTo(Boolean value) {
            addCriterion("interactive <=", value, "interactive");
            return (Criteria) this;
        }

        public Criteria andInteractiveLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("interactive <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andInteractiveIn(List<Boolean> values) {
            addCriterion("interactive in", values, "interactive");
            return (Criteria) this;
        }

        public Criteria andInteractiveNotIn(List<Boolean> values) {
            addCriterion("interactive not in", values, "interactive");
            return (Criteria) this;
        }

        public Criteria andInteractiveBetween(Boolean value1, Boolean value2) {
            addCriterion("interactive between", value1, value2, "interactive");
            return (Criteria) this;
        }

        public Criteria andInteractiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("interactive not between", value1, value2, "interactive");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status_id is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status_id is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addStatusCriterion("status_id =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("status_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addStatusCriterion("status_id <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("status_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addStatusCriterion("status_id >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("status_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addStatusCriterion("status_id >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("status_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addStatusCriterion("status_id <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("status_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addStatusCriterion("status_id <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("status_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addStatusCriterion("status_id in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addStatusCriterion("status_id not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addStatusCriterion("status_id between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addStatusCriterion("status_id not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andParameterIsNull() {
            addCriterion("parameter is null");
            return (Criteria) this;
        }

        public Criteria andParameterIsNotNull() {
            addCriterion("parameter is not null");
            return (Criteria) this;
        }

        public Criteria andParameterEqualTo(JsonNode value) {
            addParameterCriterion("parameter =", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("parameter = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualTo(JsonNode value) {
            addParameterCriterion("parameter <>", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("parameter <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThan(JsonNode value) {
            addParameterCriterion("parameter >", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("parameter > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualTo(JsonNode value) {
            addParameterCriterion("parameter >=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("parameter >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterLessThan(JsonNode value) {
            addParameterCriterion("parameter <", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("parameter < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualTo(JsonNode value) {
            addParameterCriterion("parameter <=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("parameter <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterIn(List<JsonNode> values) {
            addParameterCriterion("parameter in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotIn(List<JsonNode> values) {
            addParameterCriterion("parameter not in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterBetween(JsonNode value1, JsonNode value2) {
            addParameterCriterion("parameter between", value1, value2, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotBetween(JsonNode value1, JsonNode value2) {
            addParameterCriterion("parameter not between", value1, value2, "parameter");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNull() {
            addCriterion("timeout is null");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNotNull() {
            addCriterion("timeout is not null");
            return (Criteria) this;
        }

        public Criteria andTimeoutEqualTo(Long value) {
            addCriterion("timeout =", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("timeout = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutNotEqualTo(Long value) {
            addCriterion("timeout <>", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("timeout <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThan(Long value) {
            addCriterion("timeout >", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("timeout > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThanOrEqualTo(Long value) {
            addCriterion("timeout >=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("timeout >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThan(Long value) {
            addCriterion("timeout <", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("timeout < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThanOrEqualTo(Long value) {
            addCriterion("timeout <=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("timeout <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTimeoutIn(List<Long> values) {
            addCriterion("timeout in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotIn(List<Long> values) {
            addCriterion("timeout not in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutBetween(Long value1, Long value2) {
            addCriterion("timeout between", value1, value2, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotBetween(Long value1, Long value2) {
            addCriterion("timeout not between", value1, value2, "timeout");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNull() {
            addCriterion("job_id is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("job_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(Long value) {
            addCriterion("job_id =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("job_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(Long value) {
            addCriterion("job_id <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("job_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(Long value) {
            addCriterion("job_id >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("job_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(Long value) {
            addCriterion("job_id >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("job_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(Long value) {
            addCriterion("job_id <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("job_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(Long value) {
            addCriterion("job_id <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("job_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<Long> values) {
            addCriterion("job_id in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<Long> values) {
            addCriterion("job_id not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(Long value1, Long value2) {
            addCriterion("job_id between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(Long value1, Long value2) {
            addCriterion("job_id not between", value1, value2, "jobId");
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

        public Criteria andFinishedEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("is_finished = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualTo(Boolean value) {
            addCriterion("is_finished <>", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("is_finished <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThan(Boolean value) {
            addCriterion("is_finished >", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("is_finished > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_finished >=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("is_finished >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThan(Boolean value) {
            addCriterion("is_finished <", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("is_finished < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_finished <=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualToColumn(Task.Column column) {
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

        public Criteria andDataIsNull() {
            addCriterion("data is null");
            return (Criteria) this;
        }

        public Criteria andDataIsNotNull() {
            addCriterion("data is not null");
            return (Criteria) this;
        }

        public Criteria andDataEqualTo(JsonNode value) {
            addDataCriterion("data =", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("data = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataNotEqualTo(JsonNode value) {
            addDataCriterion("data <>", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("data <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataGreaterThan(JsonNode value) {
            addDataCriterion("data >", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("data > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualTo(JsonNode value) {
            addDataCriterion("data >=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("data >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataLessThan(JsonNode value) {
            addDataCriterion("data <", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("data < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualTo(JsonNode value) {
            addDataCriterion("data <=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("data <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDataIn(List<JsonNode> values) {
            addDataCriterion("data in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotIn(List<JsonNode> values) {
            addDataCriterion("data not in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataBetween(JsonNode value1, JsonNode value2) {
            addDataCriterion("data between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotBetween(JsonNode value1, JsonNode value2) {
            addDataCriterion("data not between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemIsNull() {
            addCriterion("operating_system_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemIsNotNull() {
            addCriterion("operating_system_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemEqualTo(String value) {
            addOperatingSystemCriterion("operating_system_id =", value, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("operating_system_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatingSystemNotEqualTo(String value) {
            addOperatingSystemCriterion("operating_system_id <>", value, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("operating_system_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatingSystemGreaterThan(String value) {
            addOperatingSystemCriterion("operating_system_id >", value, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("operating_system_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatingSystemGreaterThanOrEqualTo(String value) {
            addOperatingSystemCriterion("operating_system_id >=", value, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("operating_system_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatingSystemLessThan(String value) {
            addOperatingSystemCriterion("operating_system_id <", value, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("operating_system_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatingSystemLessThanOrEqualTo(String value) {
            addOperatingSystemCriterion("operating_system_id <=", value, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("operating_system_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatingSystemIn(List<String> values) {
            addOperatingSystemCriterion("operating_system_id in", values, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemNotIn(List<String> values) {
            addOperatingSystemCriterion("operating_system_id not in", values, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemBetween(String value1, String value2) {
            addOperatingSystemCriterion("operating_system_id between", value1, value2, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemNotBetween(String value1, String value2) {
            addOperatingSystemCriterion("operating_system_id not between", value1, value2, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andExecutionResultIsNull() {
            addCriterion("execution_result is null");
            return (Criteria) this;
        }

        public Criteria andExecutionResultIsNotNull() {
            addCriterion("execution_result is not null");
            return (Criteria) this;
        }

        public Criteria andExecutionResultEqualTo(JsonNode value) {
            addExecutionResultCriterion("execution_result =", value, "executionResult");
            return (Criteria) this;
        }

        public Criteria andExecutionResultEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_result = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionResultNotEqualTo(JsonNode value) {
            addExecutionResultCriterion("execution_result <>", value, "executionResult");
            return (Criteria) this;
        }

        public Criteria andExecutionResultNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_result <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionResultGreaterThan(JsonNode value) {
            addExecutionResultCriterion("execution_result >", value, "executionResult");
            return (Criteria) this;
        }

        public Criteria andExecutionResultGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_result > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionResultGreaterThanOrEqualTo(JsonNode value) {
            addExecutionResultCriterion("execution_result >=", value, "executionResult");
            return (Criteria) this;
        }

        public Criteria andExecutionResultGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_result >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionResultLessThan(JsonNode value) {
            addExecutionResultCriterion("execution_result <", value, "executionResult");
            return (Criteria) this;
        }

        public Criteria andExecutionResultLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_result < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionResultLessThanOrEqualTo(JsonNode value) {
            addExecutionResultCriterion("execution_result <=", value, "executionResult");
            return (Criteria) this;
        }

        public Criteria andExecutionResultLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_result <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionResultIn(List<JsonNode> values) {
            addExecutionResultCriterion("execution_result in", values, "executionResult");
            return (Criteria) this;
        }

        public Criteria andExecutionResultNotIn(List<JsonNode> values) {
            addExecutionResultCriterion("execution_result not in", values, "executionResult");
            return (Criteria) this;
        }

        public Criteria andExecutionResultBetween(JsonNode value1, JsonNode value2) {
            addExecutionResultCriterion("execution_result between", value1, value2, "executionResult");
            return (Criteria) this;
        }

        public Criteria andExecutionResultNotBetween(JsonNode value1, JsonNode value2) {
            addExecutionResultCriterion("execution_result not between", value1, value2, "executionResult");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtIsNull() {
            addCriterion("execution_start_at is null");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtIsNotNull() {
            addCriterion("execution_start_at is not null");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtEqualTo(Date value) {
            addCriterion("execution_start_at =", value, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_start_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtNotEqualTo(Date value) {
            addCriterion("execution_start_at <>", value, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_start_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtGreaterThan(Date value) {
            addCriterion("execution_start_at >", value, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_start_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtGreaterThanOrEqualTo(Date value) {
            addCriterion("execution_start_at >=", value, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_start_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtLessThan(Date value) {
            addCriterion("execution_start_at <", value, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_start_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtLessThanOrEqualTo(Date value) {
            addCriterion("execution_start_at <=", value, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_start_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtIn(List<Date> values) {
            addCriterion("execution_start_at in", values, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtNotIn(List<Date> values) {
            addCriterion("execution_start_at not in", values, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtBetween(Date value1, Date value2) {
            addCriterion("execution_start_at between", value1, value2, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtNotBetween(Date value1, Date value2) {
            addCriterion("execution_start_at not between", value1, value2, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtIsNull() {
            addCriterion("execution_end_at is null");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtIsNotNull() {
            addCriterion("execution_end_at is not null");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtEqualTo(Date value) {
            addCriterion("execution_end_at =", value, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_end_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtNotEqualTo(Date value) {
            addCriterion("execution_end_at <>", value, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_end_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtGreaterThan(Date value) {
            addCriterion("execution_end_at >", value, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_end_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtGreaterThanOrEqualTo(Date value) {
            addCriterion("execution_end_at >=", value, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_end_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtLessThan(Date value) {
            addCriterion("execution_end_at <", value, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_end_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtLessThanOrEqualTo(Date value) {
            addCriterion("execution_end_at <=", value, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("execution_end_at <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtIn(List<Date> values) {
            addCriterion("execution_end_at in", values, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtNotIn(List<Date> values) {
            addCriterion("execution_end_at not in", values, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtBetween(Date value1, Date value2) {
            addCriterion("execution_end_at between", value1, value2, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtNotBetween(Date value1, Date value2) {
            addCriterion("execution_end_at not between", value1, value2, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andMaxRetryIsNull() {
            addCriterion("max_retry is null");
            return (Criteria) this;
        }

        public Criteria andMaxRetryIsNotNull() {
            addCriterion("max_retry is not null");
            return (Criteria) this;
        }

        public Criteria andMaxRetryEqualTo(Integer value) {
            addCriterion("max_retry =", value, "maxRetry");
            return (Criteria) this;
        }

        public Criteria andMaxRetryEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("max_retry = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMaxRetryNotEqualTo(Integer value) {
            addCriterion("max_retry <>", value, "maxRetry");
            return (Criteria) this;
        }

        public Criteria andMaxRetryNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("max_retry <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMaxRetryGreaterThan(Integer value) {
            addCriterion("max_retry >", value, "maxRetry");
            return (Criteria) this;
        }

        public Criteria andMaxRetryGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("max_retry > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMaxRetryGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_retry >=", value, "maxRetry");
            return (Criteria) this;
        }

        public Criteria andMaxRetryGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("max_retry >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMaxRetryLessThan(Integer value) {
            addCriterion("max_retry <", value, "maxRetry");
            return (Criteria) this;
        }

        public Criteria andMaxRetryLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("max_retry < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMaxRetryLessThanOrEqualTo(Integer value) {
            addCriterion("max_retry <=", value, "maxRetry");
            return (Criteria) this;
        }

        public Criteria andMaxRetryLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("max_retry <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMaxRetryIn(List<Integer> values) {
            addCriterion("max_retry in", values, "maxRetry");
            return (Criteria) this;
        }

        public Criteria andMaxRetryNotIn(List<Integer> values) {
            addCriterion("max_retry not in", values, "maxRetry");
            return (Criteria) this;
        }

        public Criteria andMaxRetryBetween(Integer value1, Integer value2) {
            addCriterion("max_retry between", value1, value2, "maxRetry");
            return (Criteria) this;
        }

        public Criteria andMaxRetryNotBetween(Integer value1, Integer value2) {
            addCriterion("max_retry not between", value1, value2, "maxRetry");
            return (Criteria) this;
        }

        public Criteria andRetryNumberIsNull() {
            addCriterion("retry_number is null");
            return (Criteria) this;
        }

        public Criteria andRetryNumberIsNotNull() {
            addCriterion("retry_number is not null");
            return (Criteria) this;
        }

        public Criteria andRetryNumberEqualTo(Integer value) {
            addCriterion("retry_number =", value, "retryNumber");
            return (Criteria) this;
        }

        public Criteria andRetryNumberEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("retry_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRetryNumberNotEqualTo(Integer value) {
            addCriterion("retry_number <>", value, "retryNumber");
            return (Criteria) this;
        }

        public Criteria andRetryNumberNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("retry_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRetryNumberGreaterThan(Integer value) {
            addCriterion("retry_number >", value, "retryNumber");
            return (Criteria) this;
        }

        public Criteria andRetryNumberGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("retry_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRetryNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("retry_number >=", value, "retryNumber");
            return (Criteria) this;
        }

        public Criteria andRetryNumberGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("retry_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRetryNumberLessThan(Integer value) {
            addCriterion("retry_number <", value, "retryNumber");
            return (Criteria) this;
        }

        public Criteria andRetryNumberLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("retry_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRetryNumberLessThanOrEqualTo(Integer value) {
            addCriterion("retry_number <=", value, "retryNumber");
            return (Criteria) this;
        }

        public Criteria andRetryNumberLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("retry_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRetryNumberIn(List<Integer> values) {
            addCriterion("retry_number in", values, "retryNumber");
            return (Criteria) this;
        }

        public Criteria andRetryNumberNotIn(List<Integer> values) {
            addCriterion("retry_number not in", values, "retryNumber");
            return (Criteria) this;
        }

        public Criteria andRetryNumberBetween(Integer value1, Integer value2) {
            addCriterion("retry_number between", value1, value2, "retryNumber");
            return (Criteria) this;
        }

        public Criteria andRetryNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("retry_number not between", value1, value2, "retryNumber");
            return (Criteria) this;
        }

        public Criteria andWorkerIdIsNull() {
            addCriterion("worker_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkerIdIsNotNull() {
            addCriterion("worker_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerIdEqualTo(Long value) {
            addCriterion("worker_id =", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("worker_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotEqualTo(Long value) {
            addCriterion("worker_id <>", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("worker_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andWorkerIdGreaterThan(Long value) {
            addCriterion("worker_id >", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("worker_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andWorkerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("worker_id >=", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("worker_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andWorkerIdLessThan(Long value) {
            addCriterion("worker_id <", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("worker_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andWorkerIdLessThanOrEqualTo(Long value) {
            addCriterion("worker_id <=", value, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("worker_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andWorkerIdIn(List<Long> values) {
            addCriterion("worker_id in", values, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotIn(List<Long> values) {
            addCriterion("worker_id not in", values, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdBetween(Long value1, Long value2) {
            addCriterion("worker_id between", value1, value2, "workerId");
            return (Criteria) this;
        }

        public Criteria andWorkerIdNotBetween(Long value1, Long value2) {
            addCriterion("worker_id not between", value1, value2, "workerId");
            return (Criteria) this;
        }

        public Criteria andSingletonIsNull() {
            addCriterion("singleton is null");
            return (Criteria) this;
        }

        public Criteria andSingletonIsNotNull() {
            addCriterion("singleton is not null");
            return (Criteria) this;
        }

        public Criteria andSingletonEqualTo(Boolean value) {
            addCriterion("singleton =", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("singleton = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonNotEqualTo(Boolean value) {
            addCriterion("singleton <>", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("singleton <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonGreaterThan(Boolean value) {
            addCriterion("singleton >", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("singleton > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonGreaterThanOrEqualTo(Boolean value) {
            addCriterion("singleton >=", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("singleton >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonLessThan(Boolean value) {
            addCriterion("singleton <", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("singleton < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonLessThanOrEqualTo(Boolean value) {
            addCriterion("singleton <=", value, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("singleton <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonIn(List<Boolean> values) {
            addCriterion("singleton in", values, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonNotIn(List<Boolean> values) {
            addCriterion("singleton not in", values, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonBetween(Boolean value1, Boolean value2) {
            addCriterion("singleton between", value1, value2, "singleton");
            return (Criteria) this;
        }

        public Criteria andSingletonNotBetween(Boolean value1, Boolean value2) {
            addCriterion("singleton not between", value1, value2, "singleton");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierIsNull() {
            addCriterion("external_identifier is null");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierIsNotNull() {
            addCriterion("external_identifier is not null");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierEqualTo(UUID value) {
            addExternalIdentifierCriterion("external_identifier =", value, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("external_identifier = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierNotEqualTo(UUID value) {
            addExternalIdentifierCriterion("external_identifier <>", value, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("external_identifier <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierGreaterThan(UUID value) {
            addExternalIdentifierCriterion("external_identifier >", value, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("external_identifier > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierGreaterThanOrEqualTo(UUID value) {
            addExternalIdentifierCriterion("external_identifier >=", value, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("external_identifier >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierLessThan(UUID value) {
            addExternalIdentifierCriterion("external_identifier <", value, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("external_identifier < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierLessThanOrEqualTo(UUID value) {
            addExternalIdentifierCriterion("external_identifier <=", value, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("external_identifier <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierIn(List<UUID> values) {
            addExternalIdentifierCriterion("external_identifier in", values, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierNotIn(List<UUID> values) {
            addExternalIdentifierCriterion("external_identifier not in", values, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierBetween(UUID value1, UUID value2) {
            addExternalIdentifierCriterion("external_identifier between", value1, value2, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierNotBetween(UUID value1, UUID value2) {
            addExternalIdentifierCriterion("external_identifier not between", value1, value2, "externalIdentifier");
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

        public Criteria andSingletonUuidEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("singleton_uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonUuidNotEqualTo(UUID value) {
            addSingletonUuidCriterion("singleton_uuid <>", value, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("singleton_uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonUuidGreaterThan(UUID value) {
            addSingletonUuidCriterion("singleton_uuid >", value, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("singleton_uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonUuidGreaterThanOrEqualTo(UUID value) {
            addSingletonUuidCriterion("singleton_uuid >=", value, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("singleton_uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonUuidLessThan(UUID value) {
            addSingletonUuidCriterion("singleton_uuid <", value, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("singleton_uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSingletonUuidLessThanOrEqualTo(UUID value) {
            addSingletonUuidCriterion("singleton_uuid <=", value, "singletonUuid");
            return (Criteria) this;
        }

        public Criteria andSingletonUuidLessThanOrEqualToColumn(Task.Column column) {
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

        public Criteria andSynchronizedExecutionIsNull() {
            addCriterion("synchronized_execution is null");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionIsNotNull() {
            addCriterion("synchronized_execution is not null");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionEqualTo(Boolean value) {
            addCriterion("synchronized_execution =", value, "synchronizedExecution");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionEqualToColumn(Task.Column column) {
            addCriterion(
                    new StringBuilder("synchronized_execution = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionNotEqualTo(Boolean value) {
            addCriterion("synchronized_execution <>", value, "synchronizedExecution");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionNotEqualToColumn(Task.Column column) {
            addCriterion(
                    new StringBuilder("synchronized_execution <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionGreaterThan(Boolean value) {
            addCriterion("synchronized_execution >", value, "synchronizedExecution");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionGreaterThanColumn(Task.Column column) {
            addCriterion(
                    new StringBuilder("synchronized_execution > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionGreaterThanOrEqualTo(Boolean value) {
            addCriterion("synchronized_execution >=", value, "synchronizedExecution");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(
                    new StringBuilder("synchronized_execution >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionLessThan(Boolean value) {
            addCriterion("synchronized_execution <", value, "synchronizedExecution");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionLessThanColumn(Task.Column column) {
            addCriterion(
                    new StringBuilder("synchronized_execution < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionLessThanOrEqualTo(Boolean value) {
            addCriterion("synchronized_execution <=", value, "synchronizedExecution");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(
                    new StringBuilder("synchronized_execution <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionIn(List<Boolean> values) {
            addCriterion("synchronized_execution in", values, "synchronizedExecution");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionNotIn(List<Boolean> values) {
            addCriterion("synchronized_execution not in", values, "synchronizedExecution");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionBetween(Boolean value1, Boolean value2) {
            addCriterion("synchronized_execution between", value1, value2, "synchronizedExecution");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionNotBetween(Boolean value1, Boolean value2) {
            addCriterion("synchronized_execution not between", value1, value2, "synchronizedExecution");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidIsNull() {
            addCriterion("synchronized_execution_uuid is null");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidIsNotNull() {
            addCriterion("synchronized_execution_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidEqualTo(UUID value) {
            addSynchronizedExecutionUuidCriterion("synchronized_execution_uuid =", value, "synchronizedExecutionUuid");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("synchronized_execution_uuid = ").append(column.getEscapedColumnName())
                    .toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidNotEqualTo(UUID value) {
            addSynchronizedExecutionUuidCriterion("synchronized_execution_uuid <>", value, "synchronizedExecutionUuid");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidNotEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("synchronized_execution_uuid <> ").append(column.getEscapedColumnName())
                    .toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidGreaterThan(UUID value) {
            addSynchronizedExecutionUuidCriterion("synchronized_execution_uuid >", value, "synchronizedExecutionUuid");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidGreaterThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("synchronized_execution_uuid > ").append(column.getEscapedColumnName())
                    .toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidGreaterThanOrEqualTo(UUID value) {
            addSynchronizedExecutionUuidCriterion("synchronized_execution_uuid >=", value, "synchronizedExecutionUuid");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidGreaterThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("synchronized_execution_uuid >= ").append(column.getEscapedColumnName())
                    .toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidLessThan(UUID value) {
            addSynchronizedExecutionUuidCriterion("synchronized_execution_uuid <", value, "synchronizedExecutionUuid");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidLessThanColumn(Task.Column column) {
            addCriterion(new StringBuilder("synchronized_execution_uuid < ").append(column.getEscapedColumnName())
                    .toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidLessThanOrEqualTo(UUID value) {
            addSynchronizedExecutionUuidCriterion("synchronized_execution_uuid <=", value, "synchronizedExecutionUuid");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidLessThanOrEqualToColumn(Task.Column column) {
            addCriterion(new StringBuilder("synchronized_execution_uuid <= ").append(column.getEscapedColumnName())
                    .toString());
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidIn(List<UUID> values) {
            addSynchronizedExecutionUuidCriterion("synchronized_execution_uuid in", values,
                    "synchronizedExecutionUuid");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidNotIn(List<UUID> values) {
            addSynchronizedExecutionUuidCriterion("synchronized_execution_uuid not in", values,
                    "synchronizedExecutionUuid");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidBetween(UUID value1, UUID value2) {
            addSynchronizedExecutionUuidCriterion("synchronized_execution_uuid between", value1, value2,
                    "synchronizedExecutionUuid");
            return (Criteria) this;
        }

        public Criteria andSynchronizedExecutionUuidNotBetween(UUID value1, UUID value2) {
            addSynchronizedExecutionUuidCriterion("synchronized_execution_uuid not between", value1, value2,
                    "synchronizedExecutionUuid");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private TaskExample example;

        protected Criteria(TaskExample example) {
            super();
            this.example = example;
        }

        public TaskExample example() {
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
        void example(com.meowlomo.ems.core.model.TaskExample example);
    }
}