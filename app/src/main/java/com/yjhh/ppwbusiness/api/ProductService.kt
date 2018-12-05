package com.yjhh.ppwbusiness.api

import androidx.collection.ArrayMap
import com.yjhh.ppwbusiness.bean.SETime
import com.yjhh.ppwbusiness.bean.SubmitProductInfoModel
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface ProductService {


    @Headers("Content-Type: application/json", "Accept: application/json")//需要添加头
    @POST("shopAdminProduct/save")
    fun editProducts(@Body a : SubmitProductInfoModel ): Observable<ResponseBody>









    @FormUrlEncoded
    @POST("shopAdminProduct")
    fun allproducts(@FieldMap map: Map<String, String>): Observable<ResponseBody>//





    @FormUrlEncoded
    @POST("shopAdminProduct/del")
    fun delProduct(@FieldMap map: Map<String, String>): Observable<ResponseBody>//




    @FormUrlEncoded
    @POST("shopAdminProduct/editStatus")
    fun editSaleStatus(@FieldMap map: androidx.collection.ArrayMap<String, String>): Observable<ResponseBody>




}