package com.offer.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2018/1/8 11:25
 */
@Component
public class VoiceMsgInfo implements Serializable{
    @Value("#{configProperties['voice.tpl_id']}")
    private String tpl_id;

    @Value("#{configProperties['voice.apikey']}")
    private String apikey;

    @Value("#{configProperties['voice.mobile']}")
    private String mobile;

    public String getTpl_id() {
        return tpl_id;
    }

    public void setTpl_id(String tpl_id) {
        this.tpl_id = tpl_id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
