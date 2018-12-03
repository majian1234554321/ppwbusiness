package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.CancellationService
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.CancelationBeforeBean
import com.yjhh.ppwbusiness.iview.CancellationView
import java.lang.StringBuilder

class CancellationPresent(var context: Context, var view: CancellationView) : BasePresent() {

    val map = ArrayMap<String, String>()

    fun qrCode(code: String) {

        map.clear()
        map["code"] = code

        toSubscribe2(ApiServices.getInstance()
            .create(CancellationService::class.java)
            .qrCode(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("CancellationPresent", response)
            }

            override fun onFault(message: String) {
                Log.i("CancellationPresent", message)
            }

        })

    }


    fun history(pageIndex: Int, pageSize: Int, flag: String) {

        map.clear()
        map["pageIndex"] = pageIndex.toString()
        map["pageIndex"] = pageSize.toString()


        toSubscribe2(ApiServices.getInstance()
            .create(CancellationService::class.java)
            .history(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("CancellationPresent", response)



                view.onSuccessCancellation(response, flag)


            }

            override fun onFault(message: String) {
                Log.i("CancellationPresent", message)
            }

        })

    }


    fun detail(id: String?) {
        map.clear()
        map["id"] = id

        toSubscribe2(ApiServices.getInstance()
            .create(CancellationService::class.java)
            .detail(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("CancellationPresent", response)
                view.onSuccessCancellation(response, "")
            }

            override fun onFault(message: String) {
                Log.i("CancellationPresent", message)
            }

        })
    }


    fun shopList(id: String?) {
        map.clear()
        map["id"] = id


        toSubscribe2(ApiServices.getInstance()
            .create(CancellationService::class.java)
            .shopList(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {


                val sb = StringBuilder()
                sb.append("{\"item\":").append(response).append("}")

                Log.i("CancellationPresent", sb.toString())

                view.onSuccessCancellation(sb.toString(), "")
            }

            override fun onFault(message: String) {
                Log.i("CancellationPresent", message)
            }

        })

    }


}