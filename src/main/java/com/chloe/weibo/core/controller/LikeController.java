package com.chloe.weibo.core.controller;

import com.chloe.weibo.common.utils.ResultUtil;
import com.chloe.weibo.core.entity.Collection;
import com.chloe.weibo.core.entity.Like;
import com.chloe.weibo.core.service.interfaces.CollectionService;
import com.chloe.weibo.core.service.interfaces.LikeService;
import com.chloe.weibo.pojo.data.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ChloeWong
 * @date 2019/5/24
 */
@RestController
public class LikeController {
    @Autowired
    public LikeService likeService;

    /**
     * 添加点赞
     * @param like
     * @return
     */
    @PostMapping(value = "/add-like")
    public Result addLike(@RequestBody Like like) {
        int userId=like.getUserId();
        int weiboId=like.getWeiboId();
        likeService.addLike(userId,weiboId);
        return ResultUtil.success("添加点赞成功！");
    }

    /**
     * 删除点赞
     * @param
     * @param
     * @return
     */
    @PostMapping(value = "/delete-like")
    public Result deleteLike(@RequestBody Like like) {
        likeService.deleteLike(like.getUserId(),like.getWeiboId());
        return ResultUtil.success("删除点赞成功");
    }

    //获取某一用户的点赞信息
    //参数：weiboId
    @GetMapping(value = "/get-like-weibo")
    public Result getLike(@RequestParam("userId") int userId,@RequestParam("pagNum")int pagNum) {
        return ResultUtil.success(likeService.getLikeListByUserId(userId));
    }

    //查询用户对某条微博是否点赞
    //参数：weiboId
    @GetMapping(value = "/get-is-like-weibo")
    public Result getIsLike(@RequestParam("userId") int userId,@RequestParam("weiboId") int weiboId) {
        if(likeService.IsLikeOneWeibo(userId,weiboId)){
            return ResultUtil.success("已点赞");
        }else {
            return ResultUtil.error("未点赞");
        }
    }

}
