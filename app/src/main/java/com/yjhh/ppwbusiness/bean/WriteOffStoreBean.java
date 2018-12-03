package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class WriteOffStoreBean {


    public List<ItemBean> item;

    public static class ItemBean {
        /**
         * content : 商家还未发布公告！
         * images : []
         * logoUrl : http://192.168.2.200:8080/file/upload/20181203/eb2812ec227beec9.png
         * name : 青青河边草
         * openStatus : 1
         * openStatusText : 已审核
         * status : 0
         * statusText :
         */

        public String content;
        public String logoUrl;
        public String name;
        public int openStatus;
        public String openStatusText;
        public int status;
        public String statusText;
        public List<?> images;
    }
}
