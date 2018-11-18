package com.yjhh.ppwbusiness.iview

import com.yjhh.ppwbusiness.bean.MyMessageBean

interface SendSMSView{
    fun onSuccessSMS(value: String?)

    fun onFaultSMS(errorMsg: String?)
}
