package com.yjhh.ppwbusiness.views.main.main2


import android.os.Bundle
import android.util.Log
import com.chad.library.adapter.base.BaseQuickAdapter

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.OrderAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.OrderBean
import com.yjhh.ppwbusiness.ipresent.OrderPresent
import com.yjhh.ppwbusiness.iview.OrderView
import com.yjhh.ppwbusiness.views.cui.PPWHeader2
import kotlinx.android.synthetic.main.main2_1fragment.*
import java.util.ArrayList


class Main2_2Fragment : BaseFragment() , OrderView {
    override fun onSuccess(model: OrderBean, flag: String) {
        if ("refresh" == flag) {
            mAdapter.setNewData(model.items)
            swipeLayout.finishRefresh()

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
        swipeLayout.finishRefresh()
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
        swipeLayout.setRefreshHeader(PPWHeader2(context))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
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