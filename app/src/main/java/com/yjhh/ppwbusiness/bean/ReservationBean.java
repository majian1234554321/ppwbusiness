package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class ReservationBean {

    /**
     * items : [{"acceptTime":1543235097,"id":1001,"operBy":1023,"operByName":"18758056612","remark":"大桌","shopId":1007,"shopName":"cxp的新店","status":1,"statusText":"已接受","time":1543429979,"timeTotal":42699,"userCount":7,"userId":1023,"userMobile":"18758056614","userName":"Mr.c"},{"id":1003,"remark":"大桌","shopId":1007,"shopName":"cxp的新店","status":0,"statusText":"已提交","time":1543429979,"timeTotal":42699,"userCount":7,"userId":1023,"userMobile":"18758056614","userName":"Mr.c"},{"id":1002,"remark":"大桌","shopId":1007,"shopName":"cxp的新店","status":0,"statusText":"已提交","time":1543429979,"timeTotal":42699,"userCount":7,"userId":1023,"userMobile":"18758056614","userName":"Mr.c"},{"acceptTime":1543233462,"cause":"斤斤计较","id":1000,"operBy":1023,"operByName":"18758056612","remark":"大桌","shopId":1007,"shopName":"cxp的新店","status":3,"statusText":"店家取消","time":1543429979,"timeTotal":42699,"userCount":7,"userId":1023,"userMobile":"18758056614","userName":"Mr.c"}]
     * pageCount : 0
     * recordCount : 4
     */

    public int pageCount;
    public int recordCount;
    public int positions;




    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * acceptTime : 1543235097
         * id : 1001
         * operBy : 1023
         * operByName : 18758056612
         * remark : 大桌
         * shopId : 1007
         * shopName : cxp的新店
         * status : 1
         * statusText : 已接受
         * time : 1543429979
         * timeTotal : 42699
         * userCount : 7
         * userId : 1023
         * userMobile : 18758056614
         * userName : Mr.c
         * cause : 斤斤计较
         */

        public int acceptTime;
        public String id;
        public int operBy;
        public String operByName;
        public String remark;
        public int shopId;
        public String shopName;
        public int status;
        public String statusText;
        public int time;
        public long timeTotal;
        public int userCount;
        public int userId;
        public String userMobile;
        public String userName;
        public String cause;
    }
}
