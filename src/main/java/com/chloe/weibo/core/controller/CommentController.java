package com.chloe.weibo.core.controller;

import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.Comment;
import com.chloe.weibo.core.service.interfaces.CommentService;
import com.chloe.weibo.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ChloeWong
 * @date 2018/9/8
 */
@RestController
public class CommentController {

    @Autowired
    public CommentService commentService;

    //发送评论
    //参数：weiboId,userId,comContent
    @PostMapping(value = "/add-comment")
    public Result addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return ResultUtil.success("添加评论成功！");
    }

    /**
     * 删除评论
     * 参数：userId,weiboId,comContent
     * @param comId
     * @return
     */
    @PostMapping(value = "/delete-comment")
    public Result deleteComment(@RequestParam("comId") int comId) {
        commentService.deleteCommentByComId(comId);
        return ResultUtil.success("删除评论成功");
    }

    //获取评论
    //参数：weiboId
    @GetMapping(value = "/get-comment")
    public Result getComment(@RequestParam("weiboId") int weiboId) {
        return ResultUtil.success(commentService.getCommentVoListByWeiboId(weiboId));
    }
}
