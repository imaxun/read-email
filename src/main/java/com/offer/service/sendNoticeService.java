package com.offer.service;

import com.offer.bean.VoiceMsgBean;
import com.offer.util.HttpResult;

/**
 * @Author: lizhen
 * @Description:
 * @Date: Created in 20:40 2018/1/8
 */
public interface sendNoticeService {
    HttpResult senMessage(String json);

    HttpResult senVoice(VoiceMsgBean bean);
}
