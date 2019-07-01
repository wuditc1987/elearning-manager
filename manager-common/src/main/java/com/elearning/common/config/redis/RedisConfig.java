package com.elearning.common.config.redis;

import com.elearning.common.config.redis.handle.RedisCacheErrorHandle;
import com.elearning.common.config.redis.serializer.JsonRedisSerializer;
import com.elearning.common.config.redis.serializer.StringRedisSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * Redis配置类
 * @author wudi
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.expiry}")
    private Long expiry;

    /**
     * time to live
     */
    @Value("${spring.redis.ttl}")
    private Duration TTL;

    @Value("${spring.redis.keyPrefix}")
    private String keyPrefix;

    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer(){
        return new JsonRedisSerializer<Object>(Object.class);
    }

    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(fastJson2JsonRedisSerializer());

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(fastJson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * boot 启动时，会自动加载RedisConnectionFactory 到application上下文中，因此此处可以直接使用，其他同理
     * @param factory
     * @return
     */
    @Bean("redisCacheManager")
    public CacheManager redisCacheManager(RedisConnectionFactory factory){
        //写数据时不加锁以提高性能
        RedisCacheWriter writer = RedisCacheWriter.nonLockingRedisCacheWriter(factory);

        ClassLoader loader = this.getClass().getClassLoader();
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig(loader);

        JsonRedisSerializer valueSerializer = new JsonRedisSerializer(loader.getClass());

        StringRedisSerializer keySerializer = new StringRedisSerializer();

        //明显这不是一个明智的配置方式，每次修改配置时，需要new 很多configuration对象，会占用很多堆内存，导致性能下降
        //如果可以明确配置，最好使用RedisCacheConfiguration 中的构造参数，只new出来一个对象即可。很可惜，构造方法并没有开放出来，是private的
        /**
         * DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
         * RedisCacheConfiguration.registerDefaultConverters(conversionService);
         *
         * RedisCacheConfiguration configuration = new RedisCacheConfiguration(TTL,Boolean.FALSE,Boolean.TRUE,new CacheKeyPrefix() {
         *         @Override
         *         public String compute(String cacheName) {
         *             return cacheName + keyPrefix;
         *         }
         *     },keyPair,valuePair,conversionService);
         */

        // Key Serializer
        RedisSerializationContext.SerializationPair<String> keyPair = RedisSerializationContext.SerializationPair.fromSerializer(keySerializer);

        configuration = configuration.serializeKeysWith(keyPair);

        //Value Serializer
        RedisSerializationContext.SerializationPair<Object> valuePair = RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer);
        configuration = configuration.serializeValuesWith(valuePair);

        //设置TTL
        configuration = configuration.entryTtl(TTL);
        //设置prefix 分隔符
        configuration = configuration.computePrefixWith(new CacheKeyPrefix() {
            @Override
            public String compute(String cacheName) {
                return cacheName + keyPrefix;
            }
        });

        RedisCacheManager manager = new RedisCacheManager(writer,configuration);

        return manager;
    }

    /*
    此方法在spring-boot 2.0 以下版本使用
    @Bean
    CacheManager cacheManager(RedisTemplate<String,String> redisTemplate){
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();

        RedisCacheManager manager = new RedisCacheManager(redisTemplate);
        //使用前缀
//        manager.setUsePrefix(true);
        //缓存分割符 默认为 ":"
//        manager.setCachePrefix(new DefaultRedisCachePrefix("_"));
//        manager.setLoadRemoteCachesOnStartup(true);
        //设置缓存过期时间(秒)
//        manager.setDefaultExpiration(expiry);

//        manager.set
        return manager;
    }*/

    @Bean("errorHandler")
    @Override
    public CacheErrorHandler errorHandler(){
        return new RedisCacheErrorHandle();
    }


    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target,method,objects)->{
            StringBuilder sb = new StringBuilder();
            String[] vals = new String[1];
            Cacheable cacheable = method.getAnnotation(Cacheable.class);
            if(cacheable != null){
                vals = cacheable.value();
            }
            CachePut cachePut = method.getAnnotation(CachePut.class);
            if(null != cachePut){
                vals = cachePut.value();
            }
            CacheEvict evict = method.getAnnotation(CacheEvict.class);
            if(null != evict){
                vals = evict.value();
            }
            sb.append(vals[0]);
            for(Object obj : objects){
                sb.append(keyPrefix).append(obj.toString());
            }
            return sb.toString();
        };
    }
}
