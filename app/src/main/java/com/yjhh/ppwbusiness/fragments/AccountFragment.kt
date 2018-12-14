package com.yjhh.ppwbusiness.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUselessService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.AccountBean
import com.yjhh.ppwbusiness.bean.LoginBean
import com.yjhh.ppwbusiness.bean.rxbean.RxUserInfo
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils
import com.yjhh.ppwbusiness.utils.RxBus
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import com.yjhh.ppwbusiness.views.login.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.accountfragment.*
import org.json.JSONObject

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
                start(SetNickNameFragment.newInstance(name))
            }

            R.id.iev_bank -> {

            }
            R.id.iev_alipay -> {

            }
            R.id.iev_wx -> {

            }

            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.accountfragment


    var name: String? = null

    override fun initView() {
        arrayOf(tv_loginOut, iev_reset, iev_nickName, iev_bank, iev_alipay, iev_wx)
            .forEach {
                it.setOnClickListener(this)
            }

        iev_nickName.setTextContent(SharedPreferencesUtils.getParam(mActivity, "nickName", "").toString())

        val dis = RxBus.default.toFlowable(RxUserInfo::class.java).subscribe {
            if (it != null) {
                iev_nickName.setTextContent(it.nickName)
            }
        }




        ApiServices.getInstance()
            .create(SectionUselessService::class.java)
            .currInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("AccountFragment", response)


                    val model = Gson().fromJson<AccountBean>(response, AccountBean::class.java)
                    tv_text.text = if (model.role == 0) "管理员账户" else "员工账户"


                    ImageLoaderUtils.loadImgId(
                        mActivity,
                        iv_image,
                        if (model.role == 0) R.drawable.icon_admin  else R.drawable.icon_ordinary,
                        R.drawable.icon_logoholder,
                        R.drawable.icon_logoholder, 5
                    )





                    name = model.name

                    iev_nickName.setTextContent(model.name)

                    ImageLoaderUtils.loadCircle(
                        mActivity,
                        iv_image,
                        JSONObject(response).optString("avatarUrl"),
                        R.drawable.icon_logoholder,
                        R.drawable.icon_logoholder
                    )


                    if (model.binds != null && model.binds.isNotEmpty()) {

                        if (model.binds[0] != null) {
                            if (model.binds[0].bindStatus == 1) {
                                iev_bank.setTextContent(model.binds[0].text)

                            } else {
                                iev_bank.setTextContent("未绑定")
                            }
                            iev_bank.setLeftContent(model.binds[0].title)
                        }


                        if (model.binds[1] != null) {
                            if (model.binds[1].bindStatus == 1) {
                                iev_alipay.setTextContent(model.binds[1].text)
                            } else {
                                iev_alipay.setTextContent("未绑定")
                            }
                            iev_alipay.setLeftContent(model.binds[1].title)
                        }


                        if (model.binds[2] != null) {
                            if (model.binds[2].bindStatus == 1) {
                                iev_wx.setTextContent(model.binds[2].text)
                            } else {
                                iev_wx.setTextContent("未绑定")
                            }
                            iev_wx.setLeftContent(model.binds[2].title)
                        }


                    }


                }

                override fun onFault(message: String) {
                    Log.i("AccountFragment", message)
                }

            })



        compositeDisposable.addAll(dis)


    }
}