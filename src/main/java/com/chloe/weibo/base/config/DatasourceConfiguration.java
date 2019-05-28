package com.chloe.weibo.base.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.chloe.weibo.core.dao")
@Configuration
public class DatasourceConfiguration {
}
