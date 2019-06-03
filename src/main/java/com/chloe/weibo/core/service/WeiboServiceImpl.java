package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.LikeDao;
import com.chloe.weibo.core.dao.WeiboDao;
import com.chloe.weibo.core.entity.Forwarding;
import com.chloe.weibo.core.entity.Like;
import com.chloe.weibo.core.entity.entityExample.LikeExample;
import com.chloe.weibo.core.entity.entityExample.WeiboExample;
import com.chloe.weibo.core.service.interfaces.*;
import com.chloe.weibo.pojo.data.PageBean;
import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.Weibo;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.common.utils.ResultUtil;
import com.chloe.weibo.pojo.vo.UserVo;
import com.chloe.weibo.pojo.vo.WeiboVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WeiboServiceImpl implements WeiboService {

    @Autowired
    private WeiboDao weiboDao;
    @Autowired
    private ForwardingService forwardingService;
    @Autowired
    private MainFunctionService mainFunctionService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private CollectionService collectionService;

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
//        weibo.setIsModify(true);
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


    @Transactional
    @Override
    public WeiboVo getFatherWeiboVoByWeiboId(int orgUserId,int weiboId) {
        WeiboVo weiboVo;
        WeiboExample weiboExample=new WeiboExample();
        weiboExample.createCriteria().andWeiboIdEqualTo(weiboId);
        Weibo weibo=weiboDao.selectByExample(weiboExample).get(0);
        Boolean isForward=weibo.getWeiboType();
        if (isForward){
            int fatherId=forwardingService.getOrgWeiboIdByWeiboId(weiboId);
            weiboVo=getFatherWeiboVoByWeiboId(orgUserId,fatherId);
        }else {
            weiboVo=mainFunctionService.getOneWeiboVoByWeiboId(orgUserId,weiboId);
        }
        return weiboVo;
    }

    @Transactional
    @Override
    public Result getSearchWeiboVoList(int userId,String weiboContent,int pageNum) {
        List<WeiboVo> weiboVoList = new ArrayList<>();

        String weiboText='%'+weiboContent+'%';

        //符合搜索的总页数
        int total=weiboDao.countSearchWeibo(weiboText);
        int pagesize=5;
        PageBean<WeiboVo> weiboVoPageBean=new PageBean<>(pageNum,pagesize,total);

        int startIndex=weiboVoPageBean.getStartIndex();

        //一页所包含的微博id
        List<Integer> weiboIdList=weiboDao.selectSearchWeibo(weiboText, startIndex, pagesize);

        if (weiboIdList==null){
            return null;
        }

        for (Integer weiboId : weiboIdList) {
            WeiboVo weiboVo = mainFunctionService.getOneWeiboVoByWeiboId(userId,weiboId);
            weiboVoList.add(weiboVo);
        }
        weiboVoPageBean.setList(weiboVoList);
        return ResultUtil.success(weiboVoPageBean);
    }

    @Transactional
    @Override
    public Result getHotWeiboVoList(int userId, int pageNum) {
        List<WeiboVo> weiboVoList = new ArrayList<>();

        //符合搜索的总页数
        int total=50;
        int pagesize=5;
        PageBean<WeiboVo> weiboVoPageBean=new PageBean<>(pageNum,pagesize,total);

        int startIndex=weiboVoPageBean.getStartIndex();

        //一页所包含的微博id
        List<Integer> weiboIdList=weiboDao.getHotWeibo(startIndex,pagesize);

        if (weiboIdList==null){
            return null;
        }

        for (Integer weiboId : weiboIdList) {
            WeiboVo weiboVo = mainFunctionService.getOneWeiboVoByWeiboId(userId,weiboId);
            weiboVoList.add(weiboVo);
        }
        weiboVoPageBean.setList(weiboVoList);
        return ResultUtil.success(weiboVoPageBean);
    }

    @Transactional
    @Override
    public Result getLikeWeiboVoList(int userId, int pageNum) {

        List<WeiboVo> weiboVoList = new ArrayList<>();

        //符合点赞的总页数
        int total=likeService.getLikeWeiboCount(userId);
        int pagesize=5;
        PageBean<WeiboVo> weiboVoPageBean=new PageBean<>(pageNum,pagesize,total);

        int startIndex=weiboVoPageBean.getStartIndex();

        //一页所包含的微博id
        List<Integer> weiboIdList=likeService.getLikeWeiboIdList(userId,startIndex,pagesize);

        if (weiboIdList==null){
            return null;
        }

        for (Integer weiboId : weiboIdList) {
            WeiboVo weiboVo = mainFunctionService.getOneWeiboVoByWeiboId(userId,weiboId);
            weiboVoList.add(weiboVo);
        }
        weiboVoPageBean.setList(weiboVoList);
        return ResultUtil.success(weiboVoPageBean);
    }

    @Transactional
    @Override
    public Result getCollectionWeiboVoList(int userId, int pageNum) {
        List<WeiboVo> weiboVoList = new ArrayList<>();

        //符合点赞的总页数
        int total=collectionService.getCollectionWeiboCount(userId);
        int pagesize=5;
        PageBean<WeiboVo> weiboVoPageBean=new PageBean<>(pageNum,pagesize,total);

        int startIndex=weiboVoPageBean.getStartIndex();

        //一页所包含的微博id
        List<Integer> weiboIdList=collectionService.getCollectionWeiboIdList(userId,startIndex,pagesize);

        if (weiboIdList==null){
            return null;
        }

        for (Integer weiboId : weiboIdList) {
            WeiboVo weiboVo = mainFunctionService.getOneWeiboVoByWeiboId(userId,weiboId);
            weiboVoList.add(weiboVo);
        }
        weiboVoPageBean.setList(weiboVoList);
        return ResultUtil.success(weiboVoPageBean);
    }
}
