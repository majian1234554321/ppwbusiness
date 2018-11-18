package com.yjhh.ppwbusiness.api


import com.yjhh.ppwbusiness.base.BaseResponse
import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface LoginService {



    @FormUrlEncoded
    @POST("common/sendSms")
    fun sendSms(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("login/fromAccount")
    fun login(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("login/fromSms")
    fun fromSms(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("register/regByAccount")
    fun regByAccount(@FieldMap map: Map<String, String>): Observable<ResponseBody>


}