package com.chloe.weibo.core.dao;

import com.chloe.weibo.core.entity.Like;
import com.chloe.weibo.core.entity.entityExample.LikeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikeDao {

    int countByExample(LikeExample example);

    int deleteByExample(LikeExample example);

    int deleteByPrimaryKey(Integer collectionId);

    int insert(Like record);

    int insertSelective(Like record);

    List<Like> selectByExample(LikeExample example);

    Like selectByPrimaryKey(Integer collectionId);

    int updateByExampleSelective(@Param("record") Like record, @Param("example") LikeExample example);

    int updateByExample(@Param("record") Like record, @Param("example") LikeExample example);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);

    List<Integer> getLikeWeiIdList(@Param("userId")int userId,@Param("startIndex")int startIndex,@Param("pageSize") int pageSize);
}