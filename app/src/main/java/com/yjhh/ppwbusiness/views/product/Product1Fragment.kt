package com.yjhh.ppwbusiness.views.product

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.views.product.twoview.BaseViewAdapter
import com.yjhh.ppwbusiness.views.product.twoview.BindData
import com.yjhh.ppwbusiness.views.product.twoview.LeftBean
import com.yjhh.ppwbusiness.views.product.twoview.RightBean

import kotlinx.android.synthetic.main.product1fragment.*
import java.util.ArrayList

class Product1Fragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_1 -> {
            }
            R.id.tv_2 -> {

               start(ProductAddFragment("ADD"))


            }
            R.id.tv_3 -> {
            }
            R.id.tv_4 -> {
            }
            else -> {
            }
        }
    }


    override fun getLayoutRes(): Int = R.layout.product1fragment

    override fun initView() {


        arrayOf(tv_1, tv_2, tv_3, tv_4).forEach {
            it.setOnClickListener(this)
        }


        val leftData = ArrayList<LeftBean>()
        val rightData = ArrayList<RightBean>()

        for (i in 0..19) {
            val bean = LeftBean()
            bean.tag = "i:$i"
            bean.id = i
            leftData.add(bean)
        }
        for (i in 0..19) {
            for (j in 0..19) {
                val bean = RightBean()
                bean.tag = "i:$i"
                bean.text = "j:$j i:$i"
                rightData.add(bean)
            }

        }


        meituan.setData(object : BindData<LeftBean, RightBean>() {
            override fun getLeftLayoutId(): Int = R.layout.item

            override fun getLeftData(): MutableList<LeftBean> {
                return leftData
            }

            override fun bindLeftView(holder: BaseViewAdapter.BaseHolder?, position: Int, bean: LeftBean?) {
                val tv = holder?.findViews<TextView>(R.id.tv)
                tv?.text = bean?.tag
            }

            override fun bindDefaultStatus(holder: BaseViewAdapter.BaseHolder?, position: Int, bean: LeftBean?) {
                val tv = holder?.findViews<TextView>(R.id.tv)
                tv?.setTextColor(Color.BLACK)
                tv?.setBackgroundColor(Color.WHITE)
            }

            override fun bindSelectStatus(holder: BaseViewAdapter.BaseHolder?, position: Int, bean: LeftBean?) {
                val tv = holder?.findViews<TextView>(R.id.tv)
                tv?.setTextColor(Color.WHITE)
                tv?.setBackgroundColor(Color.BLACK)
            }

            override fun getRightLayoutId(): Int = R.layout.rightdish

            override fun bindRightView(holder: BaseViewAdapter.BaseHolder?, position: Int, bean: RightBean?) {
                val count = 0


            }

            override fun getRightData(): MutableList<RightBean> {

                return rightData

            }

            override fun rightItemClickListener(holder: BaseViewAdapter.BaseHolder?, position: Int, bean: RightBean?) {
                Toast.makeText(mActivity, bean?.text, Toast.LENGTH_SHORT).show()
            }

        })


    }
}