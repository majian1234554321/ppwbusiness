package com.yjhh.ppwbusiness.base


import com.google.gson.Gson

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

open class BasePresent {

    val gson = Gson()

    fun <T> toSubscribe(o: Observable<BaseResponse<T>>, s: Observer<BaseResponse<T>>) {
        o.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(2)//请求失败重连次数
            .subscribe(s)

    }


    fun  toSubscribe2(o: Observable<ResponseBody>, s: Observer<ResponseBody>) {
        o.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry(2)//请求失败重连次数
            .subscribe(s)

    }
}