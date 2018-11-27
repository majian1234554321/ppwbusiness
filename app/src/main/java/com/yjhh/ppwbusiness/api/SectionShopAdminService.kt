package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionShopAdminService {


    @POST("shopAdmin/home")
    fun shopAdminHome(): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("shopAdmin")
    fun shopAdmin(@FieldMap map: Map<String, String>): Observable<ResponseBody>//



//    @FormUrlEncoded
//    @POST("shopAdmin")
//    fun shopAdmin(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

}
