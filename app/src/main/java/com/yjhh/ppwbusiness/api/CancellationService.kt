package com.yjhh.ppwbusiness.api

import com.yjhh.ppwbusiness.bean.SubmitReviewCouponModel
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


    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("shopAdminCoupon/review")
    fun review(@Body map: SubmitReviewCouponModel): Observable<ResponseBody>


    @GET("shopAdmin/allow")
    fun qCodeLogin(@Query("code") value: String): Observable<ResponseBody>


}