package com.company.pms.pmsbase.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @User zcf
 * @Create 2018/7/20 10:01
 * @Desc: 获取格式化时间的工具
 */
public class TimeUtil {
    private static String DEFAULT_TYPE = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式当前时间
     * @param type 时间格式类型
     *             如： yyyy-MM-dd HH:mm:ss
     * @return 格式化后的当前时间
     */
    public static String getTime(String type) {
        SimpleDateFormat df=new SimpleDateFormat(type);
        return df.format(new Date());
    }

    /**
     * 使用默认时间格式
     * @return
     */
    public static String getTime() {
        SimpleDateFormat df=new SimpleDateFormat(DEFAULT_TYPE);
        return df.format(new Date());
    }

    /**
     * 格式化指定时间
     * @param type 时间格式类型
     *             如： yyyy-MM-dd HH:mm:ss
     * @return 格式化后的当前时间
     */
    public static String getTime(Date date, String type){
        SimpleDateFormat df=new SimpleDateFormat(type);
        return df.format(date);
    }

    /**
     * 用默认格式(yyyy-MM-dd HH:mm:ss)格式化指定时间
     * @return 格式化后的当前时间
     */
    public static String getTime(Date date){
        SimpleDateFormat df=new SimpleDateFormat(DEFAULT_TYPE);
        return df.format(date);
    }

    /**
     * 返回10位时间戳(即1970-01-01 08:00:000到当前时间的时间长度(Long类型))
     * @return
     */
    public static String getTimeStamp(){
        return String.valueOf(new Date().getTime()/1000);
    }

    private TimeUtil(){ }

//    public static void main(String[] args){
//        System.out.println(getTime("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(getTime(new Date(0), "yyyy-MM-dd HH:mm:sss"));
//        System.out.println(getTimeStamp());
//    }
}