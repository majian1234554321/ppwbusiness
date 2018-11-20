package com.yjhh.ppwbusiness.fragments

import android.support.v7.widget.LinearLayoutManager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.ActivityCenterAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.activitycenterfragment.*

class ActivityCenterFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.activitycenterfragment


    override fun initView() {
        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)

        val list = ArrayList<String>()
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")

     val mAdapter =    ActivityCenterAdapter(list)

        mRecyclerView.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            start(GameRecordFragment())
        }
    }
}