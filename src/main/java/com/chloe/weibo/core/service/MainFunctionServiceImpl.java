package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.*;
import com.chloe.weibo.pojo.data.PageBean;
import com.chloe.weibo.pojo.data.Result;
import com.chloe.weibo.core.entity.Collection;
import com.chloe.weibo.core.entity.entityExample.UserExample;
import com.chloe.weibo.core.entity.entityExample.WeiboExample;
import com.chloe.weibo.core.entity.*;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.*;
import com.chloe.weibo.common.utils.ResultUtil;
import com.chloe.weibo.pojo.vo.WeiboVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

@Service
public class MainFunctionServiceImpl implements MainFunctionService {
    @Autowired
    private WeiboDao weiboDao;
    @Autowired
    private FileDao fileDao;
    @Autowired
    private FollowDao followDao;
    @Autowired
    private ForwardingDao forwardingDao;
    @Autowired
    private CommentService commentService;
    @Autowired
    MainFunctionService mainFunctionService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private DiscussDao discussDao;
    @Autowired
    private FileService fileService;
    @Autowired
    private DiscussService discussService;
    @Autowired
    private WeiboService weiboService;
    @Autowired
    private ForwardingService forwardingService;
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private FollowService followService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private CollectionService collectionService;

    @Transactional
    @Override
    public Result sendWeiboVo(WeiboVo weiboVo) throws ParseException {

//        List<String> fileAddressList=weiboVo.getFiles();
        List<String> topicNameList=weiboVo.getTopics();

        String fileListString=weiboVo.getFileList();
        if (fileListString!=null){
            weiboVo.setIsOnlyText(false);
        }
        System.out.println(fileListString);

        Weibo weibo = new Weibo(weiboVo);

        //1.添加tb_weibo
        weiboService.addWeibo(weibo);

        int weiboId=weibo.getWeiboId();

//        //2.添加tb_file
//        fileService.addFileAddressList(weiboId,fileAddressList);

        fileService.addFileListString(weiboId,fileListString);

        //3.添加tb_discuss
        discussService.addTopicNameList(weiboId,topicNameList);

        userDataService.increaseWeiboAmount(weiboVo.getUserId());

        return ResultUtil.success("添加微博:成功！");
    }

    @Transactional
    @Override
    public Result updateWeiboVo(WeiboVo weiboVo) throws ParseException {
        int weiboId=weiboVo.getWeiboId();
        int userId=weiboVo.getUserId();

        WeiboVo org_weiboVo=mainFunctionService.getOneWeiboVoByWeiboId(userId,weiboId);
        Weibo weibo=new Weibo(weiboVo);

        List<String> new_files=weiboVo.getFiles();
        List<String> new_topics=weiboVo.getTopics();

        List<String> org_files=fileDao.selectFileAddressListByWeiboId(weiboId);
        List<String> org_topics=topicDao.selectTopicNameListByWeiboId(weiboId);

        //首先看更新内容是否有更改
        if (org_weiboVo.equals(weiboVo)){
            throw new WeiboException("该微博未修改，请修改后再提交！");
        }

        //1.更新文件
        fileService.updateFiles(weiboId,org_files,new_files);

        //2.更新话题
        discussService.updateTopicList(weiboId,org_topics,new_topics);

        //3.更新微博表
        weiboService.updateWeibo(weibo);

        return ResultUtil.success("修改微博:成功！");
    }

