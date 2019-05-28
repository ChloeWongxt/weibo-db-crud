package com.chloe.weibo.core.service;

import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.dao.FollowDao;
import com.chloe.weibo.core.dao.RecomUserMapper;
import com.chloe.weibo.core.entity.Follow;
import com.chloe.weibo.core.entity.RecomUser;
import com.chloe.weibo.core.entity.RecomUserExample;
import com.chloe.weibo.core.entity.entityExample.FollowExample;
import com.chloe.weibo.core.service.interfaces.FollowService;
import com.chloe.weibo.core.service.interfaces.UserRecommendService;
import com.chloe.weibo.core.service.interfaces.UserService;
import com.chloe.weibo.pojo.vo.UserRecomVo;
import com.chloe.weibo.pojo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/25
 */
@Service
public class UserRecommendServiceImpl implements UserRecommendService {
    @Autowired
    UserService userService;
    @Autowired
    FollowDao followDao;
    @Autowired
    RecomUserMapper recomUserMapper;
    @Autowired
    FollowService followService;

    static int usersum;     //用户数
    static int itemsum;	//物品总数
    static int N = 10;           //推荐个数
    static int[][] train; //训练集合user item rate矩阵
    static int[][] test;//测试集合user item rate矩阵
    static double[][] trainuseritem; //训练集合user item 兴趣程度 矩阵
    static int[][] recommend;  //为每个用户推荐N个物品
    static simi [][]simiItem; //排序后的相似性矩阵
    static double [][]itemsim; //未排序的相似性矩阵
    public static class simi
    {
        double value; //相似值
        int num;	 //相似物品号
    }

