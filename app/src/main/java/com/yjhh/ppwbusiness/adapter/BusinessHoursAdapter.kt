package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.views.merchant.BusinessHoursFragment

class BusinessHoursAdapter(data:List<BusinessHoursFragment.BusinessHoursBean>) :BaseQuickAdapter<BusinessHoursFragment.BusinessHoursBean,BaseViewHolder>(R.layout.businesshoursadapter,data) {
    override fun convert(helper: BaseViewHolder?, item: BusinessHoursFragment.BusinessHoursBean?) {

        helper?.setText(R.id.tv_sTime,item?.sTime)

        helper?.setText(R.id.tv_eTime,item?.eTime)

    }
}