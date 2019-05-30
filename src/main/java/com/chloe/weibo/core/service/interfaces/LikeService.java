package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.core.entity.Like;
import com.chloe.weibo.core.entity.Weibo;

import java.util.List;

public interface LikeService {

    /**
     * 通过用户id获取某个用户的点赞信息
     * @return
     */
    List<Like> getLikeListByUserId(int userId);

    /**
     * 通过获取所有点赞信息
     * @return
     */
    List<Like> getAllUserLikeList();

    /**
     * 添加点赞信息
     * @param
     * @return
     */
    void addLike(int userId, int weiboId);

    /**
     * 更新点赞信息
     * @param
     * @return
     */
    void updateLike(Like like);

    /**
     * 删除点赞信息
     * @param userId
     * @param weiboId
     * @return
     */
    void deleteLike(int userId, int weiboId);

    /**
     * 删除一个用户的所有点赞信息
     * @param userId
     * @return
     */
    void deleteAllLikeOfOneUser(int userId);

    Boolean IsLikeOneWeibo(int userId,int weiboId);

    Integer getLikeWeiboCount(int userId);
    List<Integer> getLikeWeiboIdList(int userId,int startIndex,int pagesize);
}
