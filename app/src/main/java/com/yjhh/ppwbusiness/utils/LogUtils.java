package com.yjhh.ppwbusiness.utils;

import android.util.Log;

public class LogUtils {

    private LogUtils() {
    }

    private static LogUtils logUtils;

    public static LogUtils getInstance() {
        if (logUtils == null) {
            logUtils = new LogUtils();
        }
        return logUtils;
    }


    public static void i(String tag, String value) {
       // if (Constants.DeBUG)
            Log.i(String.valueOf(tag), String.valueOf(value));
    }

    public static void i(Class clazz, String value) {
       // if (Constants.DeBUG)
            Log.i(clazz.getSimpleName(), String.valueOf(value));
    }


    public static void e(String tag, String value) {
        // if (Constants.DeBUG)
        Log.i(String.valueOf(tag), String.valueOf(value));
    }

    public static void e(Class clazz, String value) {
        // if (Constants.DeBUG)
        Log.i(clazz.getSimpleName(), String.valueOf(value));
    }
}
