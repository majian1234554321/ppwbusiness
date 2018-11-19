package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.BusinessHoursBean
import com.yjhh.ppwbusiness.views.merchant.BusinessHoursFragment

class BusinessHoursAdapter(data:List<BusinessHoursBean>) :
    BaseQuickAdapter<BusinessHoursBean,BaseViewHolder>(R.layout.businesshoursadapter,data) {


    override fun convert(helper: BaseViewHolder?, item: BusinessHoursBean?) {

        helper?.setText(R.id.tv_sTime,item?.sTime)

        helper?.setText(R.id.tv_eTime,item?.eTime)

        helper?.addOnClickListener(R.id.tv_sTime)?.addOnClickListener(R.id.tv_eTime)
            ?.addOnClickListener(R.id.tv_delete)

    }
}