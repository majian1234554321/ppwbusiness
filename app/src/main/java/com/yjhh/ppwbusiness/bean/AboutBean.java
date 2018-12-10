package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class AboutBean {

    /**
     * address : 楚河汉界
     * companyName : 拍拍味
     * content : 永建合宏
     * copyRight : 永建合宏2018
     * functions : [{"linkUrl":"http://192.168.2.200:8080/www.baidu.com","name":"加盟合作"},{"linkUrl":"http://192.168.2.200:8080/www.baidu.com","name":"帮助中心"},{"linkUrl":"http://192.168.2.200:8080/www.baidu.com","name":"意见反馈"},{"linkUrl":"http://192.168.2.200:8080/www.baidu.com","name":"协议中心"}]
     * logoUrl :
     * tel : 10086
     * weChat :
     * weChatQrCode :
     */

    public String address;
    public String companyName;
    public String content;
    public String copyRight;
    public String logoUrl;
    public String tel;
    public String weChat;
    public String weChatQrCode;
    public List<FunctionsBean> functions;

    public static class FunctionsBean {
        /**
         * linkUrl : http://192.168.2.200:8080/www.baidu.com
         * name : 加盟合作
         */

        public String linkUrl;
        public String name;
    }
}
