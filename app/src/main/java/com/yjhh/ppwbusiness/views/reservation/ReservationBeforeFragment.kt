package com.yjhh.ppwbusiness.views.reservation

import android.support.v7.widget.LinearLayoutManager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.ReservationBeforeAdapter
import com.yjhh.ppwbusiness.base.BaseFragment

import kotlinx.android.synthetic.main.reservationbeforefragment.*

class ReservationBeforeFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.reservationbeforefragment

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(mActivity)


        var dateList = ArrayList<String>()

        for (i in 0 until 15) {

            dateList.add("qwqwqwqw")

        }

        var mAdapter = ReservationBeforeAdapter(dateList)

        recyclerView.adapter = mAdapter
    }

}