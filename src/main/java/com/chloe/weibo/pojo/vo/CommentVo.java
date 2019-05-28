package com.chloe.weibo.pojo.vo;

import com.chloe.weibo.core.entity.Comment;
import com.chloe.weibo.core.entity.User;

import java.text.DateFormat;

/**
 * @author ChloeWong
 * @date 2019/4/8
 */
public class CommentVo {
    private Integer comId;
    private Integer userId;
    private String nickName;
    private String userAvatar;
    private Integer weiboId;
    private String comContent;
    private String gmtCreate;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Integer weiboId) {
        this.weiboId = weiboId;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public CommentVo(Comment com, User user) {
        this.comId = com.getComId();
        this.userId = user.getUserId();
        this.nickName = user.getNickName();
        this.userAvatar = user.getUserAvatar();
        this.weiboId = com.getWeiboId();
        this.comContent = com.getComContent();
        DateFormat dtf=DateFormat.getDateTimeInstance();
        this.gmtCreate = dtf.format(com.getGmtCreate());
    }
}
