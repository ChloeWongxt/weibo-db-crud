package com.chloe.weibo.pojo.vo;

import com.chloe.weibo.core.entity.User;
import com.chloe.weibo.core.entity.Weibo;

import java.text.DateFormat;
import java.util.List;
import java.util.Objects;

/**
 * @author ChloeWong
 * @date 2019/4/8
 */
public class WeiboVo {
    private Integer weiboId;
    private Integer userId;
    private String nickName;
    private String userAvatar;
    private List<String> files;
    private List<String> topics;
    private Integer publishType;
    private String weiboContent;
    private Boolean weiboType;
    private Integer forwardingAmount;
    private Integer commentAmount;
    private Integer likeAmount;
    private String gmtCreate;
    private Boolean isOnlyText;
    private Boolean isTopic;
    private Boolean isModify;
    private String fileList;
    private Boolean isLike;
    private Boolean isCollect;
    private WeiboVo fatherWeibo;

    public Integer getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Integer weiboId) {
        this.weiboId = weiboId;
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

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }

    public String getWeiboContent() {
        return weiboContent;
    }

    public void setWeiboContent(String weiboContent) {
        this.weiboContent = weiboContent;
    }

    public Boolean getWeiboType() {
        return weiboType;
    }

    public void setWeiboType(Boolean weiboType) {
        this.weiboType = weiboType;
    }

    public Integer getForwardingAmount() {
        return forwardingAmount;
    }

    public void setForwardingAmount(Integer forwardingAmount) {
        this.forwardingAmount = forwardingAmount;
    }

    public Integer getCommentAmount() {
        return commentAmount;
    }

    public void setCommentAmount(Integer commentAmount) {
        this.commentAmount = commentAmount;
    }

    public Integer getLikeAmount() {
        return likeAmount;
    }

    public void setLikeAmount(Integer likeAmount) {
        this.likeAmount = likeAmount;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Boolean getIsOnlyText() {
        return isOnlyText;
    }

    public void setIsOnlyText(Boolean onlyText) {
        isOnlyText = onlyText;
    }

    public Boolean getIsTopic() {
        return isTopic;
    }

    public void setTopic(Boolean topic) {
        isTopic = topic;
    }

    public Boolean getIsModify() {
        return isModify;
    }

    public void setModify(Boolean modify) {
        isModify = modify;
    }

    public String getFileList() {
        return fileList;
    }

    public void setFileList(String fileList) {
        this.fileList = fileList;
    }

    public Boolean getIsLike() {
        return isLike;
    }

    public void setIsLike(Boolean like) {
        isLike = like;
    }

    public Boolean getCollect() {
        return isCollect;
    }

    public void setCollect(Boolean collect) {
        isCollect = collect;
    }

    public WeiboVo getFatherWeibo() {
        return fatherWeibo;
    }

    public void setFatherWeibo(WeiboVo fatherWeibo) {
        this.fatherWeibo = fatherWeibo;
    }

    public WeiboVo(Weibo weibo, User user, List<String> files, List<String> topics, Boolean isLike, Boolean isCollect,WeiboVo fatherWeibo) {
        this.weiboId = weibo.getWeiboId();
        this.userId = weibo.getUserId();
        this.nickName = user.getNickName();
        this.userAvatar = user.getUserAvatar();
        this.files = files;
        this.topics = topics;
        this.publishType = weibo.getPublishType();
        this.weiboContent = weibo.getWeiboContent();
        this.weiboType = weibo.getWeiboType();
        this.forwardingAmount = weibo.getForwardingAmount();
        this.commentAmount = weibo.getCommentAmount();
        this.likeAmount = weibo.getLikeAmount();
        DateFormat dtf=DateFormat.getDateTimeInstance();
        this.gmtCreate = dtf.format(weibo.getGmtCreate());
        this.isOnlyText = weibo.getIsOnlyText();
        this.isTopic = weibo.getIsTopic();
        this.isModify = weibo.getIsModify();
        if (files!=null){
            this.fileList=files.get(0);
        }else {
            this.fileList=null;
        }
        this.isCollect=isCollect;
        this.isLike=isLike;
        this.fatherWeibo=fatherWeibo;
    }

    public WeiboVo() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        WeiboVo weiboVo = (WeiboVo) object;

        boolean isFilesEquals=true;
        if ((weiboVo.files==null&&files!=null)||(weiboVo.files!=null&&files==null)){
            isFilesEquals=false;
        }else if (weiboVo.files!=null&&files!=null){
            for(int i=0;i<files.size();i++){
                if(files.indexOf(i)!=weiboVo.getFiles().indexOf(i)){
                    isFilesEquals=false;break;
                }
            }
        }

        boolean isTopicsEquals=true;
        if ((weiboVo.topics==null&&topics!=null)||(weiboVo.topics!=null&&topics==null)){
            isFilesEquals=false;
        }else if (weiboVo.topics!=null&&topics!=null){
            for(int i=0;i<topics.size();i++){
                if(topics.indexOf(i)!=weiboVo.getTopics().indexOf(i)){
                    isTopicsEquals=false;break;
                }
            }
        }

        return Objects.equals(weiboId, weiboVo.weiboId) &&
                Objects.equals(userId, weiboVo.userId) &&
                Objects.equals(nickName, weiboVo.nickName) &&
                Objects.equals(userAvatar, weiboVo.userAvatar) &&
                isFilesEquals &&
                isTopicsEquals &&
                Objects.equals(publishType, weiboVo.publishType) &&
                Objects.equals(weiboContent, weiboVo.weiboContent) &&
                Objects.equals(weiboType, weiboVo.weiboType);
    }
}
