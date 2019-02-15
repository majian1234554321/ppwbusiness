package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class SwitchShopBean {


    /**
     * curr : {"coupons":[],"grade":0,"id":1006,"ifBrand":false,"ifBuy":false,"ifNews":false,"ifPai":false,"ifPre":false,"ifRec":false,"labelA":[],"labelB":[],"labels":[],"name":"休息休息","perCapita":""}
     * shops : [{"coupons":[],"grade":0,"id":1006,"ifBrand":false,"ifBuy":false,"ifNews":false,"ifPai":false,"ifPre":false,"ifRec":false,"labelA":[],"labelB":[],"labels":[],"logoUrl":"","name":"休息休息","perCapita":""}]
     */

    public CurrBean curr;
    public List<ShopsBean> shops;

    public static class CurrBean {
        /**
         * coupons : []
         * grade : 0
         * id : 1006
         * ifBrand : false
         * ifBuy : false
         * ifNews : false
         * ifPai : false
         * ifPre : false
         * ifRec : false
         * labelA : []
         * labelB : []
         * labels : []
         * name : 休息休息
         * perCapita :
         */

        public String logoUrl;
        public int grade;
        public int id;
        public boolean ifBrand;
        public boolean ifBuy;
        public boolean ifNews;
        public boolean ifPai;
        public boolean ifPre;
        public boolean ifRec;
        public String name;
        public String perCapita;
        public List<?> coupons;
        public List<?> labelA;
        public List<?> labelB;
        public List<?> labels;
    }

    public static class ShopsBean {
        /**
         * coupons : []
         * grade : 0
         * id : 1006
         * ifBrand : false
         * ifBuy : false
         * ifNews : false
         * ifPai : false
         * ifPre : false
         * ifRec : false
         * labelA : []
         * labelB : []
         * labels : []
         * logoUrl :
         * name : 休息休息
         * perCapita :
         */

        public int grade;
        public String id;
        public boolean ifBrand;
        public boolean ifBuy;
        public boolean ifNews;
        public boolean ifPai;
        public boolean ifPre;
        public boolean ifRec;
        public String logoUrl;
        public String name;
        public String perCapita;
        public List<?> coupons;
        public List<?> labelA;
        public List<?> labelB;
        public List<?> labels;
    }
}
