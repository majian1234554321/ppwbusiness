package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import androidx.collection.ArrayMap
import com.yjhh.ppwbusiness.api.AboutService
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.iview.AboutView

class AboutPresent(var context: Context, var view: AboutView) : BasePresent() {
    val map = ArrayMap<String, String>()
    fun feedbackList(pageIndex: Int, pageSize: Int, flag: String) {
        map.clear()
        map.put("pageIndex", pageIndex.toString())
        map.put("pageSize", pageSize.toString())
        toSubscribe2(ApiServices.getInstance()
            .create(AboutService::class.java)
            .feedbackList(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                view.onSuccess(response, flag)
            }

            override fun onFault(message: String) {

            }

        }
        )

    }


    fun feedbackDetail(id: String?) {
        map.clear()
        map.put("id", id)

        toSubscribe2(ApiServices.getInstance()
            .create(AboutService::class.java)
            .feedbackDetail(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                view.onSuccess(response, id)
            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        }
        )

    }


}