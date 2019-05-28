package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.WeiboDao;
import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.Weibo;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.ForwardingService;
import com.chloe.weibo.core.service.interfaces.WeiboService;
import com.chloe.weibo.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WeiboServiceImpl implements WeiboService {

    @Autowired
    private WeiboDao weiboDao;
    @Autowired
    private ForwardingService forwardingService;

    @Transactional
    @Override
    public void addWeibo(Weibo weibo) {
        weibo.setGmtCreate(new Date());
        weibo.setGmtModify(new Date());
        if (weiboDao.insertSelective(weibo)<=0){
            throw new WeiboException("添加微博表失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public void deleteWeibo(Integer weiboId) {
        Weibo weibo=weiboDao.selectByPrimaryKey(weiboId);
        weibo.setIsDel(true);
        if(weiboDao.updateByPrimaryKeySelective(weibo)<0){
            throw new WeiboException("删除微博表失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public Weibo selectByWeiboId(Integer weiboId) {
        return weiboDao.selectByPrimaryKey(weiboId);
    }

    @Transactional
    @Override
    public void updateWeibo(Weibo weibo) {
        if (weiboDao.updateByPrimaryKeySelective(weibo)<0){
            throw new WeiboException("更新微博表失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public void increaseLikeAmount(Integer weiboId) {
        Weibo weibo=selectByWeiboId(weiboId);
        int likeAmount=weibo.getLikeAmount()+1;
        weibo.setLikeAmount(likeAmount);
        if (weiboDao.updateByPrimaryKeySelective(weibo)<0){
            throw new WeiboException("更新微博表失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public void increaseCommentAmount(Integer weiboId) {
        Weibo weibo=selectByWeiboId(weiboId);
        int commentAmount=weibo.getCommentAmount()+1;
        weibo.setCommentAmount(commentAmount);
        if (weiboDao.updateByPrimaryKeySelective(weibo)<0){
            throw new WeiboException("更新微博表失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public void zeroCommentAmount(Integer weiboId) {
        Weibo weibo=selectByWeiboId(weiboId);
        weibo.setCommentAmount(0);
        if (weiboDao.updateByPrimaryKeySelective(weibo)<0){
            throw new WeiboException("更新微博表失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public void decreaseLikeAmount(Integer weiboId) {
        Weibo weibo=selectByWeiboId(weiboId);
        int likeAmount=weibo.getLikeAmount()-1;
        weibo.setLikeAmount(likeAmount);
        if (weiboDao.updateByPrimaryKeySelective(weibo)<0){
            throw new WeiboException("更新微博表失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public void decreaseCommentAmount(Integer weiboId) {
        Weibo weibo=selectByWeiboId(weiboId);
        int commentAmount=weibo.getCommentAmount()-1;
        weibo.setCommentAmount(commentAmount);
        if (weiboDao.updateByPrimaryKeySelective(weibo)<0){
            throw new WeiboException("更新微博表失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public Result checkWeiboIsModify(int weiboId) {
        Boolean flag=weiboDao.selectByPrimaryKey(weiboId).getIsModify();
        return ResultUtil.success(flag);
    }

    @Transactional
    @Override
    public String getWeiboContentByWeiboId(Integer weiboId) {
        Weibo weibo=weiboDao.selectByPrimaryKey(weiboId);
        if (weibo==null){
            return null;
        }else {
            return weibo.getWeiboContent();
        }
    }

}
