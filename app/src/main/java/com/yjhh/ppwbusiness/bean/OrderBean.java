package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class OrderBean {



    public int pageCount;
    public int recordCount;
    public List<ItemsBeanX> items;



    public static class ItemsBeanX {


        public int createdTime;
        public int finishTime;
        public int id;
        public int moeny;
        public String orderNo;
        public String remark;
        public ShopInfoBean shopInfo;
        public double totalMoeny;
        public List<ItemsBean> items;

        public static class ShopInfoBean {

            public String content;
            public int openStatus;
            public String openStatusText;
            public int status;
            public String statusText;
        }

        public static class ItemsBean {


            public int id;
            public String logoUrl;
            public String name;
            public List<ImagesBean> images;

            public static class ImagesBean {


                public boolean ifDoc;
                public boolean ifImage;
                public boolean ifMedia;
                public String url;
            }
        }
    }




}
