package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class ReconciliationBean {

    /**
     * balance : 1920.5
     * date : 2018-12-10
     * memo : 展示最近三个月的数据
     * todayLogs : [{"count":0,"countText":"共0笔","flag":0,"money":0,"title":"入账账单"},{"count":0,"countText":"共0笔","flag":1,"money":0,"title":"出账账单"}]
     * totalIn : 0
     * totalOut : 0
     * withdraw : 1920.45
     */

    public float balance;
    public String date;
    public String memo;
    public float totalIn;
    public float totalOut;
    public float withdraw;
    public List<TodayLogsBean> todayLogs;

    public static class TodayLogsBean {
        /**
         * count : 0
         * countText : 共0笔
         * flag : 0
         * money : 0
         * title : 入账账单
         */

        public float count;
        public String countText;
        public float flag;
        public float money;
        public String title;
    }
}
