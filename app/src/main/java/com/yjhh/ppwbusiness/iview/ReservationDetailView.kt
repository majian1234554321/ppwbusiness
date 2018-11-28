package com.yjhh.ppwbusiness.iview

import com.yjhh.ppwbusiness.base.BaseView
import com.yjhh.ppwbusiness.bean.ReservDetailsBean

interface ReservationDetailView :BaseView {
    fun  onSuccessReservDetail(model: ReservDetailsBean)
}