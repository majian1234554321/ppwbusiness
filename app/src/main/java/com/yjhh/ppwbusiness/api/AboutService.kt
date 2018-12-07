package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface AboutService {


    @Multipart
    @POST("common/upload")
    fun uploadFile(@Part file: MultipartBody.Part): Observable<ResponseBody>// 文件上传


    @FormUrlEncoded
    @POST("about/feedbackList")
    fun feedbackList(@FieldMap map: Map<String, String>): Observable<ResponseBody>// 资金记录/积分记录


}