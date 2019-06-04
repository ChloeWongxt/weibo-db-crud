package com.chloe.weibo.core.dao;

import com.chloe.weibo.core.entity.User;
import com.chloe.weibo.core.entity.entityExample.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectFollowUserListByUserId(@Param("userId")Integer userId,
                                            @Param("startIndex") int startIndex,
                                            @Param("pageSize")int pageSize);

    List<User> selectBeFollowUserListByUserId(@Param("userId")Integer userId,
                                              @Param("startIndex") int startIndex,
                                              @Param("pageSize")int pageSize);

    List<User> getPassword(String logName);

    int selectByWeiboId(int weiboId);

    int countSearchUser(String nickName);

    List<User> selectSearchUser(@Param("nickName")String nickName,
                                @Param("startIndex") int startIndex,
                                @Param("pageSize")int pageSize);
}