package com.yjhh.ppwbusiness.views.main

import android.os.Bundle

import android.util.Log


import com.google.gson.Gson

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.bean.VersionBean
import com.yjhh.ppwbusiness.fragments.MainFragment


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

import java.io.File
import com.azhon.appupdate.listener.OnDownloadListener
import com.yjhh.ppwbusiness.ipresent.CommonPresent
import com.yjhh.ppwbusiness.iview.CommonView
import com.yjhh.ppwbusiness.utils.APKVersionCodeUtils
import com.yjhh.ppwbusiness.views.cui.AppUpdateFragment
import io.reactivex.Observable
import java.lang.Exception


class MainActivity : BaseActivity(), CommonView {

    var dialog: AppUpdateFragment? = null


    internal var onDownloadListener: OnDownloadListener = object : OnDownloadListener {
        override fun start() {

        }

        override fun downloading(max: Int, progress: Int) {
            Log.i("MainActivity", "下载进度${progress / max.toDouble()}%${Thread.currentThread().name}")
            val dis = Observable.create<String> {
                it.onNext(
                    "下载进度 ${getString(
                        R.string.rmb_price_double,
                        progress / max.toDouble() * 100
                    )}%"
                )

            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dialog?.setTitle(it)
                }
        }

        override fun done(apk: File) {
            Log.i("MainActivity", "下载完成")

            val dis = Observable.create<String> {
                it.onNext("下载完成")
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dialog?.setTitle(it)
                }

        }

        override fun error(e: Exception) {

        }
    }


    override fun onSuccess(value: String?) {
        val model = Gson().fromJson<VersionBean>(value, VersionBean::class.java)

        dialog = if (model.ifCover == 1) {//是否强制覆盖(0否 1是)
            AppUpdateFragment(true)
        } else {
            AppUpdateFragment(false)
        }

        dialog?.show(supportFragmentManager, "TAG")

        dialog?.setOnAppUpdate(object : AppUpdateFragment.AppUpdateListener {
            override fun onAppUpdate() {
                APKVersionCodeUtils.startUpdate(this@MainActivity, onDownloadListener)
            }

        })

    }

    override fun onFault(errorMsg: String?) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findFragment(MainFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance())
        }

        CommonPresent(this, this).checkVersion()

    }


    override fun onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport()
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        // 设置横向(和安卓4.x动画相同)
        return DefaultHorizontalAnimator()
    }

    override fun onDestroy() {
        super.onDestroy()

    }


}
