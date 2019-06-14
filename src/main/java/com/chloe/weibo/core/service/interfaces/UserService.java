package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.User;
import com.chloe.weibo.pojo.vo.UserVo;

import java.text.ParseException;
import java.util.List;

public interface UserService {

    Result getUserVoList();

    List<User> getUserList();

    UserVo changeUserToUserVo(User user);

    List<UserVo> changeUserListToUserVoList(List<User> userList);

    UserVo getUserVoByUserId(Integer userId) throws ParseException;

    Result getUserVoByUserIdReturnResult(Integer userId) throws ParseException;

    void addUserVo(UserVo userVo);

    Result addUserVoRetrunResult(UserVo uservo);

    void deleteUser(Integer userId);

    Result deleteUserVo(Integer userId);

    void updateUser(User user);

    Result updateUserVo(UserVo userVo) throws ParseException;

    User checkPassword(String loginName, String password);

    Result queryUserIdByWeiboId(int weiboId);

    Integer countUserNum();

    String encryptPsd(String plainPsd);

    boolean validatePsd(String plainPsd, String encryptedPsd);

    Result getSearchUserVo(int userId,String userName,int pageNum);

    Result getHotUserVo(int userId,int pageNum) throws ParseException;

    Result getCommonFollowUser(int myUserId,int userId,int pageNum) throws ParseException;

    Result getMyFollowHerUser(int myUserId,int userId,int pageNum) throws ParseException;

    Result checkMissUserId();

}
