package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.User;
import com.chloe.weibo.pojo.vo.UserVo;

import java.util.List;

public interface UserService {

    Result getUserVoList();

    List<User> getUserList();

    UserVo changeUserToUserVo(User user);

    List<UserVo> changeUserListToUserVoList(List<User> userList);

    UserVo getUserVoByUserId(Integer userId);

    Result getUserVoByUserIdReturnResult(Integer userId);

    void addUserVo(UserVo userVo);

    Result addUserVoRetrunResult(UserVo uservo);

    void deleteUser(Integer userId);

    Result deleteUserVo(Integer userId);

    void updateUser(User user);

    Result updateUserVo(UserVo userVo);

    User checkPassword(String loginName, String password);

    Result queryUserIdByWeiboId(int weiboId);

    Integer countUserNum();

    String encryptPsd(String plainPsd);

    boolean validatePsd(String plainPsd, String encryptedPsd);

    Result getSearchUserVo(int userId,String userName,int pageNum);

    Result getHotUserVo(int userId,int pageNum);
}
