package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import android.util.Log
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionOrderService
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.OrderBean
import com.yjhh.ppwbusiness.imodel.OrderModel
import com.yjhh.ppwbusiness.iview.OrderView
import java.lang.StringBuilder

class OrderPresent(var context: Context, var view: OrderView) : BasePresent() {

    val mode = OrderModel()

    fun orders(status: String, pageIndex: Int, pageSize: Int, flag: String) {
        toSubscribe2(mode.orders(status, pageIndex, pageSize), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("allorders", response)
                val model = gson.fromJson<OrderBean>(response, OrderBean::class.java)
                view.onSuccess(model, flag)

            }

            override fun onFault(message: String) {
                Log.i("allorders", message)
                view.onFault(message)
            }

        })
    }


    fun orderTask(flag: String) {
        toSubscribe2(ApiServices.getInstance().create(SectionOrderService::class.java)
            .orderTask(), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {

                var sb = StringBuilder()
                sb.append("{\"items\":").append(response).append("}")
                Log.i("orderTask", sb.toString())
                val model = gson.fromJson<OrderBean>(sb.toString(), OrderBean::class.java)
                view.onSuccess(model, flag)

            }

            override fun onFault(message: String) {
                Log.i("onFault", message)
            }

        })
    }


}