    @Transactional
    @Override
    public void getUserRecommend() {
        get_user_user_num();
        System.out.println("usersum: "+usersum);
        System.out.println("itemsum: "+itemsum);
        train = new int[itemsum][usersum]; train[0][0] = 0; //训练集合user item rate矩阵
        test = new int[itemsum][usersum]; test[0][0] = 0;  //测试集合user item rate矩阵
        trainuseritem =
                new double[usersum][itemsum]; trainuseritem[0][0] = 0.0; //训练集合user item 兴趣程度 矩阵
        recommend = new int[usersum][N]; recommend[0][0] = 0;  //为每个用户推荐N个物品
        simiItem = new simi[itemsum][itemsum]; //排序后的相似性矩阵

        itemsim = new double[itemsum][itemsum]; //未排序的相似性矩阵

        int i,j,k = 8;        //去用户的k个最近邻居（相似度最高）来计算推荐物品

        for(i = 0 ;i < itemsum;++i)
            for(j = 0 ;j < itemsum;++j) simiItem[i][j] = new simi();
        SplitData(8,1);
//
//        System.out.println("------------------------------------------------------------");
//        System.out.println("1.训练集");
//        //输出初始化的矩阵
//        for (i=0;i<10;i++)
//        {
//            System.out.println("Item"+(i+1)+":  ");
//            for (j=0;j<10;j++)
//            {
//                System.out.print(train[i][j]+"  ");
//            }
//            System.out.println();
//        }
//        System.out.println("------------------------------------------------------------");
//        System.out.println("2.计算物品之间相似性，得到相似性矩阵");
        for (i=0;i<itemsum;i++)
        {
            for (j=0;j<itemsum;j++)
            {
                itemsim[i][j] = Simility(train[i],train[j]);
                if(i == j) itemsim[i][j] = 0;   //此处有bug，已修改
            }
        }
//        System.out.println("------------------------------------------------------------");
//        //输出物品相似性矩阵
//        for (i=0;i<10;i++)
//        {
//            System.out.println("Item"+":  ");
//            for (j=0;j<10;j++)
//            {
//                System.out.print(itemsim[i][j]+"  ");
//            }
//            System.out.println();
//        }
//        System.out.println("------------------------------------------------------------");
//        System.out.println("3.物品相似度由高到低排序");
        sort();
//        //输出排序后的物品相似性矩阵
//
//        for(i=0;i<10;i++)
//        {
//            System.out.println("Item"+(i+1)+":  ");
//            for(j=0;j<10;j++)
//            {
//                System.out.print("Item"+simiItem[i][j].num+":"+simiItem[i][j].value+" ");
//            }
//            System.out.println();
//        }
//        System.out.println("------------------------------------------------------------");
//        System.out.println("4.得到用户对物品兴趣程度的矩阵");
        for(i=0;i<usersum;i++)
        {
            for(j=0;j<itemsum;j++)
            {
                if(train[j][i]==0)            //如果用户i对物品j没有过行为，才计算i对j的预测兴趣程度
                    trainuseritem[i][j]=getUserLikeItem(i,j,k);

            }
        }

//        //输出用户对物品兴趣的矩阵
//        for (i=0;i<10;i++)
//        {
//            System.out.println("User_ins"+i+":  ");
//            for (j=0;j<10;j++)
//            {
//                System.out.print(trainuseritem[i][j]+"  ");
//            }
//            System.out.println();
//        }
//        System.out.println("------------------------------------------------------------");

        System.out.println("5.通过物品兴趣程度，推荐前N个");
        getRecommend();
        //输出推荐矩阵
        for (i=0;i<usersum;i++)
        {
            RecomUser recomUser=new RecomUser();
            recomUser.setUserId(i+1);
//            System.out.println("user"+(i+1));
            for (j=0;j<N;j++)
            {
                if(recommend[i][j] != 0){
//                    System.out.print(recommend[i][j]+" ");
                    switch (j){
                        case 0:recomUser.setRecomUser1Id(recommend[i][j]);break;
                        case 1:recomUser.setRecomUser2Id(recommend[i][j]);break;
                        case 2:recomUser.setRecomUser3Id(recommend[i][j]);break;
                        case 3:recomUser.setRecomUser4Id(recommend[i][j]);break;
                        case 4:recomUser.setRecomUser5Id(recommend[i][j]);break;
                        case 5:recomUser.setRecomUser6Id(recommend[i][j]);break;
                        case 6:recomUser.setRecomUser7Id(recommend[i][j]);break;
                        case 7:recomUser.setRecomUser8Id(recommend[i][j]);break;
                        case 8:recomUser.setRecomUser9Id(recommend[i][j]);break;
                        case 9:recomUser.setRecomUser10Id(recommend[i][j]);break;
                    }
                }
            }
            //先查找数据库里推荐表中是否存在该用户
            RecomUserExample recomUserExample=new RecomUserExample();
            recomUserExample.createCriteria().andUserIdEqualTo(i+1);
            List<RecomUser> recomUserList=recomUserMapper.selectByExample(recomUserExample);
            if (recomUserList.size()!=0){
                //有就，判断推荐用户是否有修改，有修改则更新推荐表，无修改不做任何操作
                RecomUser recomUserOrg=recomUserList.get(0);

                recomUser.setRecomId(recomUserList.get(0).getUserId());

                if (!recomUser.equals(recomUserOrg)){
                    int effNum=recomUserMapper.updateByPrimaryKey(recomUser);
                    if (effNum<=0){
                        throw new WeiboException("推荐：更新推荐表失败");
                    }
                }
            }else {
                //没有就新增相应条目
                int effNum=recomUserMapper.insert(recomUser);
                if (effNum<=0){
                    throw new WeiboException("推荐：插入推荐信息失败");
                }
            }
//            System.out.println();
        }
//
//        System.out.println("------------------------------------------------------------");
//        System.out.println("6.输出到所推荐的N个用户");
//        for (int l = 0; l <usersum; l++) {
//            out_userRecommend(i);
//        }
//        System.out.println("------------------------------------------------------------");
    }

    //获取用户的数量
    @Transactional
    @Override
    public void get_user_user_num(){

        itemsum = userService.countUserNum();
        usersum = itemsum;
    }


    //得到推荐用户的矩阵中
    public void out_userRecommend(int userId){
        System.out.println("推荐给用户"+userId+":");
        for (int i = 0; i <N; i++) {
            if (recommend[userId][i]!=0){
                System.out.println("用户"+recommend[userId][i]);
            }
        }
    }

