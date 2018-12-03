package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface CancellationService {

    @FormUrlEncoded
    @POST("shopAdminCommon/qrCode")
    fun qrCode(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("shopAdminCoupon/history")
    fun history(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("shopAdminCoupon/detail")
    fun detail(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("shopAdminCoupon/shopList")
    fun shopList(@FieldMap map: Map<String, String>): Observable<ResponseBody>



   // shopAdminCoupon/review


}