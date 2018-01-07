package com.offer.test;

import com.offer.bean.ConfigInfo;
import com.offer.service.MailInfoService;
import com.offer.util.HttpClientService;
import com.offer.util.HttpResult;
import com.offer.util.JacksonUtil;
import com.offer.util.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
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
    public void test() throws MessagingException {
        String url = "https://voice.yunpian.com/v2/voice/tpl_notify.json";
        Map<String,String> params = new HashMap<String, String>();
        params.put("apikey","a14e165db1a089a45b525bc6f4b4c449");
        params.put("mobile","17722626870");
        params.put("tpl_id","2139138");
        params.put("tpl_value","adas");
        params.put("callback_url","17722626870");

        try {
            HttpResult httpResult = httpRequest.doPost(url,params);
            System.out.println(httpResult.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
