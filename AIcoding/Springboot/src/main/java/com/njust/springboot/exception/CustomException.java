package com.njust.springboot.exception;


public class CustomException extends RuntimeException {//禁止访问，手动设置异常
    private String msg;
    private String code;

    public CustomException(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
