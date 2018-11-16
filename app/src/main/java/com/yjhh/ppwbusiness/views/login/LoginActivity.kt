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
import com.yjhh.ppwbusiness.bean.LoginBean
import com.yjhh.ppwbusiness.ipresent.LoginPresent
import com.yjhh.ppwbusiness.iview.LoginView
import com.yjhh.ppwbusiness.utils.RxBus
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener, LoginView {
    override fun onFault(errorMsg: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess(result: String?) {
        val jsonObject = JSONObject(result)

        val mobile = jsonObject.getString("mobile")
        val nickName = jsonObject.getString("nickName")
        val sessionId = jsonObject.getString("sessionId")
        val type = jsonObject.getString("type")

        SharedPreferencesUtils.setParam(this, "mobile", mobile)
        SharedPreferencesUtils.setParam(this, "nickName", nickName)
        SharedPreferencesUtils.setParam(this, "sessionId", sessionId)
        SharedPreferencesUtils.setParam(this, "type", type)

        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()

        RxBus.default.post(LoginBean(mobile, true))

        finish()
    }

    val identity = "2"//2商户

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

            R.id.regist -> {
                ARouter.getInstance()
                    .build("/DisplayActivity/Display")
                    .withString("displayTab", "RegistFragment")
                    .withInt("age", 23)
                    .navigation()
            }

            R.id.forget_password -> {
               startActivity(Intent(this,ForgotPasswordActivity::class.java))
            }

            R.id.iv_close -> {
                finish()
            }


            else -> {
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val observableName = RxTextView.textChanges(et_mobile)
        val observablePassword = RxTextView.textChanges(et_password)


        val disposable1 = RxView.clicks(btn_login)
            .throttleFirst(1, TimeUnit.SECONDS)
            .map {
                Observable.combineLatest(
                    observableName,
                    observablePassword,
                    BiFunction<CharSequence, CharSequence, JSONObject> { phone, password ->

                        JSONObject().put("phone", phone).put("password", password)
                            .put(
                                "flag", isPhoneValid(phone.toString()) && isPasswordValid(
                                    password.toString()
                                )
                            )
                    })
            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.i("TAG", it.blockingFirst().toString())
                if (it.blockingFirst().getString("flag")!!.toBoolean()) {

                    val loginPresent = LoginPresent(this, this)
                    loginPresent.login(
                        it.blockingFirst().getString("phone"),
                        it.blockingFirst().getString("password"),
                        identity
                    )

                } else {

                    Toast.makeText(this, "账号密码信息错误", Toast.LENGTH_SHORT).show()
                }
            }


        val view = arrayOf(iv_show_pwd, regist, forget_password, iv_close)


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


}

