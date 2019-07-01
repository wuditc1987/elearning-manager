package com.elearning.common.utils;

import cn.hutool.core.collection.ConcurrentHashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wudi
 * @version 1.0
 * @Description 数学通用方法
 * @date 2019/6/27 7:44 PM
 */
public class NumericUtils {

    public static final Long START_NUMBER = 0L;

    public static final Integer START_NUM = 2;

    /**
     * 开辟 1<<7 个空间，提高性能
     */
    public static Set<Integer> primeCache = new ConcurrentHashSet<>(1<<7);

    /**
     * 不允许new 该类
     */
    private NumericUtils(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * 判断一个数是否是素数
     * @param num 从start位置开始
     * @return
     */
    public static boolean isPrimeNumber(Integer num){
        if(num < START_NUM) {
            return false;
        }
        if(START_NUM.equals(num)) {
            return true;
        }
        if(num % START_NUM == 0) {
            return false;
        }
        if(primeCache.contains(num)) {
            return true;
        }

        for(int i = 3; i * i <= num; i += 2) {
            if(num % i == 0) {
                return false;
            }
        }
        primeCache.add(num);
        return true;
    }

}
