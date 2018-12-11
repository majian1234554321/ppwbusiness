package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class EvaluateDetailsBean {


    /**
     * content : 第三方撒地方萨芬
     * createdTime : 1544511122
     * files : []
     * id : 1000
     * ifShop : 0
     * items : [{"avatarPath":"/file/upload/png/20181127/1059bd2d5d00021b.png","content":"回答是否撒个撒的发生地方噶是的噶是的的噶是的噶十多个sad根深蒂固撒高大上的噶是的","createdTime":1544211122,"files":[],"id":1002,"ifShop":1,"items":[],"pv":0,"pvText":"浏览","shopGrade":5,"shopGradeText":"商家评分","shopLogoPath":"/file/upload/20181206/4aa8bcf437027657.png","shopName":"cxp的新dian1","shopScore":0,"timeText":"2018-12-08","userName":"用户回复:"},{"content":"d东方闪电防守打法是个大斯嘎尔问题奇特人更多发挥就好弄得我好跟本宫","createdTime":1544511122,"files":[],"id":1003,"ifShop":0,"items":[],"pv":0,"pvText":"浏览","shopGrade":5,"shopGradeText":"商家评分","shopLogoPath":"/file/upload/20181206/4aa8bcf437027657.png","shopName":"cxp的新dian1","shopScore":0,"timeText":"2018-12-11","userName":"商家回复:"}]
     * pv : 0
     * pvText : 浏览
     * shopGradeText : 商家评分
     * shopScore : 3
     * timeText : 2018-12-11
     */

    public String userName;
    public String content;
    public int createdTime;
    public String id;
    public int ifShop;
    public int pv;
    public String pvText;
    public String shopGradeText;
    public float shopScore;
    public String timeText;
    public List<?> files;
    public List<ItemsBean> items;

    public static class ItemsBean {
        /**
         * avatarPath : /file/upload/png/20181127/1059bd2d5d00021b.png
         * content : 回答是否撒个撒的发生地方噶是的噶是的的噶是的噶十多个sad根深蒂固撒高大上的噶是的
         * createdTime : 1544211122
         * files : []
         * id : 1002
         * ifShop : 1
         * items : []
         * pv : 0
         * pvText : 浏览
         * shopGrade : 5
         * shopGradeText : 商家评分
         * shopLogoPath : /file/upload/20181206/4aa8bcf437027657.png
         * shopName : cxp的新dian1
         * shopScore : 0
         * timeText : 2018-12-08
         * userName : 用户回复:
         */

        public String avatarPath;
        public String content;
        public int createdTime;
        public String id;
        public String ifShop;
        public int pv;
        public String pvText;
        public int shopGrade;
        public String shopGradeText;
        public String shopLogoPath;
        public String shopName;
        public int shopScore;
        public String timeText;
        public String userName;
        public List<?> files;
        public List<?> items;
    }
}
