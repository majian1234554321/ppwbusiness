package com.yjhh.ppwbusiness.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yjhh.ppwbusiness.adapter.EvaluateManageAdapter;
import com.yjhh.ppwbusiness.adapter.ExpandableItemAdapter;

public class SubCommentsBean implements MultiItemEntity {

    public String content;
    public String  avatarPath;
    public String  createdTime;
    public String id;
    public int time;
    public String ifShop;
    public String pv;

    public String pvText;
    public String shopGrade;
    public String shopGradeText;
    public String shopLogoPath;
    public String shopName;
    public String shopScore;
    public String timeText;
    public String userName;
    public boolean last;


    public SubCommentsBean(String content, String avatarPath, String createdTime, String id, int time, String ifShop,
                           String pv, String pvText, String shopGrade, String shopGradeText, String shopLogoPath,
                           String shopName, String shopScore, String timeText, String userName, boolean last) {
        this.content = content;
        this.avatarPath = avatarPath;
        this.createdTime = createdTime;
        this.id = id;
        this.time = time;
        this.ifShop = ifShop;
        this.pv = pv;
        this.pvText = pvText;
        this.shopGrade = shopGrade;
        this.shopGradeText = shopGradeText;
        this.shopLogoPath = shopLogoPath;
        this.shopName = shopName;
        this.shopScore = shopScore;
        this.timeText = timeText;
        this.userName = userName;
        this.last = last;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }
}
