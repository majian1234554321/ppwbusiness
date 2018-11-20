package com.yjhh.ppwbusiness.api

import com.yjhh.ppwbusiness.bean.SETime
import com.yjhh.ppwbusiness.bean.SubmitProductInfoModel
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface ProductService {


    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("shopAdmin/editProducts")
    fun editProducts(@Body a : Array<SubmitProductInfoModel> ): Observable<ResponseBody>





    @FormUrlEncoded
    @POST("shopAdmin/delProduct")
    fun delProduct(@FieldMap map: Map<String, String>): Observable<ResponseBody>//

}