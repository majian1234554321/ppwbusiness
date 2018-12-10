package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import com.google.gson.Gson
import com.yjhh.ppwbusiness.api.ActivityCenterService
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.ActivityCenterBean
import com.yjhh.ppwbusiness.bean.ActivityCenterBean2
import com.yjhh.ppwbusiness.iview.ActivityCenterView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ActivityCenterPrenent(var context: Context, var view: ActivityCenterView) : BasePresent() {
    val map = ArrayMap<String, String>()


    fun ShopAdminActivity(pageIndex: Int, pageSize: Int, flag: String) {
        map.clear()
        map.put("pageIndex", pageIndex.toString())
        map.put("pageSize", pageSize.toString())

        ApiServices.getInstance()
            .create(ActivityCenterService::class.java)
            .ShopAdminActivity(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    view.onSuccessView(response, flag)


                }

                override fun onFault(message: String) {
                    Log.i("ActivityCenterFragment", message)
                    view.onFault(message)
                }

            })
    }
}