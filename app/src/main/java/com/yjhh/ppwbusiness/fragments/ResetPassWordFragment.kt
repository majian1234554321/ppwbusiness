package com.yjhh.ppwbusiness.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.BaseApplication.context
import com.yjhh.ppwbusiness.Constants.MAX_COUNT_TIME
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.tv_mobile
import com.yjhh.ppwbusiness.R.id.tv_verifyCode
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.ipresent.PasswordPresent
import com.yjhh.ppwbusiness.iview.PasswordView
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import com.yjhh.ppwbusiness.views.login.LoginActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.resetpasswordfragment.*
import java.util.concurrent.TimeUnit

class ResetPassWordFragment : BaseFragment(), PasswordView {
    override fun onSuccess(value: String?) {
        Toast.makeText(BaseApplication.context, "重置密码成功", Toast.LENGTH_SHORT).show()
        loginOut()
        startActivity(Intent(mActivity, LoginActivity::class.java))
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

    override fun getLayoutRes(): Int = R.layout.resetpasswordfragment


    val TYPE = "21"

    val type = "1"//0原密码修改 1短信修改(只传入新密码)


    var present: PasswordPresent? = null
    override fun initView() {


        val text = "将验证码发送至   ${SharedPreferencesUtils.getParam(mActivity, "mobile", "")}"


        val style = SpannableStringBuilder(text)
        style.setSpan(ForegroundColorSpan(Color.parseColor("#FF999999")), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        tv_mobile.text = style
        present = PasswordPresent(context, this)

        bt_commit.setOnClickListener {
            if (!TextUtils.isEmpty(et_verifyCode.text.toString()) && et_password.text.toString().length >= 6) {
                present?.resetPassword(et_password.text.toString(), et_verifyCode.text.toString(), "1")
            } else {
                Toast.makeText(mActivity, "请输入验证码并且密码长度大于6位", Toast.LENGTH_SHORT).show()
            }
        }


        val disposable = RxView.clicks(tv_verifyCode)
            //防止重复点击
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())

            .doOnNext {
                present?.sendSms(TYPE, "")

            }
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {

                RxView.enabled(tv_verifyCode).accept(false)
                RxTextView.text(tv_verifyCode).accept("剩余 $MAX_COUNT_TIME 秒")

                Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                    .take(MAX_COUNT_TIME)
                    //将递增数字替换成递减的倒计时数字
                    .map { aLong -> MAX_COUNT_TIME - (aLong + 1); }

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