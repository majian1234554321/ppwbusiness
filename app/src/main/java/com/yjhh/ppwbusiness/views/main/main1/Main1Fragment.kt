package com.yjhh.ppwbusiness.views.main.main1


import android.content.Intent
import android.media.Image
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.Main1Adapter


import com.yjhh.ppwbusiness.base.BaseMainFragment
import com.yjhh.ppwbusiness.fragments.MainFragment
import com.yjhh.ppwbusiness.views.cui.GridRecyclerItemDecoration
import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import com.yjhh.ppwbusiness.views.merchant.MerchantSettingActivity
import com.yjhh.ppwbusiness.views.product.ProductManagementFragment
import com.yjhh.ppwbusiness.views.product.ProductManagementFragment2

import kotlinx.android.synthetic.main.main1fragment.*

class Main1Fragment : BaseMainFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.tv_setting -> {
                (parentFragment as MainFragment).startBrotherFragment(
                    ProductManagementFragment()
                )
            }
            else -> {
            }
        }
    }


    override fun getLayoutRes(): Int = R.layout.main1fragment


    val values = arrayOf("商户设置", "商户管理", "评价管理", "活动中心", "订单对账", "公告管理")
    val images = arrayOf(
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher
    )

    override fun initView() {


        arrayOf(tv_setting).forEach {
            it.setOnClickListener(this)
        }


        val lists = ArrayList<Main1Bean>()



        values.forEachIndexed { index, s ->
            lists.add(Main1Bean(images[index], values[index]))

        }


        recyclerView.layoutManager = GridLayoutManager(mActivity, 3)


        val mAdapter = Main1Adapter(lists)

        recyclerView.adapter = mAdapter

        recyclerView.addItemDecoration(GridRecyclerItemDecoration(40))

        mAdapter.setOnItemClickListener { adapter, view, position ->

            when (position) {
                0 -> {
                    startActivity(Intent(mActivity, MerchantSettingActivity::class.java))
                }
                1 -> {
                    (parentFragment as MainFragment).startBrotherFragment(
                        ProductManagementFragment2()
                    )
                }

                2 -> {

                }


                else -> {
                }
            }
        }


    }


    data class Main1Bean(var image: Int, var value: String)


}