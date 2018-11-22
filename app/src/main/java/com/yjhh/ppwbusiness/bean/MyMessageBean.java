package com.yjhh.ppwbusiness.bean;

import java.io.Serializable;
import java.util.List;

public class MyMessageBean implements Serializable
{

    /**
     * items : [{"content":"系统计划于10月12日，凌晨2:00-6:00升级维护，届时部分功能将无法使用，敬请谅解。","id":1,"ifRead":true,"ifShare":true,"linkUrl":"http://www.baidu.com","readTime":1541742799,"sendTime":1541742799,"status":1,"title":"系统消息","type":0},{"content":"系统计划于10月12日，凌晨2:00-6:00升级维护，届时部分功能将无法使用，敬请谅解。","id":2,"ifRead":true,"ifShare":true,"linkUrl":"http://www.baidu.com","readTime":1541742799,"sendTime":1541739199,"status":1,"title":"系统消息","type":0},{"content":"系统计划于10月12日，凌晨2:00-6:00升级维护，届时部分功能将无法使用，敬请谅解。","id":3,"ifRead":true,"ifShare":true,"linkUrl":"http://www.baidu.com","readTime":1541742799,"sendTime":1541735599,"status":1,"title":"系统消息","type":0}]
     * pageCount : 1
     * queryModel : {"pageIndex":0,"pageSize":10}
     * recordCount : 3
     */

    public int pageCount;
    public QueryModelBean queryModel;
    public int recordCount;
    public List<ItemsBean> items;

    public static class QueryModelBean implements Serializable{
        /**
         * pageIndex : 0
         * pageSize : 10
         */

        public int pageIndex;
        public int pageSize;
    }

    public static class ItemsBean implements Serializable {
        /**
         * content : 系统计划于10月12日，凌晨2:00-6:00升级维护，届时部分功能将无法使用，敬请谅解。
         * id : 1
         * ifRead : true
         * ifShare : true
         * linkUrl : http://www.baidu.com
         * readTime : 1541742799
         * sendTime : 1541742799
         * status : 1
         * title : 系统消息
         * type : 0
         */

        public String content;
        public int id;
        public boolean ifRead;
        public boolean ifShare;
        public String linkUrl;
        public int readTime;
        public int sendTime;
        public int status;
        public String title;
        public int type;
    }
}
