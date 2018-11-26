package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import android.util.Log
import anet.channel.util.Utils
import com.azhon.appupdate.utils.LogUtil
import com.google.gson.Gson
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.CommonService
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.iview.CommonView
import io.reactivex.disposables.Disposable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.lang.StringBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject


class CommonPresent(var context: Context, var view: CommonView) : BasePresent() {


    fun UpLoadFile(file: File) {

        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body = MultipartBody.Part.createFormData("multipartFile", file.name, requestFile)


        toSubscribe2(ApiServices.getInstance().create(CommonService::class.java)
            .uploadFile(body), object : ProcessObserver2(context, true) {
            override fun processValue(response: String?) {

                var sb = StringBuilder()
                sb.append("{\"item\":").append(response).append("}")
                Log.i("UpLoadFile", sb.toString())
                view.onSuccess(sb.toString())
            }

            override fun onFault(message: String) {
                Log.i("UpLoadFile", message)
                view.onFault(message)
            }
        }
        )


    }


    fun UpLoadFiles(files: List<File>) {


        val list = ArrayList<MultipartBody.Part>()


        files.forEach {
            var requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), it)
            var body = MultipartBody.Part.createFormData("multipartFile", it.name, requestFile)
            list.add(body)
        }


        toSubscribe2(ApiServices.getInstance().create(CommonService::class.java)
            .uploadFiles(list), object : ProcessObserver2(context, true) {
            override fun processValue(response: String?) {

                var sb = StringBuilder()
                sb.append("{\"item\":").append(response).append("}")
                Log.i("UpLoadFile", sb.toString())
                view.onSuccess(sb.toString())
            }

            override fun onFault(message: String) {
                Log.i("UpLoadFile", message)
                view.onFault(message)
            }
        }
        )


    }


}