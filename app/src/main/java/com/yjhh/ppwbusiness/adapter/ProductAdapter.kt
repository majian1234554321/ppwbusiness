package com.yjhh.ppwbusiness.adapter

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.iv_image
import com.yjhh.ppwbusiness.R.id.tv_stop
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.base.BaseView
import com.yjhh.ppwbusiness.bean.ProductBean
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils

class ProductAdapter(list: List<ProductBean.ItemsBean>) :
    BaseQuickAdapter<ProductBean.ItemsBean, BaseViewHolder>(R.layout.productadapter, list) {
    override fun convert(helper: BaseViewHolder, item: ProductBean.ItemsBean?) {


        with(helper) {

            setText(R.id.right_dish_name, item?.name)
            setText(R.id.tv_price, "￥ ${item?.price}")

            setText(R.id.tv_desc, item?.describe)


            //ImageLoaderUtils.loadCircle(BaseApplication.context, getView<ImageView>(R.id.iv_image), item?.logoImageUrl)


            setText(
                R.id.tv_stop, when (item?.status) {
                    0 -> {
                        "上架商品"
                    }

                    1 -> {
                        "下架商品"


                    }

                    2 -> {
                        "售罄商品"
                    }
                    else -> {
                        "未知${item?.status}"
                    }
                }
            )


            setVisible(R.id.iv_image2, item?.status == 1)


            addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.tv_stop)
                .addOnClickListener(R.id.iv_edit)
        }


    }


}
