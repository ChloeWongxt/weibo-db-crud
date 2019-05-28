package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.TagDao;
import com.chloe.weibo.core.dao.UserTagDao;
import com.chloe.weibo.core.entity.Tag;
import com.chloe.weibo.core.entity.UserTag;
import com.chloe.weibo.core.entity.Userlabels;
import com.chloe.weibo.core.entity.entityExample.UserTagExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.TagService;
import com.chloe.weibo.core.service.interfaces.UserTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/11
 */
@Service
public class UserTagServiceImpl implements UserTagService {
    @Autowired
    UserTagDao userTagDao;
    @Autowired
    TagDao tagDao;
    @Autowired
    TagService tagService;
    @Autowired
    UserTagService userTagService;

    @Transactional
    @Override
    public int getUserTagIdByUserIdAndTagId(Integer userId, Integer tagId) {
        UserTagExample userTagExample=new UserTagExample();
        userTagExample.createCriteria().andUserIdEqualTo(userId).andTagIdEqualTo(tagId).andIsDelEqualTo(false);
        List<UserTag> userTags=userTagDao.selectByExample(userTagExample);
        if (userTags.size()<0){
            throw new WeiboException("查询用户-标签ID失败：数据库未知错误");
        }else if (userTags.size()==0){
            throw new WeiboException("查询用户-标签ID失败：数据库中不存在该条目");
        }else if (userTags.size()>1){
            throw new WeiboException("查询用户-标签ID失败：条目重复");
        }else {
            return userTags.get(0).getUserTagId();
        }
    }

    @Transactional
    @Override
    public void addUserTag(Integer userId, Integer tagId) {
        UserTag userTag=new UserTag();
        userTag.setUserId(userId);
        userTag.setTagId(tagId);
        if(userTagDao.insertSelective(userTag)<=0){
            throw new WeiboException("添加用户-标签关系失败！");
        }
    }

    @Transactional
    @Override
    public Integer addUserTag(Integer userId, String tagName) {
        int tagId=tagService.getTagIdByTagName(tagName);
        if (tagId==0){
            //标签不存在，增加标签
            Tag tag=new Tag();
            tag.setTagName(tagName);
            tag.setUseAmount(0);
            tag.setIsDel(false);
            tagService.addTag(tag);
            tagId=tag.getTagId();
        }
       addUserTag(userId,tagId);
        increaseUseAmount(tagId);
        return tagId;
    }

    @Transactional
    @Override
    public void addUserTagList(Integer userId,List<String> tagsName) {
        for (String tagName:tagsName){
            int tagId=addUserTag(userId,tagName);
            if (tagId<=0){
                throw new WeiboException("添加用户失败：添加标签表失败！");
            }
        }
    }

    @Transactional
    @Override
    public void deleteUserTag(Integer userId, Integer tagId) {
        UserTagExample userTagExample=new UserTagExample();
        userTagExample.createCriteria().andIsDelEqualTo(false).andUserIdEqualTo(userId).andTagIdEqualTo(tagId);
        UserTag userTag=new UserTag();
        userTag.setIsDel(true);
        if(userTagDao.updateByExampleSelective(userTag,userTagExample)<=0){
            throw new WeiboException("删除用户-标签关系失败！");
        }
        decreaseUseAmount(tagId);
    }

    @Transactional
    @Override
    public void deleteUserTag(Integer userId, String tagName) {
        int tagId=tagService.getTagIdByTagName(tagName);
        if (tagId==0){
            throw new WeiboException("删除的标签不存在");
        }
        deleteUserTag(userId,tagId);
    }

    @Transactional
    @Override
    public void deleteUserAllTags(Integer userId) {
        List<String> tagNameList=getTagNameListByUserId(userId);
        for (String tagName:tagNameList){
            deleteUserTag(userId,tagName);
        }
    }

