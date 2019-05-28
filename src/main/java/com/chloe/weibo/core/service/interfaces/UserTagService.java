package com.chloe.weibo.core.service.interfaces;

import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/11
 */
public interface UserTagService {
    int getUserTagIdByUserIdAndTagId(Integer userId,Integer tagId);
    void addUserTag(Integer userId,Integer tagId);
    Integer addUserTag(Integer userId,String tagName);
    void addUserTagList(Integer userId,List<String> tagsName);
    void deleteUserTag(Integer userId,Integer tagId);
    void deleteUserTag(Integer userId,String tagName);
    void deleteUserAllTags(Integer userId);
    void updateUserTag(Integer userId,String org_tagName,String new_tagName);
    List<String> getTagNameListByUserId(Integer userId);
    void updateUserTag(Integer userId,List<String> org_tagsName,List<String> new_tagsName);
    void increaseUseAmount(Integer tagId);
    void decreaseUseAmount(Integer tagId);
    void insertTagsTest();
}
