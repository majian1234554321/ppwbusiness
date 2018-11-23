package com.yjhh.ppwbusiness.views.reservation

import android.support.design.widget.TabLayout
import android.view.View
import android.widget.TextView
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.DateBean
import com.yjhh.ppwbusiness.utils.DateUtil
import com.yjhh.ppwbusiness.utils.TimeUtil
import kotlinx.android.synthetic.main.reservationorderfragment.*

class ReservationOrderFragment:BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.reservationorderfragment


    override fun initView() {

        var dateList = ArrayList<DateBean>()

        for (i in 0 until 15) {
            val bean = DateBean()
            bean.YYMMDD = DateUtil.getFetureDate(i, "YMD")
            bean.week = DateUtil.dayForWeek(DateUtil.getFetureDate(i, "YMD"))
            bean.MMDD = DateUtil.getFetureDate(i, "MD")
            bean.timeStamp = TimeUtil.dateToStamp(DateUtil.getFetureDate(i, "YMD"))
            dateList.add(bean)
            val view = View.inflate(mActivity, R.layout.date15adapter, null)
            view.findViewById<TextView>(R.id.tv_MD).text = bean.MMDD
            view.findViewById<TextView>(R.id.tv_week).text = bean.week

            mTabLayout.addTab(mTabLayout.newTab())

            mTabLayout.getTabAt(i)?.customView = view
        }

        mTabLayout.setSelectedTabIndicator(0)
        mTabLayout.tabMode = TabLayout.MODE_SCROLLABLE
    }
}