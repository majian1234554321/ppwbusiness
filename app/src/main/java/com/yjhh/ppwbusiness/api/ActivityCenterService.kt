package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ActivityCenterService {


    @FormUrlEncoded
    @POST("ShopAdminActivity")
    fun ShopAdminActivity(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录


    @FormUrlEncoded
    @POST("ShopAdminActivity/detail")
    fun detail(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录
}
