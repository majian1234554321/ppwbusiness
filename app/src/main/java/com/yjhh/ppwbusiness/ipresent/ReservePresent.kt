package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import com.yjhh.ppwbusiness.R.id.view
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ReserveService
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.BaseView
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.ReservDetailsBean
import com.yjhh.ppwbusiness.bean.ReservationBean
import com.yjhh.ppwbusiness.iview.ReservationDetailView
import com.yjhh.ppwbusiness.iview.ReserveView

class ReservePresent(var context: Context) : BasePresent() {

    var view:BaseView?=null

    constructor( context: Context, view: ReserveView) :this(context){
        this.view= view
    }


    constructor( context: Context, view: ReservationDetailView) :this(context){
        this.view= view
    }














    var map = ArrayMap<String, String>()
    fun acceptReserve(id: String?, type: String?, cause: String?, flag: String, position: Int) {
        map.clear()
        map["id"] = id.toString()
        map["type"] = type.toString()
        map["cause"] = cause.toString()

        toSubscribe2(ApiServices.getInstance().create(ReserveService::class.java)
            .acceptReserve(map), object : ProcessObserver2(context) {
            override fun onFault(message: String) {
                Log.i("acceptReserve", message)
            }

            override fun processValue(response: String?) {
                Log.i("acceptReserve", response)
                val model = ReservationBean()
                model.positions = position
                ( view as ReserveView).onSuccessReserve(model, flag)
            }
        }
        )

    }


    fun reserveDetail(id:String?) {
        map.clear()
        map["id"] = id.toString()

        toSubscribe2(ApiServices.getInstance().create(ReserveService::class.java)
            .reserveDetail(map), object : ProcessObserver2(context) {
            override fun onFault(message: String) {
                Log.i("reserveDetail", message)
            }

            override fun processValue(response: String?) {
                Log.i("reserveDetail", response)

              val  model =  gson.fromJson<ReservDetailsBean>(response, ReservDetailsBean::class.java)

                ( view as ReservationDetailView).onSuccessReservDetail(model)
            }
        }
        )

    }


    fun reserves(status: String?, date: String?, pageIndex: Int, pageSize: Int, flag: String) {

        map.clear()
        map["status"] = status.toString()
        map["date"] = date.toString()
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()

        toSubscribe2(ApiServices.getInstance().create(ReserveService::class.java)
            .reserves(map), object : ProcessObserver2(context) {
            override fun onFault(message: String) {
                Log.i("reserves", message)
                ( view as ReserveView).onFault(message)
            }

            override fun processValue(response: String?) {
                Log.i("reserves", response)

                val model = gson.fromJson<ReservationBean>(response, ReservationBean::class.java)

                ( view as ReserveView).onSuccessReserve(model, flag)
            }
        }
        )

    }
}