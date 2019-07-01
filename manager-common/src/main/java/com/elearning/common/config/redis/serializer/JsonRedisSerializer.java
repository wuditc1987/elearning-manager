package com.elearning.common.config.redis.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @author wudi
 * @version 1.0
 * @Description redis value 序列化类
 * @date 2019/6/27 7:09 PM
 */
public class JsonRedisSerializer<T> implements RedisSerializer<T> {

    static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    public JsonRedisSerializer(Class<T> clazz){
        super();
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if(null == t){
            return new byte[0];
        }
        return JSON.toJSONString(t,SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if(null == bytes || bytes.length <= 0){
            return null;
        }
        String string = new String(bytes,DEFAULT_CHARSET);
        return (T)JSON.parseObject(string,clazz);
    }
}
