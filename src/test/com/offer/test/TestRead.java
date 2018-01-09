package com.offer.test;

import com.offer.bean.*;
import com.offer.service.MailInfoService;
import com.offer.service.sendNoticeService;
import com.offer.util.HttpClientService;
import com.offer.util.HttpResult;
import com.offer.util.JacksonUtil;
import com.offer.util.MessageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private SendMsgInfo sendMsgInfo;

    @Resource
    private VoiceMsgInfo voiceMsgInfo;

    @Resource
    private MailInfoService mailInfoService;

    @Autowired
    private sendNoticeService sendNoticeService;

    @Autowired
    private HttpClientService httpRequest;



    @Test
    public void test() {
        try {
            // 1. 系统参数
            String accessKeyId = "LTAIGxV4qzCQav47";
            String accessSecret = "f19myZo8QkbrIWKG15gNEXIDW3wMKK";
            // 5. 构造待签名的字符串
            SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));// 这里一定要设置GMT时区
            Map<String, String> params = new HashMap<String, String>();
            params.put("SignatureMethod", "HMAC-SHA1");
            params.put("SignatureNonce", java.util.UUID.randomUUID().toString());
            params.put("AccessKeyId", accessKeyId);
            params.put("SignatureVersion", "1.0");
            params.put("Timestamp", df.format(new Date()));
            params.put("Format", "JSON");

            // 2. 业务API参数
            params.put("Action", "SendSms");
            params.put("Version", "2017-05-25");
            params.put("RegionId", "cn-hangzhou");
            params.put("PhoneNumbers", "17722626870");
            params.put("SignName", "云购");
            params.put("TemplateCode", "SMS_120376837");
            MessageBean msgbean = new MessageBean();
            msgbean.setUsername("李桢");
            msgbean.setStarttime(new Date());
            msgbean.setTrain("G252二等座");
            msgbean.setStation("深圳北");
            msgbean.setTerminus("长沙南");
            String json = JacksonUtil.bean2Json(msgbean);
            params.put("TemplateParam", json);
            params.put("OutId", "123");

            // 3. 去除签名关键字Key
            if (params.containsKey("Signature")) {
                params.remove("Signature");
            }

            // 4. 参数KEY排序
            TreeMap<String, String> sortParas = new TreeMap<String, String>();
            sortParas.putAll(params);
            Iterator<String> it = sortParas.keySet().iterator();
            StringBuilder sortQueryStringTmp = new StringBuilder();
            while (it.hasNext()) {
                String key = it.next();
                sortQueryStringTmp.append("&").append(MessageUtil.specialUrlEncode(key)).
                        append("=").append(MessageUtil.specialUrlEncode(params.get(key)));
            }
            String sortedQueryString = sortQueryStringTmp.substring(1);// 去除第一个多余的&符号
            StringBuilder stringToSign = new StringBuilder();
            stringToSign.append("POST").append("&");
            stringToSign.append(MessageUtil.specialUrlEncode("/")).append("&");
            stringToSign.append(MessageUtil.specialUrlEncode(sortedQueryString));
            String sign = MessageUtil.sign(accessSecret + "&", stringToSign.toString());
            // 6. 签名最后也要做特殊URL编码
            String signature = MessageUtil.specialUrlEncode(sign);
            String url = "http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp;
            System.out.println("请求url--------" + url);
            HttpResult httpResult = httpRequest.doPost(url, params);
            System.out.println(httpResult.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            /*String code = URLEncoder.encode("name=谢昌&year=2017&month=1&day=8&Time=14&Minute=50&go=深圳北&to=长沙南&seat=二等座", "utf-8");
            String url = "https://voice.yunpian.com/v2/voice/tpl_notify.json";
            Map<String, String> params = new HashMap<String, String>();
            params.put("apikey", "a14e165db1a089a45b525bc6f4b4c449");
            params.put("mobile", "18917825365");
            params.put("tpl_id", "2139138");
            params.put("tpl_value", code);
            voiceMsgBean.setName("李桢");*/
            HttpResult httpResult =  sendNoticeService.senVoice();
            System.out.println("-------------"+httpResult.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test3() {
        try {
            MessageBean msgbean = new MessageBean();
            msgbean.setUsername("李桢");
            msgbean.setStarttime(new Date());
            msgbean.setTrain("G252二等座");
            msgbean.setStation("深圳北");
            msgbean.setTerminus("长沙南");
            String json = JacksonUtil.bean2Json(msgbean);
            HttpResult result = sendNoticeService.senMessage(json);
            System.out.println(result.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
