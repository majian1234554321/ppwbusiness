package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class OrderBean {




    /**
     * items : [{"avatarUrl":"http://192.168.2.200/asset/file/upload/20190110/7516d730a262b321.jpeg?stmp=32580","couponDisplayText":"未使用券","couponType":-1,"createdTime":1548751555,"disMoney":0,"id":1694,"ifShowUnDisMoney":false,"ifUseCoupon":0,"logs":[{"createdTime":1548751555,"id":2752,"orderId":1694,"status":1,"statusText":"待付款","title":"订单创建"}],"money":150,"nickName":"那M9地","orderNo":"20190129164555016001","payFlag":0,"payTypeText":"","shopId":1006,"shopLogoUrl":"","shopName":"休息休息","statusDisplay":1,"statusDisplayText":"未付款","statusText":"待付款","suppPayType":3,"times":1442712,"totalMoney":150,"unDisMoney":0,"userId":1054}]
     * pageCount : 1
     * queryModel : {"pageIndex":0,"pageSize":10}
     * recordCount : 1
     */

    public int pageCount;
    public QueryModelBean queryModel;
    public int recordCount;
    public List<ItemsBean> items;

    public static class QueryModelBean {
        /**
         * pageIndex : 0
         * pageSize : 10
         */

        public int pageIndex;
        public int pageSize;
    }

    public static class ItemsBean {
        /**
         * avatarUrl : http://192.168.2.200/asset/file/upload/20190110/7516d730a262b321.jpeg?stmp=32580
         * couponDisplayText : 未使用券
         * couponType : -1
         * createdTime : 1548751555
         * disMoney : 0.0
         * id : 1694
         * ifShowUnDisMoney : false
         * ifUseCoupon : 0
         * logs : [{"createdTime":1548751555,"id":2752,"orderId":1694,"status":1,"statusText":"待付款","title":"订单创建"}]
         * money : 150.0
         * nickName : 那M9地
         * orderNo : 20190129164555016001
         * payFlag : 0
         * payTypeText :
         * shopId : 1006
         * shopLogoUrl :
         * shopName : 休息休息
         * statusDisplay : 1
         * statusDisplayText : 未付款
         * statusText : 待付款
         * suppPayType : 3
         * times : 1442712
         * totalMoney : 150.0
         * unDisMoney : 0.0
         * userId : 1054
         */

        public int status;
        public String avatarUrl;
        public String couponDisplayText;
        public int couponType;
        public int createdTime;
        public double disMoney;
        public int id;
        public int payType;

        public boolean ifShowUnDisMoney;
        public int ifUseCoupon;
        public double money;
        public String nickName;
        public String orderNo;
        public int payFlag;
        public String payTypeText;
        public int shopId;
        public String shopLogoUrl;
        public String shopName;
        public int statusDisplay;
        public String statusDisplayText;
        public String statusText;
        public int suppPayType;
        public int times;
        public double totalMoney;
        public double unDisMoney;
        public int userId;
        public List<LogsBean> logs;

        public static class LogsBean {
            /**
             * createdTime : 1548751555
             * id : 2752
             * orderId : 1694
             * status : 1
             * statusText : 待付款
             * title : 订单创建
             */

            public int createdTime;
            public int id;
            public int orderId;
            public int status;
            public String statusText;
            public String title;
        }
    }
}
