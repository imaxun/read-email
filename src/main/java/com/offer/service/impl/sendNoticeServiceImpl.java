package com.offer.service.impl;

import com.offer.bean.SendMsgInfo;
import com.offer.bean.VoiceMsgBean;
import com.offer.bean.VoiceMsgInfo;
import com.offer.service.sendNoticeService;
import com.offer.util.HttpClientService;
import com.offer.util.HttpResult;
import com.offer.util.JacksonUtil;
import com.offer.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private VoiceMsgInfo voiceMsgInfo;

    private static  String sendMesgUrl = "http://dysmsapi.aliyuncs.com/?Signature=";

    private static final String sendVoiceUrl = "https://voice.yunpian.com/v2/voice/tpl_notify.json";

    public HttpResult senMessage(String json) {
        try {
            //转换成一大写开头的map
            sendMsgInfo.setTemplateParam(json);
            Map<String,String> params = JacksonUtil.bean2Map2(sendMsgInfo);
            params.put("Timestamp",sendMsgInfo.getTimestamp());
            params.put("SignatureNonce",sendMsgInfo.getSignatureNonce());
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
            sendMesgUrl = signature+sortQueryStringTmp;
            return httpRequest.doPost(sendMesgUrl,params);
        }catch (Exception e){
                e.printStackTrace();
        }
        return null;
    }

    public HttpResult senVoice() {
        try {
            Map<String,String>  map = JacksonUtil.bean2Map1(voiceMsgInfo);
            VoiceMsgBean voiceMsgBean = new VoiceMsgBean();
            voiceMsgBean.setName("李桢");
            voiceMsgBean.setIp("test");
            Map<String,String>  params = JacksonUtil.bean2Map1(voiceMsgBean);
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
            map.put("tpl_value",sortedQueryString);
            return  httpRequest.doPost(sendVoiceUrl,map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
