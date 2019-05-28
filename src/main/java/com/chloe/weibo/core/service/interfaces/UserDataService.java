package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.core.entity.UserData;

import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/13
 */
public interface UserDataService {
    void addUserData(int userId);
    void zeroUserData(int userId);
    UserData getUserData(int userId);
    List<UserData> getAllUserData();
    void increaseWeiboAmount(int userId);
    void decreaseWeiboAmount(int userId);
    void increaseBeFollowedAmount(int userId);
    void decreaseBeFollowedAmount(int userId);
    void increaseFollowAmount(int userId);
    void decreasFollowAmount(int userId);
    void increaseMutualAmount(int userId);
    void decreasMutualAmount(int userId);
    void updateUserData(UserData userData);
}
