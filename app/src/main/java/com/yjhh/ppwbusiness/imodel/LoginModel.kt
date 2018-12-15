package com.yjhh.ppwbusiness.imodel

import androidx.collection.ArrayMap
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.LoginService
import com.yjhh.ppwbusiness.base.BaseResponse


import io.reactivex.Observable
import okhttp3.ResponseBody

class LoginModel {


    fun login(username: String, password: String): Observable<ResponseBody> {

        val map = androidx.collection.ArrayMap<String, String>()
        with(map) {
            put("account", username)
            put("password", password)

        }

        return ApiServices.getInstance().create(LoginService::class.java).login(map)
    }


}