package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R

class PFMainAdapter(lists  :List<String>) : BaseQuickAdapter<String,BaseViewHolder>(R.layout.pfmainadapter,lists) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_name,item)
    }
}