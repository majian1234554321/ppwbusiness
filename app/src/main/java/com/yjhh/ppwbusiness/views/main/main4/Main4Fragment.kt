package com.yjhh.ppwbusiness.views.main.main4


import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.azhon.appupdate.listener.OnDownloadListener

import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUselessService
import com.yjhh.ppwbusiness.base.BaseMainFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.*
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.main4fragment.*
import org.json.JSONObject
import java.io.File
import java.lang.Exception
import java.lang.StringBuilder


class Main4Fragment : BaseMainFragment(), View.OnClickListener, CommonView, ShopSetView {


    override fun onSuccess() {

    }

    override fun AllShopInfoSuccess(model: AllShopInfo) {
        if (model.openStatus == 0) {
            tv_status.text = "营业中"
            typeStatus = "0"
        } else {
            tv_status.text = "休息中"
            typeStatus = "1"
        }



        if (model.times!=null&&model.times.size > 0) {




            val sb = StringBuilder()
            model.times.forEach {
                sb.append(it.begin)
                    .append(" - ")
                    .append(it.end)
                    .append("   ")





            }
            tv_time.text = sb.toString()
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


        val model = Gson().fromJson<VersionBean>(value, VersionBean::class.java)

        dialog = if (model.ifCover == 1) {//是否强制覆盖(0否 1是)
            AppUpdateFragment(true)
        } else {
            AppUpdateFragment(false)
        }
        dialog?.show(childFragmentManager, "TAG")

        dialog?.setOnAppUpdate(object : AppUpdateFragment.AppUpdateListener {
            override fun onAppUpdate() {
                APKVersionCodeUtils.startUpdate(mActivity, onDownloadListener)
            }

        })


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
                if (tv_status.text.toString() == "休息中") tv_status.text = "营业中" else tv_status.text = "休息中"

            }

            else -> {
            }
        }
    }


    var typeStatus = "0"     //店铺状态(0正常营业，1打烊/休息)

    override fun getLayoutRes(): Int = R.layout.main4fragment


    override fun initView() {
        val arrayView = arrayOf(
            profile_image, tv_name, tv_to, tv_status
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



        ShopSetPresent(mActivity, this)
            .getAllInfo()


        /*******************************获取当前账户*************************************/

        ApiServices.getInstance()
            .create(SectionUselessService::class.java)
            .currInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("AccountFragment", response)

                    iev_account.setTextContent(JSONObject(response).optString("roleName"))

                    ImageLoaderUtils.loadCircle(
                        mActivity,
                        profile_image,
                        JSONObject(response).optString("avatarUrl"),
                        R.drawable.icon_logoholder,
                        R.drawable.icon_logoholder
                    )


                }

                override fun onFault(message: String) {
                    Log.i("AccountFragment", message)
                }

            })

    }

}