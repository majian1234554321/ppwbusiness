package com.yjhh.ppwbusiness.iview

import com.yjhh.ppwbusiness.bean.MyMessageBean



interface  MyMessageView {
    fun onSuccess(main1bean: MyMessageBean, flag: String)

     fun onFault(errorMsg: String?)
}
