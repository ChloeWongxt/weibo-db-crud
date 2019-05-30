package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.core.entity.Collection;
import com.chloe.weibo.pojo.vo.WeiboVo;

import java.util.List;

public interface CollectionService {

    /**
     * 通过用户id获取某个用户的收藏信息
     * @return
     */
    List<Collection> getCollectionListByUserId(int userId);

    /**
     * 获取所有的收藏信息
     * @return
     */
    List<Collection> getAllCollectionList();

    /**
     * 添加收藏信息
     * @param
     * @return
     */
    void addCollection(Collection collection);

    /**
     * 动态更新收藏信息
     * @param
     * @return
     */
    void updateCollection(Collection collection);


    /**
     * 删除收藏信息
     * @param userId
     * @param weiboId
     * @return
     */
    void deleteCollection(int userId,int weiboId);

    /**
     * 删除一个用户的所有收藏信息
     * @param userId
     * @return
     */
    void deleteAllCollectionOfOneUser(int userId);

    /**
     *获得一个用户的全部收藏信息
     * @param userId
     * @return
     */
    List<WeiboVo> getCollectionWeibo(int userId);

    Boolean IsCollectOneWeibo(int userId,int weiboId);

    Integer getCollectionWeiboCount(int userId);
    List<Integer> getCollectionWeiboIdList(int userId,int startIndex,int pagesize);
}
