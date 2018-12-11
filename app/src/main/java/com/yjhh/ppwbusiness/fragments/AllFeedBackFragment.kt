package com.yjhh.ppwbusiness.fragments

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.gson.Gson
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.AllFeedBackAdapter
import com.yjhh.ppwbusiness.adapter.FeedBackDetailsFragment
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.BaseView
import com.yjhh.ppwbusiness.bean.AllFeedBackBean
import com.yjhh.ppwbusiness.ipresent.AboutPresent
import com.yjhh.ppwbusiness.ipresent.ReservePresent
import com.yjhh.ppwbusiness.iview.AboutView

import kotlinx.android.synthetic.main.allfeedbackfragment.*
import com.yjhh.ppwbusiness.R.id.recyclerView



class AllFeedBackFragment : BaseFragment(), AboutView {
    override fun onSuccess(response: String?, flag: String?) {
        Log.i("AllFeedBackFragment", response)


        val modle = Gson().fromJson<AllFeedBackBean>(response, AllFeedBackBean::class.java)


        when (flag) {
            "refresh" -> {
                if (pageIndex == 0 && "refresh" == flag && modle.items != null && modle.items.isEmpty()) {
                    swipeLayout.finishRefresh()
                    val view = View.inflate(mActivity, R.layout.emptyview, null)
                    view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                    mAdapter?.emptyView = view
                } else {
                    swipeLayout.finishRefresh()
                    mAdapter?.setNewData(modle.items)


                }
            }
            else -> {
                if (modle.items != null && modle.items.size == pageSize) {
                    mAdapter?.addData(modle.items)
                    mAdapter?.loadMoreComplete()
                } else {
                    mAdapter?.loadMoreEnd()
                }
            }
        }


    }

    override fun onFault(errorMsg: String?) {
        if (pageIndex == 0) {
            swipeLayout.finishRefresh()
            val view = View.inflate(mActivity, R.layout.emptyview, null)
            view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
            mAdapter?.emptyView = view
        }
    }

    override fun getLayoutRes(): Int = R.layout.allfeedbackfragment

    val pageSize = 15
    var pageIndex = 0

    var peresent: AboutPresent? = null
    var mAdapter: AllFeedBackAdapter? = null
    var lists = ArrayList<AllFeedBackBean.ItemsBean>()


    override fun initView() {
        peresent = AboutPresent(mActivity, this)

        initRefreshLayout()
        initAdapter()
        swipeLayout.autoRefresh()


        mAdapter?.setOnItemClickListener { adapter, view, position ->
            start(FeedBackDetailsFragment.newInstance( (adapter.data.get(position) as AllFeedBackBean.ItemsBean ).id))
        }
    }


    private fun initRefreshLayout() {
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
        }
    }

    private fun initAdapter() {
        mRecyclerView.addItemDecoration(DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL))
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)

        mAdapter?.isFirstOnly(false)
        mAdapter = AllFeedBackAdapter(lists)
        mRecyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mAdapter?.disableLoadMoreIfNotFullPage(mRecyclerView)
    }


    private fun refresh() {
        pageIndex = 0
        peresent?.feedbackList(pageIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        pageIndex++
        peresent?.feedbackList(pageIndex, pageSize, "load")

    }
}