package com.yjhh.ppwbusiness.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SectionUserService {

    @FormUrlEncoded
    @POST("user/cards")
    fun cards(@FieldMap map: Map<String, String>): Observable<ResponseBody>//用户卡片信息


    @FormUrlEncoded
    @POST("user/resetPassword")
    fun resetPassword(@FieldMap map: Map<String, String>): Observable<ResponseBody>//重置密码


    @FormUrlEncoded
    @POST("useraddress/editByLocation")
    fun editByLocation(@FieldMap map: Map<String, String>): Observable<ResponseBody>//新增用户地址


    @FormUrlEncoded
    @POST("useraddress/delete")
    fun deleteUseraddress(@FieldMap map: Map<String, String>): Observable<ResponseBody>//删除用户地址


    @FormUrlEncoded
    @POST("useraddress/list")
    fun getAllUserAddress(@FieldMap map: Map<String, String>): Observable<ResponseBody>//获取所有的用户地址

    @FormUrlEncoded
    @POST("useraddress/setDefault")
    fun setDefaultUseraddress(@FieldMap map: Map<String, String>): Observable<ResponseBody>//设置默认地址


    @FormUrlEncoded
    @POST("useraddress/detail")
    fun getUseraddressDetail(@FieldMap map: Map<String, String>): Observable<ResponseBody>//获取用户地址详情


    @POST("shopAdminUser/mgr")
    fun mgr(): Observable<ResponseBody>//设置用户头像

    @FormUrlEncoded
    @POST("shopAdminUser/del")
    fun del(@FieldMap map: Map<String, String>): Observable<ResponseBody>//获取用户信息


    @FormUrlEncoded
    @POST("user/forgotPassword")
    fun forgotPassword(@FieldMap map: Map<String, String>): Observable<ResponseBody>//忘记密码


    @FormUrlEncoded
    @POST("shopAdmin/editUserName")
    fun editUserName(@FieldMap map: Map<String, String>): Observable<ResponseBody>//设置用户昵称

    @FormUrlEncoded
    @POST("shopAdminUser/save")
    fun save(@FieldMap map: Map<String, String>): Observable<ResponseBody>


    @FormUrlEncoded
    @POST("shopAdminUser")
    fun shopAdminUser(@FieldMap map: Map<String, String>): Observable<ResponseBody>


}