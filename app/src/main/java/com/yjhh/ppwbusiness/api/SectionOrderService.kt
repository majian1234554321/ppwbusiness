package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionOrderService {


    @FormUrlEncoded
    @POST("shopAdminOrders")
    fun orders(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @POST("shopAdmin/orderTask")
    fun orderTask(): Observable<ResponseBody>//


}
