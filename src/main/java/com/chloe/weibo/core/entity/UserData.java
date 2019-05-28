package com.chloe.weibo.core.entity;

public class UserData {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_userdata.user_id
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_userdata.weibo_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    private Integer weiboAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_userdata.be_followed_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    private Integer beFollowedAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_userdata.follow_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    private Integer followAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_userdata.mutual_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    private Integer mutualAmount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_userdata.user_id
     *
     * @return the value of tb_userdata.user_id
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_userdata.user_id
     *
     * @param userId the value for tb_userdata.user_id
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_userdata.weibo_amount
     *
     * @return the value of tb_userdata.weibo_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    public Integer getWeiboAmount() {
        return weiboAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_userdata.weibo_amount
     *
     * @param weiboAmount the value for tb_userdata.weibo_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    public void setWeiboAmount(Integer weiboAmount) {
        this.weiboAmount = weiboAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_userdata.be_followed_amount
     *
     * @return the value of tb_userdata.be_followed_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    public Integer getBeFollowedAmount() {
        return beFollowedAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_userdata.be_followed_amount
     *
     * @param beFollowedAmount the value for tb_userdata.be_followed_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    public void setBeFollowedAmount(Integer beFollowedAmount) {
        this.beFollowedAmount = beFollowedAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_userdata.follow_amount
     *
     * @return the value of tb_userdata.follow_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    public Integer getFollowAmount() {
        return followAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_userdata.follow_amount
     *
     * @param followAmount the value for tb_userdata.follow_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    public void setFollowAmount(Integer followAmount) {
        this.followAmount = followAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_userdata.mutual_amount
     *
     * @return the value of tb_userdata.mutual_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    public Integer getMutualAmount() {
        return mutualAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_userdata.mutual_amount
     *
     * @param mutualAmount the value for tb_userdata.mutual_amount
     *
     * @mbggenerated Sun Apr 14 16:59:11 CST 2019
     */
    public void setMutualAmount(Integer mutualAmount) {
        this.mutualAmount = mutualAmount;
    }

    public UserData() {
    }

    public UserData(Integer userId) {
        this.userId = userId;
        this.weiboAmount = 0;
        this.beFollowedAmount = 0;
        this.followAmount = 0;
        this.mutualAmount = 0;
    }
}