    @Transactional
    @Override
    public void updateUserTag(Integer userId, String org_tagName, String new_tagName) {
        int org_tagId=tagService.getTagIdByTagName(org_tagName);
        int new_tagId=tagService.getTagIdByTagName(new_tagName);
        if (org_tagId==0){
            throw new WeiboException("原标签不存在");
        }else {
            if (new_tagId==0){
                //修改后的标签不存在，加入到表中
                new_tagId=addUserTag(userId,new_tagName);
            }
            UserTagExample userTagExample=new UserTagExample();
            userTagExample.createCriteria().andUserIdEqualTo(userId).andTagIdEqualTo(org_tagId).andIsDelEqualTo(false);
            UserTag userTag=new UserTag();
            userTag.setUserId(userId);
            userTag.setTagId(new_tagId);
            if (userTagDao.updateByExampleSelective(userTag,userTagExample)<0){
                throw new WeiboException("更新标签失败！");
            }
        }
    }

    @Transactional
    @Override
    public List<String> getTagNameListByUserId(Integer userId) {
        return userTagDao.getTagNameListByUserId(userId);
    }

    @Transactional
    @Override
    public void updateUserTag(Integer userId, List<String> org_tagsName, List<String> new_tagsName) {
        int org_tagSize=org_tagsName.size();
        int new_tagsSize=new_tagsName.size();
        if (org_tagSize==0&&new_tagsSize==0){
            //标签无更新
            //什么都不需要做
        }
        if (org_tagSize!=0&&new_tagsSize==0){
            //删除掉用户所有的标签
            userTagService.deleteUserAllTags(userId);
        }
        if (org_tagSize==0&&new_tagsSize!=0){
            //增加用户标签
            userTagService.addUserTagList(userId,new_tagsName);
        }
        if (org_tagSize!=0&&new_tagsSize!=0){
            //原标签不为空，现标签也不为空
            int min_size=org_tagSize<=new_tagsSize?org_tagSize:new_tagsSize;
            for (int i = 0; i < min_size; i++) {
                String org_tagName=org_tagsName.get(i);
                String new_tagName=new_tagsName.get(i);
                if (org_tagName!=new_tagName) {
                    updateUserTag(userId,org_tagName,new_tagName);
                }
            }
            if (org_tagSize<=new_tagsSize){
                //有新增标签
                for (int i=min_size;i<new_tagsSize;i++){
                    String tagName=new_tagsName.get(i);
                    int tagId=userTagService.addUserTag(userId,tagName);
                }
            }else {
                //标签有删减
                for (int i=min_size;i<org_tagSize;i++){
                    String tagName=org_tagsName.get(i);
                    userTagService.deleteUserTag(userId,tagName);
                }
            }
        }
    }

    @Transactional
    @Override
    public void increaseUseAmount(Integer tagId) {
        Tag tag=tagDao.selectByPrimaryKey(tagId);
        int useAmount=tag.getUseAmount()+1;
        tag.setUseAmount(useAmount);
        if (tagDao.updateByPrimaryKeySelective(tag)<0){
            throw new WeiboException("增加标签使用量失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public void decreaseUseAmount(Integer tagId) {
        Tag tag=tagDao.selectByPrimaryKey(tagId);
        int useAmount=tag.getUseAmount()-1;
        tag.setUseAmount(useAmount);
        if (tagDao.updateByPrimaryKeySelective(tag)<0){
            throw new WeiboException("减少标签使用量失败：数据库未知错误！");
        }
    }

    @Transactional
    @Override
    public void insertTagsTest() {
        List<Userlabels> userlabelsList=tagDao.selectLabels();
        for (int i=0;i<userlabelsList.size();i++){
            Userlabels userlabels=userlabelsList.get(i);
            int userId=userlabels.getUserId();
            String labels=userlabels.getLabels();
            String[] labelList=labels.split(",");
            for (int j=0;j<labelList.length;j++){
//                System.out.println(labelList[j]);
                addUserTag(userId,labelList[j]);
            }
        }
        System.out.println(userlabelsList.size());
    }
}
