package com.yjhh.ppwbusiness.iview

import com.yjhh.ppwbusiness.base.BaseView
import com.yjhh.ppwbusiness.bean.ReservationBean

interface ReserveView : BaseView {

    fun onSuccessReserve(model: ReservationBean, flag: String)
}