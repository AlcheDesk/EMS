package com.meowlomo.ems.core.model;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JobExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JobExample() {
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

    public JobExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public JobExample orderBy(String... orderByClauses) {
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
        JobExample example = new JobExample();
        return example.createCriteria();
    }

    public JobExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public JobExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        }
        else {
            otherwise.example(this);
        }
        return this;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> typeCriteria;

        protected List<Criterion> uuidCriteria;

        protected List<Criterion> statusCriteria;

        protected List<Criterion> parameterCriteria;

        protected List<Criterion> groupCriteria;

        protected List<Criterion> externalIdentifierCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            typeCriteria = new ArrayList<Criterion>();
            uuidCriteria = new ArrayList<Criterion>();
            statusCriteria = new ArrayList<Criterion>();
            parameterCriteria = new ArrayList<Criterion>();
            groupCriteria = new ArrayList<Criterion>();
            externalIdentifierCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getTypeCriteria() {
            return typeCriteria;
        }

        protected void addTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.JobTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            typeCriteria
                    .add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.JobTypeTypeHandler"));
            allCriteria = null;
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

        public boolean isValid() {
            return criteria.size() > 0 || typeCriteria.size() > 0 || uuidCriteria.size() > 0
                    || statusCriteria.size() > 0 || parameterCriteria.size() > 0 || groupCriteria.size() > 0
                    || externalIdentifierCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(typeCriteria);
                allCriteria.addAll(uuidCriteria);
                allCriteria.addAll(statusCriteria);
                allCriteria.addAll(parameterCriteria);
                allCriteria.addAll(groupCriteria);
                allCriteria.addAll(externalIdentifierCriteria);
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

        public Criteria andIdEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andNameEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andTypeIsNull() {
            addCriterion("job_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("job_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addTypeCriterion("job_type_id =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("job_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addTypeCriterion("job_type_id <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("job_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addTypeCriterion("job_type_id >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("job_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addTypeCriterion("job_type_id >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("job_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addTypeCriterion("job_type_id <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("job_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addTypeCriterion("job_type_id <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("job_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addTypeCriterion("job_type_id in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addTypeCriterion("job_type_id not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addTypeCriterion("job_type_id between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addTypeCriterion("job_type_id not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("description = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("description <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("description > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("description >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("description < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("description <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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

        public Criteria andPriorityEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("priority = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("priority <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("priority > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("priority >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("priority < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andUuidEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(UUID value) {
            addUuidCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(UUID value) {
            addUuidCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(UUID value) {
            addUuidCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(UUID value) {
            addUuidCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(UUID value) {
            addUuidCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andStatusEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("status_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addStatusCriterion("status_id <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("status_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addStatusCriterion("status_id >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("status_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addStatusCriterion("status_id >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("status_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addStatusCriterion("status_id <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("status_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addStatusCriterion("status_id <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andIsBuiltIsNull() {
            addCriterion("is_built is null");
            return (Criteria) this;
        }

        public Criteria andIsBuiltIsNotNull() {
            addCriterion("is_built is not null");
            return (Criteria) this;
        }

        public Criteria andIsBuiltEqualTo(Boolean value) {
            addCriterion("is_built =", value, "isBuilt");
            return (Criteria) this;
        }

        public Criteria andIsBuiltEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("is_built = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsBuiltNotEqualTo(Boolean value) {
            addCriterion("is_built <>", value, "isBuilt");
            return (Criteria) this;
        }

        public Criteria andIsBuiltNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("is_built <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsBuiltGreaterThan(Boolean value) {
            addCriterion("is_built >", value, "isBuilt");
            return (Criteria) this;
        }

        public Criteria andIsBuiltGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("is_built > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsBuiltGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_built >=", value, "isBuilt");
            return (Criteria) this;
        }

        public Criteria andIsBuiltGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("is_built >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsBuiltLessThan(Boolean value) {
            addCriterion("is_built <", value, "isBuilt");
            return (Criteria) this;
        }

        public Criteria andIsBuiltLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("is_built < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsBuiltLessThanOrEqualTo(Boolean value) {
            addCriterion("is_built <=", value, "isBuilt");
            return (Criteria) this;
        }

        public Criteria andIsBuiltLessThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("is_built <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsBuiltIn(List<Boolean> values) {
            addCriterion("is_built in", values, "isBuilt");
            return (Criteria) this;
        }

        public Criteria andIsBuiltNotIn(List<Boolean> values) {
            addCriterion("is_built not in", values, "isBuilt");
            return (Criteria) this;
        }

        public Criteria andIsBuiltBetween(Boolean value1, Boolean value2) {
            addCriterion("is_built between", value1, value2, "isBuilt");
            return (Criteria) this;
        }

        public Criteria andIsBuiltNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_built not between", value1, value2, "isBuilt");
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

        public Criteria andFinishedEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("is_finished = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualTo(Boolean value) {
            addCriterion("is_finished <>", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("is_finished <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThan(Boolean value) {
            addCriterion("is_finished >", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("is_finished > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_finished >=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("is_finished >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThan(Boolean value) {
            addCriterion("is_finished <", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("is_finished < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_finished <=", value, "finished");
            return (Criteria) this;
        }

        public Criteria andFinishedLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andParameterEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("parameter = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualTo(JsonNode value) {
            addParameterCriterion("parameter <>", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("parameter <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThan(JsonNode value) {
            addParameterCriterion("parameter >", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("parameter > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualTo(JsonNode value) {
            addParameterCriterion("parameter >=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("parameter >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterLessThan(JsonNode value) {
            addParameterCriterion("parameter <", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("parameter < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualTo(JsonNode value) {
            addParameterCriterion("parameter <=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andGroupEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("group_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualTo(String value) {
            addGroupCriterion("group_id <>", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("group_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThan(String value) {
            addGroupCriterion("group_id >", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("group_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualTo(String value) {
            addGroupCriterion("group_id >=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("group_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThan(String value) {
            addGroupCriterion("group_id <", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("group_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualTo(String value) {
            addGroupCriterion("group_id <=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andUpdatedAtEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andCreatedAtEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andExternalIdentifierEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("external_identifier = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierNotEqualTo(UUID value) {
            addExternalIdentifierCriterion("external_identifier <>", value, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("external_identifier <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierGreaterThan(UUID value) {
            addExternalIdentifierCriterion("external_identifier >", value, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("external_identifier > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierGreaterThanOrEqualTo(UUID value) {
            addExternalIdentifierCriterion("external_identifier >=", value, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("external_identifier >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierLessThan(UUID value) {
            addExternalIdentifierCriterion("external_identifier <", value, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("external_identifier < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierLessThanOrEqualTo(UUID value) {
            addExternalIdentifierCriterion("external_identifier <=", value, "externalIdentifier");
            return (Criteria) this;
        }

        public Criteria andExternalIdentifierLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andExecutionStartAtEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("execution_start_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtNotEqualTo(Date value) {
            addCriterion("execution_start_at <>", value, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("execution_start_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtGreaterThan(Date value) {
            addCriterion("execution_start_at >", value, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("execution_start_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtGreaterThanOrEqualTo(Date value) {
            addCriterion("execution_start_at >=", value, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("execution_start_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtLessThan(Date value) {
            addCriterion("execution_start_at <", value, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("execution_start_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtLessThanOrEqualTo(Date value) {
            addCriterion("execution_start_at <=", value, "executionStartAt");
            return (Criteria) this;
        }

        public Criteria andExecutionStartAtLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andExecutionEndAtEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("execution_end_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtNotEqualTo(Date value) {
            addCriterion("execution_end_at <>", value, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtNotEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("execution_end_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtGreaterThan(Date value) {
            addCriterion("execution_end_at >", value, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtGreaterThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("execution_end_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtGreaterThanOrEqualTo(Date value) {
            addCriterion("execution_end_at >=", value, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtGreaterThanOrEqualToColumn(Job.Column column) {
            addCriterion(new StringBuilder("execution_end_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtLessThan(Date value) {
            addCriterion("execution_end_at <", value, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtLessThanColumn(Job.Column column) {
            addCriterion(new StringBuilder("execution_end_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtLessThanOrEqualTo(Date value) {
            addCriterion("execution_end_at <=", value, "executionEndAt");
            return (Criteria) this;
        }

        public Criteria andExecutionEndAtLessThanOrEqualToColumn(Job.Column column) {
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

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(description) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private JobExample example;

        protected Criteria(JobExample example) {
            super();
            this.example = example;
        }

        public JobExample example() {
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
        void example(com.meowlomo.ems.core.model.JobExample example);
    }
}