package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class PutForwardBean
{

    /**
     * items : [{"bindType":1,"createdTime":1544174866,"id":1000,"money":55,"no":"20150202020330002","remark":"滚滚滚","shopId":1006,"status":0,"statusText":"申请","title":"提现至银行卡","typeText":"银行卡"},{"bindType":2,"createdTime":1544174866,"id":1001,"money":99,"no":"20150202020330003","remark":"山高塞复深","shopId":1006,"status":1,"statusText":"已审核","title":"提现至微信","typeText":"微信"},{"bindType":1,"createdTime":1544154866,"id":1003,"money":151515.66,"no":"20150202020330005","remark":"余额不足","shopId":1006,"status":4,"statusText":"失败","title":"提现至银行卡","typeText":"银行卡"},{"bindType":3,"createdTime":1544134866,"id":1002,"money":12,"no":"20150202020330004","remark":"十多个","shopId":1006,"status":2,"statusText":"成功","title":"提现至支付宝","typeText":"支付宝"}]
     * recordCount : 4
     */

    public int recordCount;
    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * bindType : 1
         * createdTime : 1544174866
         * id : 1000
         * money : 55.0
         * no : 20150202020330002
         * remark : 滚滚滚
         * shopId : 1006
         * status : 0
         * statusText : 申请
         * title : 提现至银行卡
         * typeText : 银行卡
         */

        public int bindType;
        public int createdTime;
        public int id;
        public double money;
        public String no;
        public String remark;
        public int shopId;
        public int status;
        public String statusText;
        public String title;
        public String typeText;
    }
}
