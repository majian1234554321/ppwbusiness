package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class AllFeedBackBean  {


    /**
     * items : [{"cause":"太空棉","createdTime":1544257013,"email":"236@qq.com","id":1011,"images":[],"name":"好","status":0,"statusText":"已提交","title":"格拉卡"},{"cause":"咯默默啦啦啦","createdTime":1544256933,"email":"5466@qq.com","id":1010,"images":[{"fileExt":".jpg","fileId":"4C839A22294F3CC3662B1AFDDF2CBB53","filePath":"/file/upload/20181208/aebdc24f3b5fa758.jpg","fileSize":2769983,"fileSizeText":"2.64MB","fileUrl":"http://192.168.2.200:8080/file/upload/20181208/aebdc24f3b5fa758.jpg"}],"name":"哈","status":0,"statusText":"已提交","title":"略略略"},{"cause":"挣钱好慢","createdTime":1544256049,"email":"5566@qq.com","id":1009,"images":[{"fileExt":".jpg","fileId":"8191D203070FCA7FF5342C2202DCB211","filePath":"/file/upload/20181208/6841e0c1a7207df4.jpg","fileSize":4170591,"fileSizeText":"3.98MB","fileUrl":"http://192.168.2.200:8080/file/upload/20181208/6841e0c1a7207df4.jpg"}],"name":"难\n","status":0,"statusText":"已提交","title":"好慢"},{"cause":"好评","createdTime":1544255851,"email":"5566@qq.co","id":1008,"images":[{"fileExt":".png","fileId":"3BC8E3C702D57A7D50DAFC1464D223C2","filePath":"/file/upload/20181208/904ffbc0c1f0ec87.png","fileSize":258301,"fileSizeText":"252.25KB","fileUrl":"http://192.168.2.200:8080/file/upload/20181208/904ffbc0c1f0ec87.png"}],"name":"我","status":0,"statusText":"已提交","title":"我们"},{"cause":"发现发现","createdTime":1544239741,"email":"nin@qq.com","id":1007,"images":[{"fileExt":".jpg","fileId":"B5A8C052F7AA2680ED8D511D9372C58A","filePath":"/file/upload/20181208/d7832fcb11f135c7.jpg","fileSize":2925986,"fileSizeText":"2.79MB","fileUrl":"http://192.168.2.200:8080/file/upload/20181208/d7832fcb11f135c7.jpg"}],"name":"大","status":0,"statusText":"已提交","title":"小"},{"cause":"这个app好还是坏","createdTime":1544239585,"email":"5566@qq.com","id":1006,"images":[{"fileExt":".jpg","fileId":"E1FB766A32D2D63B35E40BC19A64A4D9","filePath":"/file/upload/20181208/8da720bc9541c094.jpg","fileSize":2573367,"fileSizeText":"2.45MB","fileUrl":"http://192.168.2.200:8080/file/upload/20181208/8da720bc9541c094.jpg"}],"name":"好","status":0,"statusText":"已提交","title":"坏"},{"cause":"下雪太冷生意不好做","createdTime":1544184577,"email":"5566@qq.com","id":1005,"images":[{"fileExt":".jpg","fileId":"D110A0C8062A444F316902E681D1145E","filePath":"/file/upload/20181207/c439be88cc70409c.jpg","fileSize":3334689,"fileSizeText":"3.18MB","fileUrl":"http://192.168.2.200:8080/file/upload/20181207/c439be88cc70409c.jpg"}],"name":"李四","status":0,"statusText":"已提交","title":"下雪"},{"cause":"/摸摸哦","createdTime":1544184378,"email":"129654367@qq","id":1004,"images":[{"fileExt":".jpg","fileId":"012D3DE903A969B4DDC122D09492174E","filePath":"/file/upload/20181207/28a4ce4dd01d1b05.jpg","fileSize":690802,"fileSizeText":"674.61KB","fileUrl":"http://192.168.2.200:8080/file/upload/20181207/28a4ce4dd01d1b05.jpg"},{"fileExt":".jpg","fileId":"A295E1DC37ED3424EEE604A603920E70","filePath":"/file/upload/20181207/5a7337c69320cbc6.jpg","fileSize":731830,"fileSizeText":"714.68KB","fileUrl":"http://192.168.2.200:8080/file/upload/20181207/5a7337c69320cbc6.jpg"}],"name":"@","status":0,"statusText":"已提交","title":"/"}]
     * recordCount : 8
     */

    public int recordCount;
    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * cause : 太空棉
         * createdTime : 1544257013
         * email : 236@qq.com
         * id : 1011
         * images : []
         * name : 好
         * status : 0
         * statusText : 已提交
         * title : 格拉卡
         */

        public String cause;
        public String createdTime;
        public String email;
        public String id;
        public String name;
        public int status;
        public String statusText;
        public String title;
        public List<PhotoBean.ItemBean> images;
    }
}
