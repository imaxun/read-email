package com.offer.bean;

/**
 * @Author lizhen [v_zhennli@tencent.com]
 * @Date 2018/1/8 11:25
 */
public class VoiceMsg {
    private String name; //名称
    private String year;//年
    private String month;//月
    private String day;//日
    private String time;//时
    private String minute;//分
    private String go;//出发地
    private String to;//目的地
    private String seat;//席别

    public VoiceMsg() {
    }

    public VoiceMsg(String name, String year, String month, String day, String time, String minute, String go, String to, String seat) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.minute = minute;
        this.go = go;
        this.to = to;
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getGo() {
        return go;
    }

    public void setGo(String go) {
        this.go = go;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
