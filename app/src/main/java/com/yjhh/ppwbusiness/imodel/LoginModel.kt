package com.yjhh.ppwbusiness.imodel

import android.support.v4.util.ArrayMap
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.LoginService
import com.yjhh.ppwbusiness.base.BaseResponse


import io.reactivex.Observable
import okhttp3.ResponseBody

class LoginModel {


    fun login(username: String, password: String, identity: String): Observable<ResponseBody> {

        val map = ArrayMap<String, String>()
        with(map) {
            put("account", username)
            put("password", password)
            put("identity", identity)
        }

        return ApiServices.getInstance().create(LoginService::class.java).login(map)
    }


}