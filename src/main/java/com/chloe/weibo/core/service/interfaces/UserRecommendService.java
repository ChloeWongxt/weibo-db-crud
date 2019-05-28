package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.core.entity.RecomUser;
import com.chloe.weibo.pojo.vo.UserRecomVo;

import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/25
 */
public interface UserRecommendService {

    void getUserRecommend();
    void get_user_user_num();
    int SplitData(int m, int k);
    RecomUser getRecomUser(int userId);
    List<UserRecomVo> getRecomUserInfoList(int userId);
}
