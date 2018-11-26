package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter

import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.ActivityCenterBean

class ActivityCenterAdapter(data: List<ActivityCenterBean>) :
    BaseMultiItemQuickAdapter<ActivityCenterBean, BaseViewHolder>(data) {
    override fun convert(helper: BaseViewHolder?, item: ActivityCenterBean?) {
        when (helper?.itemViewType) {
            ActivityCenterBean.TEXT -> helper.setText(R.id.tv, item?.content)
            ActivityCenterBean.IMG -> helper.setText(R.id.tv_No, item?.content)
        }


    }


    init {
        addItemType(ActivityCenterBean.TEXT, R.layout.item_text_view)
        addItemType(ActivityCenterBean.IMG, R.layout.item_image_view)

    }

}
