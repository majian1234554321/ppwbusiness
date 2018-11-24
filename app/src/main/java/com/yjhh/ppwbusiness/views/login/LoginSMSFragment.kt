package com.yjhh.ppwbusiness.views.login

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.LoginBean
import com.yjhh.ppwbusiness.ipresent.PasswordPresent
import com.yjhh.ppwbusiness.iview.PasswordView
import com.yjhh.ppwbusiness.utils.RxBus
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import com.yjhh.ppwbusiness.views.main.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.loginsmsfragment.*
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class LoginSMSFragment : BaseFragment(), PasswordView, View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> {

                present?.fromSms(
                    et_mobile.text.toString()
                    , et_verifyCode.text.toString()
                    , identity
                    , "Android"
                )

            }
            R.id.loginPassword -> {
                mActivity.onBackPressed()
            }
            else -> {
            }
        }
    }

    override fun onSuccess(value: String?) {
        val jsonObject = JSONObject(value)

        val mobile = jsonObject.getString("mobile")
        val nickName = jsonObject.getString("nickName")
        val sessionId = jsonObject.getString("sessionId")
        val type = jsonObject.getString("type")

        SharedPreferencesUtils.setParam(mActivity, "mobile", mobile)
        SharedPreferencesUtils.setParam(mActivity, "nickName", nickName)
        SharedPreferencesUtils.setParam(mActivity, "sessionId", sessionId)
        SharedPreferencesUtils.setParam(mActivity, "type", type)

        Toast.makeText(mActivity, "登录成功", Toast.LENGTH_SHORT).show()

        //RxBus.default.post(LoginBean(mobile, true))

        startActivity(Intent(mActivity, MainActivity::class.java))
        mActivity.finish()
    }

    override fun onFault(errorMsg: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessSMS(value: String?) {
        Toast.makeText(BaseApplication.context, "验证码发送成功", Toast.LENGTH_SHORT).show()
    }

    override fun onFaultSMS(errorMsg: String?) {
        Toast.makeText(BaseApplication.context, "验证码发送失败$errorMsg", Toast.LENGTH_SHORT).show()
    }


    val identity = ""//身份（即客户端类型，0用户 1骑手 2商户）
    val TYPE = "1"//1登录 2注册 21 重置密码 22找回密码
    val refId = "";//推荐人ID/phone
    val MAX_COUNT_TIME = 5L

    var present: PasswordPresent? = null

    override fun getLayoutRes(): Int = R.layout.loginsmsfragment

    override fun initView() {

        arrayOf(loginPassword, btn_login).forEach {
            it.setOnClickListener(this)
        }



        present = PasswordPresent(context, this)


        val disposable = RxView.clicks(tv_verifyCode)
            //防止重复点击
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .flatMap {
                val phone = et_mobile.text.toString()
                if (!TextUtils.isEmpty(phone) && phone.length == 11) {
                    Observable.just(true)
                } else {
                    Toast.makeText(mActivity, "手机号码不符合要求", Toast.LENGTH_SHORT).show()
                    Observable.empty()
                }
            }
            .doOnNext {
                Log.i("TAG", "初始化")
                if (it) {
                    present?.sendSms(TYPE, et_mobile.text.toString())
                }

            }
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                if (it) {
                    RxView.enabled(tv_verifyCode).accept(false)
                    RxTextView.text(tv_verifyCode).accept("剩余 $MAX_COUNT_TIME 秒")

                    Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                        .take(MAX_COUNT_TIME)
                        //将递增数字替换成递减的倒计时数字
                        .map { aLong -> MAX_COUNT_TIME - (aLong + 1); }
                } else {
                    Observable.just(0L)
                }
            }

            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it == 0L) {
                    RxView.enabled(tv_verifyCode).accept(true)
                    RxTextView.text(tv_verifyCode).accept("发送验证码")

                } else {
                    RxTextView.text(tv_verifyCode).accept("剩余 $it 秒")

                }
                Log.i("TAG", it.toString())
            }

        compositeDisposable.add(disposable)


    }


}