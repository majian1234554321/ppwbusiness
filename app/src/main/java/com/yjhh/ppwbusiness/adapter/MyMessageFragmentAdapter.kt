package com.yjhh.ppwbusiness.adapter


import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R

import com.yjhh.ppwbusiness.bean.MyMessageBean
import com.yjhh.ppwbusiness.utils.TimeUtil


class MyMessageFragmentAdapter(var data: ArrayList<MyMessageBean.ItemsBean>) :
    BaseQuickAdapter<MyMessageBean.ItemsBean, BaseViewHolder>(R.layout.mymessagefragmentadapter,data) {
    override fun convert(helper: BaseViewHolder?, item: MyMessageBean.ItemsBean?) {


        helper?.setText(R.id.tv1,item?.title)
        helper?.setText(R.id.tv2,item?.content)

        helper?.setText(R.id.tv_time,TimeUtil.stampToDate(item?.sendTime.toString()))

    }


}

