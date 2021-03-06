package com.chloe.weibo.core.entity.entityExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeiboExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public WeiboExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
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
     * This method corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
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

        public Criteria andWeiboIdIsNull() {
            addCriterion("weibo_id is null");
            return (Criteria) this;
        }

        public Criteria andWeiboIdIsNotNull() {
            addCriterion("weibo_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeiboIdEqualTo(Integer value) {
            addCriterion("weibo_id =", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdNotEqualTo(Integer value) {
            addCriterion("weibo_id <>", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdGreaterThan(Integer value) {
            addCriterion("weibo_id >", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("weibo_id >=", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdLessThan(Integer value) {
            addCriterion("weibo_id <", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdLessThanOrEqualTo(Integer value) {
            addCriterion("weibo_id <=", value, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdIn(List<Integer> values) {
            addCriterion("weibo_id in", values, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdNotIn(List<Integer> values) {
            addCriterion("weibo_id not in", values, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdBetween(Integer value1, Integer value2) {
            addCriterion("weibo_id between", value1, value2, "weiboId");
            return (Criteria) this;
        }

        public Criteria andWeiboIdNotBetween(Integer value1, Integer value2) {
            addCriterion("weibo_id not between", value1, value2, "weiboId");
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

        public Criteria andPublishTypeIsNull() {
            addCriterion("publish_type is null");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIsNotNull() {
            addCriterion("publish_type is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTypeEqualTo(Integer value) {
            addCriterion("publish_type =", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNotEqualTo(Integer value) {
            addCriterion("publish_type <>", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeGreaterThan(Integer value) {
            addCriterion("publish_type >", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("publish_type >=", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeLessThan(Integer value) {
            addCriterion("publish_type <", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeLessThanOrEqualTo(Integer value) {
            addCriterion("publish_type <=", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIn(List<Integer> values) {
            addCriterion("publish_type in", values, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNotIn(List<Integer> values) {
            addCriterion("publish_type not in", values, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeBetween(Integer value1, Integer value2) {
            addCriterion("publish_type between", value1, value2, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("publish_type not between", value1, value2, "publishType");
            return (Criteria) this;
        }

        public Criteria andWeiboContentIsNull() {
            addCriterion("weibo_content is null");
            return (Criteria) this;
        }

        public Criteria andWeiboContentIsNotNull() {
            addCriterion("weibo_content is not null");
            return (Criteria) this;
        }

        public Criteria andWeiboContentEqualTo(String value) {
            addCriterion("weibo_content =", value, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboContentNotEqualTo(String value) {
            addCriterion("weibo_content <>", value, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboContentGreaterThan(String value) {
            addCriterion("weibo_content >", value, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboContentGreaterThanOrEqualTo(String value) {
            addCriterion("weibo_content >=", value, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboContentLessThan(String value) {
            addCriterion("weibo_content <", value, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboContentLessThanOrEqualTo(String value) {
            addCriterion("weibo_content <=", value, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboContentLike(String value) {
            addCriterion("weibo_content like", value, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboContentNotLike(String value) {
            addCriterion("weibo_content not like", value, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboContentIn(List<String> values) {
            addCriterion("weibo_content in", values, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboContentNotIn(List<String> values) {
            addCriterion("weibo_content not in", values, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboContentBetween(String value1, String value2) {
            addCriterion("weibo_content between", value1, value2, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboContentNotBetween(String value1, String value2) {
            addCriterion("weibo_content not between", value1, value2, "weiboContent");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeIsNull() {
            addCriterion("weibo_type is null");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeIsNotNull() {
            addCriterion("weibo_type is not null");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeEqualTo(Boolean value) {
            addCriterion("weibo_type =", value, "weiboType");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeNotEqualTo(Boolean value) {
            addCriterion("weibo_type <>", value, "weiboType");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeGreaterThan(Boolean value) {
            addCriterion("weibo_type >", value, "weiboType");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("weibo_type >=", value, "weiboType");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeLessThan(Boolean value) {
            addCriterion("weibo_type <", value, "weiboType");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("weibo_type <=", value, "weiboType");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeIn(List<Boolean> values) {
            addCriterion("weibo_type in", values, "weiboType");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeNotIn(List<Boolean> values) {
            addCriterion("weibo_type not in", values, "weiboType");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("weibo_type between", value1, value2, "weiboType");
            return (Criteria) this;
        }

        public Criteria andWeiboTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("weibo_type not between", value1, value2, "weiboType");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountIsNull() {
            addCriterion("forwarding_amount is null");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountIsNotNull() {
            addCriterion("forwarding_amount is not null");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountEqualTo(Integer value) {
            addCriterion("forwarding_amount =", value, "forwardingAmount");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountNotEqualTo(Integer value) {
            addCriterion("forwarding_amount <>", value, "forwardingAmount");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountGreaterThan(Integer value) {
            addCriterion("forwarding_amount >", value, "forwardingAmount");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("forwarding_amount >=", value, "forwardingAmount");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountLessThan(Integer value) {
            addCriterion("forwarding_amount <", value, "forwardingAmount");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountLessThanOrEqualTo(Integer value) {
            addCriterion("forwarding_amount <=", value, "forwardingAmount");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountIn(List<Integer> values) {
            addCriterion("forwarding_amount in", values, "forwardingAmount");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountNotIn(List<Integer> values) {
            addCriterion("forwarding_amount not in", values, "forwardingAmount");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountBetween(Integer value1, Integer value2) {
            addCriterion("forwarding_amount between", value1, value2, "forwardingAmount");
            return (Criteria) this;
        }

        public Criteria andForwardingAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("forwarding_amount not between", value1, value2, "forwardingAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountIsNull() {
            addCriterion("comment_amount is null");
            return (Criteria) this;
        }

        public Criteria andCommentAmountIsNotNull() {
            addCriterion("comment_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCommentAmountEqualTo(Integer value) {
            addCriterion("comment_amount =", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountNotEqualTo(Integer value) {
            addCriterion("comment_amount <>", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountGreaterThan(Integer value) {
            addCriterion("comment_amount >", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_amount >=", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountLessThan(Integer value) {
            addCriterion("comment_amount <", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountLessThanOrEqualTo(Integer value) {
            addCriterion("comment_amount <=", value, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountIn(List<Integer> values) {
            addCriterion("comment_amount in", values, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountNotIn(List<Integer> values) {
            addCriterion("comment_amount not in", values, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountBetween(Integer value1, Integer value2) {
            addCriterion("comment_amount between", value1, value2, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andCommentAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_amount not between", value1, value2, "commentAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountIsNull() {
            addCriterion("like_amount is null");
            return (Criteria) this;
        }

        public Criteria andLikeAmountIsNotNull() {
            addCriterion("like_amount is not null");
            return (Criteria) this;
        }

        public Criteria andLikeAmountEqualTo(Integer value) {
            addCriterion("like_amount =", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountNotEqualTo(Integer value) {
            addCriterion("like_amount <>", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountGreaterThan(Integer value) {
            addCriterion("like_amount >", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_amount >=", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountLessThan(Integer value) {
            addCriterion("like_amount <", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountLessThanOrEqualTo(Integer value) {
            addCriterion("like_amount <=", value, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountIn(List<Integer> values) {
            addCriterion("like_amount in", values, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountNotIn(List<Integer> values) {
            addCriterion("like_amount not in", values, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountBetween(Integer value1, Integer value2) {
            addCriterion("like_amount between", value1, value2, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andLikeAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("like_amount not between", value1, value2, "likeAmount");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNull() {
            addCriterion("gmt_modify is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNotNull() {
            addCriterion("gmt_modify is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyEqualTo(Date value) {
            addCriterion("gmt_modify =", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotEqualTo(Date value) {
            addCriterion("gmt_modify <>", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThan(Date value) {
            addCriterion("gmt_modify >", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modify >=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThan(Date value) {
            addCriterion("gmt_modify <", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modify <=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIn(List<Date> values) {
            addCriterion("gmt_modify in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotIn(List<Date> values) {
            addCriterion("gmt_modify not in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyBetween(Date value1, Date value2) {
            addCriterion("gmt_modify between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modify not between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextIsNull() {
            addCriterion("is_only_text is null");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextIsNotNull() {
            addCriterion("is_only_text is not null");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextEqualTo(Boolean value) {
            addCriterion("is_only_text =", value, "isOnlyText");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextNotEqualTo(Boolean value) {
            addCriterion("is_only_text <>", value, "isOnlyText");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextGreaterThan(Boolean value) {
            addCriterion("is_only_text >", value, "isOnlyText");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_only_text >=", value, "isOnlyText");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextLessThan(Boolean value) {
            addCriterion("is_only_text <", value, "isOnlyText");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextLessThanOrEqualTo(Boolean value) {
            addCriterion("is_only_text <=", value, "isOnlyText");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextIn(List<Boolean> values) {
            addCriterion("is_only_text in", values, "isOnlyText");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextNotIn(List<Boolean> values) {
            addCriterion("is_only_text not in", values, "isOnlyText");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextBetween(Boolean value1, Boolean value2) {
            addCriterion("is_only_text between", value1, value2, "isOnlyText");
            return (Criteria) this;
        }

        public Criteria andIsOnlyTextNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_only_text not between", value1, value2, "isOnlyText");
            return (Criteria) this;
        }

        public Criteria andIsTopicIsNull() {
            addCriterion("is_topic is null");
            return (Criteria) this;
        }

        public Criteria andIsTopicIsNotNull() {
            addCriterion("is_topic is not null");
            return (Criteria) this;
        }

        public Criteria andIsTopicEqualTo(Boolean value) {
            addCriterion("is_topic =", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicNotEqualTo(Boolean value) {
            addCriterion("is_topic <>", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicGreaterThan(Boolean value) {
            addCriterion("is_topic >", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_topic >=", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicLessThan(Boolean value) {
            addCriterion("is_topic <", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicLessThanOrEqualTo(Boolean value) {
            addCriterion("is_topic <=", value, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicIn(List<Boolean> values) {
            addCriterion("is_topic in", values, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicNotIn(List<Boolean> values) {
            addCriterion("is_topic not in", values, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicBetween(Boolean value1, Boolean value2) {
            addCriterion("is_topic between", value1, value2, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsTopicNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_topic not between", value1, value2, "isTopic");
            return (Criteria) this;
        }

        public Criteria andIsModifyIsNull() {
            addCriterion("is_modify is null");
            return (Criteria) this;
        }

        public Criteria andIsModifyIsNotNull() {
            addCriterion("is_modify is not null");
            return (Criteria) this;
        }

        public Criteria andIsModifyEqualTo(Boolean value) {
            addCriterion("is_modify =", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyNotEqualTo(Boolean value) {
            addCriterion("is_modify <>", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyGreaterThan(Boolean value) {
            addCriterion("is_modify >", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_modify >=", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyLessThan(Boolean value) {
            addCriterion("is_modify <", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyLessThanOrEqualTo(Boolean value) {
            addCriterion("is_modify <=", value, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyIn(List<Boolean> values) {
            addCriterion("is_modify in", values, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyNotIn(List<Boolean> values) {
            addCriterion("is_modify not in", values, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyBetween(Boolean value1, Boolean value2) {
            addCriterion("is_modify between", value1, value2, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsModifyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_modify not between", value1, value2, "isModify");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Boolean value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Boolean value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Boolean value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Boolean value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Boolean value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Boolean> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Boolean> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Boolean value1, Boolean value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_weibo
     *
     * @mbggenerated do_not_delete_during_merge Mon Apr 08 15:40:48 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_weibo
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
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