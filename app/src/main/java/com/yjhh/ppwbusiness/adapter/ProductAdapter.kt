package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R

class ProductAdapter(list:List<String>) :BaseQuickAdapter<String,BaseViewHolder>(R.layout.productadapter,list) {
    override fun convert(helper: BaseViewHolder?, item: String?) {

    }
}