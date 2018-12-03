package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class CancelationBeforeBean {

    /**
     * items : [{"code":"1122334455","effectiveTime":1542529331,"expiredTime":1544257331,"expiredTimeText":"2018-12-08前有效。","money":12231,"remark":"哈市的发送到发三个规格发广告","shopId":123,"shopName":"美美咔 旗舰店","title":"美美咔 五折券","totalMoney":100500,"userId":1111,"userMobile":"","userName":"拍拍拍"},{"code":"1122334455","effectiveTime":1542529331,"expiredTime":1544257331,"expiredTimeText":"2018-12-08前有效。","money":12231,"remark":"哈市的发送到发三个规格发广告","shopId":123,"shopName":"美美咔 旗舰店","title":"美美咔 五折券","totalMoney":100500,"userId":1111,"userMobile":"","userName":"拍拍拍"},{"code":"1122334455","effectiveTime":1542529331,"expiredTime":1544257331,"expiredTimeText":"2018-12-08前有效。","money":12231,"remark":"哈市的发送到发三个规格发广告","shopId":123,"shopName":"美美咔 旗舰店","title":"美美咔 五折券","totalMoney":100500,"userId":1111,"userMobile":"","userName":"拍拍拍"},{"code":"1122334455","effectiveTime":1542529331,"expiredTime":1544257331,"expiredTimeText":"2018-12-08前有效。","money":12231,"remark":"哈市的发送到发三个规格发广告","shopId":123,"shopName":"美美咔 旗舰店","title":"美美咔 五折券","totalMoney":100500,"userId":1111,"userMobile":"","userName":"拍拍拍"},{"code":"1122334455","effectiveTime":1542529331,"expiredTime":1544257331,"expiredTimeText":"2018-12-08前有效。","money":12231,"remark":"哈市的发送到发三个规格发广告","shopId":123,"shopName":"美美咔 旗舰店","title":"美美咔 五折券","totalMoney":100500,"userId":1111,"userMobile":"","userName":"拍拍拍"},{"code":"1122334455","effectiveTime":1542529331,"expiredTime":1544257331,"expiredTimeText":"2018-12-08前有效。","money":12231,"remark":"哈市的发送到发三个规格发广告","shopId":123,"shopName":"美美咔 旗舰店","title":"美美咔 五折券","totalMoney":100500,"userId":1111,"userMobile":"","userName":"拍拍拍"},{"code":"1122334455","effectiveTime":1542529331,"expiredTime":1544257331,"expiredTimeText":"2018-12-08前有效。","money":12231,"remark":"哈市的发送到发三个规格发广告","shopId":123,"shopName":"美美咔 旗舰店","title":"美美咔 五折券","totalMoney":100500,"userId":1111,"userMobile":"","userName":"拍拍拍"},{"code":"1122334455","effectiveTime":1542529331,"expiredTime":1544257331,"expiredTimeText":"2018-12-08前有效。","money":12231,"remark":"哈市的发送到发三个规格发广告","shopId":123,"shopName":"美美咔 旗舰店","title":"美美咔 五折券","totalMoney":100500,"userId":1111,"userMobile":"","userName":"拍拍拍"},{"code":"1122334455","effectiveTime":1542529331,"expiredTime":1544257331,"expiredTimeText":"2018-12-08前有效。","money":12231,"remark":"哈市的发送到发三个规格发广告","shopId":123,"shopName":"美美咔 旗舰店","title":"美美咔 五折券","totalMoney":100500,"userId":1111,"userMobile":"","userName":"拍拍拍"},{"code":"1122334455","effectiveTime":1542529331,"expiredTime":1544257331,"expiredTimeText":"2018-12-08前有效。","money":12231,"remark":"哈市的发送到发三个规格发广告","shopId":123,"shopName":"美美咔 旗舰店","title":"美美咔 五折券","totalMoney":100500,"userId":1111,"userMobile":"","userName":"拍拍拍"}]
     * recordCount : 13
     */

    public int recordCount;
    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * code : 1122334455
         * effectiveTime : 1542529331
         * expiredTime : 1544257331
         * expiredTimeText : 2018-12-08前有效。
         * money : 12231
         * remark : 哈市的发送到发三个规格发广告
         * shopId : 123
         * shopName : 美美咔 旗舰店
         * title : 美美咔 五折券
         * totalMoney : 100500
         * userId : 1111
         * userMobile :
         * userName : 拍拍拍
         */


        public String reviewUserName;


        public String type;


        public String useRange;
        public String useTime;
        public String id;
        public String code;
        public int effectiveTime;
        public String expiredTime;
        public String expiredTimeText;
        public float money;
        public String remark;
        public String shopId;
        public String shopName;
        public String title;
        public float totalMoney;
        public String userId;
        public String userMobile;
        public String userName;


        public String value;

        public List<FilesBean> files;

    }


    public static class FilesBean {

        public String fileExt;
        public String filePath;
        public String fileSize;
        public String fileSizeText;
        public String fileUrl;

    }


}
