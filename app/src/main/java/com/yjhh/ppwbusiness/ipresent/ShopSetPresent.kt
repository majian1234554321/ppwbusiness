package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import androidx.collection.ArrayMap

import android.util.Log
import android.widget.Toast
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ShopSetServices
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.AllShopInfo
import com.yjhh.ppwbusiness.bean.SubmitShopAdminConfigModel

import com.yjhh.ppwbusiness.iview.ShopSetView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShopSetPresent(var context: Context, var view: ShopSetView) : BasePresent() {
    val maps = androidx.collection.ArrayMap<String, String>()
    fun editConfig(map: SubmitShopAdminConfigModel) {

        toSubscribe2(ApiServices.getInstance()
            .create(ShopSetServices::class.java)
            .editConfig(map), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("ShopSetPresent", response)

                view.onSuccess()
               // Toast.makeText(context, "设置店铺信息成功", Toast.LENGTH_LONG).show()
            }

            override fun onFault(message: String) {
                Log.i("ShopSetPresent", message)
                view.onFault(message)
            }

        })


    }


    fun editOpen(status: String) {

        maps["status"] = status

        toSubscribe2(ApiServices.getInstance()
            .create(ShopSetServices::class.java)
            .editOpen(maps), object : ProcessObserver2(context, true) {
            override fun onFault(message: String) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }

            override fun processValue(response: String?) {
                view.onSuccess()
                // Toast.makeText(context, if (status == "0") "营业中" else "打烊", Toast.LENGTH_LONG).show()
            }

        }
        )

    }


    fun getAllInfo() {


        toSubscribe2(ApiServices.getInstance()
            .create(ShopSetServices::class.java)
            .getAllInfo(), object : ProcessObserver2(context) {
            override fun onFault(message: String) {
                Log.i("getAllInfo", message)
            }

            override fun processValue(response: String?) {
                Log.i("getAllInfo", response)

                val model = gson.fromJson<AllShopInfo>(response, AllShopInfo::class.java)

                view.AllShopInfoSuccess(model)

            }

        }
        )

    }


}

