package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.views.main.main1.Main1Fragment

class Main1Adapter(data: List<Main1Fragment.Main1Bean>) :
    BaseQuickAdapter<Main1Fragment.Main1Bean, BaseViewHolder>(R.layout.main1adapter, data) {
    override fun convert(helper: BaseViewHolder?, item: Main1Fragment.Main1Bean?) {

        helper?.setImageResource(R.id.iv_image, item?.image!!)

        helper?.setText(R.id.tv_value, item?.value)





    }

}
