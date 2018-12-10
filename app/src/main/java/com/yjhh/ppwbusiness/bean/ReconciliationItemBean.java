package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class ReconciliationItemBean {

    /**
     * items : [{"balance":56560,"createdTime":1544261728,"flag":1,"id":1030,"money":12.3,"remark":"55656","shopId":1002,"total":500,"type":2,"typeText":"提现"},{"balance":565601,"createdTime":1544261728,"flag":1,"id":1031,"money":22.3,"remark":"55656","shopId":1002,"total":500,"type":2,"typeText":"提现"},{"balance":565602,"createdTime":1544261728,"flag":1,"id":1032,"money":32.3,"remark":"55656","shopId":1002,"total":500,"type":2,"typeText":"提现"},{"balance":565603,"createdTime":1544261728,"flag":0,"id":1033,"money":42.3,"remark":"55656","shopId":1002,"total":500,"type":1,"typeText":"账户充值"},{"balance":565604,"createdTime":1544261728,"flag":0,"id":1034,"money":52.3,"remark":"55656","shopId":1002,"total":500,"type":1,"typeText":"账户充值"},{"balance":565605,"createdTime":1544261728,"flag":0,"id":1035,"money":2.53,"remark":"55656","shopId":1002,"total":500,"type":1,"typeText":"账户充值"},{"balance":565606,"createdTime":1544261728,"flag":1,"id":1036,"money":542.3,"remark":"55656","shopId":1002,"total":500,"type":2,"typeText":"提现"},{"balance":565607,"createdTime":1544261728,"flag":0,"id":1037,"money":32.3,"remark":"55656","shopId":1002,"total":500,"type":1,"typeText":"账户充值"},{"balance":565608,"createdTime":1544261728,"flag":1,"id":1038,"money":232.3,"remark":"55656","shopId":1002,"total":500,"type":2,"typeText":"提现"},{"balance":565609,"createdTime":1544261728,"flag":1,"id":1039,"money":222.3,"remark":"55656","shopId":1002,"total":500,"type":2,"typeText":"提现"}]
     * recordCount : 10
     */

    public int recordCount;
    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * balance : 56560.0
         * createdTime : 1544261728
         * flag : 1
         * id : 1030
         * money : 12.3
         * remark : 55656
         * shopId : 1002
         * total : 500.0
         * type : 2
         * typeText : 提现
         */

        public String statusText;
        public String no;
        public String title;
        public float balance;
        public String createdTime;
        public int flag;
        public int id;
        public float money;
        public String remark;
        public int shopId;
        public float total;
        public int  status;
        public int type;
        public String typeText;
    }
}
