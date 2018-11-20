package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CommonService {




    @Multipart
    @POST("common/upload")
    fun uploadFile( @Part file: MultipartBody.Part): Observable<ResponseBody>// 文件上传
}