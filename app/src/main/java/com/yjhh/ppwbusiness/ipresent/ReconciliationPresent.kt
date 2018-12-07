package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ReconciliationService
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReconciliationPresent(var context: Context) : BasePresent() {
    fun index() {
        ApiServices.getInstance().create(ReconciliationService::class.java)
            .index()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("ReconciliationFragment", response)
                }

                override fun onFault(message: String) {
                    Log.i("ReconciliationFragment", message)
                }
            })
    }


    fun logs(type: String, pageIndex: Int, pageSize: Int, flag: String) {
        val map = ArrayMap<String, String>()
        map.put("type", type)//类型 null全部 1收入 2支出
        map.put("pageIndex", pageIndex.toString())
        map.put("pageSize", pageSize.toString())

        toSubscribe2(ApiServices.getInstance().create(ReconciliationService::class.java)
            .logs(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("ReconciliationFragment", response)
            }

            override fun onFault(message: String) {
                Log.i("ReconciliationFragment", message)
            }

        })
    }
}