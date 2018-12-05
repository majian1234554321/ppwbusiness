package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ReserveService {

    @FormUrlEncoded
    @POST("shopAdminReserve/accept")
    fun acceptReserve(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @FormUrlEncoded
    @POST("shopAdminReserve/detail")
    fun reserveDetail(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @FormUrlEncoded
    @POST("shopAdminReserve")
    fun reserves(@FieldMap map: Map<String, String>): Observable<ResponseBody>//
}