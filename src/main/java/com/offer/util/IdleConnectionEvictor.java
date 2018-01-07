package com.offer.util;

import org.apache.http.conn.HttpClientConnectionManager;

import java.util.concurrent.TimeUnit;
/**
 * @Author: lizhen
 * @Description:
 * @Date: Created in 19:44 2018/1/7
 */
public class IdleConnectionEvictor extends Thread {
    private final HttpClientConnectionManager connMgr;

    private volatile boolean shutdown;

    public IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
        // 启动当前线程
        this.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown){
                synchronized (this){
                    wait(5000);
                    //关闭失效链接
                    connMgr.closeExpiredConnections();
                    // 可选的, 关闭30秒内不活动的连接
                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                }
            }
        }catch (InterruptedException e){
            // 结束
        }
    }
    public void shutdown(){
        shutdown = true;
        synchronized (this){
            notifyAll();
        }
    }
}
