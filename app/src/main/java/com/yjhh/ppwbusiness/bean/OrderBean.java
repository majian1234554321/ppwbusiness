package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class OrderBean {




    public int recordCount;
    public List<ItemsBean> items;

    public static class ItemsBean {

        public String avatarUrl;
        public String couponDisplayText;
        public int createdTime;
        public double disMoney;
        public int id;
        public int ifShowUnDisMoney;
        public int ifUseCoupon;
        public double money;
        public String nickName;
        public String orderNo;
        public int payFlag;
        public String payTypeText;
        public int shopId;
        public String shopLogoUrl;
        public String shopName;
        public int status;
        public String statusDisplayText;
        public String statusText;
        public double totalMoney;
        public double unDisMoney;
        public int userId;
        public int payType;
        public List<LogsBean> logs;

        public static class LogsBean {
            /**
             * createdTime : 1543508643
             * id : 1007
             * orderId : 1014
             * status : 1
             * statusText : 待付款
             * title : 订单已创建
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
