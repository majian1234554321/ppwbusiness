package com.yjhh.ppwbusiness.views.reconciliation

import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.bindlistfragment.*

class BindListFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iev1 -> {

            }

            R.id.iev2 ->{
                start(BindZBFragment.newInstance("ZBF"))
            }

            else -> {
                start(BindZBFragment.newInstance("YHK"))
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.bindlistfragment

    override fun initView() {
        arrayOf(iev1, iev2, iev3).forEach {
            it.setOnClickListener(this)
        }

        iev1.setTextContent("未授权")
        iev2.setTextContent("未授权")
        iev3.setTextContent("未授权")
    }

}