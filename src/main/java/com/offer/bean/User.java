package com.offer.bean;

import java.io.Serializable;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2017/12/19 15:33
 */
public class User implements Serializable{
    private Integer userId;
    private String userName;
    private String phone; // 手机号
    private Integer status; //状态 1：通知成功，0通知失败
    private String protocol; // 邮件的协议
    private String host;//服务地址
    private String euser;//邮箱账户
    private String epassword;//邮箱密码
    private String port;//端口
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getEuser() {
        return euser;
    }

    public void setEuser(String euser) {
        this.euser = euser;
    }

    public String getEpassword() {
        return epassword;
    }

    public void setEpassword(String epassword) {
        this.epassword = epassword;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
