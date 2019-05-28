package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.Follow;

import java.util.List;

public interface FollowService {

    Result getFollowUserVoList(Integer userId);

    Result getBeFollowedUserVoList(Integer userId);

    Result getMutualFollowUserVoList(Integer userId);

    List<Integer> getFollowUserIdList(Integer userId);

    List<Integer> getFansUserIdList(Integer userId);

    Result addFollow(Follow follow);

    Result deleteFollow(Follow follow);

    Result checkFollowRelationShip(Integer userId_one, Integer userId_two);

    Boolean checkIsFollow(Integer userId_one, Integer userId_two);

    Boolean checkIfMutualFollow(Integer userId_one,Integer userId_two);

    Integer countFollowNum(Integer userId);

    Integer countFansNum(Integer userId);

    Integer countCommonUsersNum(Integer userId_one,Integer userId_two);


}
