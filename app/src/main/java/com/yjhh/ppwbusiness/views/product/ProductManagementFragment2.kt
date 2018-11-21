package com.yjhh.ppwbusiness.views.product

import android.support.v4.view.ViewPager
import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
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


    override fun getLayoutRes(): Int = R.layout.productmanagementfragment

    override fun initView() {


        arrayOf(tv_right, tv_sortNO, iv_back).forEach {
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