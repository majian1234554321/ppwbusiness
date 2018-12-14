package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.ActivityCenterBean2

class GameRecordAdapter(data: List<ActivityCenterBean2.ItemsBean.Regs>) :
    BaseQuickAdapter<ActivityCenterBean2.ItemsBean.Regs, BaseViewHolder>(R.layout.gamerecordadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: ActivityCenterBean2.ItemsBean.Regs?) {

        helper?.setText(R.id.tv_time, item?.date)
        helper?.setText(R.id.tv_people, item?.total)

    }

}
