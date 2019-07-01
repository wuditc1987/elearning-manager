package com.elearning.common.config.redis.handle;

import com.elearning.common.utils.NumericUtils;
import io.lettuce.core.RedisConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.data.redis.RedisConnectionFailureException;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wudi
 * @version 1.0
 * @Description 当缓存失败时，会执行相应含有Cacheable,CachePut,CacheEvict,CacheClear等注解的方法中的方法体，否则直接抛出异常。
 * 需要重写父类相应的方法，不处理则会在redis异常时抛出相应异常。
 * @date 2019/6/27 7:32 PM
 */
public class RedisCacheErrorHandle extends SimpleCacheErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(RedisCacheErrorHandle.class);

    private static final Integer MAX_COUNT = 2000;

    /**
     * 此处配置的是全局计数，不需要根据每次请求分别计数，无论哪次调用出现异常都会进行计数，因此不使用ThreadLocal进行处理。
     */
    protected static AtomicInteger COUNT = new AtomicInteger(0);

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        handleCacheError(exception,cache,key,null);
    }

    /**
     * 此方法对应Cacheable出错时的处理请求
     * @param exception
     * @param cache
     * @param key
     * @param value
     */
    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        handleCacheError(exception,cache,key,value);
    }

    private void handleCacheError(RuntimeException exception, Cache cache, Object key, Object value){
        if(exception instanceof RedisConnectionFailureException){
            int count = COUNT.incrementAndGet();
            //每素数次打印错误日志
            if(NumericUtils.isPrimeNumber(count)){
                log.error("CacheGetError cache name = [{}],cache key = [{}],value = [{}]",cache.getName(),key,value);
                //大于最大错误次数时，则清空，重新
                if(count > MAX_COUNT){
                    COUNT.set(0);
                }
            }
        }
    }
}
