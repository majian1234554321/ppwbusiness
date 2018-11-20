package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.OrderBean
import com.yjhh.ppwbusiness.imodel.OrderModel
import com.yjhh.ppwbusiness.iview.OrderView

class OrderPresent(var context: Context, var view: OrderView) : BasePresent() {

    val mode = OrderModel()

    fun orders(status: String, pageIndex: Int, pageSize: Int, flag: String) {
        toSubscribe2(mode.orders(status, pageIndex, pageSize), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {

                val model = gson.fromJson<OrderBean>(response, OrderBean::class.java)
                view.onSuccess(model,flag)

            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        })
    }

}