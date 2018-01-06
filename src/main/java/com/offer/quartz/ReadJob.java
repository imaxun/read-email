package com.offer.quartz;

import com.offer.service.MailInfoService;
import com.offer.util.JacksonUtil;
import com.offer.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2017/12/21 10:52
 * 定时读取邮件
 */
public class ReadJob {
    @Autowired
    private MailInfoService mailInfoService;
        public void taskCycle(){
            Result result = mailInfoService.readMail();
            String str = JacksonUtil.bean2Json(result.getModel());
            System.out.println(str);
            //后续接入智能语音通知
        }
}
