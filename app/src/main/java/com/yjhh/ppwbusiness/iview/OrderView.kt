package com.yjhh.ppwbusiness.iview

import com.yjhh.ppwbusiness.base.BaseView
import com.yjhh.ppwbusiness.bean.OrderBean

interface OrderView : BaseView {
    fun onSuccess(model:OrderBean,flag:String)
}