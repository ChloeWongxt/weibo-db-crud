package com.chloe.weibo.core.dao;

import com.chloe.weibo.core.entity.Collection;
import com.chloe.weibo.core.entity.entityExample.CollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectionDao {

    int countByExample(CollectionExample example);

    int deleteByExample(CollectionExample example);

    int deleteByPrimaryKey(Integer collectionId);

    int insert(Collection record);

    int insertSelective(Collection record);

    List<Collection> selectByExample(CollectionExample example);

    Collection selectByPrimaryKey(Integer collectionId);

    int updateByExampleSelective(@Param("record") Collection record, @Param("example") CollectionExample example);

    int updateByExample(@Param("record") Collection record, @Param("example") CollectionExample example);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);
}