package com.alan.microservices.commons;

public class CodeMsg {
    public static final CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static final CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static final CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");


    //asset模块
    public static final CodeMsg DEV_NOT_EXIST = new CodeMsg(500310, "资源不存在");


    //登录模块
    public static final CodeMsg USER_NOT_EXIST = new CodeMsg(500210, "用户不存在");
    public static final CodeMsg PASSWORD_ERROR = new CodeMsg(500211, "密码错误");
    public static final CodeMsg SESSION_ERROR = new CodeMsg(500212, "会话不存在或者已经失效");

    private int code;
    private String msg;

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
