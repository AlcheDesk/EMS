package com.meowlomo.ems.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WorkerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WorkerExample() {
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

    public WorkerExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public WorkerExample orderBy(String... orderByClauses) {
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
        WorkerExample example = new WorkerExample();
        return example.createCriteria();
    }

    public WorkerExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public WorkerExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
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

        protected List<Criterion> operatingSystemCriteria;

        protected List<Criterion> statusCriteria;

        protected List<Criterion> groupCriteria;

        protected List<Criterion> typeCriteria;

        protected List<Criterion> tokenCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            uuidCriteria = new ArrayList<Criterion>();
            operatingSystemCriteria = new ArrayList<Criterion>();
            statusCriteria = new ArrayList<Criterion>();
            groupCriteria = new ArrayList<Criterion>();
            typeCriteria = new ArrayList<Criterion>();
            tokenCriteria = new ArrayList<Criterion>();
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

        public List<Criterion> getTypeCriteria() {
            return typeCriteria;
        }

        protected void addTypeCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            typeCriteria.add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.WorkerTypeTypeHandler"));
            allCriteria = null;
        }

        protected void addTypeCriterion(String condition, String value1, String value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            typeCriteria.add(
                    new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.WorkerTypeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getTokenCriteria() {
            return tokenCriteria;
        }

        protected void addTokenCriterion(String condition, Object value, String property) {
            if (value == null) { throw new RuntimeException("Value for " + property + " cannot be null"); }
            tokenCriteria.add(new Criterion(condition, value, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        protected void addTokenCriterion(String condition, UUID value1, UUID value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            tokenCriteria.add(new Criterion(condition, value1, value2, "com.meowlomo.ems.typehandler.UUIDTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0 || uuidCriteria.size() > 0 || operatingSystemCriteria.size() > 0
                    || statusCriteria.size() > 0 || groupCriteria.size() > 0 || typeCriteria.size() > 0
                    || tokenCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(uuidCriteria);
                allCriteria.addAll(operatingSystemCriteria);
                allCriteria.addAll(statusCriteria);
                allCriteria.addAll(groupCriteria);
                allCriteria.addAll(typeCriteria);
                allCriteria.addAll(tokenCriteria);
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

        public Criteria andIdEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualToColumn(Worker.Column column) {
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

        public Criteria andArchitectureIsNull() {
            addCriterion("architecture is null");
            return (Criteria) this;
        }

        public Criteria andArchitectureIsNotNull() {
            addCriterion("architecture is not null");
            return (Criteria) this;
        }

        public Criteria andArchitectureEqualTo(String value) {
            addCriterion("architecture =", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("architecture = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andArchitectureNotEqualTo(String value) {
            addCriterion("architecture <>", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("architecture <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andArchitectureGreaterThan(String value) {
            addCriterion("architecture >", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("architecture > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andArchitectureGreaterThanOrEqualTo(String value) {
            addCriterion("architecture >=", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("architecture >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andArchitectureLessThan(String value) {
            addCriterion("architecture <", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("architecture < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andArchitectureLessThanOrEqualTo(String value) {
            addCriterion("architecture <=", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("architecture <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andArchitectureLike(String value) {
            addCriterion("architecture like", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureNotLike(String value) {
            addCriterion("architecture not like", value, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureIn(List<String> values) {
            addCriterion("architecture in", values, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureNotIn(List<String> values) {
            addCriterion("architecture not in", values, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureBetween(String value1, String value2) {
            addCriterion("architecture between", value1, value2, "architecture");
            return (Criteria) this;
        }

        public Criteria andArchitectureNotBetween(String value1, String value2) {
            addCriterion("architecture not between", value1, value2, "architecture");
            return (Criteria) this;
        }

        public Criteria andCpuCoreIsNull() {
            addCriterion("cpu_core is null");
            return (Criteria) this;
        }

        public Criteria andCpuCoreIsNotNull() {
            addCriterion("cpu_core is not null");
            return (Criteria) this;
        }

        public Criteria andCpuCoreEqualTo(Integer value) {
            addCriterion("cpu_core =", value, "cpuCore");
            return (Criteria) this;
        }

        public Criteria andCpuCoreEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("cpu_core = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreNotEqualTo(Integer value) {
            addCriterion("cpu_core <>", value, "cpuCore");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("cpu_core <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreGreaterThan(Integer value) {
            addCriterion("cpu_core >", value, "cpuCore");
            return (Criteria) this;
        }

        public Criteria andCpuCoreGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("cpu_core > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("cpu_core >=", value, "cpuCore");
            return (Criteria) this;
        }

        public Criteria andCpuCoreGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("cpu_core >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreLessThan(Integer value) {
            addCriterion("cpu_core <", value, "cpuCore");
            return (Criteria) this;
        }

        public Criteria andCpuCoreLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("cpu_core < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreLessThanOrEqualTo(Integer value) {
            addCriterion("cpu_core <=", value, "cpuCore");
            return (Criteria) this;
        }

        public Criteria andCpuCoreLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("cpu_core <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCpuCoreIn(List<Integer> values) {
            addCriterion("cpu_core in", values, "cpuCore");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNotIn(List<Integer> values) {
            addCriterion("cpu_core not in", values, "cpuCore");
            return (Criteria) this;
        }

        public Criteria andCpuCoreBetween(Integer value1, Integer value2) {
            addCriterion("cpu_core between", value1, value2, "cpuCore");
            return (Criteria) this;
        }

        public Criteria andCpuCoreNotBetween(Integer value1, Integer value2) {
            addCriterion("cpu_core not between", value1, value2, "cpuCore");
            return (Criteria) this;
        }

        public Criteria andHostnameIsNull() {
            addCriterion("hostname is null");
            return (Criteria) this;
        }

        public Criteria andHostnameIsNotNull() {
            addCriterion("hostname is not null");
            return (Criteria) this;
        }

        public Criteria andHostnameEqualTo(String value) {
            addCriterion("hostname =", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("hostname = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostnameNotEqualTo(String value) {
            addCriterion("hostname <>", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("hostname <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostnameGreaterThan(String value) {
            addCriterion("hostname >", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("hostname > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostnameGreaterThanOrEqualTo(String value) {
            addCriterion("hostname >=", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("hostname >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostnameLessThan(String value) {
            addCriterion("hostname <", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("hostname < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostnameLessThanOrEqualTo(String value) {
            addCriterion("hostname <=", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("hostname <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andHostnameLike(String value) {
            addCriterion("hostname like", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotLike(String value) {
            addCriterion("hostname not like", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameIn(List<String> values) {
            addCriterion("hostname in", values, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotIn(List<String> values) {
            addCriterion("hostname not in", values, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameBetween(String value1, String value2) {
            addCriterion("hostname between", value1, value2, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotBetween(String value1, String value2) {
            addCriterion("hostname not between", value1, value2, "hostname");
            return (Criteria) this;
        }

        public Criteria andBandwidthIsNull() {
            addCriterion("bandwidth is null");
            return (Criteria) this;
        }

        public Criteria andBandwidthIsNotNull() {
            addCriterion("bandwidth is not null");
            return (Criteria) this;
        }

        public Criteria andBandwidthEqualTo(Integer value) {
            addCriterion("bandwidth =", value, "bandwidth");
            return (Criteria) this;
        }

        public Criteria andBandwidthEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("bandwidth = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthNotEqualTo(Integer value) {
            addCriterion("bandwidth <>", value, "bandwidth");
            return (Criteria) this;
        }

        public Criteria andBandwidthNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("bandwidth <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthGreaterThan(Integer value) {
            addCriterion("bandwidth >", value, "bandwidth");
            return (Criteria) this;
        }

        public Criteria andBandwidthGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("bandwidth > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("bandwidth >=", value, "bandwidth");
            return (Criteria) this;
        }

        public Criteria andBandwidthGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("bandwidth >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthLessThan(Integer value) {
            addCriterion("bandwidth <", value, "bandwidth");
            return (Criteria) this;
        }

        public Criteria andBandwidthLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("bandwidth < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthLessThanOrEqualTo(Integer value) {
            addCriterion("bandwidth <=", value, "bandwidth");
            return (Criteria) this;
        }

        public Criteria andBandwidthLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("bandwidth <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andBandwidthIn(List<Integer> values) {
            addCriterion("bandwidth in", values, "bandwidth");
            return (Criteria) this;
        }

        public Criteria andBandwidthNotIn(List<Integer> values) {
            addCriterion("bandwidth not in", values, "bandwidth");
            return (Criteria) this;
        }

        public Criteria andBandwidthBetween(Integer value1, Integer value2) {
            addCriterion("bandwidth between", value1, value2, "bandwidth");
            return (Criteria) this;
        }

        public Criteria andBandwidthNotBetween(Integer value1, Integer value2) {
            addCriterion("bandwidth not between", value1, value2, "bandwidth");
            return (Criteria) this;
        }

        public Criteria andIpAddressIsNull() {
            addCriterion("ip_address is null");
            return (Criteria) this;
        }

        public Criteria andIpAddressIsNotNull() {
            addCriterion("ip_address is not null");
            return (Criteria) this;
        }

        public Criteria andIpAddressEqualTo(String value) {
            addCriterion("ip_address =", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ip_address = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIpAddressNotEqualTo(String value) {
            addCriterion("ip_address <>", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ip_address <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThan(String value) {
            addCriterion("ip_address >", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ip_address > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ip_address >=", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ip_address >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThan(String value) {
            addCriterion("ip_address <", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ip_address < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThanOrEqualTo(String value) {
            addCriterion("ip_address <=", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ip_address <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIpAddressLike(String value) {
            addCriterion("ip_address like", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotLike(String value) {
            addCriterion("ip_address not like", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressIn(List<String> values) {
            addCriterion("ip_address in", values, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotIn(List<String> values) {
            addCriterion("ip_address not in", values, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressBetween(String value1, String value2) {
            addCriterion("ip_address between", value1, value2, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotBetween(String value1, String value2) {
            addCriterion("ip_address not between", value1, value2, "ipAddress");
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

        public Criteria andNameEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("name = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("name <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("name > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("name >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("name < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualToColumn(Worker.Column column) {
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

        public Criteria andRamIsNull() {
            addCriterion("ram is null");
            return (Criteria) this;
        }

        public Criteria andRamIsNotNull() {
            addCriterion("ram is not null");
            return (Criteria) this;
        }

        public Criteria andRamEqualTo(Integer value) {
            addCriterion("ram =", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ram = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamNotEqualTo(Integer value) {
            addCriterion("ram <>", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ram <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamGreaterThan(Integer value) {
            addCriterion("ram >", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ram > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamGreaterThanOrEqualTo(Integer value) {
            addCriterion("ram >=", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ram >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamLessThan(Integer value) {
            addCriterion("ram <", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ram < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamLessThanOrEqualTo(Integer value) {
            addCriterion("ram <=", value, "ram");
            return (Criteria) this;
        }

        public Criteria andRamLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("ram <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andRamIn(List<Integer> values) {
            addCriterion("ram in", values, "ram");
            return (Criteria) this;
        }

        public Criteria andRamNotIn(List<Integer> values) {
            addCriterion("ram not in", values, "ram");
            return (Criteria) this;
        }

        public Criteria andRamBetween(Integer value1, Integer value2) {
            addCriterion("ram between", value1, value2, "ram");
            return (Criteria) this;
        }

        public Criteria andRamNotBetween(Integer value1, Integer value2) {
            addCriterion("ram not between", value1, value2, "ram");
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

        public Criteria andUuidEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("uuid = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(UUID value) {
            addUuidCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("uuid <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(UUID value) {
            addUuidCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("uuid > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(UUID value) {
            addUuidCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("uuid >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(UUID value) {
            addUuidCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("uuid < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(UUID value) {
            addUuidCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualToColumn(Worker.Column column) {
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

        public Criteria andOperatingSystemEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("operating_system_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatingSystemNotEqualTo(String value) {
            addOperatingSystemCriterion("operating_system_id <>", value, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("operating_system_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatingSystemGreaterThan(String value) {
            addOperatingSystemCriterion("operating_system_id >", value, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("operating_system_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatingSystemGreaterThanOrEqualTo(String value) {
            addOperatingSystemCriterion("operating_system_id >=", value, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("operating_system_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatingSystemLessThan(String value) {
            addOperatingSystemCriterion("operating_system_id <", value, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("operating_system_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andOperatingSystemLessThanOrEqualTo(String value) {
            addOperatingSystemCriterion("operating_system_id <=", value, "operatingSystem");
            return (Criteria) this;
        }

        public Criteria andOperatingSystemLessThanOrEqualToColumn(Worker.Column column) {
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

        public Criteria andStatusEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("status_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addStatusCriterion("status_id <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("status_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addStatusCriterion("status_id >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("status_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addStatusCriterion("status_id >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("status_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addStatusCriterion("status_id <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("status_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addStatusCriterion("status_id <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualToColumn(Worker.Column column) {
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

        public Criteria andGroupEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("group_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualTo(String value) {
            addGroupCriterion("group_id <>", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("group_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThan(String value) {
            addGroupCriterion("group_id >", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("group_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualTo(String value) {
            addGroupCriterion("group_id >=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("group_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThan(String value) {
            addGroupCriterion("group_id <", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("group_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualTo(String value) {
            addGroupCriterion("group_id <=", value, "group");
            return (Criteria) this;
        }

        public Criteria andGroupLessThanOrEqualToColumn(Worker.Column column) {
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

        public Criteria andActiveIsNull() {
            addCriterion("is_active is null");
            return (Criteria) this;
        }

        public Criteria andActiveIsNotNull() {
            addCriterion("is_active is not null");
            return (Criteria) this;
        }

        public Criteria andActiveEqualTo(Boolean value) {
            addCriterion("is_active =", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("is_active = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualTo(Boolean value) {
            addCriterion("is_active <>", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("is_active <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThan(Boolean value) {
            addCriterion("is_active >", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("is_active > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_active >=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("is_active >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveLessThan(Boolean value) {
            addCriterion("is_active <", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("is_active < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("is_active <=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("is_active <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andActiveIn(List<Boolean> values) {
            addCriterion("is_active in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotIn(List<Boolean> values) {
            addCriterion("is_active not in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active not between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andMacAddressIsNull() {
            addCriterion("mac_address is null");
            return (Criteria) this;
        }

        public Criteria andMacAddressIsNotNull() {
            addCriterion("mac_address is not null");
            return (Criteria) this;
        }

        public Criteria andMacAddressEqualTo(String value) {
            addCriterion("mac_address =", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("mac_address = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMacAddressNotEqualTo(String value) {
            addCriterion("mac_address <>", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("mac_address <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMacAddressGreaterThan(String value) {
            addCriterion("mac_address >", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("mac_address > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMacAddressGreaterThanOrEqualTo(String value) {
            addCriterion("mac_address >=", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("mac_address >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMacAddressLessThan(String value) {
            addCriterion("mac_address <", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("mac_address < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMacAddressLessThanOrEqualTo(String value) {
            addCriterion("mac_address <=", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("mac_address <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andMacAddressLike(String value) {
            addCriterion("mac_address like", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotLike(String value) {
            addCriterion("mac_address not like", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressIn(List<String> values) {
            addCriterion("mac_address in", values, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotIn(List<String> values) {
            addCriterion("mac_address not in", values, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressBetween(String value1, String value2) {
            addCriterion("mac_address between", value1, value2, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotBetween(String value1, String value2) {
            addCriterion("mac_address not between", value1, value2, "macAddress");
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

        public Criteria andCreatedAtEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("created_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("created_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("created_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("created_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("created_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualToColumn(Worker.Column column) {
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

        public Criteria andUpdatedAtEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("updated_at = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("updated_at <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("updated_at > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("updated_at >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("updated_at < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualToColumn(Worker.Column column) {
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

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Integer value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("port = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Integer value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("port <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Integer value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("port > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("port >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Integer value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("port < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Integer value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("port <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Integer> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Integer> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Integer value1, Integer value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Integer value1, Integer value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("worker_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("worker_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addTypeCriterion("worker_type_id =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("worker_type_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addTypeCriterion("worker_type_id <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("worker_type_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addTypeCriterion("worker_type_id >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("worker_type_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addTypeCriterion("worker_type_id >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("worker_type_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addTypeCriterion("worker_type_id <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("worker_type_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addTypeCriterion("worker_type_id <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("worker_type_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addTypeCriterion("worker_type_id in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addTypeCriterion("worker_type_id not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addTypeCriterion("worker_type_id between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addTypeCriterion("worker_type_id not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andProtocolIsNull() {
            addCriterion("protocol is null");
            return (Criteria) this;
        }

        public Criteria andProtocolIsNotNull() {
            addCriterion("protocol is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolEqualTo(String value) {
            addCriterion("protocol =", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("protocol = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProtocolNotEqualTo(String value) {
            addCriterion("protocol <>", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("protocol <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProtocolGreaterThan(String value) {
            addCriterion("protocol >", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("protocol > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProtocolGreaterThanOrEqualTo(String value) {
            addCriterion("protocol >=", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("protocol >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProtocolLessThan(String value) {
            addCriterion("protocol <", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("protocol < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProtocolLessThanOrEqualTo(String value) {
            addCriterion("protocol <=", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("protocol <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andProtocolLike(String value) {
            addCriterion("protocol like", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotLike(String value) {
            addCriterion("protocol not like", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolIn(List<String> values) {
            addCriterion("protocol in", values, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotIn(List<String> values) {
            addCriterion("protocol not in", values, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolBetween(String value1, String value2) {
            addCriterion("protocol between", value1, value2, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotBetween(String value1, String value2) {
            addCriterion("protocol not between", value1, value2, "protocol");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Long value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("task_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Long value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("task_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Long value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("task_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Long value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("task_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Long value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("task_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Long value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("task_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Long> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Long> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Long value1, Long value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Long value1, Long value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andManageableIsNull() {
            addCriterion("manageable is null");
            return (Criteria) this;
        }

        public Criteria andManageableIsNotNull() {
            addCriterion("manageable is not null");
            return (Criteria) this;
        }

        public Criteria andManageableEqualTo(Boolean value) {
            addCriterion("manageable =", value, "manageable");
            return (Criteria) this;
        }

        public Criteria andManageableEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("manageable = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andManageableNotEqualTo(Boolean value) {
            addCriterion("manageable <>", value, "manageable");
            return (Criteria) this;
        }

        public Criteria andManageableNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("manageable <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andManageableGreaterThan(Boolean value) {
            addCriterion("manageable >", value, "manageable");
            return (Criteria) this;
        }

        public Criteria andManageableGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("manageable > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andManageableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("manageable >=", value, "manageable");
            return (Criteria) this;
        }

        public Criteria andManageableGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("manageable >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andManageableLessThan(Boolean value) {
            addCriterion("manageable <", value, "manageable");
            return (Criteria) this;
        }

        public Criteria andManageableLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("manageable < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andManageableLessThanOrEqualTo(Boolean value) {
            addCriterion("manageable <=", value, "manageable");
            return (Criteria) this;
        }

        public Criteria andManageableLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("manageable <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andManageableIn(List<Boolean> values) {
            addCriterion("manageable in", values, "manageable");
            return (Criteria) this;
        }

        public Criteria andManageableNotIn(List<Boolean> values) {
            addCriterion("manageable not in", values, "manageable");
            return (Criteria) this;
        }

        public Criteria andManageableBetween(Boolean value1, Boolean value2) {
            addCriterion("manageable between", value1, value2, "manageable");
            return (Criteria) this;
        }

        public Criteria andManageableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("manageable not between", value1, value2, "manageable");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("token is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("token is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(UUID value) {
            addTokenCriterion("token =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("token = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(UUID value) {
            addTokenCriterion("token <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("token <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(UUID value) {
            addTokenCriterion("token >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("token > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(UUID value) {
            addTokenCriterion("token >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("token >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(UUID value) {
            addTokenCriterion("token <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanColumn(Worker.Column column) {
            addCriterion(new StringBuilder("token < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(UUID value) {
            addTokenCriterion("token <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualToColumn(Worker.Column column) {
            addCriterion(new StringBuilder("token <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<UUID> values) {
            addTokenCriterion("token in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<UUID> values) {
            addTokenCriterion("token not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(UUID value1, UUID value2) {
            addTokenCriterion("token between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(UUID value1, UUID value2) {
            addTokenCriterion("token not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andArchitectureLikeInsensitive(String value) {
            addCriterion("upper(architecture) like", value.toUpperCase(), "architecture");
            return (Criteria) this;
        }

        public Criteria andHostnameLikeInsensitive(String value) {
            addCriterion("upper(hostname) like", value.toUpperCase(), "hostname");
            return (Criteria) this;
        }

        public Criteria andIpAddressLikeInsensitive(String value) {
            addCriterion("upper(ip_address) like", value.toUpperCase(), "ipAddress");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andMacAddressLikeInsensitive(String value) {
            addCriterion("upper(mac_address) like", value.toUpperCase(), "macAddress");
            return (Criteria) this;
        }

        public Criteria andProtocolLikeInsensitive(String value) {
            addCriterion("upper(protocol) like", value.toUpperCase(), "protocol");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        private WorkerExample example;

        protected Criteria(WorkerExample example) {
            super();
            this.example = example;
        }

        public WorkerExample example() {
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

        void example(com.meowlomo.ems.core.model.WorkerExample example);
    }
}