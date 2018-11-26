package com.yjhh.ppwbusiness.fragments

import android.support.v7.widget.LinearLayoutManager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.ActivityCenterAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.ActivityCenterBean
import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import kotlinx.android.synthetic.main.activitycenterfragment.*

class ActivityCenterFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.activitycenterfragment


    override fun initView() {


        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        mRecyclerView.addItemDecoration(SpaceItemDecoration(30, "bottom"))


        val list = ArrayList<ActivityCenterBean>()
        list.add(ActivityCenterBean(1, "1"))
        list.add(ActivityCenterBean(0, "已结束"))
        list.add(ActivityCenterBean(1, "1"))
        list.add(ActivityCenterBean(1, "1"))
        list.add(ActivityCenterBean(1, "1"))
        list.add(ActivityCenterBean(1, "1"))


        val mAdapter = ActivityCenterAdapter(list)

        mRecyclerView.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, view, position ->
            start(GameRecordFragment())
        }
    }
}