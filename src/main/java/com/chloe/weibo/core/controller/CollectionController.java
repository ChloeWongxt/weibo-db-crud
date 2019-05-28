package com.chloe.weibo.core.controller;

import com.chloe.weibo.common.utils.ResultUtil;
import com.chloe.weibo.core.entity.Collection;
import com.chloe.weibo.core.service.interfaces.CollectionService;
import com.chloe.weibo.pojo.data.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ChloeWong
 * @date 2019/5/24
 */
@RestController
public class CollectionController {
    @Autowired
    public CollectionService collectionService;

    /**
     * 添加收藏
     * @param collection
     * @return
     */
    @PostMapping(value = "/add-collection")
    public Result addCollection(@RequestBody Collection collection) {
        collectionService.addCollection(collection);
        return ResultUtil.success("添加收藏成功！");
    }

    /**
     * 删除收藏
     * @param userId
     * @param weiboId
     * @return
     */
    @PostMapping(value = "/delete-collection")
    public Result deleteCollection(@RequestBody Collection collection) {
        collectionService.deleteCollection(collection.getUserId(),collection.getWeiboId());
        return ResultUtil.success("删除收藏成功");
    }

    //获取某一用户的收藏微博
    //参数：weiboId
    @GetMapping(value = "/get-collection-weibo")
    public Result getCollection(@RequestParam("userId") int userId) {
        return ResultUtil.success(collectionService.getCollectionWeibo(userId));
    }

    //查询用户对某条微博是否收藏
    //参数：weiboId
    @GetMapping(value = "/get-is-collection-weibo")
    public Result getIsCollection(@RequestParam("userId") int userId,@RequestParam("weiboId") int weiboId) {
        if(collectionService.IsCollectOneWeibo(userId,weiboId)){
            return ResultUtil.success("已收藏");
        }else {
            return ResultUtil.error("未收藏");
        }
    }
}
