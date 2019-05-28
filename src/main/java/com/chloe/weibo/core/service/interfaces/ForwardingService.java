package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.pojo.vo.WeiboVo;

import java.text.ParseException;

/**
 * @author ChloeWong
 * @date 2019/4/11
 */
public interface ForwardingService {
    Integer getOrgWeiboIdByWeiboId(Integer weiboId);
    Integer getForwardingIdByWeiboId(Integer weiboId);
    void addForwarding(WeiboVo weiboVo,Integer orgWeiboId) throws ParseException;
    void addForwarding(Integer weiboId,Integer orgWeiboId);
    void deleteForwarding(Integer weiboId);
    void decreaseForwardingAmount(Integer weiboId);
    void increaseForwardingAmount(Integer weiboId);
}
