package com.offer.service.impl;

import com.offer.bean.ConfigInfo;
import com.offer.bean.EmailInfo;
import com.offer.service.MailInfoService;
import com.offer.util.Constant;
import com.offer.util.Result;
import com.offer.util.ResultSupport;
import com.offer.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2017/12/19 15:46
 */
@Service("mailInfoService")
public class MailInfoServiceImpl implements MailInfoService {
    @Autowired
    private ConfigInfo configInfo;

    /**
     * 读取邮件，目前只是作为测试使用，后续优化需要接入redis,
     * @param userId
     * @return
     */
    public Result readMail() {
        Result result = new ResultSupport();
        Folder  folder = null;
        Store  store = null;
        try {
            //属性封装
            Properties prop = System.getProperties();
            prop.put("mail.store.protocol", configInfo.getProtocol());
            prop.put("mail.imap.host", configInfo.getHost());
            prop.setProperty("mail.imap.port", configInfo.getPort());

            Session session = Session.getInstance(prop);//建立会话
            //使用  imap 会话机制，连接服务器
            store =  session.getStore(configInfo.getProtocol());
            store.connect(configInfo.getEuser(),configInfo.getEpassword());
            folder  =  store.getFolder("INBOX");//收件箱
            folder.open(Folder.READ_WRITE);
            // 以只读方式打开邮件夹
            int numberOfTotal = folder.getMessageCount();
            // 取得邮箱中总共有多少封信
            int numberOfUnread = folder.getUnreadMessageCount();
            int count = numberOfTotal-numberOfUnread+1;
            Message[] message = folder.getMessages(count,numberOfTotal);
            //解析邮件
            EmailInfo emailInfo= parseMessage(message);
            result = new ResultSupport("200","读取成功",emailInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultUtil.error("500","读取失败");
        }finally {
            //释放资源
            if (folder != null) try {
                folder.close(true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            if (store != null) try {
                store.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return  result;
    }

    /**
     * 解析邮件
     * @param messages
     * @return
     * @throws MessagingException
     * @throws IOException
     */
    public EmailInfo parseMessage(Message ... messages) throws MessagingException, IOException {
        if (messages == null || messages.length < 1)
            throw new MessagingException("未找到要解析的邮件!");
        //存储邮件信息
        EmailInfo emaininfo =null;
        for (Message message : messages) {
            MimeMessage msg = (MimeMessage) message;
            msg.setFlag(Flags.Flag.SEEN,true);
            //存储邮件信息
            emaininfo = new EmailInfo();
            emaininfo.setCode(msg.getMessageID());
            InternetAddress address = getFrom(msg);
            emaininfo.setSender(address.getPersonal()+"<" + address.getAddress() + ">");
            emaininfo.setTitle(msg.getSubject());//转码后的标题
            emaininfo.setReceiver(getReceiveAddress(msg, null));//收件人
            emaininfo.setAccepttime(msg.getSentDate());//收件日期
            emaininfo.setCheckstatus(Constant.CHECK_STATUS_NO);
        }
        return emaininfo;
    }

    /**
     * 获得邮件发件人
     * @param msg 邮件内容
     * @return 地址
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public InternetAddress getFrom(MimeMessage msg) throws MessagingException,UnsupportedEncodingException{
            Address [] froms = msg.getFrom();
            if (froms.length < 1 )
                throw new MessagingException("没有收件人");
            return (InternetAddress) froms[0];
    }
    /**
     * 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人
     * <p>Message.RecipientType.TO  收件人</p>
     * <p>Message.RecipientType.CC  抄送</p>
     * <p>Message.RecipientType.BCC 密送</p>
     * @param msg 邮件内容
     * @param type 收件人类型
     * @return 收件人1 <邮件地址1>, 收件人2 <邮件地址2>, ...
     * @throws MessagingException
     */
    private String getReceiveAddress(MimeMessage msg, Message.RecipientType type) throws MessagingException {
        StringBuffer receiveAddress = new StringBuffer();
        Address[] addresss = null;
        if (type == null) {
            addresss = msg.getAllRecipients();
        } else {
            addresss = msg.getRecipients(type);
        }

        if (addresss == null || addresss.length < 1)
            throw new MessagingException("没有收件人!");
        for (Address address : addresss) {
            InternetAddress internetAddress = (InternetAddress)address;
            receiveAddress.append(internetAddress.toUnicodeString()).append(",");
        }

        receiveAddress.deleteCharAt(receiveAddress.length()-1); //删除最后一个逗号

        return receiveAddress.toString();
    }
}
