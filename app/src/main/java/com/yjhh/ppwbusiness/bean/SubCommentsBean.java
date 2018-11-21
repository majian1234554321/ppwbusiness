package com.yjhh.ppwbusiness.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yjhh.ppwbusiness.adapter.EvaluateManageAdapter;
import com.yjhh.ppwbusiness.adapter.ExpandableItemAdapter;

public class SubCommentsBean implements MultiItemEntity {

    public String content;
    public boolean ifFile;
    public boolean ifShop;
    public String nickName;
    public int time;

    public SubCommentsBean(String content, boolean ifFile, boolean ifShop, String nickName, int time) {
        this.content = content;
        this.ifFile = ifFile;
        this.ifShop = ifShop;
        this.nickName = nickName;
        this.time = time;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }
}
