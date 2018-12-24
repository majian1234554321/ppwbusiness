package com.yjhh.ppwbusiness.views.reconciliation

import com.flyco.tablayout.listener.OnTabSelectListener
import com.google.gson.Gson
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyPagerAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.ReconciliationBean
import com.yjhh.ppwbusiness.bean.WithDrawBean
import com.yjhh.ppwbusiness.ipresent.WithDrawPresent
import com.yjhh.ppwbusiness.iview.WithDrowView


import kotlinx.android.synthetic.main.reconciliationfragment.*

class ReconciliationFragment : BaseFragment(), WithDrowView {
    override fun onSuccessView(response: String?, flag: String) {
        val model = Gson().fromJson<ReconciliationBean>(response, ReconciliationBean::class.java)


        tv_in.text = BaseApplication.getIns().getString(R.string.rmb_price_double, model.totalIn)
        tv_out.text = BaseApplication.getIns().getString(R.string.rmb_price_double, model.totalOut)

        tv_price.text = BaseApplication.getIns().getString(R.string.rmb_price_double2, model.balance)


        tv_inCount.text = "共${model.todayLogs[0].count}笔"
        tv_inPrice.text = "+${BaseApplication.getIns().getString(R.string.rmb_price_double, model.todayLogs[0].money)}"

        tv_outCount.text = "共${model.todayLogs[1].count}笔"
        tv_outPrice.text = "-${BaseApplication.getIns().getString(R.string.rmb_price_double, model.todayLogs[1].money)}"

    }

    override fun onFault(errorMsg: String?) {

    }


    override fun getLayoutRes(): Int = R.layout.reconciliationfragment


    private val mTitles = arrayOf("全部", "收入", "支出")

    override fun initView() {

        tv_forward.setOnClickListener {
            start(PutForwardFragment())
        }

        iv_back.setOnClickListener {
            mActivity.onBackPressed()
        }

        val fagments = ArrayList<BaseFragment>()

        val f1 = Reconciliation1Fragment()
        val f2 = Reconciliation2Fragment()
        val f3 = Reconciliation3Fragment()

        fagments.add(f1)
        fagments.add(f2)
        fagments.add(f3)
        // fagments.add(Product4Fragment())

        WithDrawPresent(mActivity, this).shopAdminWithdrawIndex()
        // WithDrawPresent(mActivity).shopAdminWithdraw()
        // WithDrawPresent(mActivity).logs(0,15)


        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            f1.loadNetData()
            f2.loadNetData()
            f3.loadNetData()
            swipeLayout.finishRefresh()
        }


        mViewPager.adapter = MyPagerAdapter(childFragmentManager, fagments, mTitles)
        mTabLayout.setViewPager(mViewPager)
        mViewPager.offscreenPageLimit = fagments.size
        mTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                mViewPager.currentItem = position
            }

            override fun onTabReselect(position: Int) {
                if (position == 0) {

                } else {

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