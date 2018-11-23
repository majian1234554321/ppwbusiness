package com.yjhh.ppwbusiness.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by TL20160309 on 2016/11/14.
 */

public class DateUtil {


    public static String getFormatDHMmDate(long seconds) {
        if (seconds < 0)
            return "还有0小时0分0秒";
        long one_day = 60 * 60 * 24;
        long one_hour = 60 * 60;
        long one_minute = 60;
        long day, hour, minute, second = 0L;

        day = seconds / one_day;
        hour = seconds % one_day / one_hour;
        minute = seconds % one_day % one_hour / one_minute;
        second = seconds % one_day % one_hour % one_minute;

        if (seconds < one_minute) {
            return seconds + "秒";
        } else if (seconds < one_hour) {
            return minute + "分" + second + "秒";
        } else if (seconds < one_day) {
            return hour + "时" + minute + "分" + second + "秒";
        } else {
            return day + "天" + hour + "时" + minute + "分" + second + "秒";
        }
    }


    public static String getFetureDate(int past, String regx) {
        SimpleDateFormat format;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();

        if ("MD".equals(regx)) {
            format = new SimpleDateFormat("MM-dd");
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd");
        }


        String result = format.format(today);

        return result;
    }


    public static String dayForWeek(String pTime) throws Throwable {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date tmpDate = format.parse(pTime);
        Calendar cal = Calendar.getInstance();

        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        try {
            cal.setTime(tmpDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;

        return weekDays[w];

    }


}
