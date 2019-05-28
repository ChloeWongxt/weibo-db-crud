package com.chloe.weibo.core.dao;

import com.chloe.weibo.core.entity.Weibo;
import com.chloe.weibo.core.entity.entityExample.WeiboExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WeiboDao {

    int countByExample(WeiboExample example);

    int deleteByExample(WeiboExample example);

    int deleteByPrimaryKey(Integer weiboId);

    int insert(Weibo record);

    int insertSelective(Weibo record);

    List<Weibo> selectByExample(WeiboExample example);

    Weibo selectByPrimaryKey(Integer weiboId);

    List<Integer> selectWeiboIdByUserIdLimit(int userId,int startIndex,int pageSize);

    List<Integer> selectAllWeiboIdPageByUserIdList(Map map);

    int countAllWeiboNumberByUserIdList(Map map);

    List<Integer> selectAllWeiboIdPageByUserIdListLimit(Map map);

    int updateByExampleSelective(@Param("record") Weibo record, @Param("example") WeiboExample example);

    int updateByExample(@Param("record") Weibo record, @Param("example") WeiboExample example);

    int updateByPrimaryKeySelective(Weibo record);

    int updateByPrimaryKey(Weibo record);
}