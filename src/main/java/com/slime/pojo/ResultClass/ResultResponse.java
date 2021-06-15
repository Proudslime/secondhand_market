package com.slime.pojo.ResultClass;

public class ResultResponse {
    private final static String SUCCESS = "success";

    public ResultResponse() {}

    public static <T> Result<T> makeOKRsp() {
        Result<T> result = new Result<T>();
        result.setCode(RetCode.SUCCESS);
        result.setMessage(SUCCESS);
        return result;
    }

    public static <T> Result<T> makeOKRsp(T data) {
        Result<T> result = new Result<T>();
        result.setCode(RetCode.SUCCESS);
        result.setMessage(SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> makeErrRsp(String msg) {
        Result<T> result = new Result<T>();
        result.setCode(RetCode.FAIL);
        result.setMessage(msg);
        return result;
    }

    public static <T> Result<T> makeRsp(int code, String msg) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static <T> Result<T> makeRsp(int code, String msg, T data) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }
}
