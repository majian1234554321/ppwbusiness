package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import android.util.Log
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionShopAdminService
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.ShopAdminBean
import com.yjhh.ppwbusiness.iview.Main1View

class Main1Present(var context: Context, var main1: Main1View) : BasePresent() {
    fun shopAdminHome() {
        toSubscribe2(ApiServices.getInstance().create(SectionShopAdminService::class.java).shopAdminHome()
            , object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("ShopAdminPresent", response)
                    val bean = gson.fromJson<ShopAdminBean>(response, ShopAdminBean::class.java)

                    main1.onsuccessShopAdmin(bean)

                }

                override fun onFault(message: String) {
                    main1.onFault(message)
                }
            })
    }
}