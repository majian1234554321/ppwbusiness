package com.yjhh.ppwbusiness.views.reconciliation

import android.support.v4.view.ViewPager
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyPagerAdapter
import com.yjhh.ppwbusiness.base.BaseFragment

import kotlinx.android.synthetic.main.reconciliationfragment.*

class ReconciliationFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.reconciliationfragment


    private val mTitles = arrayOf("全部", "收款", "提现")

    override fun initView() {

        tv_forward.setOnClickListener {
            start(PutForwardFragment())
        }

        iv_back.setOnClickListener{
            mActivity.onBackPressed()
        }

        val fagments = ArrayList<BaseFragment>()

        fagments.add(Reconciliation1Fragment())
        fagments.add(Reconciliation2Fragment())
        fagments.add(Reconciliation3Fragment())
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