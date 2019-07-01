package com.elearning.common.config;

import com.elearning.common.utils.YamlUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wudi
 * @version 1.0
 * @Description 全局配置类-获取属性
 * @date 2019/6/21 3:59 PM
 */
public class Global {

    private static final String NAME = "application.yml";

    /**
     * 全局属性缓存
     */
    private static Map<String, String> map = new HashMap<>();

    private Global() {}

    /**
     * 获取value
     * @param key
     * @return
     */
    public static String getValue(String key){
        String value = map.get(key);
        if(StringUtils.isBlank(value)){
            Map<String,Object> yamlMap = YamlUtils.load(NAME);
            if(null != yamlMap){
                value = String.valueOf(yamlMap.get(key));
                map.put(key,value);
            }
        }
        return value;
    }
}
