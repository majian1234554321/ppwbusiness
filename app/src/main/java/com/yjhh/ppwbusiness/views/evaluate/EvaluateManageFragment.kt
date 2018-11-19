package com.yjhh.ppwbusiness.views.evaluate

import android.support.v7.widget.LinearLayoutManager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.EvaluateManageAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.evaluatemanagefragment.*

class EvaluateManageFragment : BaseFragment() {

    private val mTitles = arrayOf("全部评价", "好评", "中评", "差评")

    override fun getLayoutRes(): Int = R.layout.evaluatemanagefragment

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(mActivity)


        val list = ArrayList<String>()
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")


        val mAdapter = EvaluateManageAdapter(mActivity, list)
        recyclerView.adapter = mAdapter


        mAdapter.setOnItemClickListener { adapter, view, position ->
            start(EvaluateDetailsFragment())

        }

    }


}