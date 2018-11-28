package com.yjhh.ppwbusiness.views.main

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils

import android.util.Log
import android.view.View
import com.azhon.appupdate.manager.DownloadManager


import com.google.gson.Gson

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.CommonService
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.VersionBean
import com.yjhh.ppwbusiness.fragments.MainFragment
import com.yjhh.ppwbusiness.utils.HProgressDialogUtils


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

import java.io.File
import com.azhon.appupdate.config.UpdateConfiguration
import com.azhon.appupdate.listener.OnButtonClickListener
import com.azhon.appupdate.listener.OnDownloadListener
import java.lang.Exception


class MainActivity : BaseActivity(), OnButtonClickListener, OnDownloadListener {
    override fun start() {

    }

    override fun downloading(max: Int, progress: Int) {

    }

    override fun done(apk: File?) {

    }

    override fun error(e: Exception?) {

    }

    override fun onButtonClick(id: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findFragment(MainFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance())
        }





        ApiServices.getInstance()
            .create(CommonService::class.java)
            .version()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(this) {
                override fun processValue(response: String?) {
                    Log.i("MainActivity", response)

                    val model = Gson().fromJson<VersionBean>(response, VersionBean::class.java)

                    if (model.ifCover == 1) {//是否强制覆盖(0否 1是)
                        startUpdate3()
                    } else {
                        //startUpdate3()
                    }


                }

                override fun onFault(message: String) {
                    Log.i("MainActivity", message)

                }

            })


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


    private fun startUpdate3() {
        /*
         * 整个库允许配置的内容
         * 非必选
         */
        val configuration = UpdateConfiguration()
            //输出错误日志
            .setEnableLog(true)
            //设置自定义的下载
            //.setHttpManager()
            //下载完成自动跳动安装页面
            .setJumpInstallPage(true)
            //设置对话框背景图片 (图片规范参照demo中的示例图)
            //.setDialogImage(R.drawable.ic_dialog)
            //设置按钮的颜色
            .setDialogButtonColor(Color.parseColor("#E743DA"))
            //设置按钮的文字颜色
            .setDialogButtonTextColor(Color.WHITE)
            //支持断点下载
            .setBreakpointDownload(true)
            //设置是否显示通知栏进度
            .setShowNotification(true)
            //设置强制更新
            .setForcedUpgrade(true)
            //设置对话框按钮的点击监听
            .setButtonClickListener(this)
            //设置下载过程的监听
            .setOnDownloadListener(this)

        val manager = DownloadManager.getInstance(this)
        manager.setApkName("appupdate.apk")
            .setApkUrl("http://test-1251233192.coscd.myqcloud.com/1_1.apk")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setShowNewerToast(true)
            .setConfiguration(configuration)
            //                .setDownloadPath(Environment.getExternalStorageDirectory() + "/AppUpdate")
            //.setApkVersionCode(2)
            .setApkVersionName("2.1.8")
            .setApkSize("20.4")
            .setAuthorities(packageName)
            .setApkDescription("1.支持断点下载\n2.支持Android N\n3.支持Android O\n4.支持自定义下载过程\n5.支持 设备>=Android M 动态权限的申请\n6.支持通知栏进度条展示(或者自定义显示进度)")
            .download()
    }


}
