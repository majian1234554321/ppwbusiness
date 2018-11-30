package com.yjhh.ppwbusiness.adapter

import androidx.core.app.ComponentActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.OrderBean

class OrderTaskAdapter(data:List<OrderBean.ItemsBean>) :BaseQuickAdapter<OrderBean.ItemsBean,BaseViewHolder>(R.layout.ordertaskadapter,data) {
    override fun convert(helper: BaseViewHolder?, item: OrderBean.ItemsBean?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}