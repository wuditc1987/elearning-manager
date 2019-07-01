package com.elearning.common.base.result;

/**
 * @author wudi
 * @version 1.0
 * @Description 数据返回定义接口
 * @date 2019/6/19 11:24 AM
 */
public interface IResult<T> {

    boolean isSuccess();

    T getData();

    String getCode();

}
