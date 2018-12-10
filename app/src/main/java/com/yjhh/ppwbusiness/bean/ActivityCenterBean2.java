package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class ActivityCenterBean2 {


    /**
     * items : [{"activityId":1000,"dateSign":"2018001","describe":"三生三世","effectiveTime":1543320291,"expiredTime":1546320291,"files":[{"fileExt":".jpg","fileId":"FF7C839CCAC5EFDE2D564A9C8FDA16D7","filePath":"/file/upload/20181130/13ffe34cb2bb9d1f.jpg","fileSize":280895,"fileSizeText":"274.31KB","fileUrl":"http://192.168.2.200:8080/file/upload/20181130/13ffe34cb2bb9d1f.jpg"}],"id":1000,"linkUrl":"http://www.baidu.com","name":"201812月锦鲤活动","regs":[],"ruleUrl":"http://www.baidu.com","shopId":0,"status":1,"statusText":"进行中","totalUserByToday":0,"totalUserByYest":0}]
     * recordCount : 2
     */

    public int recordCount;
    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * activityId : 1000
         * dateSign : 2018001
         * describe : 三生三世
         * effectiveTime : 1543320291
         * expiredTime : 1546320291
         * files : [{"fileExt":".jpg","fileId":"FF7C839CCAC5EFDE2D564A9C8FDA16D7","filePath":"/file/upload/20181130/13ffe34cb2bb9d1f.jpg","fileSize":280895,"fileSizeText":"274.31KB","fileUrl":"http://192.168.2.200:8080/file/upload/20181130/13ffe34cb2bb9d1f.jpg"}]
         * id : 1000
         * linkUrl : http://www.baidu.com
         * name : 201812月锦鲤活动
         * regs : []
         * ruleUrl : http://www.baidu.com
         * shopId : 0
         * status : 1
         * statusText : 进行中
         * totalUserByToday : 0
         * totalUserByYest : 0
         */

        public int activityId;
        public String dateSign;
        public String describe;
        public String effectiveTime;
        public String expiredTime;
        public String id;
        public String linkUrl;
        public String name;
        public String ruleUrl;
        public int shopId;
        public int status;
        public String statusText;
        public String totalUserByToday;
        public String totalUserByYest;
        public List<FilesBean> files;
        public List<Regs> regs;

        public static class Regs{
            public String date;
            public String text;
            public String total;
        }


        public static class FilesBean {
            /**
             * fileExt : .jpg
             * fileId : FF7C839CCAC5EFDE2D564A9C8FDA16D7
             * filePath : /file/upload/20181130/13ffe34cb2bb9d1f.jpg
             * fileSize : 280895
             * fileSizeText : 274.31KB
             * fileUrl : http://192.168.2.200:8080/file/upload/20181130/13ffe34cb2bb9d1f.jpg
             */

            public String fileExt;
            public String fileId;
            public String filePath;
            public int fileSize;
            public String fileSizeText;
            public String fileUrl;
        }
    }
}
