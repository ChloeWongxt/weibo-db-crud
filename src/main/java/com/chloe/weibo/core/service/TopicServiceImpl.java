package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.TopicDao;
import com.chloe.weibo.core.entity.Topic;
import com.chloe.weibo.core.entity.entityExample.TopicExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/11
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicDao topicDao;

    @Transactional
    @Override
    public int getTopicIdByTopicName(String topicName) {
        TopicExample topicExample=new TopicExample();
        topicExample.createCriteria().andTopicNameEqualTo(topicName).andIsDelEqualTo(false);
        List<Topic> topicList=topicDao.selectByExample(topicExample);
        if (topicList.size()<=0){
            throw new WeiboException("查找话题Id:该话题名不存在！");
        }
        else if (topicList.size()>1){
            throw new WeiboException("查找话题Id:该话题名存在多条！");
        }else {
            return topicList.get(0).getTopicId();
        }
    }

    @Transactional
    @Override
    public String getTopicNameByTopicId(Integer topicId) {
        String topicName=topicDao.selectByPrimaryKey(topicId).getTopicName();
        return topicName;
    }

    @Transactional
    @Override
    public void addTopic(Topic topic) {
        topic.setDiscussAmount(0);
        topic.setGmtCreate(new Date());
        topic.setGmtModify(new Date());
        topic.setIsDel(false);
        if (topicDao.insertSelective(topic)<=0){
            throw new WeiboException("添加话题:失败！");
        }
    }

    @Transactional
    @Override
    public void deleteTopicByTopicId(Integer topicId) {
        Topic topic=new Topic();
        topic.setTopicId(topicId);
        topic.setIsDel(true);
        if (topicDao.updateByPrimaryKeySelective(topic)<=0){
            throw new WeiboException("删除话题:失败！");
        }
    }

    @Transactional
    @Override
    public void increaseTopicDiscussAmountByTopicId(Integer topicId) {
        Topic topic=topicDao.selectByPrimaryKey(topicId);
        int topicAmount=topic.getDiscussAmount();
        topic.setDiscussAmount(topicAmount+1);
        topic.setGmtModify(new Date());
        if (topicDao.updateByPrimaryKeySelective(topic)<=0){
            throw new WeiboException("增加话题讨论量:失败！");
        }
    }

    @Transactional
    @Override
    public void decreaseTopicDiscussAmountByTopicId(Integer topicId) {
        Topic topic=topicDao.selectByPrimaryKey(topicId);
        int topicAmount=topic.getDiscussAmount();
        topic.setDiscussAmount(topicAmount-1);
        topic.setGmtModify(new Date());
        if (topicDao.updateByPrimaryKeySelective(topic)<=0){
            throw new WeiboException("减少话题讨论量:失败！");
        }
    }
}
