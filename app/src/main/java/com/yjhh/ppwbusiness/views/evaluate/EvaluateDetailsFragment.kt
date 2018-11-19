package com.yjhh.ppwbusiness.views.evaluate

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.EvaluateDetailsAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.evaluatedetailsfragment.*

class EvaluateDetailsFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.evaluatedetailsfragment

    var mAdapter: EvaluateDetailsAdapter? = null
    override fun initView() {
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

        mAdapter = EvaluateDetailsAdapter(list)
        addHeadView()
        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        recyclerView.adapter = mAdapter


    }

    private fun addHeadView() {
        val headView = View.inflate(mActivity, R.layout.evaluatedetailshead, null)
        mAdapter?.addHeaderView(headView)
    }
}