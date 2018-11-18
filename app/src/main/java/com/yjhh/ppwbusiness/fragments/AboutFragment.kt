package com.yjhh.ppwbusiness.fragments

import android.util.Log
import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUselessService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.aboutfragment.*
import org.json.JSONObject

class AboutFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
             R.id.iev_1-> {
            }
            R.id.iev_2-> {
            }
            R.id.iev_3-> {
                start(A_FeedBackFragment())
            }
            R.id.iev_4-> {
            }
            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.aboutfragment


    override fun initView() {

        arrayOf(iev_1,iev_2,iev_3,iev_4).forEach {
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

                    val jsonObject = JSONObject(response)

                    tv_introduce.text = jsonObject.optString("content")

                    tv_servicePhone.text = "服务热线: ${jsonObject.optString("tel")}"
                }

                override fun onFault(message: String) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    Log.i("AboutFragment", message)
                }

            })


    }
}
