package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.Weibo;
import com.chloe.weibo.pojo.vo.WeiboVo;

import java.util.List;

public interface WeiboService {

    /**
     * 添加微博关系
     * @param weibo
     * @return
     */
    void addWeibo(Weibo weibo);
    void deleteWeibo(Integer weiboId);
    Weibo selectByWeiboId(Integer weiboId);
    void updateWeibo(Weibo weibo);
    void increaseLikeAmount(Integer weiboId);
    void increaseCommentAmount(Integer weiboId);
    void zeroCommentAmount(Integer weiboId);
    void decreaseLikeAmount(Integer weiboId);
    void decreaseCommentAmount(Integer weiboId);
    Result checkWeiboIsModify(int weiboId);
    String getWeiboContentByWeiboId(Integer weiboId);

    WeiboVo getFatherWeiboVoByWeiboId(int orgUserId,int weiboId);
    Result getSearchWeiboVoList(int userId,String weiboContent,int pageNum);
    Result getHotWeiboVoList(int userId,int pageNum);
    Result getLikeWeiboVoList(int userId,int pageNum);
    Result getCollectionWeiboVoList(int userId,int pageNum);
}
