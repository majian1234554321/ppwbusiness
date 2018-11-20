package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionEvluateService {


    @POST("shopAdmin")
    fun shopAdmin(): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("shopAdmin/comments")
    fun allcomments(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @FormUrlEncoded
    @POST("shopAdmin/comment")
    fun comment(@FieldMap map: Map<String, String>): Observable<ResponseBody>//



}
