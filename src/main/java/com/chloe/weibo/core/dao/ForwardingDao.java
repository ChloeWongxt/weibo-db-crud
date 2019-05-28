package com.chloe.weibo.core.dao;

import com.chloe.weibo.core.entity.Forwarding;
import com.chloe.weibo.core.entity.entityExample.ForwardingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ForwardingDao {

    int countByExample(ForwardingExample example);

    int deleteByExample(ForwardingExample example);

    int deleteByPrimaryKey(Integer forwardingId);

    int insert(Forwarding record);

    int insertSelective(Forwarding record);

    List<Forwarding> selectByExample(ForwardingExample example);

    Forwarding selectByPrimaryKey(Integer forwardingId);

    int updateByExampleSelective(@Param("record") Forwarding record, @Param("example") ForwardingExample example);

    int updateByExample(@Param("record") Forwarding record, @Param("example") ForwardingExample example);

    int updateByPrimaryKeySelective(Forwarding record);

    int updateByPrimaryKey(Forwarding record);
}