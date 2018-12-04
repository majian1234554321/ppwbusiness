package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.BusinessHoursBean



class AllFeedBackAdapter(data:List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.allfeedbackadapter,data) {


    override fun convert(helper: BaseViewHolder?, item: String?) {



    }
}