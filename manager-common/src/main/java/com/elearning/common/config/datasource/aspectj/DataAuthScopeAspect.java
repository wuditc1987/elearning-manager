package com.elearning.common.config.datasource.aspectj;

import com.elearning.common.annotation.DataAuthScope;
import com.elearning.common.config.datasource.constants.DataAuthScopeType;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author wudi
 * @version 1.0
 * @Description 数据权限范围切面
 * @date 2019/6/19 2:33 PM
 */
@Aspect
public class DataAuthScopeAspect {

    @Pointcut("@annotation(com.elearning.common.annotation.DataAuthScope)")
    public void pointCut(){

    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint){
        DataAuthScope scope = getAnnotation(proceedingJoinPoint);
        if(ObjectUtils.allNotNull(scope)){

        }
    }

    private DataAuthScope getAnnotation(ProceedingJoinPoint proceedingJoinPoint){
        MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(DataAuthScope.class);
    }

    /**
     * 重写sql
     * 添加相应数据范围条件
     * @param sql
     * @return
     */
    private String rewriteSQL(String sql, DataAuthScopeType type){
        StringBuffer buffer = new StringBuffer(sql);
//        if(sql.contains()){
//
//        }
        switch (type){

            case USER:
                buffer.append(" ");
                break;
            case DEPARTMENT:
                break;
            case ROLE:
                break;
            case ALL:
            default:
                break;
        }
        return buffer.toString();
    }

}
