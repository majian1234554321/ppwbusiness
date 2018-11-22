package com.yjhh.ppwbusiness.views.main.main1


import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.Main1Adapter
import com.yjhh.ppwbusiness.adapter.OrderTaskAdapter


import com.yjhh.ppwbusiness.base.BaseMainFragment
import com.yjhh.ppwbusiness.bean.OrderBean
import com.yjhh.ppwbusiness.bean.ShopAdminBean
import com.yjhh.ppwbusiness.fragments.ActivityCenterFragment
import com.yjhh.ppwbusiness.fragments.MainFragment
import com.yjhh.ppwbusiness.ipresent.Main1Present
import com.yjhh.ppwbusiness.ipresent.OrderPresent

import com.yjhh.ppwbusiness.iview.Main1View
import com.yjhh.ppwbusiness.iview.OrderView
import com.yjhh.ppwbusiness.views.cui.GridRecyclerItemDecoration
import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import com.yjhh.ppwbusiness.views.evaluate.EvaluateManageFragment
import com.yjhh.ppwbusiness.views.main.main2.Main2Fragment
import com.yjhh.ppwbusiness.views.merchant.BusinessHoursFragment
import com.yjhh.ppwbusiness.views.merchant.MerchantSettingActivity
import com.yjhh.ppwbusiness.views.product.ProductManagementFragment
import com.yjhh.ppwbusiness.views.product.ProductManagementFragment2
import com.yjhh.ppwbusiness.views.reconciliation.ReconciliationFragment

import kotlinx.android.synthetic.main.main1fragment.*

class Main1Fragment : BaseMainFragment(), View.OnClickListener, Main1View, OrderView {


    override fun onSuccess(model: OrderBean, flag: String) {
        recyclerView2.adapter = OrderTaskAdapter(model.items)
    }

    override fun onsuccessShopAdmin(bean: ShopAdminBean) {
        tv_Turnover.text = bean.account.today.toString()
        tv_YTurnover.text = bean.account.blance.toString()

        tv_TOrder.text = bean.account.order.toString()
        tv_YOrder.text = bean.account.yesOrder.toString()
    }

    override fun onFault(errorMsg: String?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.tv_setting -> {
                (parentFragment as MainFragment).startBrotherFragment(
                    ProductManagementFragment()
                )
            }

            R.id.tv_more -> {
                (parentFragment as MainFragment).mBottomBar.setCurrentItem(1)
            }

            else -> {
            }
        }
    }


    override fun getLayoutRes(): Int = R.layout.main1fragment


    val values = arrayOf("店铺设置", "商品管理", "评价管理", "活动中心", "资金对账")
    val images = arrayOf(
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher

    )

    override fun initView() {

        Main1Present(mActivity, this).ShopAdmin()

        arrayOf(tv_setting, tv_more).forEach {
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

                4 -> {


                    (parentFragment as MainFragment).startBrotherFragment(
                        ReconciliationFragment()
                    )
                }


                3 -> {
                    (parentFragment as MainFragment).startBrotherFragment(
                        ActivityCenterFragment()
                    )
                }


                2 -> {
                    (parentFragment as MainFragment).startBrotherFragment(
                        EvaluateManageFragment()
                    )
                }

                else -> {

                }
            }
        }




        OrderPresent(mActivity, this).orderTask("")


        recyclerView2.layoutManager = LinearLayoutManager(mActivity)


    }


    data class Main1Bean(var image: Int, var value: String)


}