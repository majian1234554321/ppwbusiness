package com.yjhh.ppwbusiness.imodel

import android.support.v4.util.ArrayMap

import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUselessService
import io.reactivex.Observable
import okhttp3.ResponseBody

class SectionUselessModel {

    val map = ArrayMap<String, String>()


    fun userhistory(type: String, pageIndex: Int, pageSize: Int): Observable<ResponseBody> {
        map.clear()
        map["type"] = type
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()
        return ApiServices.getInstance().create(SectionUselessService::class.java).userhistory(map)
    }


    fun usermessage(type: String, share: String, pageIndex: Int, pageSize: Int): Observable<ResponseBody> {
        map.clear()
        map["type"] = type
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()
        return ApiServices.getInstance().create(SectionUselessService::class.java).usermessage(map)
    }


    fun usercollect(type: String, itemType: String, pageIndex: Int, pageSize: Int): Observable<ResponseBody> {
        map.clear()
        map["type"] = type
        map["itemType"] = itemType
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()
        return ApiServices.getInstance().create(SectionUselessService::class.java).usercollect(map)
    }


    fun useraccount(type: String, pageIndex: Int, pageSize: Int): Observable<ResponseBody> {
        map.clear()
        map["type"] = type
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()
        return ApiServices.getInstance().create(SectionUselessService::class.java).useraccount(map)
    }


    fun useraccountindex(): Observable<ResponseBody> {

        return ApiServices.getInstance().create(SectionUselessService::class.java).useraccountindex()
    }


}