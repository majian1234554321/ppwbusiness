package com.yjhh.ppwbusiness.views.product

import android.graphics.Color
import androidx.viewpager.widget.ViewPager
import android.view.View
import android.widget.Toast
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyPagerAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.ProductBean

import kotlinx.android.synthetic.main.productmanagementfragment.*
import java.util.ArrayList

class ProductManagementFragment2 : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_right -> {
                start(ProductAddFragment.newInstance(ProductBean.ItemsBean(), "ADD"))
            }

            R.id.tv_sortNO -> {

            }
            R.id.iv_back -> {
                mActivity.onBackPressed()
            }

            else -> {
            }
        }
    }

    private val mTitles = arrayOf("全部", "上架中", "已下架")

    var sortType = "时间向上"
    override fun getLayoutRes(): Int = R.layout.productmanagementfragment


    var order = ""   //排序,0（0 时间排序 1价格排序）
    var orderType = "" //排序方式，0(0升序 1倒叙)


    override fun initView() {


        arrayOf(tv_right, tv_sortNO, iv_back).forEach {
            it.setOnClickListener(this)
        }


        val f1 = ProductAllFragment()
        val f2 = Product2Fragment()
        val f3 = Product3Fragment()


        tv_timeSort.setBackgroundResource(R.drawable.stroke_rb_leftselect)
        tv_timeSort.setTextColor(Color.WHITE)


        tv_priceSort.setBackgroundResource(R.drawable.stroke_rb_rightunselect)
        tv_priceSort.setTextColor(Color.parseColor("#333333"))


        val fagments = ArrayList<BaseFragment>()

        fagments.add(f1)
        fagments.add(f2)
        fagments.add(f3)
        // fagments.add(Product4Fragment())

        mViewPager.offscreenPageLimit = 3
        mViewPager.adapter = MyPagerAdapter(childFragmentManager, fagments, mTitles)
        mTabLayout.setViewPager(mViewPager)

        mTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                mViewPager.currentItem = position
            }

            override fun onTabReselect(position: Int) {
                when (position) {
                    0 -> f1.sortType(order, orderType)
                    1 -> f2.sortType(order, orderType)
                    else -> f3.sortType(order, orderType)
                }
            }
        })

        mViewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mTabLayout.currentTab = position

                if (position==0){
                    f1.autoRefresh()
                }else if (position==1) {
                    f2.autoRefresh()
                }else if (position==2){
                    f3.autoRefresh()
                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })




        rg.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.tv_timeSort -> {

                    tv_timeSort.setBackgroundResource(R.drawable.stroke_rb_leftselect)
                    tv_timeSort.setTextColor(Color.WHITE)


                    tv_priceSort.setBackgroundResource(R.drawable.stroke_rb_rightunselect)
                    tv_priceSort.setTextColor(Color.parseColor("#333333"))


                    if (tv_timeSort.text.toString() == "时间排序 ↓") {
                        tv_timeSort.text = "时间排序 ↑"

                        sortType = "时间向上"

                        //Toast.makeText(mActivity, sortType, Toast.LENGTH_SHORT).show()

                        order = "0"   //排序,0（0 时间排序 1价格排序）
                        orderType = "0" //排序方式，0(0升序 1倒叙)

                    } else {
                        tv_timeSort.text = "时间排序 ↓"
                        sortType = "时间向下"
                        // Toast.makeText(mActivity, sortType, Toast.LENGTH_SHORT).show()
                        order = "0"   //排序,0（0 时间排序 1价格排序）
                        orderType = "1" //排序方式，0(0升序 1倒叙)
                    }



                    if (mViewPager.currentItem == 0) {
                        f1.sortType(order, orderType)
                    } else if (mViewPager.currentItem == 1) {
                        f2.sortType(order, orderType)
                    } else {
                        f3.sortType(order, orderType)
                    }


                }
                R.id.tv_priceSort -> {

                    tv_priceSort.setBackgroundResource(R.drawable.stroke_rb_rightselect)
                    tv_priceSort.setTextColor(Color.WHITE)

                    tv_timeSort.setBackgroundResource(R.drawable.stroke_rb_leftunselect)
                    tv_timeSort.setTextColor(Color.parseColor("#333333"))


                    if (tv_priceSort.text.toString() == "价格排序 ↓") {
                        tv_priceSort.text = "价格排序 ↑"


                        order = "1"   //排序,0（0 时间排序 1价格排序）
                        orderType = "0" //排序方式，0(0升序 1倒叙)

                    } else {
                        tv_priceSort.text = "价格排序 ↓"


                        order = "1"   //排序,0（0 时间排序 1价格排序）
                        orderType = "1" //排序方式，0(0升序 1倒叙)
                    }
                    if (mViewPager.currentItem == 0) {
                        f1.sortType(order, orderType)
                    } else if (mViewPager.currentItem == 1) {
                        f2.sortType(order, orderType)
                    } else {
                        f3.sortType(order, orderType)
                    }

                }


                else -> {
                }
            }

        }


    }

}