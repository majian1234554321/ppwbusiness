package com.yjhh.ppwbusiness.views.merchant

import android.support.v7.widget.LinearLayoutManager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.BusinessHoursAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.businesshoursfragment.*

class BusinessHoursFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.businesshoursfragment

    override fun initView() {


        val list = ArrayList<BusinessHoursBean>()

        list.add(BusinessHoursBean("08:00", "12:00"))
        list.add(BusinessHoursBean("12:00", "12:30"))

        list.add(BusinessHoursBean("14:00", "18:00"))


        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        recyclerView.adapter = BusinessHoursAdapter(list)

    }


    data class BusinessHoursBean(var sTime: String, var eTime: String)
}