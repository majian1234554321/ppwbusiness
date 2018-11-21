package com.yjhh.ppwbusiness.views.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.LoginBean
import com.yjhh.ppwbusiness.fragments.MainFragment
import com.yjhh.ppwbusiness.ipresent.LoginPresent
import com.yjhh.ppwbusiness.iview.LoginView
import com.yjhh.ppwbusiness.utils.RxBus
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import com.yjhh.ppwbusiness.views.main.MainActivity

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

class LoginActivity : BaseActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            if (findFragment(LoginFragment::class.java) == null) {
                loadRootFragment(R.id.fl_container, LoginFragment.newInstance())
            }
        }

        override fun onBackPressedSupport() {

            super.onBackPressedSupport()
        }

        override fun onCreateFragmentAnimator(): FragmentAnimator {

            return DefaultHorizontalAnimator()
        }



































}

