package com.offer.test;

import com.offer.bean.EmailAuthenticator;
import com.offer.service.MailInfoService;
import com.offer.util.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2017/12/19 17:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/*.xml")
public class TestRead {
    @Resource
    private MailInfoService mailInfoService;
    @Test
    public void test() throws MessagingException {
//        // 准备连接服务器的会话信息
//       Result result  =  mailInfoService.readMail(new Integer(3));
//       System.out.println(result);
    }
}
