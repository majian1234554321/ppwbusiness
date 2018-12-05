package com.yjhh.ppwbusiness.bean;

import java.io.Serializable;
import java.util.List;

public class ProductBean implements Serializable {


    /**
     * items : [{"describe":"s分公司告诉对方是否","id":1,"ifBuy":false,"ifCollect":false,"ifMerRec":false,"ifPlatRec":false,"ifRead":false,"name":"大包sod米啊","price":100.99,"status":0},{"describe":"s分公司告诉对方是否","id":2,"ifBuy":false,"ifCollect":false,"ifMerRec":false,"ifPlatRec":false,"ifRead":false,"name":"大包sod米啊","price":100.99,"status":0},{"describe":"s分公司告诉对方是否","id":3,"ifBuy":false,"ifCollect":false,"ifMerRec":false,"ifPlatRec":false,"ifRead":false,"name":"大包sod米啊","price":100.99,"status":0},{"describe":"s分公司告诉对方是否","id":4,"ifBuy":false,"ifCollect":false,"ifMerRec":false,"ifPlatRec":false,"ifRead":false,"name":"大包sod米啊","price":100.99,"status":0},{"describe":"s分公司告诉对方是否","id":5,"ifBuy":false,"ifCollect":false,"ifMerRec":false,"ifPlatRec":false,"ifRead":false,"name":"大包sod米啊","price":100.99,"status":0}]
     * pageCount : 1
     * queryModel : {"pageIndex":0,"pageSize":100}
     * recordCount : 10
     */

    public int position;
    public int pageCount;
    public QueryModelBean queryModel;
    public int recordCount;
    public List<ItemsBean> items;

    public static class QueryModelBean implements Serializable {
        /**
         * pageIndex : 0
         * pageSize : 100
         */

        public int pageIndex;
        public int pageSize;
    }

    public static class ItemsBean implements Serializable {
        /**
         * describe : s分公司告诉对方是否
         * id : 1
         * ifBuy : false
         * ifCollect : false
         * ifMerRec : false
         * ifPlatRec : false
         * ifRead : false
         * name : 大包sod米啊
         * price : 100.99
         * status : 0
         */

        public String describe;
        public String id;
        public String itemId;

        public int saleStatus;
        public boolean ifBuy;
        public boolean ifCollect;
        public boolean ifMerRec;
        public boolean ifPlatRec;
        public boolean ifRead;
        public String name;
        public String logoUrl;
        public double price;


        public List<PhotoBean.ItemBean> images;

    }
}
