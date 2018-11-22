package com.yjhh.ppwbusiness.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.layout.item

import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.MyMessageBean
import com.yjhh.ppwbusiness.bean.ProductBean
import com.yjhh.ppwbusiness.utils.TimeUtil
import com.yjhh.ppwbusiness.views.product.ProductAddFragment
import kotlinx.android.synthetic.main.messagedetailfragment.*


class MessageDetailFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.messagedetailfragment

    override fun initView() {


        val objectValue = arguments?.getSerializable("objectValue") as MyMessageBean.ItemsBean


        tv1?.text = objectValue?.title
        tv2?.text = objectValue?.content

        tv_time?.text = TimeUtil.stampToDate(objectValue?.sendTime.toString())

    }


    companion object {

        fun newInstance(objectValue: MyMessageBean.ItemsBean): MessageDetailFragment {
            val fragment = MessageDetailFragment()
            val bundle = Bundle()

            bundle.putSerializable("objectValue", objectValue)
            fragment.arguments = bundle
            return fragment
        }


    }


}