package com.yjhh.ppwbusiness.iview

import com.yjhh.ppwbusiness.base.BaseView

interface CancellationView : BaseView {

    fun onSuccessCancellation(response: String?,flag:String?)

}