package com.yjhh.ppwbusiness.iview

import com.yjhh.ppwbusiness.base.BaseView
import com.yjhh.ppwbusiness.bean.AllShopInfo

interface ShopSetView : BaseView {
    fun onSuccess()
    fun onSuccess(statues:String?)

    fun AllShopInfoSuccess(model: AllShopInfo)

}