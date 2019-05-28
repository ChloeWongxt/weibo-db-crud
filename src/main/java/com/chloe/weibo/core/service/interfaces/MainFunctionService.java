package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.pojo.vo.WeiboVo;

import java.text.ParseException;

public interface MainFunctionService {

    Result sendWeiboVo(WeiboVo weiboVo) throws ParseException;
    Result updateWeiboVo(WeiboVo weiboVo) throws ParseException;
    Result ForwardingWeiboVo(WeiboVo weiboVo, int org_weiboId);
    boolean deleteWeiboVo(int weiboId);

    Result getAllWeiboVoListByUserId(int userId);
    Result getOwnWeiboVoListByUserId(int userId);
    WeiboVo getOneWeiboVoByWeiboId(int orguserId,int weiboId);

    Result getOwnWeiboVoPageByUserId(int userId, int pagNum);
    Result getAllWeiboVoPageByUserId(int userId, int pagNum);
    Result getForwardingOrignalWeiboContent(int WeiboId);
    Result getWeiboContentByWeiboId(int weiboId);

    void changeComInfo();
    void changeFollowInfo();
    Integer randomUserId();
    void changeLikeInfo();
    void changeCollectionInfo();
    void changeMutualFollowNum();
    void addMutualFollowNum();
}
