package com.offer.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2017/12/19 16:23
 */
public class EmailInfo implements Serializable{
    private String code;//ID
    private String sender;//内容
    private String title;//标题
    private String receiver;//收件人
    private Date accepttime;//收件日期
    private Integer checkstatus;//状态

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getAccepttime() {
        return accepttime;
    }

    public void setAccepttime(Date accepttime) {
        this.accepttime = accepttime;
    }

    public Integer getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }
}
