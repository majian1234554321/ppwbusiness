package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R

class PutForwardAdapter(data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.putforwardadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_1, item)

        helper?.setText(R.id.tv_2, "\n$item")

        helper?.setText(R.id.tv_3, item)
        helper?.setText(R.id.tv_4, item)
    }
}