package com.yjhh.ppwbusiness.iview

import com.yjhh.ppwbusiness.base.BaseView
import com.yjhh.ppwbusiness.bean.ProductBean

interface ProductView : BaseView {
    fun onSuccess(result: ProductBean?,flag: String)

}
