package com.yjhh.ppwbusiness.iview

interface LoginView {
    abstract fun onFault(errorMsg: String?)

    abstract fun onSuccess(result: String?)
}