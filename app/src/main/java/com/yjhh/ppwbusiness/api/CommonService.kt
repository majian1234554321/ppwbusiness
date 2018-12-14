package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface CommonService {


    @Multipart
    @POST("common/upload")
    fun uploadFile(@Part file: MultipartBody.Part): Observable<ResponseBody>// 文件上传


    @Multipart
    @POST("common/upload")
    fun uploadFiles(@Part() list: List<MultipartBody.Part>): Observable<ResponseBody>// 文件上传


    @POST("common/version")
    fun version(): Observable<ResponseBody>// 文件上传

    @POST("shopAdminCommon/init")
    fun init(): Observable<ResponseBody>//


}