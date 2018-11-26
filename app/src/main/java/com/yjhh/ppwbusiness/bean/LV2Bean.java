package com.yjhh.ppwbusiness.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yjhh.ppwbusiness.adapter.ExpandableItemAdapter;

public class LV2Bean implements MultiItemEntity {

    public String tvCount;

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }
}
