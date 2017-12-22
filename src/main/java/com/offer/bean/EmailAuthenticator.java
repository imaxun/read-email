package com.offer.bean;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2017/12/20 10:56
 * 此类用户校验部分需要授权密码的邮箱
 */
public class EmailAuthenticator extends Authenticator {
    String userName = null;
    String password = null;
    public EmailAuthenticator() {
    }

    public EmailAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
