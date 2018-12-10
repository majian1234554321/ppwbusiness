package com.yjhh.ppwbusiness.bean;

import java.io.Serializable;

public class PutForwardSuccessBean  implements Serializable {


    /**
     * bindType : 2
     * bindTypeText : 微信
     * createdTime : 1544421818
     * createdTimeText : 2018-12-10 14:03:38
     * moneText : ¥ 102.00
     * money : 102.0
     * orderNo : 181210140200002
     * remark : 提现申请已提交，具体到账时间以第三方服务为准
     * status : 0
     * statusText : 申请成功
     * title : 余额提现至微信
     */

    public String bindType;
    public String bindTypeText;
    public String createdTime;
    public String createdTimeText;
    public String moneText;
    public float money;
    public String orderNo;
    public String remark;
    public String status;
    public String statusText;
    public String title;
}
