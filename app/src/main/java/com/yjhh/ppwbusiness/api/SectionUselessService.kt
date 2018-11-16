package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionUselessService {

    @FormUrlEncoded
    @POST("userhistory")
    fun userhistory(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @FormUrlEncoded
    @POST("usermessage")
    fun usermessage(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @FormUrlEncoded
    @POST("usercollect")
    fun usercollect(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 我的收藏



    @FormUrlEncoded
    @POST("useraccount/logs")
    fun useraccount(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录



    @POST("useraccount")
    fun useraccountindex(): Observable<ResponseBody>// 我的余额/积分



}
