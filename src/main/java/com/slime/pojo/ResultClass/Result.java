package com.slime.pojo.ResultClass;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 433365382815180652L;
    //状态码
    private int code;
    //提示信息
    private String message;
    //返回的数据
    private T data;

    public Result() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(RetCode code) {
        this.code = code.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
