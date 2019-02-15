package com.yjhh.ppwbusiness.views.login

import androidx.lifecycle.Transformations.map
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.CommonService
import com.yjhh.ppwbusiness.api.ShopSetServices
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.LoginBean
import com.yjhh.ppwbusiness.ipresent.CommonPresent
import com.yjhh.ppwbusiness.ipresent.LoginPresent
import com.yjhh.ppwbusiness.iview.CommonView
import com.yjhh.ppwbusiness.iview.LoginView
import com.yjhh.ppwbusiness.utils.RxBus
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import com.yjhh.ppwbusiness.views.main.MainActivity
import com.yjhh.ppwbusiness.views.webview.BackViewActivity
import com.yjhh.ppwbusiness.views.webview.BackViewFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.loginfragment.*
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment(), View.OnClickListener, LoginView, CommonView {
    override fun getLayoutRes(): Int = R.layout.loginfragment


    override fun onFault(errorMsg: String?) {


        if ("01018" == errorMsg) {


            ApiServices.getInstance().create(CommonService::class.java)
                .init()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ProcessObserver2(mActivity) {
                    override fun processValue(response: String?) {
                        Log.i("01018", response)
                        start(BackViewFragment.newInstance(JSONObject(response).optString("applyShopUrl")))

                    }

                    override fun onFault(message: String) {
                        Log.i("01018", message)
                    }
                })


        } else {
            Toast.makeText(mActivity, errorMsg, Toast.LENGTH_SHORT).show()
        }


    }

    override fun onSuccess(result: String?) {
        val jsonObject = JSONObject(result)

        val mobile = jsonObject.getString("mobile")
        val nickName = jsonObject.getString("nickName")
        val sessionId = jsonObject.getString("sessionId")
        val type = jsonObject.getString("type")


        val multiShop = jsonObject.optBoolean("multiShop")





        SharedPreferencesUtils.setParam(mActivity, "mobile", mobile)
        SharedPreferencesUtils.setParam(mActivity, "nickName", nickName)
        SharedPreferencesUtils.setParam(mActivity, "sessionId", sessionId)
        SharedPreferencesUtils.setParam(mActivity, "type", type)

        Toast.makeText(mActivity, "登录成功", Toast.LENGTH_SHORT).show()

        //  RxBus.default.post(LoginBean(mobile, true))


        //

        start(LoginTipsFragment())
//        if (multiShop) {
//            start(LoginTipsFragment())
//        } else {
//            startActivity(Intent(mActivity, MainActivity::class.java))
//            mActivity.finish()
//        }


    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_show_pwd -> {
                if (et_password.inputType != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    et_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    iv_show_pwd.setImageResource(R.drawable.pass_visuable)
                } else {
                    et_password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    iv_show_pwd.setImageResource(R.drawable.pass_gone)
                }
                val pwd = et_password.text.toString()
                if (!TextUtils.isEmpty(pwd))
                    et_password.setSelection(pwd.length)
            }


            R.id.forget_password -> {

                start(ForgotPasswordFragment())

            }


            R.id.loginSMS -> {

                start(LoginSMSFragment())

            }

            R.id.tv_kaidian -> {
                ApiServices.getInstance().create(CommonService::class.java)
                    .init()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : ProcessObserver2(mActivity) {
                        override fun processValue(response: String?) {
                            Log.i("01018", response)
                            start(BackViewFragment.newInstance(JSONObject(response).optString("applyShopUrl")))

                        }

                        override fun onFault(message: String) {
                            Log.i("01018", message)
                        }
                    })
            }

            R.id.tv_question -> {
                ApiServices.getInstance().create(CommonService::class.java)
                    .init()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : ProcessObserver2(context) {
                        override fun processValue(response: String?) {
                            Log.i("EvaluateManageFragment", response)

                            start(BackViewFragment.newInstance(JSONObject(response).optString("helpIndexUrl")))
                        }

                        override fun onFault(message: String) {
                            Log.i("EvaluateManageFragment", message)
                        }

                    })


            }

            else -> {

            }
        }
    }

    override fun initView() {


        CommonPresent(mActivity, this)


        val disposable1 = RxView.clicks(btn_login)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribe(
                {
                    if (et_mobile.text != null && et_mobile.length() == 11 && et_password != null && et_password.length() >= 6) {
                        val loginPresent = LoginPresent(mActivity, this)
                        loginPresent.login(
                            et_mobile.text.toString(),
                            et_password.text.toString()

                        )
                    } else {
                        Toast.makeText(mActivity, "账号密码信息错误", Toast.LENGTH_SHORT).show()
                    }
                }, {
                    Toast.makeText(mActivity, "账号密码信息错误", Toast.LENGTH_SHORT).show()
                }
            )


        val view = arrayOf(iv_show_pwd, forget_password, loginSMS, tv_kaidian, tv_question)


        view.forEach {
            it.setOnClickListener(this)
        }





        compositeDisposable.add(disposable1)

    }


    private fun isPhoneValid(phone: String): Boolean {
        return phone.length == 11
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }


    companion object {
        fun newInstance(): LoginFragment {

            val args = Bundle()

            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }

}