    //这里全部为测试集，k并没有用
    //拆分数据集为测试集test和训练集trainuser，其中1/m为测试集,取不同的k<=m-1值 在相同的随即种子下可得到不同的测/训集合
    @Transactional
    @Override
    public int SplitData(int m, int k)
    {
        FollowExample followExample=new FollowExample();
        followExample.createCriteria().andIsDelEqualTo(false);
        int followId = 0;
        int beFollowedId = 0;
        List<Follow> followList=followDao.selectByExample(followExample);
        System.out.println("followListSize:"+followList.size());
        for (int i=0;i<followList.size();i++){
            followId =followList.get(i).getFollowUserId();
            beFollowedId=followList.get(i).getBeFollowedUserId();
            if (followId <= usersum && beFollowedId <= itemsum)
            {
//                if(System.currentTimeMillis()%(m-1)==k) //设置当前时间为随机种子  //判断随机产生0-7之间的随机数是否等于k
//                    test[beFollowedId-1][followId-1] = 1;        //rate为评分，再此实验中只需统计有无评分的，无需讨论具体评分
//                else
                    train[beFollowedId-1][followId -1] = 1;  //用户号的物品号均从0开始算起，
            }
        }
        return 1;
    }

    //利用训练集计算用户之间相似度
    /* 计算向量ItemA和ItemB的相似性，返回值为ItemA和ItemB的相似度 */
    public static double Simility(int[] ItemA, int[] ItemB)
    {
        int comUser = 0;                   //ItemA与ItemB的都被用户评论的用户个数
        double simility = 0.0;
        int countIa = 0;
        int countIb = 0;

        int i;
        for (i=0;i<usersum;i++)      //此处有bug，已修改
        {
            if (ItemA[i]>0&&ItemB[i]>0)
            {
                comUser++;//查找ItemA与ItemB的都被用户评论的用户个数（被同一个用户关注的用户个数）
            }
            if (ItemA[i]>0){
                countIa++;//评论ItemA的用户数量（关注ItemA的用户量）
            }
            if (ItemB[i]>0){
                countIb++;//评论ItemB的用户数量（关注ItemB的用户量）
            }
        }
        double tem = Math.sqrt(countIa*countIb);

        if(tem == 0)
        {
            return 0;
        }
        else
        {
            simility = comUser/tem;
            return simility;
        }
    }


