package com.elearning.common.config.datasource.aspectj;

import com.elearning.common.annotation.DataSource;
import com.elearning.common.config.datasource.dynamic.DynamicDataSourceContextHolder;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 动态数据源切换
 * 注：必须定义为组件{@link Component}，否则该切面不生效
 * @author wudi
 */
@Aspect
@Order(-1)
@Component
public class DataSourceAspect {

//    private final static Logger log = LoggerFactory.getLogger(DataSourceAspectj.class);

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.elearning.common.annotation.DataSource)")
    public void dsPointCut(){
    }

    /**
     * 环绕通知
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        DataSource dataSource = getAnnotation(point);
        if(ObjectUtils.allNotNull(dataSource)){
            DynamicDataSourceContextHolder.setDataSource(dataSource.type());
        }
        try{
            return point.proceed();
        }finally {
            //必须重置数据库，否则下次请求依然会使用该库
            DynamicDataSourceContextHolder.clear();
        }
    }

    /**
     * 获取DataSource注解
     * @param point
     * @return
     */
    private DataSource getAnnotation(ProceedingJoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(DataSource.class);
    }
}
