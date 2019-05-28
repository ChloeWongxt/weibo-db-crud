package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.FollowDao;
import com.chloe.weibo.core.dao.UserDao;
import com.chloe.weibo.core.dao.UserTagDao;
import com.chloe.weibo.core.dao.WeiboDao;
import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.User;
import com.chloe.weibo.core.entity.entityExample.UserExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.UserDataService;
import com.chloe.weibo.core.service.interfaces.UserService;
import com.chloe.weibo.core.service.interfaces.UserTagService;
import com.chloe.weibo.common.utils.EncryptUtils;
import com.chloe.weibo.common.utils.ResultUtil;
import com.chloe.weibo.pojo.vo.UserVo;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final int HASH_ITERATIONS = 1024;

    private static final int SALT_SIZE = 8;

    @Autowired
    UserDao userDao;
    @Autowired
    WeiboDao weiboDao;
    @Autowired
    FollowDao followDao;
    @Autowired
    UserTagDao userTagDao;
    @Autowired
    UserTagService userTagService;
    @Autowired
    UserDataService userDataService;

    @Transactional
    @Override
    public Result getUserVoList() {

        //测试
        UserExample userExample=new UserExample();
        userExample.createCriteria().andIsDelEqualTo(false);
        //查询所有用户的数量
        int userNum=userDao.countByExample(userExample);
        System.out.println("用户数量"+userNum);

        List<User> userList=getUserList();
        List<UserVo> userVoList=new ArrayList<>();
        for (User user:userList){
            userVoList.add(changeUserToUserVo(user));
        }
        return ResultUtil.success(userVoList);
    }

    @Transactional
    @Override
    public List<User> getUserList() {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andIsDelEqualTo(false);
        List<User> userList=userDao.selectByExample(userExample);
        return userList;
    }

    @Transactional
    @Override
    public UserVo changeUserToUserVo(User user) {
        List<String> tags=userTagDao.getTagNameListByUserId(user.getUserId());
        UserVo userVo=new UserVo(user,tags);
        return userVo;
    }

    @Transactional
    @Override
    public List<UserVo> changeUserListToUserVoList(List<User> userList) {
        List<UserVo> userVoList=new ArrayList<>();
        for (User user:userList){
            userVoList.add(changeUserToUserVo(user));
        }
        return userVoList;
    }

    @Transactional
    @Override
    public UserVo getUserVoByUserId(Integer userId) {
        User user=userDao.selectByPrimaryKey(userId);
        user.setUserPassword(null);
        return changeUserToUserVo(user);
    }

    @Transactional
    @Override
    public Result getUserVoByUserIdReturnResult(Integer userId) {
        UserVo userVo=getUserVoByUserId(userId);
        if (userVo!=null){
            return ResultUtil.success(userVo);
        }else {
            return ResultUtil.error("查询微博信息失败！");
        }
    }

    @Transactional
    @Override
    public void addUserVo(UserVo userVo) {
        String phoneNumber=userVo.getPhoneNumber();
        if (phoneNumber!=""&&phoneNumber!=null){
            if (!userVo.getPhoneNumber().matches("1[0-9]{10}") ||
                    userVo.getPhoneNumber().length() > 11) {
                throw new WeiboException("添加用户表失败：手机号符合规范！");
            }
        }

        userVo.setUserPassword(encryptPsd(userVo.getUserPassword()));
        User user=new User(userVo);
        if (userDao.insertSelective(user)<=0){
            throw new WeiboException("添加用户表失败：数据库未知错误！");
        }else {
            int userId=user.getUserId();
            //添加 tags表
            List<String> tags=userVo.getTags();
            if (tags!=null){
                userTagService.addUserTagList(userId,tags);
            }
            userDataService.addUserData(userId);
        }
    }

    @Transactional
    @Override
    public Result addUserVoRetrunResult(UserVo userVo) {
        addUserVo(userVo);
        return ResultUtil.success("添加用户成功！");
    }

    @Transactional
    @Override
    public void deleteUser(Integer userId) {
        //不能注销用户！
    }

    @Transactional
    @Override
    public Result deleteUserVo(Integer userId) {
        //不能注销用户！
        return null;
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        if (userDao.updateByPrimaryKeySelective(user)<0){
            throw new WeiboException("更新用户表失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public Result updateUserVo(UserVo userVo) {
//        List<String> new_tagsName=userVo.getTags();
        User new_user=new User(userVo);
        int userId=userVo.getUserId();

        UserVo org_userVo=getUserVoByUserId(userId);
        User org_user=userDao.selectByPrimaryKey(userId);
//        List<String> org_tagsName=userTagDao.getTagNameListByUserId(userId);

        if (org_user.equals(new_user)){
            //用户信息没有修改
            return ResultUtil.error("更新用户信息失败：请修改后再提交！");
        }else {
            updateUser(new_user);
        }
//        userTagService.updateUserTag(userId,org_tagsName,new_tagsName);
        return ResultUtil.success("更新用户成功！");
    }

    @Transactional
    @Override
    public User checkPassword(String loginName, String password) {
        if (loginName.isEmpty() || password.isEmpty()) {
            return null;
        }
        List<User> userList=userDao.getPassword(loginName);
        if (userList.isEmpty()){
            return null;
        }
        User user=userList.get(0);
        String encryptedPassword = user.getUserPassword();
        if (validatePsd(password, encryptedPassword)) {
            return user;
        }
        return null;
    }

    @Transactional
    @Override
    public Result queryUserIdByWeiboId(int weiboId) {
        int userId =userDao.selectByWeiboId(weiboId);
        return ResultUtil.success(userId);
    }

    @Transactional
    @Override
    public Integer countUserNum() {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andIsDelEqualTo(false);
        //查询所有用户的数量
        int userNum=userDao.countByExample(userExample);
        System.out.println("用户数量"+userNum);
        return userNum;
    }

    @Override
    public String encryptPsd(String plainPsd) {
        //密码加密
        // 1.获取随机数
        byte[] salt = EncryptUtils.generateSalt(SALT_SIZE);
        // 2.对随机数用Hex编码
        String encodedSalt = EncryptUtils.encodeHex(salt);
        // 3.将随机数和盐通过sha1算法加密
        byte[] sha1Psd = EncryptUtils.sha1(plainPsd.getBytes(), salt, HASH_ITERATIONS);
        // 4.将上一步得到的值通过Hex来编码
        String hexSha1 = new String(Hex.encodeHex(sha1Psd));
        // 5.将第二步和第四步的值拼凑
        String encryptedPsd = encodedSalt + hexSha1;

        return encryptedPsd;
    }

    @Override
    public boolean validatePsd(String plainPsd, String encryptedPsd) {
        //密码验证
        boolean flag = false;
        try {
            // 1.获取密文中的盐
            byte[] salt = Hex.decodeHex(encryptedPsd.substring(0, SALT_SIZE * 2).toCharArray());
            // 2.将盐和plainPsd加密HASH_ITERATIONS次
            byte[] sha1Psd = EncryptUtils.sha1(plainPsd.getBytes(), salt, HASH_ITERATIONS);
            // 3.用Hex来编码
            String sha1Hex = new String(Hex.encodeHex(sha1Psd));
            // 4.拼凑第三步得到的值和密文中的盐
            String psd = encryptedPsd.substring(0, SALT_SIZE * 2) + sha1Hex;
            if (psd.equals(encryptedPsd)) {
                flag = true;
            }
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