    @Transactional
    @Override
    public WeiboVo getOneWeiboVoByWeiboId(int orguserId,int weiboId) {
        Weibo weibo=weiboDao.selectByPrimaryKey(weiboId);
        if (weibo==null){
            throw new WeiboException("查找微博信息失败！");
        }

        int userId = weibo.getUserId();
        Boolean isOnlyText = weibo.getIsOnlyText();
        Boolean isTopic = weibo.getIsTopic();
        User user=userDao.selectByPrimaryKey(userId);
        if (user==null){
            throw new WeiboException("查找用户信息失败！");
        }
        List<String> files =null;
        if(!isOnlyText){
            files=fileDao.selectFileAddressListByWeiboId(weiboId);
            if (files.size()==0){
                throw new WeiboException("查找文件地址列表失败！");
            }
        }
        List<String> topics = null;
        if (isTopic){
            topics=topicDao.selectTopicNameListByWeiboId(weiboId);
            if (topics.size()==0){
                throw  new WeiboException("查找话题名列表失败！");
            }
        }

        Boolean isLike=likeService.IsLikeOneWeibo(orguserId,weiboId);
        Boolean isCollect=collectionService.IsCollectOneWeibo(orguserId,weiboId);

        WeiboVo weiboVo=new WeiboVo(weibo,user,files,topics,isLike,isCollect);

        return weiboVo;
    }

    @Transactional
    @Override
    public boolean deleteWeiboVo(int weiboId) {
        //首先看它是否为转发微博，转发微博要删除转发表，并将转发数降低1
        Weibo weibo=weiboDao.selectByPrimaryKey(weiboId);
        if (weibo.getWeiboType()){
            //为转发微博
            forwardingService.deleteForwarding(weiboId);
        }
        if (!weibo.getIsOnlyText()){
            //1.删除对应图片文件
            fileService.deleteFilesByWeiboId(weiboId);
        }
        //2.删除对应讨论表，并将该话题讨论度降低1
        commentService.deleteCommentByWeiboId(weiboId);
        //3.删除微博表
        weiboService.deleteWeibo(weiboId);

        userDataService.decreaseWeiboAmount(weibo.getUserId());

        return true;
    }

    @Transactional
    @Override
    public Result getOwnWeiboVoPageByUserId(int userId, int pagNum) {
        List<WeiboVo> weiboVoList = new ArrayList<>();

        //此人所发微博总数
        WeiboExample weiboExample=new WeiboExample();
        weiboExample.createCriteria().andUserIdEqualTo(userId).andIsDelEqualTo(false);

        int total=weiboDao.countByExample(weiboExample);
        int pagesize=5;
        PageBean<WeiboVo> weiboVoPageBean=new PageBean<>(pagNum,pagesize,total);

        int startIndex=weiboVoPageBean.getStartIndex();

        //一页所包含的微博id
        List<Integer> weiboIdList=weiboDao.selectWeiboIdByUserIdLimit(userId,startIndex,pagesize);

        for (Integer weiboId : weiboIdList) {
            WeiboVo weiboVo = mainFunctionService.getOneWeiboVoByWeiboId(userId,weiboId);
            weiboVoList.add(weiboVo);
        }
        weiboVoPageBean.setList(weiboVoList);
        return ResultUtil.success(weiboVoPageBean);
    }

    @Transactional
    @Override
    public Result getAllWeiboVoPageByUserId(int userId, int pagNum) {
        //存放所有关注了的人及自己的微博内容
        List<WeiboVo> weiboVoList = new ArrayList<>();

        //关注了的人的userid
        List<Integer> followList = followDao.selectFollowUserIdList(userId);

        followList.add(userId);//添加自己的userid进list

        Map<String,Object> map = new HashMap<>();
        map.put("list",followList);
        int totalNum= weiboDao.countAllWeiboNumberByUserIdList(map);

        int pageSize=10;
        PageBean<WeiboVo> weiboVoPageBean =new PageBean<>(pagNum,pageSize,totalNum);
        int startIndex= weiboVoPageBean.getStartIndex();
        map.put("startIndex",startIndex);
        map.put("pageSize",pageSize);
        List<Integer> weiboIdList=weiboDao.selectAllWeiboIdPageByUserIdListLimit(map);

        for (Integer weiboId : weiboIdList) {
            WeiboVo weiboVo = mainFunctionService.getOneWeiboVoByWeiboId(userId,weiboId);
            weiboVoList.add(weiboVo);
        }
        weiboVoPageBean.setList(weiboVoList);
        return ResultUtil.success(weiboVoPageBean);
    }

