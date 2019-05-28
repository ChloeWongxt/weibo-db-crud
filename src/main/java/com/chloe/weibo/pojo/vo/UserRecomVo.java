package com.chloe.weibo.pojo.vo;

import java.util.Date;
import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/5/26
 */
public class UserRecomVo {
    private Integer userId;
    private String logName;
    private String userPassword;
    private String nickName;
    private String emailAddress;
    private String phoneNumber;
    private String userAvatar;
    private String userGender;
    private String userPerSignature;
    private String userPlace;
    private Date userBirthday;
    private List<String> tags;
    private Boolean isTag;
    private Boolean isDel;

    private String city;
    private Boolean isVip;
    private Boolean isIdentify;
    private String authentication;
    private Integer vipLevel;

    private Boolean isFollow;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserPerSignature() {
        return userPerSignature;
    }

    public void setUserPerSignature(String userPerSignature) {
        this.userPerSignature = userPerSignature;
    }

    public String getUserPlace() {
        return userPlace;
    }

    public void setUserPlace(String userPlace) {
        this.userPlace = userPlace;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getTag() {
        return isTag;
    }

    public void setTag(Boolean tag) {
        isTag = tag;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getVip() {
        return isVip;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }

    public Boolean getIdentify() {
        return isIdentify;
    }

    public void setIdentify(Boolean identify) {
        isIdentify = identify;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Boolean getFollow() {
        return isFollow;
    }

    public void setFollow(Boolean follow) {
        isFollow = follow;
    }

    public UserRecomVo(UserVo userVo,Boolean isFollow) {
        this.userId = userVo.getUserId();
        this.logName = userVo.getLogName();
        this.userPassword = userVo.getUserPassword();
        this.nickName = userVo.getNickName();
        this.emailAddress = userVo.getEmailAddress();
        this.phoneNumber = userVo.getPhoneNumber();
        this.userAvatar = userVo.getUserAvatar();
        this.userGender = userVo.getUserGender();
        this.userPerSignature = userVo.getUserPerSignature();
        this.userPlace = userVo.getUserPlace();
        this.userBirthday = userVo.getUserBirthday();
        this.tags = userVo.getTags();
        this.isTag = userVo.getTag();
        this.isDel = userVo.getDel();
        this.city = userVo.getCity();
        this.isVip = userVo.getVip();
        this.isIdentify = userVo.getIdentify();
        this.authentication = userVo.getAuthentication();
        this.vipLevel = userVo.getVipLevel();
        this.isFollow = isFollow;
    }

    public UserRecomVo() {
    }
}
