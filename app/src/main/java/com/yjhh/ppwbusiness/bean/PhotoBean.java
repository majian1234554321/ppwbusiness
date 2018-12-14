package com.yjhh.ppwbusiness.bean;

import java.io.Serializable;
import java.util.List;

public class PhotoBean implements Serializable {

    public List<ItemBean> item;

    public static class ItemBean implements Serializable {
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
        public String fileExt;
        public String fileName;
        public String fileId;
        public String fileMd5;
        public String filePath;
        public String postName;
        public long fileSize;
        public String sizeText;

        public String fileUrl;
    }
}
