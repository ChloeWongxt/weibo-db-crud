package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.core.entity.Comment;
import com.chloe.weibo.pojo.vo.CommentVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> getCommentListByWeiboId(int WeiboId);
    List<Comment> getAllComment();
    void addComment(Comment com);
    void deleteCommentByComId(int ComId);
    void deleteCommentByWeiboId(int weiboId);
    List<CommentVo> getCommentVoListByWeiboId(int weiboId);
    void updateComment(Comment comment);
}
