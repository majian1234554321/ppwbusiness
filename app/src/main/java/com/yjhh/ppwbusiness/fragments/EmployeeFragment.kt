package com.yjhh.ppwbusiness.fragments

import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment

import kotlinx.android.synthetic.main.employeefragment.*

class EmployeeFragment : BaseFragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                mActivity.onBackPressed()
            }

            R.id.iv_add -> {
                start(
                    EmployeeADUFragment()
                )
            }
            else -> {

            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.employeefragment

    override fun initView() {
        val arrayView = arrayOf(iv_back, iv_add)
        arrayView.forEach {
            it.setOnClickListener(this)
        }
    }

}