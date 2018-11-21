package com.yjhh.ppwbusiness.iview

import com.yjhh.ppwbusiness.bean.MyMessageBean

interface CommonView{
    fun onSuccess(value: String?)

    fun onFault(errorMsg: String?)
}
