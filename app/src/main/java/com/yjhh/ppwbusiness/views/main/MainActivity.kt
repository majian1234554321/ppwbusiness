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


class MainActivity : BaseActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findFragment(MainFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance())
        }

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
