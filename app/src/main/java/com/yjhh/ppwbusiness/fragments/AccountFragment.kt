package com.yjhh.ppwbusiness.fragments

import android.content.Intent
import android.util.Log
import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUselessService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.views.login.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
            R.id.iev_reset -> {
                start(ResetPassWordFragment())
            }
            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.accountfragment

    override fun initView() {
        arrayOf(tv_loginOut, iev_reset)
            .forEach {
                it.setOnClickListener(this)
            }




    }
}