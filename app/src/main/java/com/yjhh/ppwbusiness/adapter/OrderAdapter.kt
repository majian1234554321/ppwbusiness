package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R

class OrderAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.orderadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_name, item)
    }
}