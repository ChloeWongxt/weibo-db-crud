package com.chloe.weibo.core.service.interfaces;

import com.chloe.weibo.core.entity.Tag;
/**
 * @author ChloeWong
 * @date 2019/4/11
 */
public interface TagService {
    int getTagIdByTagName(String tagName);
    String getTagNameByTagId(Integer tagId);
    void addTag(Tag tag);
    void deleteTagByTagId(Integer tagId);
    void increaseTagUseAmountByTagId(Integer tagId);
    void decreaseTagUseAmountByTagId(Integer tagId);
}
