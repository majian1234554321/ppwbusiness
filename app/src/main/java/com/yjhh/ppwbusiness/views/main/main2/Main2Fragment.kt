package com.yjhh.ppwbusiness.views.main.main2


import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyPagerAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.BaseMainFragment
import com.yjhh.ppwbusiness.fragments.AccountFragment
import com.yjhh.ppwbusiness.fragments.MainFragment
import com.yjhh.ppwbusiness.views.product.Product2Fragment
import com.yjhh.ppwbusiness.views.product.Product3Fragment
import com.yjhh.ppwbusiness.views.product.ProductAllFragment
import com.yjhh.ppwbusiness.views.reservation.ReservationOrderFragment
import kotlinx.android.synthetic.main.main2fragment.*

import java.util.*


class Main2Fragment : BaseMainFragment() {


    private val mTitles = arrayOf("全部订单", "未付款", "已付款")
    override fun getLayoutRes(): Int = R.layout.main2fragment


    override fun initView() {

        val fagments = ArrayList<BaseFragment>()

        fagments.add(Main2_1Fragment())
        fagments.add(Main2_2Fragment())
        fagments.add(Main2_3Fragment())
        // fagments.add(Product4Fragment())
        tv_to.setOnClickListener {


            (parentFragment as MainFragment).start(
                ReservationOrderFragment()
            )

        }




        mViewPager.adapter = MyPagerAdapter(childFragmentManager, fagments, mTitles)

        mViewPager.offscreenPageLimit = 3

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