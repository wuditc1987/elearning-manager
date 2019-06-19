package com.elearning.common.annotation;

import com.elearning.common.config.datasource.constants.DataSourceType;

import java.lang.annotation.*;

/**
 * 动态数据源切换注解
 * @author wudi
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    /**
     * 默认为master数据库
     * @return
     */
    DataSourceType type() default DataSourceType.MASTER;
}
