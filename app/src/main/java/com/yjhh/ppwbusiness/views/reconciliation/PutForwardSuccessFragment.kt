package com.yjhh.ppwbusiness.views.reconciliation

import android.graphics.Color
import android.os.Bundle
import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.PutForwardSuccessBean
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import com.yjhh.ppwbusiness.utils.TimeUtil
import kotlinx.android.synthetic.main.putforwardsuccessfragment.*
import java.lang.StringBuilder

class PutForwardSuccessFragment : BaseFragment() {


    val stringList = ArrayList<String>()


    override fun getLayoutRes(): Int = R.layout.putforwardsuccessfragment

    override fun initView() {


        val jsonString = arguments?.getString("value")
        val model = Gson().fromJson<PutForwardSuccessBean>(jsonString, PutForwardSuccessBean::class.java)


        tv_1.text = model.statusText
        tv_2.text = TimeUtil.stampToDate(model.createdTime)
        tv_3.text = model.remark
        tv_4.text = model.moneText
        tv_5.text = model.bindTypeText
        tv_6.text = model.orderNo

        tv_tips.text = model.title
        tv_price.text = model.moneText


    }


    companion object {
        fun newInstance(value: String?): PutForwardSuccessFragment {
            val fragment = PutForwardSuccessFragment()
            val bundle = Bundle()

            bundle.putString("value", value)
            fragment.arguments = bundle
            return fragment
        }
    }


}