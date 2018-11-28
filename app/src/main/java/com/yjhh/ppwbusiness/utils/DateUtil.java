package com.yjhh.ppwbusiness.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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


    public static String getFetureDate(int past, String regx) throws ParseException {
        SimpleDateFormat format;
        SimpleDateFormat format2;
        String result;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();

        if ("MD".equals(regx)) {


            format2 = new SimpleDateFormat("yyyy-MM-dd");
            result = format2.format(today);
            if (IsToday(result)) {
                return "今天";
            }

            if (IsTomorrowday(result)) {
                return "明天";
            }

            format = new SimpleDateFormat("MM-dd");

            result = format.format(today);


        } else {
            format = new SimpleDateFormat("yyyy-MM-dd");
            result = format.format(today);
            if (IsToday(result)) {
                return "今天";
            }

            if (IsTomorrowday(result)) {
                return "明天";
            }

        }


        return result;
    }


    public static String getFetureDate2(int past, String regx) throws ParseException {
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


    public static boolean IsToday(String day) throws ParseException {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }


    public static boolean IsTomorrowday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 1) {
                return true;
            }
        }
        return false;
    }


}
