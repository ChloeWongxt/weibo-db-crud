package com.chloe.weibo.core.controller;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.service.interfaces.MainFunctionService;
import com.chloe.weibo.core.service.interfaces.UserRecommendService;
import com.chloe.weibo.core.service.interfaces.UserService;
import com.chloe.weibo.core.service.interfaces.UserTagService;
import com.chloe.weibo.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ChloeWong
 * @date 2018/9/8
 */
@RestController
public class WeiboTestController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserTagService userTagService;

    @Autowired
    private UserRecommendService userRecommendService;

    @Autowired
    private MainFunctionService mainFunctionService;

    /**
     * 随机获取用户的用户Id
     * @param
     * @return
     */
    @PostMapping(value = "/random-userId")
    public Result randomUserId() {
        int userId=mainFunctionService.randomUserId();
        System.out.println(userId);
        return ResultUtil.success("随机获取用户Id成功！");
    }

    /**
     * 随机获取用户的用户Id
     * @param
     * @return
     */
    @PostMapping(value = "/add-followInfo-test")
    public Result addFollowInfoTest() {
        mainFunctionService.changeFollowInfo();
        return ResultUtil.success("添加关注和被关注信息成功！");
    }

    /**
     * 改变评论者的ID为关注该微博发布用户的ID
     * @param
     * @return
     */
    @PostMapping(value = "/change-com-userId")
    public Result changeComUserId() {
        mainFunctionService.changeComInfo();
        return ResultUtil.success("修改评论userId成功！");
    }

    /**
     * 改变点赞者的ID为关注该微博发布用户的ID
     * @param
     * @return
     */
    @PostMapping(value = "/change-like-userId")
    public Result changeLikeUserId() {
        mainFunctionService.changeLikeInfo();
        return ResultUtil.success("修改点赞userId成功！");
    }

    /**
     * 改变收藏者的ID为关注该微博发布用户的ID
     * @param
     * @return
     */
    @PostMapping(value = "/change-collection-userId")
    public Result changeCollectionUserId() {
        mainFunctionService.changeCollectionInfo();
        return ResultUtil.success("修改收藏者userId成功！");
    }

    /**
     * 改变收藏者的ID为关注该微博发布用户的ID
     * @param
     * @return
     */
    @PostMapping(value = "/change-mutual-follow-num")
    public Result changeMutualFollowNum() {
        mainFunctionService.changeMutualFollowNum();
        return ResultUtil.success("修改所有相互关注数量成功！");
    }

    /**
     * 增加相互关注的人数
     * @param
     * @return
     */
    @PostMapping(value = "/add-mutual-follow-num")
    public Result addMutualFollowNum() {
        mainFunctionService.addMutualFollowNum();
        return ResultUtil.success("增加相互关注成功！");
    }

    @PostMapping(value = "/check-user-info")
    public Result checkUserInfo() {
        mainFunctionService.checkUserInofo();
        return ResultUtil.success("用户微博数粉丝数等已更新");
    }

    @PostMapping(value = "/check-weibo-info")
    public Result checkWeiboInfo() {
        mainFunctionService.checkWeiboInfo();
        return ResultUtil.success("微博的点赞数，转发数等已更新");
    }

}
