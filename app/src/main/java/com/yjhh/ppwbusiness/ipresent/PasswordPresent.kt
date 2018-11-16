package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import android.util.Log

import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.imodel.PasswordModel
import com.yjhh.ppwbusiness.iview.PasswordView

class PasswordPresent(var context: Context, var registView: PasswordView) : BasePresent() {

    private val regByAccountModel = PasswordModel()


    fun sendSms(type: String, phone: String) {

        toSubscribe2(
            regByAccountModel.sendSms(type, phone)
            ,object :ProcessObserver2(context){

            override fun processValue(response: String?) {
                registView.onSuccessSMS(response)
            }

            override fun onFault(message: String) {
                registView.onFaultSMS(message)
            }

        })
    }




    fun regByAccount(phone: String, password: String, smsCode: String, identity: String, refId: String) {
        toSubscribe2(
            regByAccountModel.regByAccount2(phone, password, smsCode, identity, refId),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    registView.onSuccess(response)
                }

                override fun onFault(message: String) {
                    registView.onFault(message)
                }

            })
    }



    fun forgotPassword(phone: String?, password: String?, smsCode: String?) {
        toSubscribe2(regByAccountModel.forgotPassword(phone, password, smsCode), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("forgotPassword", response)
                registView.onSuccess(response)

            }

            override fun onFault(message: String) {
                registView.onFault(message)
            }
        })
    }


}