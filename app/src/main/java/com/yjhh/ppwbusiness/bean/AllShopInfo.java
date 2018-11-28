package com.yjhh.ppwbusiness.bean;

import java.util.ArrayList;
import java.util.List;

public class AllShopInfo {


    /**
     * account : {"order":0,"today":0,"yesOrder":0,"yesToday":0}
     * address : 测试一下地址能不能提交成功
     * content : 测试一下修改描述
     * curr : {"mobile":"187****12","nickName":"18758056612","roleName":"商户管理员","type":1,"userId":1023}
     * images : []
     * logoUrl : http://192.168.2.200:8080/file/upload/png/20181127/e5fac74ae2e1932f.png
     * mobile : 027-6878886
     * openStatus : 1
     * openStatusText : 营业中
     * status : 1
     * statusText : 营业中
     * times : [{"begin":"01:00","end":"18:00"},{"begin":"19:00","end":"21:00"},{"begin":"21:10","end":"21:10"}]
     */

    public AccountBean account;
    public String address;
    public String content;
    public CurrBean curr;
    public String logoUrl;
    public String name;
    public String mobile;
    public int openStatus;
    public String openStatusText;
    public int status;
    public String statusText;
    public List<?> images;
    public ArrayList<TimesBean> times;

    public static class AccountBean {
        /**
         * order : 0
         * today : 0
         * yesOrder : 0
         * yesToday : 0
         */

        public int order;
        public int today;
        public int yesOrder;
        public int yesToday;
    }

    public static class CurrBean {
        /**
         * mobile : 187****12
         * nickName : 18758056612
         * roleName : 商户管理员
         * type : 1
         * userId : 1023
         */

        public String mobile;
        public String nickName;
        public String roleName;
        public int type;
        public int userId;
    }

    public static class TimesBean {
        /**
         * begin : 01:00
         * end : 18:00
         */

        public String begin;
        public String end;
    }
}
