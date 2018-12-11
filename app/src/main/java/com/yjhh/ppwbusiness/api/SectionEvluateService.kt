package com.yjhh.ppwbusiness.api

import com.yjhh.ppwbusiness.bean.SubmitShopReplyCommentModel
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface SectionEvluateService {


    @POST("shopAdmin")
    fun shopAdmin(): Observable<ResponseBody>//

    @FormUrlEncoded
    @POST("shopAdminComment")
    fun allcomments(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @FormUrlEncoded
    @POST("shopAdminComment/detail")
    fun comment(@FieldMap map: Map<String, String>): Observable<ResponseBody>//


    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("shopAdminComment/reply")
    fun reply(@Body map: SubmitShopReplyCommentModel): Observable<ResponseBody>//



    @POST("shopAdminComment/nav")
    fun nav(): Observable<ResponseBody>//
}
