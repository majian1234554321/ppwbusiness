package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R

import com.yjhh.ppwbusiness.bean.ReservationBean
import com.yjhh.ppwbusiness.utils.TimeUtil


class ReservationBeforeAdapter(data: List<ReservationBean.ItemsBean>) :
    BaseQuickAdapter<ReservationBean.ItemsBean, BaseViewHolder>(R.layout.reservationbeforeadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: ReservationBean.ItemsBean?) {

        helper?.setText(R.id.tv_time, TimeUtil.stampToDate2(item?.acceptTime.toString()))
        helper?.setText(R.id.tv_name, item?.userName)
        helper?.setText(R.id.tv_count, item?.userCount.toString())
        helper?.setText(R.id.tv_phone, item?.userMobile)
        helper?.setText(R.id.tv_status, item?.statusText)


    }
}