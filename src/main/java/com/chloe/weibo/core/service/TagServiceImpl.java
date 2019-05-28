package com.chloe.weibo.core.service;

import com.chloe.weibo.core.dao.TagDao;
import com.chloe.weibo.core.entity.Tag;
import com.chloe.weibo.core.entity.entityExample.TagExample;
import com.chloe.weibo.common.exception.WeiboException;
import com.chloe.weibo.core.service.interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ChloeWong
 * @date 2019/4/11
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao tagDao;

    @Transactional
    @Override
    public int getTagIdByTagName(String tagName) {
        TagExample tagExample=new TagExample();
        tagExample.createCriteria().andTagNameEqualTo(tagName).andIsDelEqualTo(false);
        List<Tag> tagList=tagDao.selectByExample(tagExample);
        if (tagList.size()<=0){
            return 0;
        }
        else if (tagList.size()>1){
            return 0;
        }else {
            return tagList.get(0).getTagId();
        }
    }

    @Transactional
    @Override
    public String getTagNameByTagId(Integer tagId) {
        String tagName=tagDao.selectByPrimaryKey(tagId).getTagName();
        return tagName;
    }
    @Transactional
    @Override
    public void addTag(Tag tag) {
        tag.setUseAmount(0);
        tag.setIsDel(false);
        if (tagDao.insertSelective(tag)<=0){
            throw new WeiboException("添加标签:失败！");
        }
    }

    @Transactional
    @Override
    public void deleteTagByTagId(Integer tagId) {
        Tag tag =new Tag();
        tag.setTagId(tagId);
        tag.setIsDel(true);
        if (tagDao.updateByPrimaryKeySelective(tag)<=0){
            throw new WeiboException("删除标签:失败！");
        }
    }

    @Transactional
    @Override
    public void increaseTagUseAmountByTagId(Integer tagId) {
        Tag tag=tagDao.selectByPrimaryKey(tagId);
        int tagAmount= tag.getUseAmount();
        tag.setUseAmount(tagAmount+1);
        if (tagDao.updateByPrimaryKeySelective(tag)<=0){
            throw new WeiboException("增加标签使用量:失败！");
        }
    }

    @Transactional
    @Override
    public void decreaseTagUseAmountByTagId(Integer tagId) {
        Tag tag=tagDao.selectByPrimaryKey(tagId);
        int tagAmount= tag.getUseAmount();
        tag.setUseAmount(tagAmount-1);
        if (tagDao.updateByPrimaryKeySelective(tag)<=0){
            throw new WeiboException("减少标签使用量:失败！");
        }
    }
}
