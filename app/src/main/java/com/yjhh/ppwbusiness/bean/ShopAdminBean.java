package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class ShopAdminBean {


    /**
     * account : {"blance":154,"order":88,"today":543,"yesOrder":20}
     * functions : [{"code":"shop-mgr","name":"店铺管理","url":"apps://shop/index"},{"code":"pro-mgr","name":"商品管理","url":"apps://shop/product"},{"code":"comment-mgr","name":"评价管理","url":"apps://comment/index"},{"code":"activity-mgr","name":"活动中心","url":"apps://activity/index"},{"code":"report-mgr","name":"对账报表","url":"apps://shop/report"},{"code":"report-mgr","name":"网页跳转测试","url":"http://www.bhyjhh.com"}]
     * openStatus : 0
     * openStatusText : 营业中
     * preNum : 0
     * preTotal : 0
     * shopName : 休息休息
     * status : 1
     */

    public AccountBean account;
    public int openStatus;
    public String openStatusText;
    public int preNum;
    public int preTotal;
    public String shopName;
    public int status;
    public List<FunctionsBean> functions;

    public static class AccountBean {
        /**
         * blance : 154.0
         * order : 88
         * today : 543.0
         * yesOrder : 20
         */

        public double blance;
        public int order;
        public double today;
        public int yesOrder;
    }

    public static class FunctionsBean {
        /**
         * code : shop-mgr
         * name : 店铺管理
         * url : apps://shop/index
         */

        public String code;
        public String name;
        public String url;
    }
}
