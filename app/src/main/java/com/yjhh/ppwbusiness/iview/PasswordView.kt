package com.yjhh.ppwbusiness.iview

interface PasswordView :SendSMSView {
    fun onSuccess(value: String?)

    fun onFault(errorMsg: String?)
}