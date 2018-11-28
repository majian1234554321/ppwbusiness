package com.yjhh.ppwbusiness.fragments

import android.annotation.SuppressLint
import android.support.v4.view.PagerAdapter
import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.photofragment.*

import com.yjhh.ppwbusiness.views.cui.GlideLoader


@SuppressLint("ValidFragment")
class PhotoFragment(var url: String) : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.photofragment

    override fun initView() {


        val array = ArrayList<String>()
        array.add(url)

        banner!!.setImages(array)
            .setImageLoader(GlideLoader())
            .setDelayTime(10000)
            .start()
    }


}