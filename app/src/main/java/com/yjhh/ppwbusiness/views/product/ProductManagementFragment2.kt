package com.yjhh.ppwbusiness.views.product

import android.support.v4.view.ViewPager
import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyPagerAdapter
import com.yjhh.ppwbusiness.base.BaseFragment

import kotlinx.android.synthetic.main.productmanagementfragment.*
import java.util.ArrayList

class ProductManagementFragment2 : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_add -> {
                start(ProductAddFragment())
            }

            R.id.tv_sort -> {
            }
            else -> {
            }
        }
    }

    private val mTitles = arrayOf("全部", "上架中", "已下架")


    override fun getLayoutRes(): Int = R.layout.productmanagementfragment

    override fun initView() {


        arrayOf(tv_add, tv_sort).forEach {
            it.setOnClickListener(this)
        }


        val fagments = ArrayList<BaseFragment>()

        fagments.add(ProductAllFragment())
        fagments.add(Product2Fragment())
        fagments.add(Product3Fragment())
        // fagments.add(Product4Fragment())


        mViewPager.adapter = MyPagerAdapter(childFragmentManager, fagments, mTitles)
        mTabLayout.setViewPager(mViewPager)

        mTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                mViewPager.currentItem = position
            }

            override fun onTabReselect(position: Int) {
                if (position == 0) {

                }
            }
        })

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mTabLayout.currentTab = position
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


    }

}