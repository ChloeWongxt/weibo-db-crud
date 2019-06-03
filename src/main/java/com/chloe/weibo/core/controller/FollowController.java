package com.chloe.weibo.core.controller;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.Follow;
import com.chloe.weibo.core.service.interfaces.FollowService;
import com.chloe.weibo.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;

    /**
     * 添加关注 followUserId，beFollowedUserId
     * @param follow
     * @return
     */
    @PostMapping(value = "/add-follow")
    public Result addFollow(@RequestBody Follow follow) {
        Result result= followService.addFollow(follow);
        return result;
    }

    @PostMapping(value = "/change-data-info")
    public Result changeDataInfo() {
        return ResultUtil.success("修改成功");
    }

    /**
     * 删除关注 followUserId,beFollowedUserId
     * @param follow
     * @return
     */
    @PostMapping(value = "/delete-follow")
    public Result deleteFollow(@RequestBody Follow follow) {
        Result result= followService.deleteFollow(follow);
        return result;
    }

    /**
     * 查看我所关注的所有人
     * @param userId
     * @return
     */
    @GetMapping(value = "/query-all-follow")
    public Result queryAllFollow(@RequestParam("userId") int userId,@RequestParam("myUserId")int myUserId,@RequestParam("pageNum") int pageNum) {
        Result result= followService.getFollowUserVoList(userId,myUserId,pageNum);
        return result;
    }

    /**
     * 查看和我相互关注的人
     * @param userId
     * @return
     */
    @GetMapping(value = "/query-mutual-follow")
    public Result queryMutualFollow(int userId) {
        Result result= followService.getMutualFollowUserVoList(userId);
        return result;
    }

    /**
     * 查看所有关注我的人
     * @param userId
     * @return
     */
    @GetMapping(value = "/query-follow-me")
    public Result queryFollowMe(@RequestParam("userId")int userId,@RequestParam("myUserId")int myUserId,@RequestParam("pageNum")int pageNum) {
        Result result= followService.getBeFollowedUserVoList(userId,myUserId,pageNum);
        return result;
    }

    /**
     * 查询相互关注情况
     * @param userId_one
     * @param userId_two
     * @return
     */
    @GetMapping(value = "/query-follow-relationship")
    public Result queryFollowRelationship(@RequestParam("userId_one")int userId_one,@RequestParam("userId_two")int userId_two) {

        Result result= followService.checkFollowRelationShip(userId_one,userId_two);

        return result;
    }

    @PostMapping(value = "/query-is-follow")
    public Result queryIsFollowRelationship(@RequestBody Follow follow) {

        Result result= followService.checkFollowRelationShip(follow.getFollowUserId(),follow.getBeFollowedUserId());

        return result;
    }


}
