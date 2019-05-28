package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.LikeDao;
import com.chloe.weibo.core.entity.Like;
import com.chloe.weibo.core.entity.Weibo;
import com.chloe.weibo.core.entity.entityExample.LikeExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.LikeService;
import com.chloe.weibo.core.service.interfaces.WeiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeDao likeDao;
    @Autowired
    WeiboService weiboService;

    @Transactional
    @Override
    public List<Like> getLikeListByUserId(int userId) {
        LikeExample likeExample=new LikeExample();
        likeExample.createCriteria().andUserIdEqualTo(userId).andIsDelEqualTo(false);
        List<Like> likeList=likeDao.selectByExample(likeExample);
        return likeList;
    }

    @Transactional
    @Override
    public List<Like> getAllUserLikeList() {
        LikeExample likeExample=new LikeExample();
        likeExample.createCriteria().andIsDelEqualTo(false);
        List<Like> likeList=likeDao.selectByExample(likeExample);
        return likeList;
    }

    @Transactional
    @Override
    public List<Weibo> getLikeWeiboPageByUserIdAndPageNum(int userId, int pageNum) {

//        List<Like> likeList=getLikeListByUserId(userId);
//        if (likeList.size()>0){
//            List<Integer> likeWeiboIdList=
//        }
        return null;
    }

    @Transactional
    @Override
    public void addLike(int userId, int weiboId) {
        LikeExample likeExample=new LikeExample();
        likeExample.createCriteria().andWeiboIdEqualTo(weiboId).andUserIdEqualTo(userId).andIsDelEqualTo(false);
        List<Like> likeList=likeDao.selectByExample(likeExample);
        if (likeList.size()>0){
            throw new WeiboException("已点赞");
        }
        Like like=new Like();
        like.setUserId(userId);
        like.setWeiboId(weiboId);
        like.setGmtCreate(new Date());
        like.setGmtModify(new Date());
        like.setIsDel(false);
        if (likeDao.insertSelective(like)<=0){
            throw new WeiboException("添加点赞失败：数据库未知原因！");
        }
        weiboService.increaseLikeAmount(like.getWeiboId());
    }

    @Transactional
    @Override
    public void updateLike(Like like) {
        if (likeDao.updateByPrimaryKeySelective(like)<0){
            throw new WeiboException("更新点赞失败：数据库未知原因！");
        }
    }

    @Transactional
    @Override
    public void deleteLike(int userId, int weiboId) {
        LikeExample likeExample=new LikeExample();
        likeExample.createCriteria().andUserIdEqualTo(userId).andWeiboIdEqualTo(weiboId).andIsDelEqualTo(false);
        Like like=new Like();
        like.setGmtModify(new Date());
        like.setIsDel(true);
        if (likeDao.updateByExampleSelective(like,likeExample)<=0){
            throw new WeiboException("删除点赞失败：数据库未知错误！");
        }
        weiboService.decreaseLikeAmount(weiboId);
    }

    @Transactional
    @Override
    public void deleteAllLikeOfOneUser(int userId){
        //不能注销账户，取消点赞只能够一个一个取消！
        LikeExample likeExample=new LikeExample();
        likeExample.createCriteria().andUserIdEqualTo(userId).andIsDelEqualTo(false);
        Like like=new Like();
        like.setGmtModify(new Date());
        like.setIsDel(true);
        if (likeDao.updateByExampleSelective(like,likeExample)==0){
            throw new WeiboException("删除点赞失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public Boolean IsLikeOneWeibo(int userId, int weiboId) {
        LikeExample likeExample=new LikeExample();
        likeExample.createCriteria().andUserIdEqualTo(userId).andWeiboIdEqualTo(weiboId).andIsDelEqualTo(false);
        List<Like> likeList=likeDao.selectByExample(likeExample);
        if (likeList.size()>0){
            return true;
        }else {
            return false;
        }
    }
}
