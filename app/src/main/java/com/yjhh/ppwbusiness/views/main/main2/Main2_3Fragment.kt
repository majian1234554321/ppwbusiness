package com.yjhh.ppwbusiness.views.main.main2


import android.os.Bundle
import android.util.Log
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment


class Main2_3Fragment : BaseFragment()  {
    override fun getLayoutRes(): Int   = R.layout.main2_1fragment

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        Log.i("Main2Fragment","Main2_3Fragment")
    }
}