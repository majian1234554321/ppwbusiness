package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.ProductBean

class ProductAdapter(list: List<ProductBean.ItemsBean>) :
    BaseQuickAdapter<ProductBean.ItemsBean, BaseViewHolder>(R.layout.productadapter, list) {
    override fun convert(helper: BaseViewHolder, item: ProductBean.ItemsBean?) {


        with(helper) {

            setText(R.id.right_dish_name, item?.name)
            setText(R.id.tv_price, "￥ ${item?.price}")


            addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.tv_stop)
                .addOnClickListener(R.id.iv_edit)
        }


    }
}