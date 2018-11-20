package com.yjhh.ppwbusiness.ipresent

import android.content.Context
import android.util.Log
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.CommonService
import com.yjhh.ppwbusiness.base.BasePresent
import com.yjhh.ppwbusiness.base.ProcessObserver2
import io.reactivex.disposables.Disposable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class CommonPresent(var context: Context) : BasePresent() {


    fun UpLoadFile(file: File) {

        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body = MultipartBody.Part.createFormData("multipartFile", file.name, requestFile)


        toSubscribe2(ApiServices.getInstance().create(CommonService::class.java)
            .uploadFile(body), object : ProcessObserver2(context, true) {
            override fun processValue(response: String?) {
                Log.i("UpLoadFile",response)
            }

            override fun onFault(message: String) {
                Log.i("UpLoadFile",message)
            }
        }
        )


    }

}