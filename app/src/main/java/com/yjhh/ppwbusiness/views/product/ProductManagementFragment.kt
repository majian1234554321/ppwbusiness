package com.yjhh.ppwbusiness.views.product

import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyPagerAdapter
import com.yjhh.ppwbusiness.base.BaseFragment

import kotlinx.android.synthetic.main.productmanagementfragment.*
import java.util.ArrayList

class ProductManagementFragment : BaseFragment() {

    private val mTitles = arrayOf("全部", "上架中", "已下架", "售罄")


    override fun getLayoutRes(): Int = R.layout.productmanagementfragment

    override fun initView() {





        val fagments = ArrayList<BaseFragment>()

        fagments.add(Product1Fragment())
        fagments.add(Product2Fragment())
        fagments.add(Product3Fragment())
        fagments.add(Product4Fragment())


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

        mViewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
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