package com.yjhh.ppwbusiness.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.baidu.location.g.j.s
import com.google.gson.Gson
import com.yjhh.ppwbusiness.utils.LogUtils
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import com.yjhh.ppwbusiness.views.login.LoginActivity

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import org.json.JSONObject


abstract class ProcessObserver2(var context: Context) : Observer<ResponseBody> {
    private var showProgress: Boolean = true


    object constructor {
        val gson = Gson()
    }


    // private var progressDialog: WaitProgressDialog = WaitProgressDialog(context)
    override fun onSubscribe(d: Disposable) {

    }

    constructor(context: Context, showProgress: Boolean) : this(context) {
        this.showProgress = showProgress
    }

    override fun onNext(t: ResponseBody) {


        val response = t.string()


        LogUtils.i("ProcessObserver2", response)
        if (!TextUtils.isEmpty(response) && response.contains("success")) {
            val jsonValue = JSONObject(response)
            if (jsonValue.getBoolean("success")) {
                val jsonString = jsonValue.getString("data")
                processValue(jsonString)
            } else {




                if ("01001" == jsonValue.optString("code")) {
                    Toast.makeText(context, "请重新登录", Toast.LENGTH_SHORT).show()


                    SharedPreferencesUtils.setParam(context, "mobile", "")
                    SharedPreferencesUtils.setParam(context, "nickName", "")
                    SharedPreferencesUtils.setParam(context, "sessionId", "")
                    SharedPreferencesUtils.setParam(context, "type", "")

                    context.startActivity(Intent(context, LoginActivity::class.java))
                    (context as Activity).finish()
                } else {
                    onFault(jsonValue.getString("message"))
                }
            }


        } else {
            onFault(response)
        }


    }

    abstract fun processValue(response: String?)

    abstract fun onFault(message: String)

    override fun onError(e: Throwable) {
        onFault(e.toString())
    }

    override fun onComplete() {

    }


    private fun showProgressDialog() {
        if (showProgress) {
        }
        //   progressDialog.show()
    }

    private fun dismissProgressDialog() {
        if (showProgress) {

        }
        // progressDialog.dismiss()
    }

}
