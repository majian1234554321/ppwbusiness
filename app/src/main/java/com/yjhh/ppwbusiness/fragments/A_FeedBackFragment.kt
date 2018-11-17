package com.yjhh.ppwbusiness.fragments

import android.util.ArrayMap
import android.view.View
import android.widget.Toast
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUselessService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.a_feedbackfragment.*

class A_FeedBackFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_commit -> {

                val map = ArrayMap<String, String>()

                map.put("cause", et_5.text.toString())
                map.put("contact", et_1.text.toString())
                map.put("name", et_3.text.toString())
                map.put("deviceId", "Android")
                map.put("files", et_1.text.toString())

                ApiServices.getInstance()
                    .create(SectionUselessService::class.java)
                    .feedback(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : ProcessObserver2(mActivity) {
                        override fun processValue(response: String?) {
                            Toast.makeText(mActivity, "反馈提交成功", Toast.LENGTH_SHORT).show()
                            mActivity.onBackPressed()
                        }

                        override fun onFault(message: String) {
                            Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show()
                        }

                    })

            }
            else -> {

            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.a_feedbackfragment

    override fun initView() {
        arrayOf(tv_commit, tv_see)
            .forEach {
                it.setOnClickListener(this)
            }
    }
}