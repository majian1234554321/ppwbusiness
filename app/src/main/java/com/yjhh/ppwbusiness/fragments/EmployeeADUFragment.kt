package com.yjhh.ppwbusiness.fragments

import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.employeeadufragment.*

class EmployeeADUFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                mActivity.onBackPressed()
            }

            R.id.iv_delete -> {
            }
            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.employeeadufragment

    override fun initView() {

        val arrayView = arrayOf(iv_back, iv_delete)
        arrayView.forEach {
            it.setOnClickListener(this)
        }

    }

}