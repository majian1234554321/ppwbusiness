package com.yjhh.ppwbusiness.adapter

import android.graphics.Color
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.*
import com.yjhh.ppwbusiness.bean.OrderBean
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils
import com.yjhh.ppwbusiness.utils.TimeUtil

class OrderAdapter(data: List<OrderBean.ItemsBean>) :
    BaseQuickAdapter<OrderBean.ItemsBean, BaseViewHolder>(R.layout.orderadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: OrderBean.ItemsBean?) {
        helper?.setText(R.id.tv_1, TimeUtil.stampToDate(item?.createdTime.toString(), "yyyy/MM/dd HH:mm:ss"))

        helper?.setText(R.id.tv_name, item?.nickName)


        helper?.setText(R.id.tv_4, BaseApplication.context.getString(R.string.rmb_price_double2, item?.totalMoney))
        helper?.setText(R.id.tv_6, BaseApplication.context.getString(R.string._rmb_price_double, item?.disMoney))


        helper?.setText(R.id.tv_8, BaseApplication.context.getString(R.string._rmb_price_double, item?.unDisMoney))

        helper?.setText(R.id.tv_10, BaseApplication.context.getString(R.string.rmb_price_double2, item?.money))


        val tv9 = helper?.getView<TextView>(R.id.tv_9)



        when (item?.payType) { //payType支付方式(1微信 2支付宝 4中国银联 8[平台])

            1 -> {
                val drawable = BaseApplication.context.resources.getDrawable(R.drawable.icon_wx)
                //drawable.setBounds(0, 0, 28, 20)
                tv9?.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
            }

            2 -> {
                val drawable = BaseApplication.context.resources.getDrawable(R.drawable.icon_zfb)
                //drawable.setBounds(0, 0, 28, 20)
                tv9?.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
            }
            else -> {
                tv9?.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            }
        }









        ImageLoaderUtils.load(
            BaseApplication.context,
            helper?.getView<ImageView>(R.id.iv_image),
            item?.avatarUrl,
            R.drawable.icon_place,
            R.drawable.icon_place,
            0
        )

        when (item?.status) {
            0 -> {

            }
            1 -> {
                helper?.setText(R.id.tv_2, "待付款")
                helper?.setTextColor(R.id.tv_2, Color.parseColor("#2C85FF"))
            }
            else -> {
            }
        }

//        helper?.setText(R.id.tv_2, "￥${item?.totalMoeny}")
//
//        helper?.setText(R.id.tv_3, "￥${item?.moeny}")


        //  helper?.setVisible()

    }
}