package com.elearning.common.utils;

import com.elearning.common.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * boot工具类
 * @author wudi
 */
public class BootUtils {

    private static ConfigurableApplicationContext applicationContext = null;

    private static ConfigurableBeanFactory beanFactory;

    public static ApplicationContext getApplicationContext(){
        if(applicationContext == null){
            throw new RuntimeException("applicationContext属性未注入!");
        }
        return applicationContext;
    }

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext){
        BootUtils.applicationContext = applicationContext;
        setBeanFactory(applicationContext.getBeanFactory());
    }

    public static void setBeanFactory(ConfigurableBeanFactory beanFactory){
        BootUtils.beanFactory = beanFactory;
    }

    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String bean){
        return (T)getApplicationContext().getBean(bean);
    }

    /**
     * 扫描path 下的jar包
     * @param path 路径
     * @return String
     */
    public static String scanJar(String path){
        return "";
    }

    /**
     * 获取jar名称
     * @param path 路径
     * @return String
     */
    private static String getJarName(String path){
        return "";
    }

    /**
     * 获取jar包版本
     * @return String
     */
    public String getAppVersion(){
        return "";
    }

    /**
     * 获取环境变量
     * @return String
     */
    public static String getSpringProfiles(String profile){
        if(StringUtils.isBlank(profile)){
            profile = "dev";
        }
        profile = profile.toLowerCase();
        if(profile.startsWith(Constants.ENV_DEV)){
            profile = "develop";
        }else if(profile.startsWith(Constants.ENV_TEST)){
            profile = "test";
        }else if(profile.startsWith(Constants.ENV_PRE)){
            profile = "pre";
        }else if(profile.startsWith(Constants.ENV_HOTFIX)){
            profile = Constants.ENV_HOTFIX;
        }else if(profile.startsWith(Constants.ENV_PROD)){
            profile = Constants.ENV_PROD;
        }
        return profile;
    }

    public static String getSpringProfiles(){
        String profiles = System.getProperty("spring.profiles.active");
        return getSpringProfiles(profiles);
    }

}
