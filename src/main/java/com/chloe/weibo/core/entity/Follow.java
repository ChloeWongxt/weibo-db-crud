package com.chloe.weibo.core.entity;

import java.util.Date;

public class Follow {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_follow.follow_id
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    private Integer followId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_follow.follow_user_id
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    private Integer followUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_follow.be_followed_user_id
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    private Integer beFollowedUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_follow.gmt_create
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_follow.gmt_modify
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    private Date gmtModify;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_follow.is_del
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    private Boolean isDel;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_follow.follow_id
     *
     * @return the value of tb_follow.follow_id
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public Integer getFollowId() {
        return followId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_follow.follow_id
     *
     * @param followId the value for tb_follow.follow_id
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public void setFollowId(Integer followId) {
        this.followId = followId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_follow.follow_user_id
     *
     * @return the value of tb_follow.follow_user_id
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public Integer getFollowUserId() {
        return followUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_follow.follow_user_id
     *
     * @param followUserId the value for tb_follow.follow_user_id
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public void setFollowUserId(Integer followUserId) {
        this.followUserId = followUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_follow.be_followed_user_id
     *
     * @return the value of tb_follow.be_followed_user_id
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public Integer getBeFollowedUserId() {
        return beFollowedUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_follow.be_followed_user_id
     *
     * @param beFollowedUserId the value for tb_follow.be_followed_user_id
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public void setBeFollowedUserId(Integer beFollowedUserId) {
        this.beFollowedUserId = beFollowedUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_follow.gmt_create
     *
     * @return the value of tb_follow.gmt_create
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_follow.gmt_create
     *
     * @param gmtCreate the value for tb_follow.gmt_create
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_follow.gmt_modify
     *
     * @return the value of tb_follow.gmt_modify
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public Date getGmtModify() {
        return gmtModify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_follow.gmt_modify
     *
     * @param gmtModify the value for tb_follow.gmt_modify
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_follow.is_del
     *
     * @return the value of tb_follow.is_del
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_follow.is_del
     *
     * @param isDel the value for tb_follow.is_del
     *
     * @mbggenerated Mon Apr 08 15:40:48 CST 2019
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}