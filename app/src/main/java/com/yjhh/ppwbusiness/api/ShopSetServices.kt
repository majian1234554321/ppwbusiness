package com.yjhh.ppwbusiness.api

import android.support.v4.util.ArrayMap
import com.yjhh.ppwbusiness.bean.SETime
import com.yjhh.ppwbusiness.bean.SubmitShopAdminConfigModel
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*


interface ShopSetServices {
    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("shopAdmin/editTimes")
    fun editTimes(@Body a: Array<SETime>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("shopAdmin/editOpen")
    fun editOpen(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("shopAdmin/editConfig")
    fun editConfig(@Body map: ArrayMap<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("shopAdmin/editProductStatusByBatch")
    fun editProductStatusByBatch(@FieldMap map: Map<String, String>): Observable<ResponseBody>





}