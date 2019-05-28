package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.ForwardingDao;
import com.chloe.weibo.core.dao.WeiboDao;
import com.chloe.weibo.core.entity.Forwarding;
import com.chloe.weibo.core.entity.Weibo;
import com.chloe.weibo.core.entity.entityExample.ForwardingExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.ForwardingService;
import com.chloe.weibo.core.service.interfaces.WeiboService;
import com.chloe.weibo.pojo.vo.WeiboVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;

/**
 * @author ChloeWong
 * @date 2019/4/11
 */
@Service
public class ForwardingServiceImpl implements ForwardingService {
    @Autowired
    ForwardingDao forwardingDao;
    @Autowired
    WeiboDao weiboDao;
    @Autowired
    WeiboService weiboService;

    @Transactional
    @Override
    public Integer getForwardingIdByWeiboId(Integer weiboId) {
        ForwardingExample forwardingExample=new ForwardingExample();
        forwardingExample.createCriteria().andWeiboIdEqualTo(weiboId).andIsDelEqualTo(false);
        Forwarding forwarding=forwardingDao.selectByExample(forwardingExample).get(0);
        return forwarding.getForwardingId();
    }

    @Transactional
    @Override
    public Integer getOrgWeiboIdByWeiboId(Integer weiboId) {
        ForwardingExample forwardingExample=new ForwardingExample();
        forwardingExample.createCriteria().andWeiboIdEqualTo(weiboId).andIsDelEqualTo(false);
        Forwarding forwarding=forwardingDao.selectByExample(forwardingExample).get(0);
        return forwarding.getOrgWeiboId();
    }

    @Transactional
    @Override
    public void addForwarding(WeiboVo weiboVo,Integer orgWeiboId) throws ParseException {
        Weibo weibo=new Weibo(weiboVo);
        weibo.setGmtCreate(new Date());
        weibo.setIsDel(false);
        weiboService.addWeibo(weibo);
        int new_weiboId=weibo.getWeiboId();
        addForwarding(new_weiboId,orgWeiboId);
        increaseForwardingAmount(orgWeiboId);
    }

    @Transactional
    @Override
    public void addForwarding(Integer weiboId, Integer orgWeiboId) {
        Forwarding forwarding=new Forwarding();
        forwarding.setWeiboId(weiboId);
        forwarding.setOrgWeiboId(orgWeiboId);
        forwarding.setIsDel(false);
        if (forwardingDao.insertSelective(forwarding)<=0){
            throw new WeiboException("转发微博失败：数据库未知原因！");
        }
    }

    @Transactional
    @Override
    public void deleteForwarding(Integer weiboId) {
        int forwardingId=getForwardingIdByWeiboId(weiboId);
        int orgWeiboId=getOrgWeiboIdByWeiboId(weiboId);
        Forwarding forwarding=new Forwarding();
        forwarding.setForwardingId(forwardingId);
        forwarding.setIsDel(true);
        if (forwardingDao.updateByPrimaryKeySelective(forwarding)<=0){
            throw new WeiboException("删除转发失败：数据库未知错误！");
        }
        decreaseForwardingAmount(orgWeiboId);
    }

    @Transactional
    @Override
    public void decreaseForwardingAmount(Integer weiboId) {
        Weibo weibo=weiboDao.selectByPrimaryKey(weiboId);
        if (weibo.getWeiboType()){
            //微博为转发
            decreaseForwardingAmount(getOrgWeiboIdByWeiboId(weiboId));
        }
        int forwardingAmount=weibo.getForwardingAmount()-1;
        weibo.setForwardingAmount(forwardingAmount);
        if (weiboDao.updateByPrimaryKeySelective(weibo)<0){
            throw new WeiboException("减少转发量失败：数据库未知原因！");
        }
    }

    @Transactional
    @Override
    public void increaseForwardingAmount(Integer weiboId) {
        Weibo weibo=weiboDao.selectByPrimaryKey(weiboId);
        if (weibo.getWeiboType()){
            //微博为转发
            increaseForwardingAmount(getOrgWeiboIdByWeiboId(weiboId));
        }
        int forwardingAmount=weibo.getForwardingAmount()+1;
        weibo.setForwardingAmount(forwardingAmount);
        if (weiboDao.updateByPrimaryKeySelective(weibo)<0){
            throw new WeiboException("增加转发量失败：数据库未知原因！");
        }
    }
}
