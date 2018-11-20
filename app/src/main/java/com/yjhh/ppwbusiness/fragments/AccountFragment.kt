package com.yjhh.ppwbusiness.fragments

import android.content.Intent
import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.views.login.LoginActivity
import kotlinx.android.synthetic.main.accountfragment.*

class AccountFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_loginOut -> {
                loginOut()
                //tv_name.text = "未登录"
                startActivity(Intent(mActivity, LoginActivity::class.java))
                mActivity.finish()
            }
            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.accountfragment

    override fun initView() {
        arrayOf(tv_loginOut)
            .forEach {
                it.setOnClickListener(this)
            }


        ApiServices.getInstance()
            .create()
            .

    }
}