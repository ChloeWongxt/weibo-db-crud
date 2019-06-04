package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.FollowDao;
import com.chloe.weibo.core.dao.UserDao;
import com.chloe.weibo.pojo.data.PageBean;
import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.Follow;
import com.chloe.weibo.core.entity.User;
import com.chloe.weibo.core.entity.entityExample.FollowExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.FollowService;
import com.chloe.weibo.core.service.interfaces.UserDataService;
import com.chloe.weibo.core.service.interfaces.UserService;
import com.chloe.weibo.common.utils.ResultUtil;
import com.chloe.weibo.pojo.vo.UserRecomVo;
import com.chloe.weibo.pojo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    FollowDao followDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    UserDataService userDataService;
    @Autowired
    UserService userService;

    @Transactional
    @Override
    public Result getFollowUserVoList(Integer userId,Integer myUserId,Integer pageNum) {
        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andFollowUserIdEqualTo(userId).andIsDelEqualTo(false);
        int total=followDao.countByExample(followExample);
        int pagesize=5;
        PageBean<UserRecomVo> userVoPageBean =new PageBean<>(pageNum,pagesize,total);

        int startIndex= userVoPageBean.getStartIndex();

        List<User> followUserList =userDao.selectFollowUserListByUserId(userId,startIndex,pagesize);
        List<UserVo> followUserVoList=userService.changeUserListToUserVoList(followUserList);
        List<UserRecomVo> userRecomVoList=new ArrayList<>();
        for (UserVo userVo: followUserVoList){
            UserRecomVo userRecomVo=new UserRecomVo(userVo,checkIsFollow(myUserId,userVo.getUserId()));
            userRecomVoList.add(userRecomVo);
        }

            userVoPageBean.setList(userRecomVoList);
            return ResultUtil.success(userVoPageBean);
    }

    @Transactional
    @Override
    public Result getBeFollowedUserVoList(Integer userId,Integer myUserId,Integer pageNum) {

        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andBeFollowedUserIdEqualTo(userId).andIsDelEqualTo(false);
        int total=followDao.countByExample(followExample);
        int pagesize=5;
        PageBean<UserRecomVo> userVoPageBean =new PageBean<>(pageNum,pagesize,total);

        int startIndex= userVoPageBean.getStartIndex();

        List<User> befollowUserList =userDao.selectBeFollowUserListByUserId(userId,startIndex,pagesize);
        List<UserVo> befollowedUserVoList =userService.changeUserListToUserVoList(befollowUserList);
        List<UserRecomVo> userRecomVoList=new ArrayList<>();
        for (UserVo userVo: befollowedUserVoList){
            UserRecomVo userRecomVo=new UserRecomVo(userVo,checkIsFollow(myUserId,userVo.getUserId()));
            userRecomVoList.add(userRecomVo);
        }

        userVoPageBean.setList(userRecomVoList);
        return ResultUtil.success(userVoPageBean);
    }

    @Transactional
    @Override
    public Result getMutualFollowUserVoList(Integer userId) {
        List<Integer> followUserIdList=getFollowUserIdList(userId);
        List<Integer> mutualUserIdList=new ArrayList<>();
        for (Integer followUserId:followUserIdList){
            if (checkIfMutualFollow(followUserId,userId)){
                mutualUserIdList.add(followUserId);
            }
        }
        List<User> userList=new ArrayList<>();
        for (Integer mutualUserId:mutualUserIdList){
            userList.add(userDao.selectByPrimaryKey(mutualUserId));
        }
        List<UserVo> mutualFollowUserVoList =userService.changeUserListToUserVoList(userList);
        return ResultUtil.success(mutualFollowUserVoList);
    }

    @Transactional
    @Override
    public List<Integer> getFollowUserIdList(Integer userId) {
        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andIsDelEqualTo(false).andFollowUserIdEqualTo(userId);
        List<Integer> followUserIdList=followDao.selectFollowUserIdList(userId);
        return followUserIdList;
    }


    @Transactional
    @Override
    public Result addFollow(Follow follow) {
        int followUserId =follow.getFollowUserId();
        int beFollowedUserId=follow.getBeFollowedUserId();
        if(checkIsFollow(followUserId,beFollowedUserId)){
            return null;
//            throw new WeiboException("添加关注失败：不要重复添加！");
        }else {
            follow.setGmtCreate(new Date());
            follow.setGmtModify(new Date());
            follow.setIsDel(false);
            int effectedNum=followDao.insertSelective(follow);
            if (effectedNum>0){
                userDataService.increaseBeFollowedAmount(beFollowedUserId);
                userDataService.increaseFollowAmount(followUserId);
                if (checkIsFollow(beFollowedUserId, followUserId)){
                    //对方也关注了我,增加双方的互相关注人数
                    userDataService.increaseMutualAmount(followUserId);
                    userDataService.increaseMutualAmount(beFollowedUserId);
                }
                return ResultUtil.success("添加关注成功！");
            }else {
                throw new WeiboException("添加关注失败：数据库未知错误！");
            }
        }
    }

    @Transactional
    @Override
    public Result deleteFollow(Follow follow) {
        int followedUserId=follow.getFollowUserId();
        int beFollowedUserId=follow.getBeFollowedUserId();
        follow.setIsDel(true);
        follow.setGmtModify(new Date());
        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andBeFollowedUserIdEqualTo(beFollowedUserId).andFollowUserIdEqualTo(followedUserId).andIsDelEqualTo(false);
        int effectedNum=followDao.updateByExampleSelective(follow,followExample);
        if (effectedNum>0){
            userDataService.decreaseBeFollowedAmount(beFollowedUserId);
            userDataService.decreasFollowAmount(followedUserId);
            if (checkIsFollow(beFollowedUserId,followedUserId)){
                //对方也关注了我,减少双方的互相关注人数
                userDataService.decreasMutualAmount(followedUserId);
                userDataService.decreasMutualAmount(beFollowedUserId);
            }
            return ResultUtil.success("删除关注成功！");
        }else {
            throw new WeiboException("删除关注失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public Result checkFollowRelationShip(Integer userId_one, Integer userId_two) {
        if(checkIfMutualFollow(userId_one,userId_two)){
            return ResultUtil.success("mutualfollow");
        }else if (checkIsFollow(userId_one,userId_two)){
            return ResultUtil.success("follow");
        }else if (checkIsFollow(userId_two, userId_one)){
            return ResultUtil.success("befollowed");
        }else {
            return ResultUtil.success("unfollow");
        }
    }

    @Transactional
    @Override
    public Boolean checkIsFollow(Integer userId_one, Integer userId_two) {
        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andFollowUserIdEqualTo(userId_one).andBeFollowedUserIdEqualTo(userId_two).andIsDelEqualTo(false);
        int effNum=followDao.selectByExample(followExample).size();
        if(effNum==1){
            return true;
        }else
        if(effNum==0){
            return false;
        }else{
            throw  new WeiboException("数据库关注条目重复！");
        }
    }

    @Transactional
    @Override
    public Boolean checkIfMutualFollow(Integer userId_one, Integer userId_two) {
        if (checkIsFollow(userId_one,userId_two)&& checkIsFollow(userId_two,userId_one)){
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    @Override
    public Integer countFollowNum(Integer userId) {
        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andFollowUserIdEqualTo(userId).andIsDelEqualTo(false);
        int followNum=followDao.countByExample(followExample);
        return followNum;
    }

    @Transactional
    @Override
    public Integer countFansNum(Integer userId) {
        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andBeFollowedUserIdEqualTo(userId).andIsDelEqualTo(false);
        int fansNum=followDao.countByExample(followExample);
        return fansNum;
    }

    @Transactional
    @Override
    public Integer countCommonUsersNum(Integer userId_one, Integer userId_two) {
        List<Integer> userOneFollowList=getFollowUserIdList(userId_one);
        List<Integer> userTwoFollowList=getFollowUserIdList(userId_two);
        Integer commonFollowNum=0;
        for (Integer userIdOne:userOneFollowList){
            for (Integer userIdTwo:userTwoFollowList){
                if (userIdOne==userIdTwo){
                    commonFollowNum++;break;
                }
            }
        }
        return commonFollowNum;
    }

    @Transactional
    @Override
    public List<Integer> getFansUserIdList(Integer userId) {
        List<Integer> fansUserIdList=followDao.selectFansUserIdList(userId);
        return fansUserIdList;
    }
}
