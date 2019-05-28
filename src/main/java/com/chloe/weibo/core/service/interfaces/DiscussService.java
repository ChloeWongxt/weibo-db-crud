package com.chloe.weibo.core.service.interfaces;

import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/11
 */
public interface DiscussService {
    int getDiscussIdByWeiboIdAndTopicId(Integer weiboId,Integer topicId);
    void addDiscuss(Integer weiboId,Integer topicId);
    Integer addDiscuss(Integer weiboId,String topicName);
    void addTopicNameList(Integer weiboId,List<String> topicNameList);
    void deleteDiscuss(Integer weiboId,Integer topicId);
    void deleteDiscuss(Integer weiboId,String tagName);
    void deleteDiscuss(Integer weiboId);
    void updateDiscuss(Integer weiboId,String org_topicName,String new_topicName);
    void updateTopicList(Integer weiboId, List<String> org_topicNames,List<String> new_topicName);
}
