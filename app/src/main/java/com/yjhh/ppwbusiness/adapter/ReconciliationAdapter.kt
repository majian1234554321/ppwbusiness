


package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R

class ReconciliationAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.reconciliationadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_paystatus, item)
    }
}