package com.offer.bean;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: lizhen
 * @Description:
 * @Date: Created in 20:58 2018/1/8
 */
@Component
public class SendMsgInfo implements Serializable {
    @Value("#{configProperties['mesg.accessKeyId']}")
    private String accessKeyId;

    @Value("#{configProperties['mesg.accessSecret']}")
    private String accessSecret;

    @Value("#{configProperties['mesg.signatureMethod']}")
    private String signatureMethod;

    @Value("#{configProperties['mesg.signatureVersion']}")
    private String signatureVersion;

    @Value("#{configProperties['mesg.format']}")
    private String format;

    @Value("#{configProperties['mesg.action']}")
    private String action;

    @Value("#{configProperties['mesg.version']}")
    private String version;

    @Value("#{configProperties['mesg.regionId']}")
    private String regionId;

    @Value("#{configProperties['mesg.phoneNumbers']}")
    private String phoneNumbers;

    @Value("#{configProperties['mesg.signName']}")
    private String signName;

    @Value("#{configProperties['mesg.templateCode']}")
    private String templateCode;

    private static String timestamp;

    private static String signatureNonce;

    private String templateParam;
    static {
        SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));// 这里一定要设置GMT时区
        timestamp = df.format(new Date());
        signatureNonce = UUID.randomUUID().toString();
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    public String getSignatureVersion() {
        return signatureVersion;
    }

    public void setSignatureVersion(String signatureVersion) {
        this.signatureVersion = signatureVersion;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getSignatureMethod() {
        return signatureMethod;
    }

    public void setSignatureMethod(String signatureMethod) {
        this.signatureMethod = signatureMethod;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    public static String getSignatureNonce() {
        return signatureNonce;
    }

    public static void setSignatureNonce(String signatureNonce) {
        SendMsgInfo.signatureNonce = signatureNonce;
    }
}
