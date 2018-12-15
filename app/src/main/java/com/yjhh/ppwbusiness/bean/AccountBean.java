package com.yjhh.ppwbusiness.bean;

import java.io.Serializable;
import java.util.List;

public class AccountBean implements Serializable {

    /**
     * displayMobile : 176****86
     * id : 1001
     * mobile : 17601386386
     * name : 数据误删....
     * role : 0
     * roleName : 管理员
     * shopId : 1006
     * status : 0
     * userId : 1022
     */


    public String avatarUrl;


    /**
     * binds : [{"bindLinkUrl":"http://www.paipaiwei.com","bindStatus":1,"text":"招商 622****025","title":"银行卡","type":1},{"bindLinkUrl":"http://www.paipaiwei.com","bindStatus":1,"text":"已绑定","title":"微信","type":2},{"bindLinkUrl":"http://www.paipaiwei.com","bindStatus":1,"text":"187****14","title":"支付宝","type":3}]
     * displayMobile : 176****86
     * id : 1001
     * mobile : 17601386386
     * name : sss
     * role : 0
     * roleName : 管理员
     * shopId : 1006
     * status : 0
     * userId : 1022
     */

    public String logoUrl;
    public String displayMobile;
    public int id;
    public String mobile;
    public String name;
    public int role;
    public String roleName;
    public int shopId;
    public int status;
    public int userId;
    public List<BindsBean> binds;

    public static class BindsBean implements Serializable {
        /**
         * bindLinkUrl : http://www.paipaiwei.com
         * bindStatus : 1
         * text : 招商 622****025
         * title : 银行卡
         * type : 1
         */

        public String bindLinkUrl;
        public int bindStatus;
        public String text;
        public String title;
        public int type;
    }
}
