package com.elearning.common.config.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 * @author wudi
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    private Long expiry;

    private final Long TTL = 30L;



}
