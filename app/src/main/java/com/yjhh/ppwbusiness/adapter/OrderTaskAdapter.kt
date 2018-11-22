package com.yjhh.ppwbusiness.adapter

import android.support.v4.app.SupportActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.OrderBean

class OrderTaskAdapter(data:List<OrderBean.ItemsBeanX>) :BaseQuickAdapter<OrderBean.ItemsBeanX,BaseViewHolder>(R.layout.ordertaskadapter,data) {
    override fun convert(helper: BaseViewHolder?, item: OrderBean.ItemsBeanX?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}