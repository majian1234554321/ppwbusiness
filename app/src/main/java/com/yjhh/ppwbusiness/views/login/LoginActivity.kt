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
import com.yjhh.ppwbusiness.ipresent.LoginPresent
import com.yjhh.ppwbusiness.iview.LoginView
import com.yjhh.ppwbusiness.utils.RxBus
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import com.yjhh.ppwbusiness.views.main.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(this, "sessionId", "") as String)) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            val displayTab = intent.getStringExtra("displayTab")


            val fragmentTransaction = supportFragmentManager.beginTransaction()
            var fragments: BaseFragment? = null
            fragments = when (displayTab) {

                "LoginFragment" -> {
                    LoginFragment()
                }


                "LoginSMSFragment" -> {
                    LoginSMSFragment()
                }

                "ForgotPasswordFragment" -> {
                    ForgotPasswordFragment()
                }

                else -> {
                    LoginFragment()
                }

            }

            fragmentTransaction.replace(R.id.fr_fragment, fragments).commit()
        }


    }


}

