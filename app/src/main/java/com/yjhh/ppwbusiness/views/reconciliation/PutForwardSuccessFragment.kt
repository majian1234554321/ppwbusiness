package com.yjhh.ppwbusiness.views.reconciliation

import android.graphics.Color
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import kotlinx.android.synthetic.main.putforwardsuccessfragment.*
import java.lang.StringBuilder

class PutForwardSuccessFragment : BaseFragment() {


    val tab = "\t\t\t\t\t\t\t"
    val varargs = arrayOf("当前状态", "申请时间", "到账时间", "提现金额", "资金去处", "提现单号")

    val stringList = ArrayList<String>()


    override fun getLayoutRes(): Int = R.layout.putforwardsuccessfragment

    override fun initView() {

        stringList.add(StringBuilder().append(varargs[0]).append(tab).append("111").toString())
        stringList.add(StringBuilder().append(varargs[1]).append(tab).append("222").toString())
        stringList.add(StringBuilder().append(varargs[2]).append(tab).append("333").toString())
        stringList.add(StringBuilder().append(varargs[3]).append(tab).append("444").toString())
        stringList.add(StringBuilder().append(varargs[4]).append(tab).append("555").toString())
        stringList.add(StringBuilder().append(varargs[5]).append(tab).append("666").toString())


        val views = arrayOf(tv_1, tv_2, tv_3, tv_4, tv_5, tv_6)

        views.forEachIndexed { index, textView ->

            textView.text = TextStyleUtils.changeTextColor(stringList[index], 0, 4, Color.parseColor("#666666"))
        }


    }
}