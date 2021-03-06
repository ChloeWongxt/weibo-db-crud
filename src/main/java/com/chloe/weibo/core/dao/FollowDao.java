package com.chloe.weibo.core.dao;

import com.chloe.weibo.core.entity.Follow;
import com.chloe.weibo.core.entity.entityExample.FollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FollowDao {

    int countByExample(FollowExample example);

    int deleteByExample(FollowExample example);

    int deleteByPrimaryKey(Integer followId);

    int insert(Follow record);

    int insertSelective(Follow record);

    List<Follow> selectByExample(FollowExample example);

    Follow selectByPrimaryKey(Integer followId);

    List<Integer> selectFollowUserIdList(Integer userId);

    List<Integer> selectFansUserIdList(Integer userId);

    int updateByExampleSelective(@Param("record") Follow record, @Param("example") FollowExample example);

    int updateByExample(@Param("record") Follow record, @Param("example") FollowExample example);

    int updateByPrimaryKeySelective(Follow record);

    int updateByPrimaryKey(Follow record);

    List<Integer> getCommonFollowUserList(@Param("myUserId")Integer myUserId,
                                          @Param("userId")Integer userId,
                                          @Param("startIndex") int startIndex,
                                          @Param("pageSize")int pageSize);

    List<Integer> getCommonFollowUserListNoPage(@Param("myUserId")Integer myUserId,
                                          @Param("userId")Integer userId);

    Integer getCommonFollowUserListCount(@Param("myUserId")Integer myUserId,
                                         @Param("userId")Integer userId);

    List<Integer> getMyFollowHerUserList(@Param("myUserId")Integer myUserId,
                                         @Param("userId")int userId,
                                         @Param("startIndex") int startIndex,
                                         @Param("pageSize")int pageSize);
    Integer getMyFollowHerUserListCount(@Param("myUserId")Integer myUserId,
                                         @Param("userId")Integer userId);
    Integer countMutualNum(@Param("userId")Integer userId);
}