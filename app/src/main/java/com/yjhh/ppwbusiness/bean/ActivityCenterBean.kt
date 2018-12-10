package com.yjhh.ppwbusiness.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

class ActivityCenterBean : MultiItemEntity {

    constructor(itemType: Int, content: ActivityCenterBean2.ItemsBean) {
        this.itemType = itemType

        this.content = content
    }

    var content: ActivityCenterBean2.ItemsBean? = null

    override fun getItemType(): Int {
        return itemType
    }

    private var itemType: Int = 0

    companion object {
        val TEXT = 0
        val IMG = 1
    }

}
