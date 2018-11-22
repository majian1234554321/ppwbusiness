package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class ShopAdminBean {


    /**
     * account : {"blance":154,"order":88,"today":543,"yesOrder":20}
     * functions : [{"code":"pro-mgr","name":"商品管理","orderBy":100,"url":"apps:/shop/product"},{"code":"report-mgr","name":"报表","orderBy":200,"url":"http://www.baidu.com"}]
     * shopName : 哇哈哈
     */

    public AccountBean account;
    public String shopName;
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
        public double yesToday;
        public int yesOrder;
    }

    public static class FunctionsBean {
        /**
         * code : pro-mgr
         * name : 商品管理
         * orderBy : 100
         * url : apps:/shop/product
         */

        public String code;
        public String name;
        public int orderBy;
        public String url;
    }
}
