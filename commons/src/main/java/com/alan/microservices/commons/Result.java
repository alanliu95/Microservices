package com.alan.microservices.commons;

public class Result<T> {
    private int code;
    private String msg;
    T data;

    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 操作成功时调用
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
    }

    private Result(CodeMsg codeMsg) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    private Result(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