    @Transactional
    @Override
    public Result getForwardingOrignalWeiboContent(int weiboId) {

        String message=null;
        if (weiboDao.selectByPrimaryKey(weiboId).getWeiboType()){
            int org_weiboId=forwardingService.getOrgWeiboIdByWeiboId(weiboId);
            Weibo weibo=weiboDao.selectByPrimaryKey(org_weiboId);
            if (weibo.getWeiboType()){
                String userName=userDao.selectByPrimaryKey(weibo.getUserId()).getNickName();
                //原微博也为转发微博
                message="//用户"+userName+":"+weiboService.getWeiboContentByWeiboId(weiboId);
            }
        }
        return ResultUtil.success(message);
    }

    @Override
    public Result getWeiboContentByWeiboId(int weiboId) {
        String ownMessage=weiboService.getWeiboContentByWeiboId(weiboId);
        return ResultUtil.success(ownMessage);
    }

    @Transactional
    @Override
    public Result ForwardingWeiboVo(WeiboVo weiboVo, int org_weiboId) {
        try {
            forwardingService.addForwarding(weiboVo,org_weiboId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResultUtil.success("转发微博成功！");
    }

    @Transactional
    @Override
    public Result getAllWeiboVoListByUserId(int userId) {
        //存放所有关注了的人及自己的微博内容
        List<WeiboVo> weiboVoList = new ArrayList<>();

        //关注列表userid
        List<Integer> followUserIdList = followDao.selectFollowUserIdList(userId);

        //添加自己的userid进list
        followUserIdList.add(userId);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("list",followUserIdList);

        //存储所有相关微博的微博id
        List<Integer> weiboIdList =weiboDao.selectAllWeiboIdPageByUserIdList(map);

        //获取关注了的人的微博id
        for (Integer weiboId : weiboIdList) {
            WeiboVo weiboVo = mainFunctionService.getOneWeiboVoByWeiboId(userId,weiboId);
            weiboVoList.add(weiboVo);
        }

        if(weiboVoList.size()!=0){
            return ResultUtil.success(weiboVoList);
        }else{
            throw new WeiboException("没有微博内容，请发送微博！");
        }
    }

    @Transactional
    @Override
    public Result getOwnWeiboVoListByUserId(int userId) {

        //存放自己所有的微博内容
        List<WeiboVo> weiboVoList = new ArrayList<>();

        //关注列表userid
        List<Integer> userIdList = new ArrayList<>();

        //添加自己的userid进list
        userIdList.add(userId);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", userIdList);

        //存储所有相关微博的微博id
        List<Integer> weiboIdList = weiboDao.selectAllWeiboIdPageByUserIdList(map);

        //获取关注了的人的微博内容
        for (Integer weiboId : weiboIdList) {
            WeiboVo weiboVo = mainFunctionService.getOneWeiboVoByWeiboId(userId,weiboId);
            weiboVoList.add(weiboVo);
        }

        if (weiboVoList.size() != 0) {
            return ResultUtil.success(weiboVoList);
        } else {
            throw new WeiboException("没有微博内容，请发送微博！");
        }
    }

    @Transactional
    @Override
    public void changeComInfo() {
        List<Comment> commentList=commentService.getAllComment();
        for (Comment com:commentList){
            int weiboId=com.getWeiboId();
            int userId=weiboService.selectByWeiboId(weiboId).getUserId();
            List<Integer> fansUser=followService.getFansUserIdList(userId);
            Random rand = new Random();
            int fansId=fansUser.get(rand.nextInt(fansUser.size()));
            com.setUserId(fansId);
            com.setComContent(null);
            commentService.updateComment(com);
        }
    }

    @Transactional
    @Override
    public void changeFollowInfo() {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andIsDelEqualTo(false);
        List<User> userList=userDao.selectByExample(userExample);
        for (User user:userList){
            int userId=user.getUserId();
            int fansNum=followService.countFansNum(userId);
            int followNum=followService.countFollowNum(userId);
            if (fansNum<=0){
                //添加粉丝
                for (int i=0;i<8;i++){
                    Follow follow=new Follow();
                    follow.setFollowUserId(randomUserId());
                    follow.setBeFollowedUserId(userId);
                    follow.setIsDel(false);
                    follow.setGmtCreate(new Date());
                    follow.setGmtModify(new Date());
                    followService.addFollow(follow);
                }
            }
            if (followNum<=0){
                //添加关注的人
                for (int i=0;i<12;i++){
                    Follow follow=new Follow();
                    follow.setFollowUserId(userId);
                    follow.setBeFollowedUserId(randomUserId());
                    follow.setIsDel(false);
                    follow.setGmtCreate(new Date());
                    follow.setGmtModify(new Date());
                    followService.addFollow(follow);
                }
            }
        }
    }

    @Transactional
    @Override
    public Integer randomUserId() {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andIsDelEqualTo(false);
        List<User> userList=userDao.selectByExample(userExample);
        Random random=new Random();
        int userId=userList.get(random.nextInt(userList.size())).getUserId();
        return userId;
    }

    @Transactional
    @Override
    public void changeLikeInfo() {
        List<Like> likeList=likeService.getAllUserLikeList();
        for (Like like:likeList){
            int weiboId=like.getWeiboId();
            int userId=weiboService.selectByWeiboId(weiboId).getUserId();
            List<Integer> fansUser=followService.getFansUserIdList(userId);
            Random rand = new Random();
            int fansId=fansUser.get(rand.nextInt(fansUser.size()));
            like.setUserId(fansId);
            likeService.updateLike(like);
        }
    }

    @Transactional
    @Override
    public void changeCollectionInfo() {
        List<Collection> collectionList=collectionService.getAllCollectionList();
        for (Collection collection:collectionList){
            int weiboId=collection.getWeiboId();
            int userId=weiboService.selectByWeiboId(weiboId).getUserId();
            List<Integer> fansUser=followService.getFansUserIdList(userId);
            Random rand = new Random();
            int fansId=fansUser.get(rand.nextInt(fansUser.size()));
            collection.setUserId(fansId);
            collectionService.updateCollection(collection);
        }
    }

    @Transactional
    @Override
    public void changeMutualFollowNum() {
        List<UserData> userDataList=userDataService.getAllUserData();
        for (UserData userData:userDataList){
            int userId=userData.getUserId();
            int mutualNum=0;
            List<Integer> fansUserIdList=followService.getFansUserIdList(userId);
            for (int fansId:fansUserIdList){
                if (followService.checkIfMutualFollow(userId,fansId)){
                    mutualNum++;
                }
            }
            userData.setMutualAmount(mutualNum);
            userDataService.updateUserData(userData);
        }
        System.out.println(userDataList.size());
    }

    @Transactional
    @Override
    public void addMutualFollowNum() {
        List<UserData> userDataList=userDataService.getAllUserData();
        for (UserData userData:userDataList){
            int userId=userData.getUserId();
            int mutualNum=userData.getMutualAmount();
            if (mutualNum<4){
                List<Integer> fansUser=followService.getFansUserIdList(userId);
                if (fansUser.size()!=0){
                    for (int i=0;i<4;i++){
                        Random random=new Random();
                        int fansId=random.nextInt(fansUser.size());
                        if (!followService.checkIfMutualFollow(userId,fansUser.get(fansId))){
                            Follow follow=new Follow();
                            follow.setFollowUserId(userId);
                            follow.setBeFollowedUserId(fansUser.get(fansId));
                            follow.setGmtModify(new Date());
                            follow.setGmtCreate(new Date());
                            followService.addFollow(follow);
                        }
                    }
                }
            }
        }
    }
}
