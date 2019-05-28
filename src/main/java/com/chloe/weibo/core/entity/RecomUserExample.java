package com.chloe.weibo.core.entity;

import java.util.ArrayList;
import java.util.List;

public class RecomUserExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    public RecomUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRecomIdIsNull() {
            addCriterion("recom_id is null");
            return (Criteria) this;
        }

        public Criteria andRecomIdIsNotNull() {
            addCriterion("recom_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecomIdEqualTo(Integer value) {
            addCriterion("recom_id =", value, "recomId");
            return (Criteria) this;
        }

        public Criteria andRecomIdNotEqualTo(Integer value) {
            addCriterion("recom_id <>", value, "recomId");
            return (Criteria) this;
        }

        public Criteria andRecomIdGreaterThan(Integer value) {
            addCriterion("recom_id >", value, "recomId");
            return (Criteria) this;
        }

        public Criteria andRecomIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_id >=", value, "recomId");
            return (Criteria) this;
        }

        public Criteria andRecomIdLessThan(Integer value) {
            addCriterion("recom_id <", value, "recomId");
            return (Criteria) this;
        }

        public Criteria andRecomIdLessThanOrEqualTo(Integer value) {
            addCriterion("recom_id <=", value, "recomId");
            return (Criteria) this;
        }

        public Criteria andRecomIdIn(List<Integer> values) {
            addCriterion("recom_id in", values, "recomId");
            return (Criteria) this;
        }

        public Criteria andRecomIdNotIn(List<Integer> values) {
            addCriterion("recom_id not in", values, "recomId");
            return (Criteria) this;
        }

        public Criteria andRecomIdBetween(Integer value1, Integer value2) {
            addCriterion("recom_id between", value1, value2, "recomId");
            return (Criteria) this;
        }

        public Criteria andRecomIdNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_id not between", value1, value2, "recomId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdIsNull() {
            addCriterion("recom_user1_id is null");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdIsNotNull() {
            addCriterion("recom_user1_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdEqualTo(Integer value) {
            addCriterion("recom_user1_id =", value, "recomUser1Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdNotEqualTo(Integer value) {
            addCriterion("recom_user1_id <>", value, "recomUser1Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdGreaterThan(Integer value) {
            addCriterion("recom_user1_id >", value, "recomUser1Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_user1_id >=", value, "recomUser1Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdLessThan(Integer value) {
            addCriterion("recom_user1_id <", value, "recomUser1Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdLessThanOrEqualTo(Integer value) {
            addCriterion("recom_user1_id <=", value, "recomUser1Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdIn(List<Integer> values) {
            addCriterion("recom_user1_id in", values, "recomUser1Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdNotIn(List<Integer> values) {
            addCriterion("recom_user1_id not in", values, "recomUser1Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdBetween(Integer value1, Integer value2) {
            addCriterion("recom_user1_id between", value1, value2, "recomUser1Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser1IdNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_user1_id not between", value1, value2, "recomUser1Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdIsNull() {
            addCriterion("recom_user2_id is null");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdIsNotNull() {
            addCriterion("recom_user2_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdEqualTo(Integer value) {
            addCriterion("recom_user2_id =", value, "recomUser2Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdNotEqualTo(Integer value) {
            addCriterion("recom_user2_id <>", value, "recomUser2Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdGreaterThan(Integer value) {
            addCriterion("recom_user2_id >", value, "recomUser2Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_user2_id >=", value, "recomUser2Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdLessThan(Integer value) {
            addCriterion("recom_user2_id <", value, "recomUser2Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdLessThanOrEqualTo(Integer value) {
            addCriterion("recom_user2_id <=", value, "recomUser2Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdIn(List<Integer> values) {
            addCriterion("recom_user2_id in", values, "recomUser2Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdNotIn(List<Integer> values) {
            addCriterion("recom_user2_id not in", values, "recomUser2Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdBetween(Integer value1, Integer value2) {
            addCriterion("recom_user2_id between", value1, value2, "recomUser2Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser2IdNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_user2_id not between", value1, value2, "recomUser2Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdIsNull() {
            addCriterion("recom_user3_id is null");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdIsNotNull() {
            addCriterion("recom_user3_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdEqualTo(Integer value) {
            addCriterion("recom_user3_id =", value, "recomUser3Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdNotEqualTo(Integer value) {
            addCriterion("recom_user3_id <>", value, "recomUser3Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdGreaterThan(Integer value) {
            addCriterion("recom_user3_id >", value, "recomUser3Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_user3_id >=", value, "recomUser3Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdLessThan(Integer value) {
            addCriterion("recom_user3_id <", value, "recomUser3Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdLessThanOrEqualTo(Integer value) {
            addCriterion("recom_user3_id <=", value, "recomUser3Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdIn(List<Integer> values) {
            addCriterion("recom_user3_id in", values, "recomUser3Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdNotIn(List<Integer> values) {
            addCriterion("recom_user3_id not in", values, "recomUser3Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdBetween(Integer value1, Integer value2) {
            addCriterion("recom_user3_id between", value1, value2, "recomUser3Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser3IdNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_user3_id not between", value1, value2, "recomUser3Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdIsNull() {
            addCriterion("recom_user4_id is null");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdIsNotNull() {
            addCriterion("recom_user4_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdEqualTo(Integer value) {
            addCriterion("recom_user4_id =", value, "recomUser4Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdNotEqualTo(Integer value) {
            addCriterion("recom_user4_id <>", value, "recomUser4Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdGreaterThan(Integer value) {
            addCriterion("recom_user4_id >", value, "recomUser4Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_user4_id >=", value, "recomUser4Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdLessThan(Integer value) {
            addCriterion("recom_user4_id <", value, "recomUser4Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdLessThanOrEqualTo(Integer value) {
            addCriterion("recom_user4_id <=", value, "recomUser4Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdIn(List<Integer> values) {
            addCriterion("recom_user4_id in", values, "recomUser4Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdNotIn(List<Integer> values) {
            addCriterion("recom_user4_id not in", values, "recomUser4Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdBetween(Integer value1, Integer value2) {
            addCriterion("recom_user4_id between", value1, value2, "recomUser4Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser4IdNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_user4_id not between", value1, value2, "recomUser4Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdIsNull() {
            addCriterion("recom_user5_id is null");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdIsNotNull() {
            addCriterion("recom_user5_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdEqualTo(Integer value) {
            addCriterion("recom_user5_id =", value, "recomUser5Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdNotEqualTo(Integer value) {
            addCriterion("recom_user5_id <>", value, "recomUser5Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdGreaterThan(Integer value) {
            addCriterion("recom_user5_id >", value, "recomUser5Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_user5_id >=", value, "recomUser5Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdLessThan(Integer value) {
            addCriterion("recom_user5_id <", value, "recomUser5Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdLessThanOrEqualTo(Integer value) {
            addCriterion("recom_user5_id <=", value, "recomUser5Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdIn(List<Integer> values) {
            addCriterion("recom_user5_id in", values, "recomUser5Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdNotIn(List<Integer> values) {
            addCriterion("recom_user5_id not in", values, "recomUser5Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdBetween(Integer value1, Integer value2) {
            addCriterion("recom_user5_id between", value1, value2, "recomUser5Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser5IdNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_user5_id not between", value1, value2, "recomUser5Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdIsNull() {
            addCriterion("recom_user6_id is null");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdIsNotNull() {
            addCriterion("recom_user6_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdEqualTo(Integer value) {
            addCriterion("recom_user6_id =", value, "recomUser6Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdNotEqualTo(Integer value) {
            addCriterion("recom_user6_id <>", value, "recomUser6Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdGreaterThan(Integer value) {
            addCriterion("recom_user6_id >", value, "recomUser6Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_user6_id >=", value, "recomUser6Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdLessThan(Integer value) {
            addCriterion("recom_user6_id <", value, "recomUser6Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdLessThanOrEqualTo(Integer value) {
            addCriterion("recom_user6_id <=", value, "recomUser6Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdIn(List<Integer> values) {
            addCriterion("recom_user6_id in", values, "recomUser6Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdNotIn(List<Integer> values) {
            addCriterion("recom_user6_id not in", values, "recomUser6Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdBetween(Integer value1, Integer value2) {
            addCriterion("recom_user6_id between", value1, value2, "recomUser6Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser6IdNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_user6_id not between", value1, value2, "recomUser6Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdIsNull() {
            addCriterion("recom_user7_id is null");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdIsNotNull() {
            addCriterion("recom_user7_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdEqualTo(Integer value) {
            addCriterion("recom_user7_id =", value, "recomUser7Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdNotEqualTo(Integer value) {
            addCriterion("recom_user7_id <>", value, "recomUser7Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdGreaterThan(Integer value) {
            addCriterion("recom_user7_id >", value, "recomUser7Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_user7_id >=", value, "recomUser7Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdLessThan(Integer value) {
            addCriterion("recom_user7_id <", value, "recomUser7Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdLessThanOrEqualTo(Integer value) {
            addCriterion("recom_user7_id <=", value, "recomUser7Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdIn(List<Integer> values) {
            addCriterion("recom_user7_id in", values, "recomUser7Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdNotIn(List<Integer> values) {
            addCriterion("recom_user7_id not in", values, "recomUser7Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdBetween(Integer value1, Integer value2) {
            addCriterion("recom_user7_id between", value1, value2, "recomUser7Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser7IdNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_user7_id not between", value1, value2, "recomUser7Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdIsNull() {
            addCriterion("recom_user8_id is null");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdIsNotNull() {
            addCriterion("recom_user8_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdEqualTo(Integer value) {
            addCriterion("recom_user8_id =", value, "recomUser8Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdNotEqualTo(Integer value) {
            addCriterion("recom_user8_id <>", value, "recomUser8Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdGreaterThan(Integer value) {
            addCriterion("recom_user8_id >", value, "recomUser8Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_user8_id >=", value, "recomUser8Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdLessThan(Integer value) {
            addCriterion("recom_user8_id <", value, "recomUser8Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdLessThanOrEqualTo(Integer value) {
            addCriterion("recom_user8_id <=", value, "recomUser8Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdIn(List<Integer> values) {
            addCriterion("recom_user8_id in", values, "recomUser8Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdNotIn(List<Integer> values) {
            addCriterion("recom_user8_id not in", values, "recomUser8Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdBetween(Integer value1, Integer value2) {
            addCriterion("recom_user8_id between", value1, value2, "recomUser8Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser8IdNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_user8_id not between", value1, value2, "recomUser8Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdIsNull() {
            addCriterion("recom_user9_id is null");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdIsNotNull() {
            addCriterion("recom_user9_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdEqualTo(Integer value) {
            addCriterion("recom_user9_id =", value, "recomUser9Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdNotEqualTo(Integer value) {
            addCriterion("recom_user9_id <>", value, "recomUser9Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdGreaterThan(Integer value) {
            addCriterion("recom_user9_id >", value, "recomUser9Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_user9_id >=", value, "recomUser9Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdLessThan(Integer value) {
            addCriterion("recom_user9_id <", value, "recomUser9Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdLessThanOrEqualTo(Integer value) {
            addCriterion("recom_user9_id <=", value, "recomUser9Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdIn(List<Integer> values) {
            addCriterion("recom_user9_id in", values, "recomUser9Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdNotIn(List<Integer> values) {
            addCriterion("recom_user9_id not in", values, "recomUser9Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdBetween(Integer value1, Integer value2) {
            addCriterion("recom_user9_id between", value1, value2, "recomUser9Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser9IdNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_user9_id not between", value1, value2, "recomUser9Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdIsNull() {
            addCriterion("recom_user10_id is null");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdIsNotNull() {
            addCriterion("recom_user10_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdEqualTo(Integer value) {
            addCriterion("recom_user10_id =", value, "recomUser10Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdNotEqualTo(Integer value) {
            addCriterion("recom_user10_id <>", value, "recomUser10Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdGreaterThan(Integer value) {
            addCriterion("recom_user10_id >", value, "recomUser10Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_user10_id >=", value, "recomUser10Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdLessThan(Integer value) {
            addCriterion("recom_user10_id <", value, "recomUser10Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdLessThanOrEqualTo(Integer value) {
            addCriterion("recom_user10_id <=", value, "recomUser10Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdIn(List<Integer> values) {
            addCriterion("recom_user10_id in", values, "recomUser10Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdNotIn(List<Integer> values) {
            addCriterion("recom_user10_id not in", values, "recomUser10Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdBetween(Integer value1, Integer value2) {
            addCriterion("recom_user10_id between", value1, value2, "recomUser10Id");
            return (Criteria) this;
        }

        public Criteria andRecomUser10IdNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_user10_id not between", value1, value2, "recomUser10Id");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_recom_user
     *
     * @mbggenerated do_not_delete_during_merge Sat May 25 20:24:43 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_recom_user
     *
     * @mbggenerated Sat May 25 20:24:43 CST 2019
     */
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
            } else {
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
}