package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.core.entity.Topic;

/**
 * @author ChloeWong
 * @date 2019/4/11
 */
public interface TopicService {
    int getTopicIdByTopicName(String topicName);
    String getTopicNameByTopicId(Integer topicId);
    void addTopic(Topic topic);
    void deleteTopicByTopicId(Integer topicId);
    void increaseTopicDiscussAmountByTopicId(Integer topicId);
    void decreaseTopicDiscussAmountByTopicId(Integer topicId);
}
