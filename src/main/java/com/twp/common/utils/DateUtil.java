/*    
 * 五星电器公司拥有完全的版权   
 * 使用者必须经过许可
 *----------------------------------------------------------------------*
 * Copyright  (c) 2016 FiveStar Co.,Ltd. All rights reserved
 * Author       : FiveStar Development
 * Description  : DateUtil.java
 *----------------------------------------------------------------------*
 * Change-History: Change history
 * Developer  Date      Description
 * shidong  2017年5月27日 Short description containing Message, Note ID or CR ID
 *----------------------------------------------------------------------*  
 */
package com.twp.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期util
 *
 * @author shidong
 * @since JDK 1.8
 */
public class DateUtil {
    public final static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public final static String YYYYMMDDP = "yyyy.MM.dd";

    public final static String YYYYMMDD = "yyyyMMdd";

    /**
     * 返回当前的日期时间的字符表示 默认使用 yyyy-MM-dd HH:mm:ss 格式
     *
     * @return String 当前时间("yyyy-MM-dd HH:mm:ss" 格式)
     */
    public static String getNowString() {
        SimpleDateFormat formatter = new SimpleDateFormat(DateUtil.YYYYMMDDHHMMSS);
        Date currentTime = new Date();
        return formatter.format(currentTime);
    }

    /**
     * 日期型变量由 java.util.Date 转换成 String
     *
     * @param date   java.util.Date 型变量
     * @param format 日期格式
     * @return String 型日期
     */
    public static String parseUtilToString(Date date, String format) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);
        } else {
            return null;
        }
    }
    public static String parseUtilToString(Date date) {
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
            return formatter.format(date);
        } else {
            return null;
        }
    }

    /**
     * 日期型变量由 String 转换成 java.util.Date
     *
     * @param date   String 型变量
     * @param format 日期格式
     * @return java.util.Date 型变量
     */
    public static Date parseStringToUtil(String date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date dtResult = null;
        try {
            dtResult = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dtResult;
    }

    public static Date getDayLastTime(Date d) {
        Calendar calendar=Calendar.getInstance();
        if (null != d) calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 增加日期
     *
     * @param date
     * @param amount
     * @return
     * @author shidong
     * @since JDK 1.8
     */
    public static Date addDays(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, amount);
        return c.getTime();
    }

    public static Date[] getThreeYearDate(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        c.set(c.get(Calendar.YEAR) ,0, 1);//开始时间日期
        Date startDate = c.getTime();
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.YEAR, 1);
        ca.set(ca.get(Calendar.YEAR) ,11, ca.getActualMaximum(Calendar.DAY_OF_MONTH));//结束日期
        Date endDate = ca.getTime();
        return new Date[]{startDate,endDate};
    }


    /**
     * 获得该月第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获得该月最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }
}
