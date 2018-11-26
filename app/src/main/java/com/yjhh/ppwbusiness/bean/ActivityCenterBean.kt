package com.yjhh.ppwbusiness.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

class ActivityCenterBean : MultiItemEntity {

    constructor(itemType: Int, content: String) {
        this.itemType = itemType

        this.content = content
    }

    var content: String? = null

    override fun getItemType(): Int {
        return itemType
    }

    private var itemType: Int = 0

    companion object {
        val TEXT = 0
        val IMG = 1
    }

}
