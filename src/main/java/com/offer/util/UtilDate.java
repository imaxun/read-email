
package com.offer.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/* *
 *类名：UtilDate
 *功能：自定义订单类
 *详细：工具类，可以用作获取系统日期、订单编号等
 *版本：3.3
 *说明：
 */
public class UtilDate {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat DT_SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";

    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";

    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";

    /** 年月日(无下划线) yyyyMMdd */
    public static final String ddShort                 = "dd";

    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtSimple                 = "yyyy-MM-dd";

    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtString               = "yyyy年MM月dd日";

    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间+三位随机数
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}

    public  static String getOrderNumTree(){
        Date date=new Date();
        DateFormat df=new SimpleDateFormat(dtLong);
        return df.format(date)+getThree();
    }


    /**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(Date date){
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}

    /**
     * 获取系统当期日，格式：dd
     * @return
     */
    public static String getDateDD(Date date){
        DateFormat df=new SimpleDateFormat(ddShort);
        return df.format(date);
    }

    /**
     * 获取系统当期日，格式：yyyyy年MM月dd日
     * @return
     */
    public static String getDate2(Date date){
        DateFormat df=new SimpleDateFormat(dtString);
        return df.format(date);
    }

    /**
     * 计算当前时间与当天某个时间点差
     * @param date
     * @param time
     * @return
     */
    public static long getPointTime(Date date ,int time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,time);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);

        long time1 = cal.getTimeInMillis();
        cal.setTime(date);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000);
        return Math.abs(between_days);
    }

    /**
     * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
     * @return
     */
    public static String getSimpleDate(){
        Date date=new Date();
        DateFormat df=new SimpleDateFormat(dtSimple);
        return df.format(date);
    }

	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}

    /**
     *计算两个时间之间相差多少秒
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getDatePoor(Date startTime, Date endTime) {
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数

        long diff = endTime.getTime() - startTime.getTime();
        long sec = diff/ns;//计算差多少秒//输出结果
        return sec;
    }


    /**
     *计算两个时间之间相差多少秒
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getDatePoorHaomiao(Date startTime, Date endTime) {
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数

        long diff = endTime.getTime() - startTime.getTime();
        long sec = diff;//计算差多少秒//输出结果
        return Math.abs(sec);
    }



    /**
     * 计算当前时间与当天五点的时间差
     * @param date
     * @return
     */
    public static long get5Time(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,5);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);

        long time1 = cal.getTimeInMillis();
        cal.setTime(date);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000);
        return between_days;
    }


    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time1-time2)/(1000*3600*24);

        return  (int)((time1-time2)/(1000*3600* 24));
        // return Math.abs(Integer.parseInt(String.valueOf(between_days)));
    }

    /**
     * 根据日期获得星期
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysCode[intWeek];
    }

    /**
     * 根据日期获得星期
     * @param date
     * @return
     */
    public static String getchineseWeekOfDate(Date date) {
        String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * 优惠券结束时间计算
     * 当前日期增加固定天数
     * @param deadline
     * @return
     */
    public static String getEndTime(int deadline) {
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        String date = format.format(d);
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, deadline);// 30为增加的天数，可以改变的
        d = ca.getTime();
        String backTime = format.format(d);
        return backTime;
    }

    /**
     * 周日判断
     *
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        boolean weekend = false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            weekend = true;
        }
        return weekend;
    }

    /**
     * 加天
     *
     * @param date
     * @param i
     */
    public static Date addDate(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, i);
        return cal.getTime();
    }

    /**
     * 减天
     *
     * @param date
     * @param i
     */
    public static Date deleteDate(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }

    /**
     * 加天
     *
     * @param date
     * @param i
     */
    public static Long addDate2(Date date, int i) {
        return addDate(date,i).getTime()/1000;
    }


    /**
     * 加分钟
     *
     * @param date
     * @param i
     */
    public static Date addMinute(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, i);
        return cal.getTime();
    }

    /**
     * 减分钟
     *
     * @param date
     * @param i
     */
    public static Date deleteMinute(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - i);
        return cal.getTime();
    }

    /**
     * 加月
     *
     * @param date
     * @param i
     */
    public static Date addMonth(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }


    /**
     * 加年
     *
     * @param date
     * @param i
     */
    public static Date addYear(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, i);
        return cal.getTime();
    }

    /**
     * 计算两个日期时间戳相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween2(Integer smdate, Integer bdate) throws ParseException
    {
        return (bdate - smdate)/(24 * 60 * 60);
    }

}
