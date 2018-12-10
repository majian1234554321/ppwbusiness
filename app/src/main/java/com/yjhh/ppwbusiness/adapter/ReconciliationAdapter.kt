package com.yjhh.ppwbusiness.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.ReconciliationItemBean
import com.yjhh.ppwbusiness.utils.TimeUtil

class ReconciliationAdapter(data: List<ReconciliationItemBean.ItemsBean>) :
    BaseQuickAdapter<ReconciliationItemBean.ItemsBean, BaseViewHolder>(R.layout.reconciliationadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: ReconciliationItemBean.ItemsBean?) {
        helper?.setText(R.id.tv_paystatus, item?.typeText)

        if (item?.flag == 1) {
            helper?.setText(
                R.id.tv_endstatus, "出账"
            )

            helper?.setText(
                R.id.tv_price,
                BaseApplication.getIns().getString(R.string.rmb_price_doublec, item?.money)
            )

            helper?.setTextColor(R.id.tv_price, Color.parseColor("#54B36D"))

        } else {
            helper?.setText(
                R.id.tv_endstatus, "入账"
            )
            helper?.setText(
                R.id.tv_price,
               BaseApplication.getIns().getString(R.string.rmb_price_doublep, item?.money)
            )

            helper?.setTextColor(R.id.tv_price, Color.parseColor("#FF552E"))
        }

        helper?.setText(R.id.tv_time, TimeUtil.stampToDate(item?.createdTime))
        helper?.setText(
            R.id.tv_enfPrice,
            "余额${BaseApplication.getIns().getString(R.string.rmb_price_double, item?.balance)}"
        )

    }
}