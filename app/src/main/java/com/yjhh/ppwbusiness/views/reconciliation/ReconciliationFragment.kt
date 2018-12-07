package com.yjhh.ppwbusiness.views.reconciliation

import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyPagerAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ReconciliationService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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