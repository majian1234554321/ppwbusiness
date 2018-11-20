package com.yjhh.ppwbusiness.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.tv_price
import com.yjhh.ppwbusiness.bean.OrderBean

class OrderAdapter(data: List<OrderBean.ItemsBeanX>) :
    BaseQuickAdapter<OrderBean.ItemsBeanX, BaseViewHolder>(R.layout.orderadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: OrderBean.ItemsBeanX?) {
        helper?.setText(R.id.remark, item?.remark)

        helper?.setText(R.id.tv_totalMoeny, "总价         ￥${item?.totalMoeny}")

        helper?.setText(R.id.tv_price, "实付金额         ￥${item?.moeny}")


    }
}