package com.chloe.weibo.core.controller;

import com.chloe.weibo.core.service.interfaces.*;
import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.User;
import com.chloe.weibo.common.utils.ResultUtil;
import com.chloe.weibo.pojo.vo.UserRecomVo;
import com.chloe.weibo.pojo.vo.UserVo;
import com.chloe.weibo.pojo.vo.WeiboVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChloeWong
 * @date 2018/9/8
 */
@RestController
public class UserController {

    /**
     * 用来打印日志
     */
    Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserTagService userTagService;

    @Autowired
    private UserRecommendService userRecommendService;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private FollowService followService;

    /**
     * 添加用户信息
     * userPassword,userName
     * @param userVo
     * @return
     */
    @PostMapping(value = "/add-user")
    public Result addUserInfo(@RequestBody UserVo userVo) {
        userService.addUserVo(userVo);
        return ResultUtil.success("添加用户信息成功！");
    }

    /**
     * 删除用户信息
     * userId
     * @param userVo
     * @return
     */
    @PostMapping(value = "/delete-user")
    public Result deleteUserInfo(UserVo userVo) {
        //用户不能注销!!!
        userService.updateUserVo(userVo);
        return ResultUtil.success("删除用户信息成功！");
    }

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/query-user")
    public Result queryUserInfo(@RequestParam("userId")int userId,@RequestParam("myUserId")int myUserId) {
        UserVo userVo=userService.getUserVoByUserId(userId);
        UserRecomVo userRecomVo=new UserRecomVo(userVo,followService.checkIsFollow(myUserId,userId));
        return ResultUtil.success(userRecomVo);
    }

    /**
     * 更新用户信息
     * 参数：userid,....
     *
     * @param userVo
     * @return
     */
    @PostMapping(value = "/update-user")
    public Result updateUserInfo(@RequestBody UserVo userVo) {
        userService.updateUserVo(userVo);
        return ResultUtil.success("修改用户信息成功！");
    }

    /**
     * 用户登录
     * @param
     * @return
     */
    @PostMapping(value = "/loginUser")
    public Result loginUser(@RequestBody UserVo userVo) {
        String logName=userVo.getLogName();
        String userPassword=userVo.getUserPassword();
//        logger.info("登录用户名：" + logName + "，密码：" + userPassword);
//        System.out.println(logName+userPassword);
        if (!logName.isEmpty() && !userPassword.isEmpty()) {
            User user = userService.checkPassword(logName, userPassword);
            if (user != null) {
//                logger.info(logName + "登录成功");
                int userId=user.getUserId();
                userVo=userService.getUserVoByUserId(userId);
                return ResultUtil.success(userVo);
            } else {
                return ResultUtil.error("登录失败");
            }
        }
        return ResultUtil.error("登录失败");
    }

    /**
     * 获取用户微博数、粉丝数、关注数、被关注数
     * @return
     */
    @GetMapping(value = "/get-user-data")
    public Result getUserData(int userId) {
        return ResultUtil.success(userDataService.getUserData(userId));
    }

    /**
     * 查看所有用户信息
     * @return
     */
    @GetMapping(value = "/query-all-userInfo")
    public Result queryAllUserInfo() {
        Result result= userService.getUserVoList();
        return result;
    }

    /**
     * 查找微博的发送用户的UserId
     * @param weiboId
     * @return
     */
    @GetMapping(value = "/query-userId-by-weibo-id")
    public Result queryUserIdByWeiboId(int weiboId) {
        Result result= userService.queryUserIdByWeiboId(weiboId);
        return result;
    }

    /**
     * 获取推荐用户信息UserVo
     * @param userId
     * @return
     */
    @GetMapping(value = "/get-recommend-userId")
    public Result getRecommendUserId(int userId) {
        return ResultUtil.success(userRecommendService.getRecomUserInfoList(userId));
    }


    @GetMapping(value = "/get-user-labels")
    public Result getRecommendUserId() {
        userTagService.insertTagsTest();
        return ResultUtil.success("插入标签成功");
    }

    /**
     * 更新用户头像
     * @param userVo
     * @return
     */
    @PostMapping(value = "/update-user-avatar")
    public Result updateUserAvatar(@RequestBody UserVo userVo) {
        userService.updateUserVo(userVo);
        return ResultUtil.success("修改用户信息成功！");
    }

    /**
     * 模糊搜索用户
     * @return
     */
    @GetMapping("/search-user")
    public Result SearchUser(@RequestParam("userId")int userId,@RequestParam("userName")String userName,@RequestParam("pageNum")int pageNum) {
        return userService.getSearchUserVo(userId,userName,pageNum);
    }

    /**
     * 获取热门用户
     * @return
     */
    @GetMapping("/get-hot-user")
    public Result GetHotUser(@RequestParam("userId")int userId,@RequestParam("pageNum")int pageNum) {
        return userService.getHotUserVo(userId,pageNum);
    }

    /**
     * 获取共同关注的用户
     * @return
     */
    @GetMapping("/get-common-follow-user")
    public Result GetCommonFollowUser(@RequestParam("myUserId")int myUserId,@RequestParam("userId")int userId,@RequestParam("pageNum")int pageNum) {
        return userService.getCommonFollowUser(myUserId,userId,pageNum);
    }

    /**
     * 获取我关注的人也关注他的用户
     * @return
     */
    @GetMapping("/get-my-follow-her-user")
    public Result GetMyFollowHerUser(@RequestParam("myUserId")int myUserId,@RequestParam("userId")int userId,@RequestParam("pageNum")int pageNum) {
        return userService.getMyFollowHerUser(myUserId,userId,pageNum);
    }

    /**
     * 更新用户推荐表
     * @return
     */
    @PostMapping(value = "/update-recom-user")
    public Result updateRecomUser() {
        userRecommendService.getUserRecommend();
        return ResultUtil.success("更新推荐表成功");
    }
}
