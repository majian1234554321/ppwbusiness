package com.yjhh.ppwbusiness.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.tv_details
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.messagedetailfragment.*
import me.yokeyword.fragmentation.SupportFragment

class MessageDetailFragment: BaseFragment(){
    override fun getLayoutRes(): Int = R.layout.messagedetailfragment

    override fun initView() {
        tv_details.text = "111"
    }






}