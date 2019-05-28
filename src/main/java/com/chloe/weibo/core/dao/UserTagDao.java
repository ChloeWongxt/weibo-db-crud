package com.chloe.weibo.core.dao;

import com.chloe.weibo.core.entity.UserTag;
import com.chloe.weibo.core.entity.entityExample.UserTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTagDao {

    int countByExample(UserTagExample example);

    int deleteByExample(UserTagExample example);

    int deleteByPrimaryKey(Integer userTagId);

    int insert(UserTag record);

    int insertSelective(UserTag record);

    List<UserTag> selectByExample(UserTagExample example);

    UserTag selectByPrimaryKey(Integer userTagId);

    List<String> getTagNameListByUserId(Integer userId);

    int updateByExampleSelective(@Param("record") UserTag record, @Param("example") UserTagExample example);

    int updateByExample(@Param("record") UserTag record, @Param("example") UserTagExample example);

    int updateByPrimaryKeySelective(UserTag record);

    int updateByPrimaryKey(UserTag record);
}