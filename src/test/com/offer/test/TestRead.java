package com.offer.test;

import com.offer.bean.ConfigInfo;
import com.offer.bean.EmailInfo;
import com.offer.service.MailInfoService;
import com.offer.util.HttpClientService;
import com.offer.util.HttpResult;
import com.offer.util.JacksonUtil;
import com.offer.util.Result;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2017/12/19 17:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/*.xml"})
public class TestRead {
    @Resource
    private ConfigInfo configInfo;

    @Resource
    private MailInfoService mailInfoService;

    @Autowired
    private HttpClientService httpRequest;
    @Test
    public void test() throws MessagingException, UnsupportedEncodingException {
        String code = URLEncoder.encode("name=刘亚丽&year=2017&month=1&day=8&Time=14&Minute=50&go=深圳北&to=长沙南&seat=二等座", "utf-8");
        String url = "https://voice.yunpian.com/v2/voice/tpl_notify.json";
        Map<String,String> params = new HashMap<String, String>();
        params.put("apikey","a14e165db1a089a45b525bc6f4b4c449");
        params.put("mobile","15916009252");
        params.put("tpl_id","2139138");
        params.put("tpl_value",code);
        try {
            HttpResult httpResult = httpRequest.doPost(url,params);
            System.out.println(httpResult.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
