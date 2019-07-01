package com.elearning.common.config.redis.serializer;

import com.elearning.common.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @author wudi
 * @version 1.0
 * @Description key正反序列化
 * @date 2019/6/21 4:20 PM
 */
public class StringRedisSerializer<T extends Serializable> implements RedisSerializer {

    private static final Logger log = LoggerFactory.getLogger(StringRedisSerializer.class);

    private String charset = Constants.CHAR_UTF8;

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        try {
            return o.toString().getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        try {
            return new String(bytes,charset);
        }catch (UnsupportedEncodingException e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public void setCharset(String charset){
        this.charset = charset;
    }
}
