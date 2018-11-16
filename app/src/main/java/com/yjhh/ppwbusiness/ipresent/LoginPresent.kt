package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.imodel.LoginModel
import com.yjhh.ppwbusiness.iview.LoginView

import org.json.JSONObject


class LoginPresent(var context: Context, var loginView: LoginView) : BasePresent() {
    private var loginModel: LoginModel = LoginModel()



    fun login(username: String, password: String, identity: String) {

        toSubscribe2(loginModel.login(username, password, identity), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                    loginView.onSuccess(response)
            }

            override fun onFault(message: String) {
                loginView.onFault(message)
            }

        })


    }


}