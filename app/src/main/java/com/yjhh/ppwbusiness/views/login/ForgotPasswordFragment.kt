package com.yjhh.ppwbusiness.views.login

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.ipresent.PasswordPresent
import com.yjhh.ppwbusiness.iview.PasswordView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_forgot_password.*
import java.util.concurrent.TimeUnit

class ForgotPasswordFragment:BaseFragment(), View.OnClickListener, PasswordView {

    override fun onSuccess(value: String?) {
        Toast.makeText(BaseApplication.context, "重置密码成功", Toast.LENGTH_SHORT).show()
        mActivity.finish()
    }

    override fun onFault(errorMsg: String?) {
        Toast.makeText(BaseApplication.context, "重置密码失败$errorMsg", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessSMS(value: String?) {
        Toast.makeText(BaseApplication.context, "验证码发送成功", Toast.LENGTH_SHORT).show()
    }

    override fun onFaultSMS(errorMsg: String?) {
        Toast.makeText(BaseApplication.context, "验证码发送失败$errorMsg", Toast.LENGTH_SHORT).show()
    }



    val TYPE = "22"//1登录 2注册 21 重置密码 22找回密码
    val refId = "";//推荐人ID/phone
    private val MAX_COUNT_TIME = 5L


    override fun onClick(v: View?) {

        if (et_phone.text.length == 11 && et_password.text.length >= 6 && et_verifyCode.text != null) {
            val present = PasswordPresent(BaseApplication.context, this)
            present.forgotPassword(et_phone.text.toString(), et_password.text.toString(), et_verifyCode.text.toString())
        } else {
            Toast.makeText(BaseApplication.context, "手机号、密码、验证码不符合要求", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getLayoutRes(): Int = R.layout.activity_forgot_password

    override fun initView() {
        val regByAccountPresent = PasswordPresent(context, this)
        bt_commit.setOnClickListener(this)


        val disposable = RxView.clicks(tv_verifyCode)
            //防止重复点击
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .flatMap {
                val phone = et_phone.text.toString()
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
                    regByAccountPresent.sendSms(TYPE, et_phone.text.toString())
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
