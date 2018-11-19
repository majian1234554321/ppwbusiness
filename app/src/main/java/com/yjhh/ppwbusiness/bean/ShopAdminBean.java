package com.yjhh.ppwbusiness.bean;

public class ShopAdminBean {

    /**
     * account : {"blance":154,"order":88,"today":543,"yesOrder":20}
     * mobile : 176****86
     * nickName : 6660
     */

    public AccountBean account;
    public String mobile;
    public String nickName;

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
        public double yesToday;
    }
}
