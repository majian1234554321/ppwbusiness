package com.yjhh.ppwbusiness.base;

public class BaseResponse<T> {
    public String code;
    public String message;
    public boolean success;
    public T data;

    public boolean isSuccess() {
        return "200".equals(code);
    }
}
