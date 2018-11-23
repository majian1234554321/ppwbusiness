package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R

class ReservationOrderAdapter(data:List<String>) :BaseQuickAdapter<String,BaseViewHolder>( R.layout.reservationorderadapter,data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        //
    }
}