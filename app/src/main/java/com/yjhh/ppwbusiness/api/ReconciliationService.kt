package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ReconciliationService {


    @POST("shopAdminAccount/index")
    fun index(): Observable<ResponseBody>//




    @FormUrlEncoded
    @POST("shopAdminAccount/logs")
    fun logs(@FieldMap map: Map<String, String>): Observable<ResponseBody>//
}