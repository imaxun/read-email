package com.offer.util;

/**
 * @Author: lizhen
 * @Description:
 * @Date: Created in 20:28 2018/1/7
 */
public enum  BackEnum {
    REQUESTERROR("500","提交json数据请求发生异常");

    BackEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
