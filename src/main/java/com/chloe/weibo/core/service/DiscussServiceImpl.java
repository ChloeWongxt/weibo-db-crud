package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.DiscussDao;
import com.chloe.weibo.core.entity.Discuss;
import com.chloe.weibo.core.entity.Topic;
import com.chloe.weibo.core.entity.entityExample.DiscussExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.DiscussService;
import com.chloe.weibo.core.service.interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/11
 */
@Service
public class DiscussServiceImpl implements DiscussService {

    @Autowired
    DiscussDao discussDao;
    @Autowired
    TopicService topicService;

    @Transactional
    @Override
    public int getDiscussIdByWeiboIdAndTopicId(Integer weiboId, Integer topicId) {
        DiscussExample discussExample =new DiscussExample();
        discussExample.createCriteria().andWeiboIdEqualTo(weiboId).andTopicIdEqualTo(topicId).andIsDelEqualTo(false);
        List<Discuss> discusses=discussDao.selectByExample(discussExample);
        if (discusses.size()<0){
            throw new WeiboException("查询讨论ID失败：数据库未知错误");
        }else if (discusses.size()==0){
            throw new WeiboException("查询讨论ID失败：数据库中不存在该条目");
        }else if (discusses.size()>1){
            throw new WeiboException("查询讨论ID失败：条目重复");
        }else {
            return discusses.get(0).getDiscussId();
        }
    }

    @Transactional
    @Override
    public void addDiscuss(Integer weiboId, Integer topicId) {
        Discuss discusses=new Discuss();
        discusses.setWeiboId(weiboId);
        discusses.setTopicId(topicId);
        if(discussDao.insertSelective(discusses)<=0){
            throw new WeiboException("添加讨论关系失败！");
        }
    }

    @Transactional
    @Override
    public Integer addDiscuss(Integer weiboId, String topicName) {
        int topicId=topicService.getTopicIdByTopicName(topicName);
        if (topicId==0){
            //话题不存在，增加话题
            Topic topic=new Topic();
            topic.setTopicName(topicName);
            topic.setDiscussAmount(0);
            topic.setIsDel(false);
            topicService.addTopic(topic);
            topicId=topic.getTopicId();
        }
        addDiscuss(weiboId,topicId);
        return topicId;
    }

    @Transactional
    @Override
    public void addTopicNameList(Integer weiboId, List<String> topicNameList) {
        if(topicNameList==null){
            return;
        }else {
            for (String topicName:topicNameList){
                int topicId=addDiscuss(weiboId,topicName);
                topicService.increaseTopicDiscussAmountByTopicId(topicId);
            }
        }
    }

    @Transactional
    @Override
    public void deleteDiscuss(Integer weiboId, Integer topicId) {
        DiscussExample discussesExample=new DiscussExample();
        discussesExample.createCriteria().andIsDelEqualTo(false).andWeiboIdEqualTo(weiboId).andTopicIdEqualTo(topicId);
        Discuss discusses=new Discuss();
        discusses.setIsDel(true);
        if(discussDao.updateByExampleSelective(discusses,discussesExample)<=0){
            throw new WeiboException("删除讨论关系失败！");
        }
        topicService.deleteTopicByTopicId(topicId);
    }

    @Transactional
    @Override
    public void deleteDiscuss(Integer weiboId, String topicName) {
        int topicId=topicService.getTopicIdByTopicName(topicName);
        if (topicId==0){
            throw new WeiboException("删除的话题不存在");
        }
        deleteDiscuss(weiboId,topicId);
    }

    @Override
    public void deleteDiscuss(Integer weiboId) {
        DiscussExample discussExample=new DiscussExample();
        discussExample.createCriteria().andWeiboIdEqualTo(weiboId).andIsDelEqualTo(false);
        Discuss discuss=new Discuss();
        discuss.setIsDel(true);
        if (discussDao.updateByExampleSelective(discuss,discussExample)<=0){
            throw new WeiboException("删除话题失败：数据库未知原因！");
        }
    }

    @Transactional
    @Override
    public void updateDiscuss(Integer weiboId, String org_topicName, String new_topicName) {
        int org_topicId=topicService.getTopicIdByTopicName(org_topicName);
        int new_topicId=topicService.getTopicIdByTopicName(new_topicName);
        if (org_topicId==0){
            throw new WeiboException("原话题不存在");
        }else {
            if (new_topicId==0){
                //修改后的标签不存在，加入到表中
                new_topicId=addDiscuss(weiboId,new_topicName);
            }
            DiscussExample discussesExample=new DiscussExample();
            discussesExample.createCriteria().andWeiboIdEqualTo(weiboId).andTopicIdEqualTo(org_topicId).andIsDelEqualTo(false);
            Discuss discusses=new Discuss();
            discusses.setWeiboId(weiboId);
            discusses.setTopicId(new_topicId);
            if (discussDao.updateByExampleSelective(discusses,discussesExample)<0){
                throw new WeiboException("更新话题失败！");
            }
        }
    }

    @Transactional
    @Override
    public void updateTopicList(Integer weiboId, List<String> org_topicNames, List<String> new_topicNames) {
        int org_topicNum=0;
        if (org_topicNames!=null){
            org_topicNum=org_topicNames.size();
        }
        int new_topicNum=0;
        if (new_topicNames!=null){
            new_topicNum=new_topicNames.size();
        }
        if (org_topicNum==0&&org_topicNum==0){
            //原微博没有包含话题，现微博也没有包含话题
            //什么都不做
        }
        if (org_topicNum==0&&new_topicNum!=0){
            //原微博没有包含话题，现微博有包含话题
            //将话题-微博，加入tb_disccuss表，并查询是否有新建的话题
            addTopicNameList(weiboId,new_topicNames);

        }
        if (org_topicNum!=0&new_topicNum==0){
            //原微博有包含话题，现微博没有包含话题
            //将tb_discuss表中关系删除
            deleteDiscuss(weiboId);
        }
        if (org_topicNum!=0&&new_topicNum!=0) {
            //原微博包含话题，现微博也有包含话题
            //检查所包含的话题是否有修改
            int min_size = org_topicNum<=new_topicNum ? org_topicNum: new_topicNum;
            for (int i = 0; i < min_size; i++) {
                String org_topicName=org_topicNames.get(i);
                String new_topicName=new_topicNames.get(i);
                if (org_topicName!=new_topicName) {
                    updateDiscuss(weiboId,org_topicName,new_topicName);
                }
            }
            if (org_topicNum<=new_topicNum){
                //有新增标签
                for (int i=min_size;i<new_topicNum;i++){
                    String topicName=new_topicNames.get(i);
                    int topicId=addDiscuss(weiboId,topicName);
                }
            }else {
                //标签有删减
                for (int i=min_size;i<org_topicNum;i++){
                    String topicName=org_topicNames.get(i);
                    deleteDiscuss(weiboId,topicName);
                }
            }
        }
    }
}
