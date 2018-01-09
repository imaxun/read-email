package com.offer.util;

import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;

/**
 * @Author: lizhen
 * @Description:
 * @Date: Created in 20:14 2018/1/8
 */
public class MessageUtil {
    public static String specialUrlEncode(String value) throws Exception {
        return URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }
    public static String sign(String accessSecret, String stringToSign) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(accessSecret.getBytes("UTF-8"), "HmacSHA1"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        return new BASE64Encoder().encode(signData);
    }
}
