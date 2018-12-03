package com.yjhh.ppwbusiness.views.main.main1


import android.Manifest
import android.content.Intent
import android.graphics.Color
import androidx.recyclerview.widget.GridLayoutManager
import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.Main1Adapter


import com.yjhh.ppwbusiness.base.BaseMainFragment
import com.yjhh.ppwbusiness.bean.OrderBean
import com.yjhh.ppwbusiness.bean.ShopAdminBean
import com.yjhh.ppwbusiness.fragments.ActivityCenterFragment
import com.yjhh.ppwbusiness.fragments.MainFragment
import com.yjhh.ppwbusiness.ipresent.Main1Present
import com.yjhh.ppwbusiness.ipresent.OrderPresent

import com.yjhh.ppwbusiness.iview.Main1View
import com.yjhh.ppwbusiness.iview.OrderView
import com.yjhh.ppwbusiness.utils.RecyclerGridSpace
import com.yjhh.ppwbusiness.views.evaluate.EvaluateManageFragment
import com.yjhh.ppwbusiness.views.merchant.MerchantSettingActivity
import com.yjhh.ppwbusiness.views.product.ProductManagementFragment
import com.yjhh.ppwbusiness.views.product.ProductManagementFragment2
import com.yjhh.ppwbusiness.views.reconciliation.ReconciliationFragment

import kotlinx.android.synthetic.main.main1fragment.*
import android.widget.Toast
import com.azhon.appupdate.utils.Constant


import com.tbruyelle.rxpermissions2.RxPermissions
import com.uuzuche.lib_zxing.activity.CodeUtils
import com.yjhh.ppwbusiness.base.BaseActivity.REQUEST_CODE


import com.yjhh.ppwbusiness.views.CaptureActivity2
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import com.yjhh.ppwbusiness.views.cui.PPWHeader
import com.yjhh.ppwbusiness.views.cui.PPWHeader2
import com.yjhh.ppwbusiness.views.writeoff.WriteOffFragment


class Main1Fragment : BaseMainFragment(), View.OnClickListener, Main1View, OrderView {


    override fun onSuccess(model: OrderBean, flag: String) {

    }

    override fun onsuccessShopAdmin(bean: ShopAdminBean) {

        val stringToday = mActivity.getString(
            R.string.rmb_price_double,
            bean.account.today
        )
        tv_Turnover.text = TextStyleUtils.changeTextAa(stringToday, stringToday.length - 2, stringToday.length, 20)

        val stringBlance = "昨日营业额  ${mActivity.getString(R.string.rmb_price_double, bean.account.blance)}"
        tv_YTurnover.text = TextStyleUtils.changeTextColor(stringBlance, 0, 5, Color.parseColor("#8C8C8C"))

        tv_TOrder.text = bean.account.order.toString()


        val stringYesOrder = "昨日订单量  ${bean.account.yesOrder}"
        tv_YOrder.text = TextStyleUtils.changeTextColor(stringYesOrder, 0, 5, Color.parseColor("#8C8C8C"))



        tv_title.text = bean.shopName

        sc_left.setcontent("${bean.preNum} 单")
        sc_right.setcontent("${bean.preTotal} 单")

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

            R.id.iv_scan -> {


                (parentFragment as MainFragment).startBrotherFragment(
                    WriteOffFragment.newInstance("123")
                )


/*

                RxPermissions(this)
                    .request(Manifest.permission.CAMERA)
                    .subscribe {
                        if (it) {
                            val intent = Intent(context, CaptureActivity2::class.java)

                            this@Main1Fragment.startActivityForResult(intent, 10086)
                        } else {
                            Toast.makeText(mActivity, "请前往设置中心开启照相机权限", Toast.LENGTH_SHORT).show()
                        }
                    }
*/


            }

            else -> {
            }
        }
    }


    override fun getLayoutRes(): Int = R.layout.main1fragment


    val values = arrayOf("店铺设置", "商品管理", "评价管理", "活动中心", "资金对账")
    val images = arrayOf(
        R.drawable.grid1,
        R.drawable.grid2,
        R.drawable.grid3,
        R.drawable.grid4,
        R.drawable.grid5

    )

    override fun initView() {

        Main1Present(mActivity, this).shopAdminHome()

        arrayOf(tv_setting, tv_more, iv_scan).forEach {
            it.setOnClickListener(this)
        }


        // swipeLayout.setRefreshHeader(PPWHeader2(mActivity))

        swipeLayout.setRefreshHeader(PPWHeader(mActivity))


        val lists = ArrayList<Main1Bean>()

        values.forEachIndexed { index, s ->
            lists.add(Main1Bean(images[index], values[index]))

        }

        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(mActivity, 3)
        val mAdapter = Main1Adapter(lists)
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(RecyclerGridSpace(3, Color.parseColor("#ECECEC")))
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


        //  OrderPresent(mActivity, this).orderTask("")


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        when (requestCode) {
            10086 -> {
                //处理扫描结果（在界面上显示）
                if (null != data) {
                    val bundle = data.extras ?: return
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        val result = bundle.getString(CodeUtils.RESULT_STRING)
                        Toast.makeText(mActivity, "解析结果:" + result!!, Toast.LENGTH_LONG).show()
                        (parentFragment as MainFragment).startBrotherFragment(WriteOffFragment())

                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(mActivity, "解析二维码失败", Toast.LENGTH_LONG).show()
                    }
                }


            }
        }

    }


    data class Main1Bean(var image: Int, var value: String)


}