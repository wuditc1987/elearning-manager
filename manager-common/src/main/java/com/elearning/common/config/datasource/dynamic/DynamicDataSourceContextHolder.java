package com.elearning.common.config.datasource.dynamic;

import com.elearning.common.config.datasource.constants.DataSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态数据源切换
 * @author wudi
 */
public class DynamicDataSourceContextHolder {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    public DynamicDataSourceContextHolder() {
        throw new IllegalStateException("Utility class");
    }

    private static final ThreadLocal<DataSourceType> HOLDER = new ThreadLocal<>();

    public static void setDataSource(DataSourceType dsType){
        log.info("切换到{}数据源",dsType.name());
        HOLDER.set(dsType);
    }

    static DataSourceType getDataSource(){
        return HOLDER.get();
    }

    public static void clear(){
        HOLDER.remove();
    }
}
