package com.yjhh.ppwbusiness.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.ReconciliationItemBean
import com.yjhh.ppwbusiness.utils.TimeUtil

class PutForwardAdapter(data: List<ReconciliationItemBean.ItemsBean>) :
    BaseQuickAdapter<ReconciliationItemBean.ItemsBean, BaseViewHolder>(R.layout.putforwardadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: ReconciliationItemBean.ItemsBean?) {
        helper?.setText(R.id.tv_1, item?.title)

        helper?.setText(R.id.tv_3, item?.no)


        if (item?.status == 1) {

            helper?.setText(
                R.id.tv_4,
                BaseApplication.getIns().getString(R.string.rmb_price_doublep, item?.money)
            )

            helper?.setTextColor(R.id.tv_4, Color.parseColor("#54B36D"))

        } else {

            helper?.setText(
                R.id.tv_4,
                BaseApplication.getIns().getString(R.string.rmb_price_doublec, item?.money)
            )

            helper?.setTextColor(R.id.tv_4, Color.parseColor("#FF552E"))
        }


        helper?.setText(R.id.tv_5, TimeUtil.stampToDate(item?.createdTime))
        helper?.setText(R.id.tv_6, item?.statusText)
    }
}