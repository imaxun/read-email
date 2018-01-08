package com.offer.quartz;

import com.offer.service.MailInfoService;
import com.offer.util.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2017/12/21 10:52
 * 定时读取邮件
 */
public class ReadJob {
    @Autowired
    private MailInfoService mailInfoService;

    @Autowired
    private HttpClientService httpRequest;
    public void taskCycle(){

    }
}
