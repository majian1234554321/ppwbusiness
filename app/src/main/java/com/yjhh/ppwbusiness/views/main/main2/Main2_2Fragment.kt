package com.yjhh.ppwbusiness.views.main.main2


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.OrderAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.OrderBean
import com.yjhh.ppwbusiness.ipresent.OrderPresent
import com.yjhh.ppwbusiness.iview.OrderView

import kotlinx.android.synthetic.main.main2_1fragment.*
import java.util.ArrayList


class Main2_2Fragment : BaseFragment(), OrderView {
    override fun onSuccess(model: OrderBean, flag: String) {
        if ("refresh" == flag) {

            if (startindex == 0 && model.items.isEmpty()) {
                val view = View.inflate(mActivity, R.layout.emptyview, null)
                view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                mAdapter?.emptyView = view
            } else {
                mAdapter.setNewData(model.items)
            }


        } else {
            if (pageSize > model.items.size) {
                mAdapter.loadMoreEnd()
            } else {
                mAdapter.addData(model.items)
                mAdapter.loadMoreComplete()
            }

        }
    }

    override fun onFault(errorMsg: String?) {
        if (swipeLayout != null) {
            swipeLayout.finishRefresh()
        }
        if (startindex == 0) {
            val view = View.inflate(mActivity, R.layout.emptyview, null)
            view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
            mAdapter?.emptyView = view
        }
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        swipeLayout.autoRefresh()
    }

    var startindex = 0
    val pageSize = 15
    val status = "1"//状态，默认null（null/0 全部 1未付款 3已付款 4已完成）
    var list = ArrayList<OrderBean.ItemsBean>()
    lateinit var mAdapter: OrderAdapter

    override fun getLayoutRes(): Int = R.layout.main2_1fragment

    var present: OrderPresent? = null

    override fun initView() {


        mRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                mActivity,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )

        present = OrderPresent(context, this)

        initAdapter()
        initRefreshLayout()



        mAdapter.setOnItemClickListener { adapter, view, position ->

        }
    }

    private fun initAdapter() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        mAdapter = OrderAdapter(list)
        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mAdapter.isFirstOnly(false)
        mRecyclerView.adapter = mAdapter


    }

    private fun initRefreshLayout() {
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            swipeLayout.finishRefresh()
        }
    }

    private fun refresh() {
        startindex = 0
        present?.orders(status, startindex, pageSize, "refresh")

    }

    private fun loadMore() {
        startindex++
        present?.orders(status, startindex, pageSize, "load")

    }


}