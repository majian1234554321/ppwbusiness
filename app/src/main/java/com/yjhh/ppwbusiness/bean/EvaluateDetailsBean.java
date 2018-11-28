package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class EvaluateDetailsBean {


    /**
     * content : 很好是实话实说
     * files : [{"ext":".jpg","fileId":"","fileName":"","ifDoc":false,"ifImage":false,"ifMedia":false,"url":"http://192.168.2.200:8080/file/upload/jpg/20181116/dba9e8b58da3a250.jpg"},{"ext":".jpg","fileId":"","fileName":"","ifDoc":false,"ifImage":false,"ifMedia":false,"url":"http://192.168.2.200:8080/file/upload/jpg/20181116/dba9e8b58da3a250.jpg"},{"ext":".jpg","fileId":"","fileName":"","ifDoc":false,"ifImage":false,"ifMedia":false,"url":"http://192.168.2.200:8080/file/upload/jpg/20181116/dba9e8b58da3a250.jpg"}]
     * grade : 4
     * id : 112
     * ifFile : true
     * ifShop : false
     * nickName : 欢乐的小二逼
     * time : 1539234130
     */

    public String content;
    public int grade;
    public int id;
    public boolean ifFile;
    public boolean ifShop;
    public String nickName;
    public int time;
    public List<FilesBean> files;

    public static class FilesBean {
        /**
         * ext : .jpg
         * fileId :
         * fileName :
         * ifDoc : false
         * ifImage : false
         * ifMedia : false
         * url : http://192.168.2.200:8080/file/upload/jpg/20181116/dba9e8b58da3a250.jpg
         */

        public String ext;
        public String fileId;
        public String fileName;
        public boolean ifDoc;
        public boolean ifImage;
        public boolean ifMedia;
        public String fileUrl;
    }
}
