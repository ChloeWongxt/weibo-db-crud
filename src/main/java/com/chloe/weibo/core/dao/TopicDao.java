package com.chloe.weibo.core.dao;

import com.chloe.weibo.core.entity.Topic;
import com.chloe.weibo.core.entity.entityExample.TopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicDao {

    int countByExample(TopicExample example);

    int deleteByExample(TopicExample example);

    int deleteByPrimaryKey(Integer topicId);

    int insert(Topic record);

    int insertSelective(Topic record);

    List<Topic> selectByExample(TopicExample example);

    Topic selectByPrimaryKey(Integer topicId);

    List<String> selectTopicNameListByWeiboId(Integer weiboId);

    int updateByExampleSelective(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByExample(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);
}