package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R

import com.yjhh.ppwbusiness.bean.ShopTimesModel


class BusinessHoursAdapter(data:List<ShopTimesModel>) :
    BaseQuickAdapter<ShopTimesModel,BaseViewHolder>(R.layout.businesshoursadapter,data) {


    override fun convert(helper: BaseViewHolder?, item: ShopTimesModel?) {

        helper?.setText(R.id.tv_sTime,item?.begin)

        helper?.setText(R.id.tv_eTime,item?.end)

        helper?.addOnClickListener(R.id.tv_sTime)?.addOnClickListener(R.id.tv_eTime)
            ?.addOnClickListener(R.id.tv_delete)

    }
}