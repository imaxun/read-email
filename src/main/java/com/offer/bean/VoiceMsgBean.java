package com.offer.bean;

import java.io.Serializable;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2018/1/8 11:25
 */
public class VoiceMsgBean implements Serializable {
    private String name;

    private String ip;//服务器ip

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public VoiceMsgBean(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    public VoiceMsgBean() {
    }
}
