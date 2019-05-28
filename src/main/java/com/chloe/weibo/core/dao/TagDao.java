package com.chloe.weibo.core.dao;

import com.chloe.weibo.core.entity.Tag;
import com.chloe.weibo.core.entity.Userlabels;
import com.chloe.weibo.core.entity.entityExample.TagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagDao {

    int countByExample(TagExample example);

    int deleteByExample(TagExample example);

    int deleteByPrimaryKey(Integer tagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    List<Tag> selectByExample(TagExample example);

    List<Userlabels> selectLabels();

    Tag selectByPrimaryKey(Integer tagId);

    int updateByExampleSelective(@Param("record") Tag record, @Param("example") TagExample example);

    int updateByExample(@Param("record") Tag record, @Param("example") TagExample example);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}