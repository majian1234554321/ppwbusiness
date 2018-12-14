package com.yjhh.ppwbusiness.views.main.main4


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.TextUtils
import android.text.style.BackgroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.azhon.appupdate.listener.OnDownloadListener

import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUselessService
import com.yjhh.ppwbusiness.base.BaseMainFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.*
import com.yjhh.ppwbusiness.bean.rxbean.RxUserInfo
import com.yjhh.ppwbusiness.fragments.*
import com.yjhh.ppwbusiness.ipresent.CommonPresent
import com.yjhh.ppwbusiness.ipresent.Main1Present
import com.yjhh.ppwbusiness.ipresent.ShopSetPresent
import com.yjhh.ppwbusiness.iview.CommonView
import com.yjhh.ppwbusiness.iview.Main1View
import com.yjhh.ppwbusiness.iview.ShopSetView
import com.yjhh.ppwbusiness.utils.*
import com.yjhh.ppwbusiness.views.cui.AppUpdateFragment
import com.yjhh.ppwbusiness.views.login.LoginActivity
import com.yjhh.ppwbusiness.views.merchant.MerchantSettingActivity
import com.yjhh.ppwbusiness.views.webview.BackViewFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.accountfragment.*

import kotlinx.android.synthetic.main.main4fragment.*
import org.json.JSONObject
import java.io.File
import java.lang.Exception
import java.lang.StringBuilder


class Main4Fragment : BaseMainFragment(), View.OnClickListener, CommonView, ShopSetView {

    override fun onSuccess() {
        if (tv_status.text.toString() == "休息中") {
            tv_status.text = "营业中"
            tv_status.setBackgroundResource(R.drawable.stroke_frame_zthj)
        } else {
            tv_status.text = "休息中"
            tv_status.setBackgroundResource(R.drawable.stroke_frame_jthj)
        }
    }

    override fun AllShopInfoSuccess(model: AllShopInfo) {
        if (model.openStatus == 0) {
            tv_status.text = "营业中"
            typeStatus = "0"
            tv_status.setBackgroundResource(R.drawable.stroke_frame_zthj)
        } else {
            tv_status.text = "休息中"
            typeStatus = "1"
            tv_status.setBackgroundResource(R.drawable.stroke_frame_jthj)
        }


        ImageLoaderUtils.loadCircle(
            mActivity,
            profile_image,
            model?.logoUrl,
            R.drawable.icon_logoholder,
            R.drawable.icon_logoholder

        )

        tv_name.text = model.name


        if (model.times != null && model.times.size > 0) {


            val sb = StringBuilder()
            model.times.forEach {
                sb.append(it.begin)
                    .append(" - ")
                    .append(it.end)
                    .append("   ")


            }
            tv_time.text = sb.toString()
        }


        if (!TextUtils.isEmpty(model.adminUrl) && profile_image != null) {
            profile_image.setOnLongClickListener {


                (parentFragment as MainFragment).startBrotherFragment(
                    BackViewFragment.newInstance(model.adminUrl)
                )

                true
            }

        }

        tv_address.text = model.address
    }

    var dialog: AppUpdateFragment? = null

    internal var onDownloadListener: OnDownloadListener = object : OnDownloadListener {
        override fun start() {

        }

        override fun downloading(max: Int, progress: Int) {

        }

        override fun done(apk: File) {

        }

        override fun error(e: Exception) {

        }
    }

    override fun onSuccess(value: String?) {


        val modelVersionBean = Gson().fromJson<VersionBean>(value, VersionBean::class.java)

        if (APKVersionCodeUtils.getVerName(mActivity) != modelVersionBean.version) {

            dialog = if (modelVersionBean.ifCover == 1) {//是否强制覆盖(0否 1是)
                AppUpdateFragment(true, modelVersionBean.content,modelVersionBean.marketUrl)
            } else {
                AppUpdateFragment(false, modelVersionBean.content,modelVersionBean.marketUrl)
            }
            dialog?.show(childFragmentManager, "TAG")

            dialog?.setOnAppUpdate(object : AppUpdateFragment.AppUpdateListener {
                override fun onAppUpdate() {
                    APKVersionCodeUtils.startUpdate(mActivity, modelVersionBean.downloadUrl, onDownloadListener)
                }

            })

        }
    }

    override fun onFault(errorMsg: String?) {

    }

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
            }


            R.id.iev_Management -> {

                (parentFragment as MainFragment).startBrotherFragment(
                    EmployeeFragment.newInstance(model)
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

                CommonPresent(mActivity, this).checkVersion()
            }
            R.id.tv_to -> {
                startActivity(Intent(mActivity, MerchantSettingActivity::class.java))
            }

            R.id.tv_status -> {


                if (tv_status.text.toString() == "休息中") {
                    ShopSetPresent(mActivity, this)?.editOpen("0")
                } else {
                    ShopSetPresent(mActivity, this)?.editOpen("1")
                }


            }

            else -> {
            }
        }
    }

    var typeStatus = "0"     //店铺状态(0正常营业，1打烊/休息)

    override fun getLayoutRes(): Int = R.layout.main4fragment

    var model: AccountBean? = null

    override fun initView() {
        val arrayView = arrayOf(
            profile_image, tv_name, tv_to, tv_status
            , iev_message, iev_notice, iev_Management, iev_about, iev_account, iev_updateVersion
        )

        arrayView.forEach {
            it.setOnClickListener(this)
        }


        if (!TextUtils.isEmpty(SharedPreferencesUtils.getParam(context, "sessionId", "") as String)) {
            // tv_name.text = SharedPreferencesUtils.getParam(context, "nickName", "") as String
        } else {
            // tv_name.text = "未登录"
        }

        val dis = RxBus.default.toFlowable(LoginBean::class.java).subscribe {
            LogUtils.i("Main4Fragment", it.mobile)
            if (it.loginSuccess) {
                //  tv_name.text = SharedPreferencesUtils.getParam(context, "nickName", "") as String
            } else {
                //  tv_name.text = "未登录"
            }
        }


        checkShopInfo()
        getUserInfo()

        val dis2 = RxBus.default.toFlowable(RxUserInfo::class.java).subscribe {
            if (it != null) {
                getUserInfo()
            }
        }


        compositeDisposable.addAll(dis2)

    }

    fun checkShopInfo() {
        ShopSetPresent(mActivity, this)
            .getAllInfo()
    }

    fun change() {
        ShopSetPresent(mActivity, this)
            .getAllInfo()
    }

    fun getUserInfo() {
        ApiServices.getInstance()
            .create(SectionUselessService::class.java)
            .currInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("AccountFragment", response)
                    model = Gson().fromJson<AccountBean>(response, AccountBean::class.java)
                    // model.add

                    iev_account.setTextContent("${model?.roleName}: ${model?.name}")

//                    ImageLoaderUtils.load(
//                        mActivity,
//                        profile_image,
//                        model?.logoUrl,
//                        R.drawable.icon_logoholder,
//                        R.drawable.icon_logoholder
//                    )


                }

                override fun onFault(message: String) {
                    Log.i("AccountFragment", message)
                    // Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

                }

            })
    }

    override fun onResume() {
        Log.i("Main4Fragment", "Main4Fragment")
        super.onResume()
        ShopSetPresent(mActivity, this)
            .getAllInfo()
    }


}