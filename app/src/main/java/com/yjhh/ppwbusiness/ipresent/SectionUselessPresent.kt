package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.base.ProcessObserver2.constructor.gson
import com.yjhh.ppwbusiness.bean.MyMessageBean
import com.yjhh.ppwbusiness.imodel.SectionUselessModel
import com.yjhh.ppwbusiness.iview.MyMessageView

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import java.util.function.Function
import kotlin.collections.ArrayList

class SectionUselessPresent(var context: Context) : BasePresent() {


    lateinit var myMessageView: MyMessageView






    constructor(context: Context, myMessageView: MyMessageView) : this(context) {
        this.myMessageView = myMessageView
    }


    val model = SectionUselessModel()







    fun usermessage(status: String, share: String, pageIndex: Int, pageSize: Int, flag: String) {

        toSubscribe2(model.usermessage(status, share, pageIndex, pageSize), object : ProcessObserver2(context) {
            override fun processValue(response: String?) {
                Log.i("usermessage", response)

                val recentlyBrowseBean = gson.fromJson<MyMessageBean>(response, MyMessageBean::class.java)

                myMessageView.onSuccess(recentlyBrowseBean, flag)
            }

            override fun onFault(message: String) {
                Log.i("usermessage", message)
                myMessageView.onFault(message)
            }

        })
    }


}