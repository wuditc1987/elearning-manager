package com.elearning.common.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * @author wudi
 * @version 1.0
 * @Description yaml工具类
 * @date 2019/6/21 4:03 PM
 */
public class YamlUtils {

    public static Map<String,Object> load(String name){
        if(StringUtils.isNotEmpty(name)){
            InputStream in = YamlUtils.class.getClassLoader().getResourceAsStream(name);
            return new Yaml().load(in);
        }
        return null;
    }

}
