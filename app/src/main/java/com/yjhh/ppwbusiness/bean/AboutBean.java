package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class AboutBean {


    /**
     * address : 武昌区汉街国际总部E座1701室
     * companyName : 永建合宏科技有限公司
     * content : 湖北永建合宏科技有限公司成立于2018年，立足湖北武汉，致力于成为领先的综合性本地生活服务平台，用科技连接消费者和商家，提供服务以满足人们日常「食」的需求，并进一步扩展至多种生活周边服务。通过线下和线上相互促进，构建一个生活消费的生态链场景，配合平台庞大的实时配送网络，使这种连接成为可能。
     * copyRight : Copyrigh © 2018
     * functions : [{"client":3,"linkUrl":"http://192.168.2.200:8080/about/join","name":"加盟合作","type":1},{"client":2,"linkUrl":"http://192.168.2.200:8080/help/index","name":"帮助中心","type":1},{"client":3,"linkUrl":"apps://feedback/index","name":"意见反馈","type":2},{"client":3,"linkUrl":"http://192.168.2.200:8080/help/index","name":"协议中心","type":1},{"client":3,"linkUrl":"tels://057-12345677","name":"客服电话","type":3}]
     * logoUrl :
     * weChat :
     * weChatQrCode :
     */

    public String address;
    public String companyName;
    public String content;
    public String copyRight;
    public String logoUrl;
    public String weChat;
    public String weChatQrCode;
    public List<FunctionsBean> functions;

    public static class FunctionsBean {
        /**
         * client : 3
         * linkUrl : http://192.168.2.200:8080/about/join
         * name : 加盟合作
         * type : 1
         */

        public int client;
        public String linkUrl;
        public String name;
        public int type;
    }
}
