package com.yjhh.ppwbusiness.views.main.main4


import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.baidu.mapsdkplatform.comapi.map.MessageCenter
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.BaseMainFragment
import com.yjhh.ppwbusiness.bean.LoginBean
import com.yjhh.ppwbusiness.fragments.*
import com.yjhh.ppwbusiness.utils.LogUtils
import com.yjhh.ppwbusiness.utils.RxBus
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import com.yjhh.ppwbusiness.views.login.LoginActivity
import kotlinx.android.synthetic.main.main4fragment.*


class Main4Fragment : BaseMainFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.profile_image -> {
                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {

                } else {
                    startActivity(Intent(mActivity, LoginActivity::class.java))
                }

            }

            R.id.tv_name -> {

                if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {

                } else {
                    startActivity(Intent(mActivity, LoginActivity::class.java))
                }
            }




            R.id.iev_message -> {
                (parentFragment as MainFragment).startBrotherFragment(
                    MessageCenterFragment()
                )
            }

            R.id.iev_notice -> {
                (parentFragment as MainFragment).startBrotherFragment(
                    MessageSetFragment()
                )

                // start(   MessageSetFragment())
            }


            R.id.iev_Management -> {

                (parentFragment as MainFragment).startBrotherFragment(
                    EmployeeFragment()
                )

            }


            R.id.iev_about -> {
                (parentFragment as MainFragment).startBrotherFragment(
                    AboutFragment()
                )
            }


            R.id.iev_account -> {

                (parentFragment as MainFragment).startBrotherFragment(
                    AccountFragment()
                )

            }

            R.id.iev_updateVersion -> {

            }

            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.main4fragment


    override fun initView() {
        val arrayView = arrayOf(
            profile_image, tv_name
            , iev_message, iev_notice, iev_Management, iev_about, iev_account, iev_updateVersion
        )

        arrayView.forEach {
            it.setOnClickListener(this)
        }







        if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
            tv_name.text = SharedPreferencesUtils.getParam(context, "nickName", "") as String
        } else {
            tv_name.text = "未登录"
        }

        val dis = RxBus.default.toFlowable(LoginBean::class.java).subscribe {
            LogUtils.i("Main4Fragment", it.mobile)
            if (it.loginSuccess) {
                tv_name.text = SharedPreferencesUtils.getParam(context, "nickName", "") as String
            } else {
                tv_name.text = "未登录"
            }
        }


    }

}