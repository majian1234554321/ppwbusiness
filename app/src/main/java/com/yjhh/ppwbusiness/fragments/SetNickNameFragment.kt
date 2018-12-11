package com.yjhh.ppwbusiness.fragments

import android.content.Intent
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.widget.Toast
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.FeedBackDetailsFragment
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUserService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.rxbean.RxUserInfo
import com.yjhh.ppwbusiness.utils.RxBus
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.accountfragment.*
import kotlinx.android.synthetic.main.setnicknamefragment.*

class SetNickNameFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.setnicknamefragment

    override fun initView() {


        val value = arguments?.getString("value")

        et_nickName.setText(value)


        tv_commit.setOnClickListener {
            val map = ArrayMap<String, String>()
            map["name"] = et_nickName.text.toString()
            ApiServices.getInstance().create(SectionUserService::class.java)
                .editUserName(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ProcessObserver2(mActivity) {
                    override fun processValue(response: String?) {
                        Toast.makeText(mActivity, "设置名字成功", Toast.LENGTH_SHORT).show()

                        SharedPreferencesUtils.setParam(mActivity, "nickName", et_nickName.text.toString())

                        val model = RxUserInfo()
                        model.nickName = et_nickName.text.toString()

                        RxBus.default.post(model)

                        mActivity.onBackPressed()
                    }

                    override fun onFault(message: String) {
                        Toast.makeText(mActivity, "设置名字失败", Toast.LENGTH_SHORT).show()
                    }

                })

        }


    }


    companion object {
        fun newInstance(value: String?): SetNickNameFragment {
            val fragment = SetNickNameFragment()
            val bundle = Bundle()

            bundle.putString("value", value)
            fragment.arguments = bundle
            return fragment
        }
    }


}