    /*物品相似性矩阵排序（根据相似性由高到低排序）*/
    public static void quickSort(int x, int start, int end) {
        if (start < end) {
            double base = simiItem[x][start].value; // 选定的基准值（第一个数值作为基准值）
            double temp; // 记录临时中间值
            int i_tmp;
            int i = start, j = end;
            do {
                while ((simiItem[x][i].value > base) && (i < end))
                    i++;
                while ((simiItem[x][j].value < base) && (j > start))
                    j--;
                if (i <= j) {
                    temp = simiItem[x][i].value;
                    simiItem[x][i].value = simiItem[x][j].value;
                    simiItem[x][j].value = temp;
                    i_tmp = simiItem[x][i].num;
                    simiItem[x][i].num = simiItem[x][j].num;
                    simiItem[x][j].num = i_tmp;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (start < j)
                quickSort(x, start, j);
            if (end > i)
                quickSort(x, i, end);
        }
    }

    public static int sort()
    {
        for (int i=0;i<itemsum;i++)
        {
            for(int j = 0; j < itemsum; ++j) {
                simiItem[i][j].num = j;
                simiItem[i][j].value = itemsim[i][j];
            }
            quickSort(i,0,itemsum-1);
        }
        return 1;

    }

    //得到用户i对物品j预测兴趣程度，用于推荐
    public static double getUserLikeItem(int i,int j,int k)
    {
        for(int x=0;x<k;x++)//从物品j最相似的k个物品中，找出用户i有过行为的物品
        {
//            System.out.println(simiItem[j][x].num);
            if(train[simiItem[j][x].num][i]>0)//若这个用户同样对相似物品也有过行为
            {
                trainuseritem[i][j]+=simiItem[j][x].value;
            }
        }
        return trainuseritem[i][j];
    }

    /*通过物品兴趣程度，推荐前N个*/
    public static int getRecommend() //有bug,已修改
    {
        int maxnum;//当前最感兴趣物品号
        for(int i=0;i<usersum;i++)
        {

            int []finflag = new int[itemsum];

            for (int x=0;x<N;x++)//推荐N个
            {
                maxnum = 0;
                while(maxnum < itemsum && finflag[maxnum]!=0)
                    maxnum++;
                for (int j=0;j<itemsum;j++)  //每循环一次就寻找此次感兴趣最大的物品
                {
                    if (trainuseritem[i][maxnum] < trainuseritem[i][j]&&finflag[j]==0)
                        maxnum = j;
                }
                finflag[maxnum] = 1;
                if(trainuseritem[i][maxnum] != 0)
                    recommend[i][x]=maxnum+1;//recommend数组从1开始使用
            }
        }
        return 1;
    }

    @Transactional
    @Override
    public RecomUser getRecomUser(int userId) {
        RecomUser recomUser;
        RecomUserExample recomUserExample=new RecomUserExample();
        recomUserExample.createCriteria().andUserIdEqualTo(userId);
        List<RecomUser> recomUserList=recomUserMapper.selectByExample(recomUserExample);
        if (recomUserList.size()==0){
            throw new WeiboException("该用户不存在于推荐表");
        }else {
            recomUser=recomUserList.get(0);
        }
        return recomUser;
    }

    @Transactional
    @Override
    public List<UserRecomVo> getRecomUserInfoList(int userId) {
        RecomUser recomUser=getRecomUser(userId);
        List<Integer> recomUserIdList=new ArrayList<>();

        int recomNum=5;//主页显示推荐用户个数
        switch (recomNum){
            case 10:
                if (recomUser.getRecomUser10Id()!=null) {
                    recomUserIdList.add(recomUser.getRecomUser10Id());
                }
            case 9:if (recomUser.getRecomUser9Id()!=null) {
                    recomUserIdList.add(recomUser.getRecomUser9Id());
            }
            case 8:if (recomUser.getRecomUser8Id()!=null) {
                    recomUserIdList.add(recomUser.getRecomUser8Id());
            }
            case 7:if (recomUser.getRecomUser7Id()!=null) {
                    recomUserIdList.add(recomUser.getRecomUser7Id());
            }
            case 6:if (recomUser.getRecomUser6Id()!=null) {
                    recomUserIdList.add(recomUser.getRecomUser6Id());
            }
            case 5:if (recomUser.getRecomUser5Id()!=null) {
                    recomUserIdList.add(recomUser.getRecomUser5Id());
            }
            case 4:if (recomUser.getRecomUser4Id()!=null) {
                    recomUserIdList.add(recomUser.getRecomUser4Id());
            }
            case 3:if (recomUser.getRecomUser3Id()!=null) {
                    recomUserIdList.add(recomUser.getRecomUser3Id());
            }
            case 2:if (recomUser.getRecomUser2Id()!=null) {
                    recomUserIdList.add(recomUser.getRecomUser2Id());
            }
            case 1:if (recomUser.getRecomUser1Id()!=null) {
                    recomUserIdList.add(recomUser.getRecomUser1Id());
            }
        }
        if (recomUserIdList.size()!=0){
            Collections.reverse(recomUserIdList);
            List<UserRecomVo> userRecomVoList=new ArrayList<>();
            for(int recomUserId:recomUserIdList){
                UserVo userVo=userService.getUserVoByUserId(recomUserId);
                UserRecomVo userRecomVo=new UserRecomVo(userVo,followService.checkIsFollow(userId,recomUserId));
                userRecomVoList.add(userRecomVo);
            }
            return userRecomVoList;
        }else {
            return null;
        }
    }

    //系统启动时，自动启动
//    @PostConstruct
//    public void start() {
//        getUserRecommend();
//    }
}
