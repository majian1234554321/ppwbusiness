package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class PhotoBean {

    public List<ItemBean> item;

    public static class ItemBean {
        /**
         * beforeName : 1541425795284.jpg
         * contentType : multipart/form-data
         * ext : .jpg
         * fileName : 7f6d2334f5c079e8.jpg
         * id : 7318E6BF89C22C0138E21B63B87BE315
         * md5 : 411FF5B6C2577D6B63631F7FEC05655B
         * path : http://192.168.2.200:8080/file/upload/jpg/20181121/7f6d2334f5c079e8.jpg
         * postName : multipartFile
         * size : 510071
         * sizeText : 498.12KB
         */

        public String beforeName;
        public String contentType;
        public String ext;
        public String fileName;
        public String id;
        public String md5;
        public String path;
        public String postName;
        public int size;
        public String sizeText;
    }
}
