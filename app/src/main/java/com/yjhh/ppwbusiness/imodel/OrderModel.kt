package com.yjhh.ppwbusiness.imodel

import android.util.ArrayMap
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionOrderService
import io.reactivex.Observable
import okhttp3.ResponseBody

class OrderModel {

    val map = ArrayMap<String, String>()
    fun orders(status: String, pageIndex: Int, pageSize: Int): Observable<ResponseBody> {
        map.clear()
        map["status"] = status
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()

        return ApiServices.getInstance().create(SectionOrderService::class.java).orders(map)

    }
}