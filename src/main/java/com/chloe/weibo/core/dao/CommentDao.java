package com.chloe.weibo.core.dao;

import com.chloe.weibo.core.entity.Comment;
import com.chloe.weibo.core.entity.entityExample.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentDao {

    int countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer comId);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer comId);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}