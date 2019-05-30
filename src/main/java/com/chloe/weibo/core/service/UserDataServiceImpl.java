package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.UserDataDao;
import com.chloe.weibo.core.entity.UserData;
import com.chloe.weibo.core.entity.entityExample.UserDataExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/13
 */
@Service
public class UserDataServiceImpl implements UserDataService {
    @Autowired
    UserDataDao userDataDao;

    @Transactional
    @Override
    public void addUserData(int userId) {
        UserData userData=new UserData(userId);
        if (userDataDao.insertSelective(userData)<0){
            throw new WeiboException("用户初始化失败！");
        }
    }

    @Transactional
    @Override
    public void zeroUserData(int userId) {
        UserData userData=new UserData(userId);
        if (userDataDao.updateByPrimaryKeySelective(userData)<0){
            throw new WeiboException("删除用户失败！");
        }
    }

    @Transactional
    @Override
    public UserData getUserData(int userId) {
        return userDataDao.selectByPrimaryKey(userId);
    }

    @Transactional
    @Override
    public List<UserData> getAllUserData() {
        UserDataExample userDataExample=new UserDataExample();
        userDataExample.createCriteria();
        List<UserData> userDataList=userDataDao.selectByExample(userDataExample);
        return userDataList;
    }

    @Transactional
    @Override
    public void increaseWeiboAmount(int userId) {
        UserData userData=userDataDao.selectByPrimaryKey(userId);
        int weiboAmount=userData.getWeiboAmount()+1;
        userData.setWeiboAmount(weiboAmount);
        if (userDataDao.updateByPrimaryKeySelective(userData)<0){
            throw new WeiboException("增加微博数失败！");
        }
    }

    @Transactional
    @Override
    public void decreaseWeiboAmount(int userId) {
        UserData userData=userDataDao.selectByPrimaryKey(userId);
        int weiboAmount=userData.getWeiboAmount()-1;
        userData.setWeiboAmount(weiboAmount);
        if (userDataDao.updateByPrimaryKeySelective(userData)<0){
            throw new WeiboException("减少微博数失败！");
        }
    }

    @Transactional
    @Override
    public void increaseBeFollowedAmount(int userId) {
        UserData userData=userDataDao.selectByPrimaryKey(userId);
        int beFollowAmount=userData.getBeFollowedAmount()+1;
        userData.setBeFollowedAmount(beFollowAmount);
        if (userDataDao.updateByPrimaryKeySelective(userData)<0){
            throw new WeiboException("增加被关注数失败！");
        }
    }

    @Transactional
    @Override
    public void decreaseBeFollowedAmount(int userId) {
        UserData userData=userDataDao.selectByPrimaryKey(userId);
        int beFollowAmount=userData.getBeFollowedAmount()-1;
        userData.setBeFollowedAmount(beFollowAmount);
        if (userDataDao.updateByPrimaryKeySelective(userData)<0){
            throw new WeiboException("减少被关注数失败！");
        }
    }

    @Transactional
    @Override
    public void increaseFollowAmount(int userId) {
        UserData userData=userDataDao.selectByPrimaryKey(userId);
        int followAmount=userData.getFollowAmount()+1;
        userData.setFollowAmount(followAmount);
        if (userDataDao.updateByPrimaryKeySelective(userData)<0){
            throw new WeiboException("增加关注数失败！");
        }
    }

    @Transactional
    @Override
    public void decreasFollowAmount(int userId) {
        UserData userData=userDataDao.selectByPrimaryKey(userId);
        int followAmount=userData.getFollowAmount()-1;
        userData.setFollowAmount(followAmount);
        if (userDataDao.updateByPrimaryKeySelective(userData)<0){
            throw new WeiboException("减少关注数失败！");
        }
    }

    @Transactional
    @Override
    public void increaseMutualAmount(int userId) {
        UserData userData=userDataDao.selectByPrimaryKey(userId);
        int mutualAmount =userData.getMutualAmount()+1;
        userData.setMutualAmount(mutualAmount);
        if (userDataDao.updateByPrimaryKeySelective(userData)<0){
            throw new WeiboException("增加相互关注数失败！");
        }
    }

    @Transactional
    @Override
    public void decreasMutualAmount(int userId) {
        UserData userData=userDataDao.selectByPrimaryKey(userId);
        int mutualAmount =userData.getMutualAmount()-1;
        userData.setMutualAmount(mutualAmount);
        if (userDataDao.updateByPrimaryKeySelective(userData)<0){
            throw new WeiboException("减少相互关注数失败！");
        }
    }

    @Transactional
    @Override
    public void updateUserData(UserData userData) {
        if (userDataDao.updateByPrimaryKeySelective(userData)<0){
            throw new WeiboException("更新UserData失败");
        }
    }
}
