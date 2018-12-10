package com.yjhh.ppwbusiness.bean;

import java.util.List;

public class WithDrawBean {


    /**
     * balance : 1000000.0
     * binds : [{"id":12,"text":"微*","title":"微信"},{"id":13,"text":"187****14","title":"支付宝"},{"id":11,"text":"招商 622****025","title":"银行卡"}]
     * ifHasBindInfo : true
     * maxRangeErrorText : 无限制
     * minRange : 100.0
     * minRangeErrorText : 提现最低100.00元
     * noBindInfoErrorText : 当前未绑定商户账号信息，请联系业务员处理
     * withdraw : 1000000.0
     */

    public float balance;
    public boolean ifHasBindInfo;
    public String maxRangeErrorText;
    public float minRange;
    public String minRangeErrorText;
    public String noBindInfoErrorText;
    public float withdraw;
    public List<BindsBean> binds;

    public static class BindsBean {
        /**
         * id : 12
         * text : 微*
         * title : 微信
         */

        public String id;
        public String text;
        public String title;
    }
}
