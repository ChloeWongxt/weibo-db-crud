package com.chloe.weibo.core.dao;

import com.chloe.weibo.core.entity.File;
import com.chloe.weibo.core.entity.entityExample.FileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileDao {

    int countByExample(FileExample example);

    int deleteByExample(FileExample example);

    int deleteByPrimaryKey(Integer fileId);

    int insert(File record);

    int insertSelective(File record);

    List<File> selectByExample(FileExample example);

    List<String> selectFileAddressListByWeiboId(Integer weiboId);

    File selectByPrimaryKey(Integer fileId);

    int updateByExampleSelective(@Param("record") File record, @Param("example") FileExample example);

    int updateByExample(@Param("record") File record, @Param("example") FileExample example);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);

}