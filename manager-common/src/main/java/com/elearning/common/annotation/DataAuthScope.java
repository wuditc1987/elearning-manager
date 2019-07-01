package com.elearning.common.annotation;

import com.elearning.common.config.datasource.constants.DataAuthScopeType;

/**
 * @author wudi
 * @version 1.0
 * @Description 数据权限范围注解
 * @date 2019/6/18 8:18 PM
 */
public @interface DataAuthScope {

    DataAuthScopeType type() default DataAuthScopeType.USER;
}
