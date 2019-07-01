package com.elearning.common.base.result;

/**
 * @author wudi
 * @version 1.0
 * @Description 返回类
 * @date 2019/6/19 11:12 AM
 */
public class Result<T> implements IResult<T>{

    /**
     * 命名规则：
     */
    private String code;

    private String message;

    private boolean isSuccess;

    /**
     * 数据项
     */
    private T data;

    public Result(String code,String message){
        this.code = code;
        this.message = message;
    }

    public Result(String message){
        this.message = message;
    }

    public static Result success(){
        return new Result("","操作成功!");
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public String getCode() {
        return code;
    }


}
