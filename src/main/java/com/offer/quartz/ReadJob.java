package com.offer.quartz;

import com.offer.util.UtilDate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2017/12/21 10:52
 * 定时读取邮件
 */
public class ReadJob {
        public void taskCycle(){
            //模拟读取邮件
            System.out.println("现在是北京时间："+ UtilDate.getDate(new Date())+"使用SpringMVC框架配置定时任务读取邮件中.........");
        }
}
