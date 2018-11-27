package com.yjhh.ppwbusiness.views.reservation

import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.ReservationOrderAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.DateBean
import com.yjhh.ppwbusiness.utils.DateUtil
import com.yjhh.ppwbusiness.utils.TimeUtil
import kotlinx.android.synthetic.main.reservationorderfragment.*

class ReservationOrderFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                mActivity.onBackPressed()
            }

            R.id.tv_right -> {
                //start(ReservationBeforeFragment())
                start(ReservationDetailFragment())
            }
            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.reservationorderfragment


    override fun initView() {

        arrayOf(iv_back, tv_right)
            .forEach {
                it.setOnClickListener(this)
            }

        var dateList = ArrayList<DateBean>()

        for (i in 0 until 15) {
            val bean = DateBean()
            bean.YYMMDD = DateUtil.getFetureDate(i, "YMD")
            bean.week = DateUtil.dayForWeek(DateUtil.getFetureDate(i, "YMD"))
            bean.MMDD = DateUtil.getFetureDate(i, "MD")
            bean.timeStamp = TimeUtil.dateToStamp(DateUtil.getFetureDate(i, "YMD"))
            dateList.add(bean)

        }
        val bean = DateBean()
        bean.MMDD = "即将到时"
        dateList.add(0, bean)


        dateList.forEach {

            mTabLayout.addTab(mTabLayout.newTab().setText(it.MMDD))
        }

        mTabLayout.tabMode = TabLayout.MODE_SCROLLABLE


        var dateList2 = ArrayList<String>()

        for (i in 0 until 15) {

            dateList2.add("qwqwqwqw")

        }
        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        var mAdapter = ReservationOrderAdapter(mActivity, dateList2)

        recyclerView.adapter = mAdapter


        mAdapter.setOnItemClickListener { adapter, view, position ->
            // recyclerView.scrollToPosition(position)

            ((recyclerView.layoutManager) as LinearLayoutManager).scrollToPositionWithOffset(position, 0)
        }

    }
}