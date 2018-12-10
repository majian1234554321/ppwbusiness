package com.yjhh.ppwbusiness.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
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
            setText(R.id.tv_price, BaseApplication.context.getString(R.string.rmb_price_double2,item?.price))

            setText(R.id.tv_desc, item?.describe)


            ImageLoaderUtils.load(
                BaseApplication.context,
                getView<ImageView>(R.id.iv_image),
                item?.logoUrl,
                R.drawable.icon_place,
                R.drawable.icon_place,
                0
            )


            setText(
                R.id.tv_stop, when (item?.saleStatus) {
                    0 -> {
                        "下架商品"
                    }

                    1 -> {
                        "上架商品"
                    }

                    2 -> {
                        "售罄商品"
                    }
                    else -> {
                        "未知${item?.saleStatus}"
                    }
                }
            )


            setVisible(R.id.iv_image2, item?.saleStatus == 1)


            addOnClickListener(R.id.tv_delete)
                .addOnClickListener(R.id.tv_stop)
                .addOnClickListener(R.id.iv_edit)
                .addOnClickListener(R.id.iv_image)
        }


    }


}
