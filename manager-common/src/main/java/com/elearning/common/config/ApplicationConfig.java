package com.elearning.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 应用配置类
 * 如果不需要该配置，需要在SpringBootApplication注解中的excludes中进行排除
 * @author wudi
 * @date
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan(basePackages = "com.elearning.*.mapper")
public class ApplicationConfig {


}
