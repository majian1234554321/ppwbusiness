package com.yjhh.ppwbusiness.views.reconciliation

import android.os.Bundle
import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.bindzbfragment.*

class BindZBFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.bindzbfragment

    override fun initView() {


        val type = arguments?.getString("type")
        when (type) {
            "ZBF" -> {
                tbv_title.setTitle("绑定支付宝")
                tv_type.text = "支付宝"
                iev1.setLeftContent("收款人")
                iev2.setLeftContent("收款账号")
                iev3.visibility = View.GONE
            }
            else -> {
                tbv_title.setTitle("绑定银行卡")
                tv_type.text = "银行卡"
                iev1.setLeftContent("卡号")
                iev2.setLeftContent("开户人姓名")
                iev3.setLeftContent("开户预留手机号")
            }
        }


    }


    companion object {
        fun newInstance(type: String): BindZBFragment {

            val bindZBFragment = BindZBFragment()

            val bundle = Bundle()
            bundle.putString("type", type)
            bindZBFragment.arguments = bundle

            return bindZBFragment

        }
    }


}