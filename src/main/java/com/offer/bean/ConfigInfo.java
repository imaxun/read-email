package com.offer.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2017/12/19 15:33
 */
@Component
public class ConfigInfo implements Serializable{
    @Value("#{configProperties['user.name']}")
    private String userName;
    @Value("#{configProperties['email.phone']}")
    private String phone; // 手机号
    @Value("#{configProperties['email.protocol']}")
    private String protocol; // 邮件的协议
    @Value("#{configProperties['email.host']}")
    private String host;//服务地址
    @Value("#{configProperties['email.username']}")
    private String euser;//邮箱账户
    @Value("#{configProperties['email.password']}")
    private String epassword;//邮箱密码
    @Value("#{configProperties['email.port']}")
    private String port;//端口


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
