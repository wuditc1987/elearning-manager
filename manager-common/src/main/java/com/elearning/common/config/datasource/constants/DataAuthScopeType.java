package com.elearning.common.config.datasource.constants;

/**
 * @author wudi
 * @version 1.0
 * @Description 数据权限范围枚举类
 * @date 2019/6/18 8:41 PM
 */
public enum DataAuthScopeType {

    /**
     * 所有数据权限，一般只有admin才会有
     */
    ALL,

    /**
     * 部门权限，只有相应部门的人才能看到
     */
    DEPARTMENT,

    /**
     * 角色权限，所属角色可以看到
     */
    ROLE,

    /**
     * 用户权限，用户只能看到自己的数据
     */
    USER;

}
