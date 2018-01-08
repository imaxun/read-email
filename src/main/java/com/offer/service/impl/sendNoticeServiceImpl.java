package com.offer.service.impl;

import com.offer.bean.MessageBean;
import com.offer.bean.SendMsgInfo;
import com.offer.bean.VoiceMsgBean;
import com.offer.service.sendNoticeService;
import com.offer.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: lizhen
 * @Description:
 * @Date: Created in 20:46 2018/1/8
 */
@Service
public class sendNoticeServiceImpl implements sendNoticeService {
    @Autowired
    private HttpClientService httpRequest;

    @Autowired
    private SendMsgInfo sendMsgInfo;

    public HttpResult senMessage(String json) {
        try {
            //转换成一大写开头的map
            sendMsgInfo.setTemplateParam(json);
            Map<String,String> params = JacksonUtil.bean2Map1(sendMsgInfo);
            // 3. 去除签名关键字Key
            if (params.containsKey("Signature")){
                params.remove("Signature");
            }
            // 4. 参数KEY排序
            TreeMap<String,String> sortParas = new TreeMap<String, String>();
            sortParas.putAll(params);
            Iterator<String> it = sortParas.keySet().iterator();
            StringBuilder sortQueryStringTmp = new StringBuilder();
            while (it.hasNext()){
                String key = it.next();
                sortQueryStringTmp.append("&").append(MessageUtil.specialUrlEncode(key)).
                        append("=").append(MessageUtil.specialUrlEncode(params.get(key)));
            }
            String sortedQueryString = sortQueryStringTmp.substring(1);// 去除第一个多余的&符号
            StringBuilder stringToSign = new StringBuilder();
            stringToSign.append("POST").append("&");
            stringToSign.append(MessageUtil.specialUrlEncode("/")).append("&");
            stringToSign.append(MessageUtil.specialUrlEncode(sortedQueryString));
            String sign = MessageUtil.sign(sendMsgInfo.getAccessSecret() + "&", stringToSign.toString());
            // 6. 签名最后也要做特殊URL编码
            String signature = MessageUtil.specialUrlEncode(sign);
            String url = "http://dysmsapi.aliyuncs.com/?Signature="+signature+sortQueryStringTmp;
            return httpRequest.doPost(url,params);
        }catch (Exception e){
                e.printStackTrace();
        }
        return null;
    }

    public HttpResult senVoice(VoiceMsgBean bean) {
        return null;
    }
}
