package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.CommentDao;
import com.chloe.weibo.core.dao.UserDao;
import com.chloe.weibo.core.entity.Comment;
import com.chloe.weibo.core.entity.User;
import com.chloe.weibo.core.entity.entityExample.CommentExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.CommentService;
import com.chloe.weibo.core.service.interfaces.WeiboService;
import com.chloe.weibo.pojo.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private WeiboService weiboService;

    @Transactional
    @Override
    public List<Comment> getCommentListByWeiboId(int weiboId) {
        CommentExample commentExample=new CommentExample();
        commentExample.createCriteria().andWeiboIdEqualTo(weiboId).andIsDelEqualTo(false);
        List<Comment> commentList=commentDao.selectByExample(commentExample);
        return commentList;
    }

    @Transactional
    @Override
    public List<Comment> getAllComment() {
        CommentExample commentExample=new CommentExample();
        commentExample.createCriteria().andIsDelEqualTo(false);
        List<Comment> commentList=commentDao.selectByExample(commentExample);
        return commentList;
    }

    @Transactional
    @Override
    public void addComment(Comment com) {
        com.setGmtCreate(new Date());
        com.setGmtModify(new Date());
        com.setIsDel(false);
        int effNum=commentDao.insertSelective(com);
        if(effNum<0){
            throw new WeiboException("添加评论失败:数据库未知错误！");
        }else if(effNum==0){
            throw new WeiboException("添加评论失败！");
        }
        weiboService.increaseCommentAmount(com.getWeiboId());
    }

    @Transactional
    @Override
    public void deleteCommentByComId(int comId) {
        Comment comment=commentDao.selectByPrimaryKey(comId);
        int weiboId=comment.getWeiboId();
        comment.setGmtModify(new Date());
        comment.setIsDel(true);
        int effNum=commentDao.updateByPrimaryKeySelective(comment);
        if(effNum<0){
            throw new WeiboException("删除评论失败:数据库未知错误！");
        }else if(effNum==0){
            throw new WeiboException("删除评论失败！");
        }
        weiboService.decreaseCommentAmount(weiboId);
    }

    @Transactional
    @Override
    public void deleteCommentByWeiboId(int weiboId) {
        CommentExample commentExample=new CommentExample();
        commentExample.createCriteria().andWeiboIdEqualTo(weiboId).andIsDelEqualTo(false);

        Comment comment=new Comment();
        comment.setGmtModify(new Date());
        comment.setIsDel(true);
        int effNum=commentDao.updateByExampleSelective(comment,commentExample);
        if(effNum<0){
            throw new WeiboException("删除评论失败:数据库未知错误！");
        }
        weiboService.zeroCommentAmount(weiboId);
    }

    @Transactional
    @Override
    public List<CommentVo> getCommentVoListByWeiboId(int weiboId) {
        List<CommentVo> commentVoList=new ArrayList<>();
        List<Comment> commentList=getCommentListByWeiboId(weiboId);
        //判断评论是否为空
        if(commentList.size()==0){
            return null;
        }
        for (Comment comment:commentList){
            User user=userDao.selectByPrimaryKey(comment.getUserId());
            CommentVo commentVo=new CommentVo(comment,user);
            commentVoList.add(commentVo);
        }
        return commentVoList;
    }

    @Transactional
    @Override
    public void updateComment(Comment comment) {
        int effNum=commentDao.updateByPrimaryKeySelective(comment);
        if (effNum<=0){
            throw new WeiboException("更新评论失败");
        }
    }
}
