package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface WithDrawService {

    @POST("shopAdminAccount")
    fun index(): Observable<ResponseBody>//

    @POST("shopAdminWithdraw")
    fun shopAdminWithdraw(): Observable<ResponseBody>//


    @FormUrlEncoded
    @POST("shopAdminWithdraw/apply")
    fun apply(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("shopAdminWithdraw/logs")
    fun logs(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录


}