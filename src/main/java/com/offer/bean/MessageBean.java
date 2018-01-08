package com.offer.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: lizhen
 * @Description:
 * @Date: Created in 19:28 2018/1/8
 */
public class MessageBean implements Serializable {
    private String username;//购票人名称
    private Date starttime;//出发日期 yyyy-mm-dd
    private String train;//C车次
    private String station;//出发站台
    private String terminus;//目的地

    public MessageBean() {
    }

    public MessageBean(String username, Date starttime, String train, String station, String terminus) {
        this.username = username;
        this.starttime = starttime;
        this.train = train;
        this.station = station;
        this.terminus = terminus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTerminus() {
        return terminus;
    }

    public void setTerminus(String terminus) {
        this.terminus = terminus;
    }
}
