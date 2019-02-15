package com.yjhh.ppwbusiness.views.login

import android.content.Intent
import android.util.ArrayMap
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.SwitchShopAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ShopSetServices
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.SwitchShopBean
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils
import com.yjhh.ppwbusiness.views.main.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.logintipsfragment.*

class LoginTipsFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.logintipsfragment


    override fun initView() {


        recyclerView.layoutManager = LinearLayoutManager(mActivity)


        ApiServices.getInstance().create(ShopSetServices::class.java).toggleList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {

                    Log.i("onActivityResult", response)


                    val modelX = Gson().fromJson<SwitchShopBean>(response, SwitchShopBean::class.java)




                    val mAdapter = SwitchShopAdapter(modelX.shops)

                    recyclerView.adapter = mAdapter



                    mAdapter.setOnItemClickListener { adapter, view, position ->

                        val map = ArrayMap<String, String>()
                        map.clear()
                        map["shopId"] = mAdapter.data[position].id

                        ApiServices.getInstance().create(ShopSetServices::class.java).toggle(map)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : ProcessObserver2(mActivity) {
                                override fun onFault(message: String) {
                                    Log.i("toggle", message)

                                    startActivity(Intent(mActivity, MainActivity::class.java))
                                    mActivity.finish()
                                }

                                override fun processValue(response: String?) {

                                    Log.i("toggle", response)


                                    startActivity(Intent(mActivity, MainActivity::class.java))
                                    mActivity.finish()

                                }

                            })
                    }
                }

                override fun onFault(message: String) {
                    Log.i("onActivityResult", message)
                }

            })




















        mb_login.setOnClickListener {
            startActivity(Intent(mActivity, MainActivity::class.java))
            mActivity.finish()
        }


    }

}