package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import androidx.collection.ArrayMap

import android.util.Log
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ProductService
import com.yjhh.ppwbusiness.api.ShopSetServices
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.ProductBean
import com.yjhh.ppwbusiness.iview.ProductView
import java.text.FieldPosition

class ProductPresent(var context: Context, var view: ProductView) : BasePresent() {
    val map = androidx.collection.ArrayMap<String, String>()

    fun allproducts(
        categoryId: String,
        order: String,
        desc: String,
        status: String,
        pageIndex: Int,
        pageSize: Int,
        flag: String
    ) {

        map.clear()
        map["categoryId"] = categoryId
        map["order"] = order
        map["desc"] = desc
       // map["desc"] = desc

        map["status"] = status
        map["pageIndex"] = pageIndex.toString()
        map["pageSize"] = pageSize.toString()



        toSubscribe2(ApiServices.getInstance().create(ProductService::class.java).allproducts(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {
                    Log.i("allproducts", response)
                    val model = gson.fromJson<ProductBean>(response, ProductBean::class.java)
                    view.onSuccess(model, flag)
                }

                override fun onFault(message: String) {
                    view.onFault(message)
                }

            })

    }


    fun delProduct(id: String,itemId:String , position: Int, flag: String) {
        map.clear()
        map["id"] = id
        map["itemId"] = itemId

        toSubscribe2(ApiServices.getInstance().create(ProductService::class.java).delProduct(map),
            object : ProcessObserver2(context) {
                override fun processValue(response: String?) {

                    val model = ProductBean()
                    model.position = position
                    view.onSuccess(model, flag)
                }

                override fun onFault(message: String) {
                    view.onFault(message)
                }

            })

    }


    fun editProductStatusByBatch(ids: String, status: String) {

        map.clear()
        map["ids"] = ids

        map["saleStatus"] = status//上架状态(0上架 1下架)

        toSubscribe2(
            ApiServices.getInstance()
                .create(ShopSetServices::class.java)
                .editProductStatusByBatch(map), object : ProcessObserver2(context, true) {
                override fun onFault(message: String) {

                }

                override fun processValue(response: String?) {

                }

            })
    }


    fun editSaleStatus(id: String, itemId:String,status: String, position: Int, flag: String) {

        map.clear()
        map["id"] = id
        map["itemId"] = itemId
        map["saleStatus"] = status//上架状态(0上架 1下架)

        toSubscribe2(
            ApiServices.getInstance()
                .create(ProductService::class.java)
                .editSaleStatus(map), object : ProcessObserver2(context, true) {
                override fun onFault(message: String) {
                    view.onFault(message)

                }

                override fun processValue(response: String?) {
                    Log.i("editSaleStatus", response)
                    val model = ProductBean()
                    model.position = position

                    view.onSuccess(model, flag)
                }

            })
    }


}