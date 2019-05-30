package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.CollectionDao;
import com.chloe.weibo.core.entity.Collection;
import com.chloe.weibo.core.entity.entityExample.CollectionExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.CollectionService;
import com.chloe.weibo.core.service.interfaces.MainFunctionService;
import com.chloe.weibo.pojo.vo.WeiboVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionDao collectionDao;
    @Autowired
    MainFunctionService mainFunctionService;

    @Transactional
    @Override
    public List<Collection> getCollectionListByUserId(int userId) {
        CollectionExample collectionExample=new CollectionExample();
        collectionExample.createCriteria().andUserIdEqualTo(userId).andIsDelEqualTo(false);
        List<Collection> collectionList=collectionDao.selectByExample(collectionExample);
        return collectionList;
    }

    @Transactional
    @Override
    public List<Collection> getAllCollectionList() {
        CollectionExample collectionExample=new CollectionExample();
        collectionExample.createCriteria().andIsDelEqualTo(false);
        List<Collection> collectionList=collectionDao.selectByExample(collectionExample);
        return collectionList;
    }

    @Transactional
    @Override
    public void addCollection(Collection collection) {
        collection.setGmtCreate(new Date());
        collection.setGmtModify(new Date());
        collection.setIsDel(false);
        CollectionExample collectionExample=new CollectionExample();
        collectionExample.createCriteria().andUserIdEqualTo(collection.getUserId()).andWeiboIdEqualTo(collection.getWeiboId()).andIsDelEqualTo(false);
        List<Collection> collections=collectionDao.selectByExample(collectionExample);
        if (collections.size()>0){
            throw new WeiboException("添加收藏失败：数据库中已存在该收藏信息");
        }
        if (collectionDao.insertSelective(collection)<=0){
            throw new WeiboException("添加收藏失败：数据库未知原因！");
        }
    }

    @Transactional
    @Override
    public void updateCollection(Collection collection) {
        if (collectionDao.updateByPrimaryKeySelective(collection)<0){
            throw new WeiboException("更新收藏失败：数据库未知原因！");
        }
    }

    @Transactional
    @Override
    public void deleteCollection(int userId, int weiboId) {
        CollectionExample collectionExample=new CollectionExample();
        collectionExample.createCriteria().andUserIdEqualTo(userId).andWeiboIdEqualTo(weiboId).andIsDelEqualTo(false);
        Collection collection=new Collection();
        collection.setGmtModify(new Date());
        collection.setIsDel(true);
        if (collectionDao.updateByExampleSelective(collection,collectionExample)<=0){
            throw new WeiboException("删除收藏失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public void deleteAllCollectionOfOneUser(int userId){
        CollectionExample collectionExample=new CollectionExample();
        collectionExample.createCriteria().andUserIdEqualTo(userId).andIsDelEqualTo(false);
        Collection collection=new Collection();
        collection.setGmtModify(new Date());
        collection.setIsDel(true);
        if (collectionDao.updateByExampleSelective(collection,collectionExample)==0){
            throw new WeiboException("删除收藏失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public List<WeiboVo> getCollectionWeibo(int userId) {
        List<Collection> collectionList=getCollectionListByUserId(userId);
        List<WeiboVo> weiboVos=new ArrayList<>();
        for (Collection collection :collectionList){
            int weiboId=collection.getWeiboId();
            WeiboVo weiboVo=mainFunctionService.getOneWeiboVoByWeiboId(userId,weiboId);
            weiboVos.add(weiboVo);
        }
        return weiboVos;
    }

    @Transactional
    @Override
    public Boolean IsCollectOneWeibo(int userId, int weiboId) {
        CollectionExample collectExample =new CollectionExample();
        collectExample.createCriteria().andUserIdEqualTo(userId).andWeiboIdEqualTo(weiboId).andIsDelEqualTo(false);
        List<Collection> collectList =collectionDao.selectByExample(collectExample);
        if (collectList.size()>0){
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    @Override
    public Integer getCollectionWeiboCount(int userId) {
        CollectionExample collectionExample=new CollectionExample();
        collectionExample.createCriteria().andUserIdEqualTo(userId).andIsDelEqualTo(false);
        int total=collectionDao.countByExample(collectionExample);
        return total;
    }

    @Transactional
    @Override
    public List<Integer> getCollectionWeiboIdList(int userId, int startIndex, int pagesize) {
        return collectionDao.getCollectionWeiIdList(userId,startIndex,pagesize);
    }
}
