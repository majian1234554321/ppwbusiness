package com.yjhh.ppwbusiness.fragments

import android.support.v7.widget.LinearLayoutManager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.GameRecordAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.evaluatedetailsfragment.*

class GameRecordFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.gamerecordfragment

    override fun initView() {

        recyclerView.layoutManager = LinearLayoutManager(mActivity)


        val list = ArrayList<String>()

        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")



        recyclerView.adapter = GameRecordAdapter(list)

    }
}