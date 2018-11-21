package com.yjhh.ppwbusiness.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUselessService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.LoginBean
import com.yjhh.ppwbusiness.bean.rxbean.RxUserInfo
import com.yjhh.ppwbusiness.utils.RxBus
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
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

            R.id.iev_nickName -> {
                start(SetNickNameFragment())
            }

            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.accountfragment

    override fun initView() {
        arrayOf(tv_loginOut, iev_reset, iev_nickName)
            .forEach {
                it.setOnClickListener(this)
            }

        iev_nickName.setTextContent(SharedPreferencesUtils.getParam(mActivity, "nickName", "").toString())

        val dis = RxBus.default.toFlowable(RxUserInfo::class.java).subscribe {
            if (it != null) {
                iev_nickName.setTextContent(it.nickName)
            }
        }

        compositeDisposable.addAll(dis)


    }
}