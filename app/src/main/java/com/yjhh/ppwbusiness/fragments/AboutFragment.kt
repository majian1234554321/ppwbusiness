package com.yjhh.ppwbusiness.fragments

import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUselessService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.AboutBean
import com.yjhh.ppwbusiness.views.webview.BackViewFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.aboutfragment.*
import org.json.JSONObject

class AboutFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iev_1 -> {
                if (function != null && function!!.isNotEmpty()) {
                    start(BackViewFragment.newInstance(function!![0].linkUrl))
                }

            }

            R.id.iev_2 -> {
                if (function != null && function!!.size > 1) {
                    start(BackViewFragment.newInstance(function!![1].linkUrl))
                }


            }

            R.id.iev_3 -> {
                start(A_FeedBackFragment())
            }

            R.id.iev_4 -> {
                if (function != null && function!!.size > 2) {
                    start(BackViewFragment.newInstance(function!![2].linkUrl))
                }
            }

            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.aboutfragment


    var function: List<AboutBean.FunctionsBean>? = null

    override fun initView() {

        arrayOf(iev_1, iev_2, iev_3, iev_4).forEach {
            it.setOnClickListener(this)
        }

        ApiServices.getInstance().create(SectionUselessService::class.java)
            .about()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                    Log.i("AboutFragment", response)

                    val model = Gson().fromJson<AboutBean>(response, AboutBean::class.java)



                    function = model.functions



                    tv_introduce.text = model.content
                    iev_5.setTextContent(model.tel)
                    iev_5.setArrow()
                }

                override fun onFault(message: String) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    Log.i("AboutFragment", message)
                }

            })


    }
}
