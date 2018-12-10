package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import android.util.Log
import androidx.collection.ArrayMap
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ReconciliationService
import com.yjhh.ppwbusiness.api.WithDrawService
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.iview.WithDrowView
import com.yjhh.ppwbusiness.views.reconciliation.ReconciliationFragment

class WithDrawPresent (
    var context: Context,
   var view : WithDrowView
):BasePresent() {
    var map = ArrayMap<String,String>()
    fun shopAdminWithdraw(){
        toSubscribe2(ApiServices.getInstance().create(WithDrawService::class.java)
            .shopAdminWithdraw(),object :ProcessObserver2(context){
            override fun processValue(response: String?) {
                Log.i("shopAdminWithdraw",response)
                view.onSuccessView(response,"")
            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        })
    }



    fun shopAdminWithdrawIndex(){
        toSubscribe2(ApiServices.getInstance().create(WithDrawService::class.java)
            .index(),object :ProcessObserver2(context){
            override fun processValue(response: String?) {
                Log.i("shopAdminWithdraw",response)
                view.onSuccessView(response,"")
            }

            override fun onFault(message: String) {
                view.onFault(message)
            }

        })
    }



    fun shopAdminWithdrawLogs(flag: String, pageIndex: Int, pageSize: Int, flag2: String) {
        val map = ArrayMap<String, String>()
        map.put("flag", flag)//类型 null全部 1收入 2支出
        map.put("pageIndex", pageIndex.toString())
        map.put("pageSize", pageSize.toString())

        toSubscribe2(ApiServices.getInstance().create(ReconciliationService::class.java)
            .logs(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("ReconciliationFragment", response)

                view.onSuccessView(response,flag2)

            }

            override fun onFault(message: String) {
                Log.i("ReconciliationFragment", message)
                view.onFault(message)
            }

        })
    }




    fun apply(money:String?,bindId:String?,flag:String){
        map.clear()
        map["money"] = money
        map["bindId"] = bindId

        toSubscribe2(ApiServices.getInstance().create(WithDrawService::class.java)
            .apply(map),object :ProcessObserver2(context){
            override fun processValue(response: String?) {
                Log.i("shopAdminWithdrawapply",response)
                view.onSuccessView(response,flag)
            }

            override fun onFault(message: String) {
                Log.i("shopAdminWithdrawapply",message)
                view.onFault(message)
            }

        })
    }


    fun logs(pageIndex:Int,pageSize:Int,flag: String){
        map.clear()
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()

        toSubscribe2(ApiServices.getInstance().create(WithDrawService::class.java)
            .logs(map),object :ProcessObserver2(context){
            override fun processValue(response: String?) {
                view.onSuccessView(response,flag)
                Log.i("shopAdminWithdrawsssss",response)
            }

            override fun onFault(message: String) {
                Log.i("shopAdminWithdrawsssss",message)
                view.onFault(message)
            }

        })
    }
}