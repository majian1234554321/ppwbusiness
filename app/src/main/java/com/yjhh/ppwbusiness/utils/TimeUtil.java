package com.yjhh.ppwbusiness.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * King on 2016/12/6.
 */

public class TimeUtil {

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt*1000);
        res = simpleDateFormat.format(date);
        return res;
    }



    public static String stampToDate2(String s){

        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt*1000);
        res = simpleDateFormat.format(date);
        return res;
    }



    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime()/1000;
        res = String.valueOf(ts);
        return res;
    }


}
