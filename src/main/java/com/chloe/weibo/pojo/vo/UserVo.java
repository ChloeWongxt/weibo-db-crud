package com.chloe.weibo.pojo.vo;

import com.chloe.weibo.core.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/8
 */
public class UserVo {
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

    public UserVo(User user, List<String> tags) {

        this.userId = user.getUserId();
        this.logName = user.getLogName();
        this.userPassword=user.getUserPassword();
        this.nickName = user.getNickName();
        this.emailAddress = user.getEmailAddress();
        this.phoneNumber = user.getPhoneNumber();
        this.userAvatar = user.getUserAvatar();
        this.userGender = user.getUserGender();
        this.userPerSignature = user.getUserPerSignature();
        this.userPlace = user.getUserPlace();
        this.userBirthday = user.getUserBirthday();
        this.tags = tags;
        this.isTag = user.getIsTag();
        this.isDel = user.getIsDel();
        this.city=user.getCity();
        this.isVip=user.getIsVip();
        this.isIdentify=user.getIsIdentify();
        this.authentication=user.getAuthentication();
        this.vipLevel=user.getVipLevel();
    }

    public UserVo() {
    }
}
