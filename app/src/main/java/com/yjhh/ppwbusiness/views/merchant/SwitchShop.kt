package com.yjhh.ppwbusiness.views.merchant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.SwitchShopAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ShopSetServices
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.AboutBean
import com.yjhh.ppwbusiness.bean.SwitchShopBean
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_switch_shop.*

class SwitchShop : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_shop)


        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)





        ApiServices.getInstance().create(ShopSetServices::class.java).toggleList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(this) {
                override fun processValue(response: String?) {

                    Log.i("onActivityResult", response)


                    val modelX = Gson().fromJson<SwitchShopBean>(response, SwitchShopBean::class.java)


                    ImageLoaderUtils.loadCircle(
                        this@SwitchShop,
                        iv_image,
                        modelX.curr.logoUrl,
                        R.drawable.icon_place,
                        R.drawable.icon_place
                    )
                    tv_text.text = modelX.curr.name


                    val mAdapter = SwitchShopAdapter(modelX.shops)

                    recyclerView.adapter = mAdapter



                    mAdapter.setOnItemClickListener { adapter, view, position ->

                        val map = ArrayMap<String, String>()
                        map.clear()
                        map["shopId"] = mAdapter.data[position].id

                        ApiServices.getInstance().create(ShopSetServices::class.java).toggle(map)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : ProcessObserver2(this@SwitchShop) {
                                override fun onFault(message: String) {
                                    Log.i("toggle", message)


                                }

                                override fun processValue(response: String?) {

                                    Log.i("toggle", response)


                                    ImageLoaderUtils.loadCircle(
                                        this@SwitchShop,
                                        iv_image,
                                        mAdapter.data[position].logoUrl,
                                        R.drawable.icon_place,
                                        R.drawable.icon_place
                                    )
                                    tv_text.text = mAdapter.data[position].name



                                    val resultIntent = Intent()
                                    setResult(RESULT_OK, resultIntent)
                                    finish()


                                }

                            })
                    }
                }

                override fun onFault(message: String) {
                    Log.i("onActivityResult", message)
                }

            })


    }




}
