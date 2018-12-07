package com.yjhh.ppwbusiness.fragments

import android.util.Log
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.AllFeedBackAdapter
import com.yjhh.ppwbusiness.adapter.FeedBackDetailsFragment
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.BaseView
import com.yjhh.ppwbusiness.ipresent.AboutPresent
import com.yjhh.ppwbusiness.ipresent.ReservePresent
import com.yjhh.ppwbusiness.iview.AboutView
import com.yjhh.ppwbusiness.views.cui.PPWHeader2
import kotlinx.android.synthetic.main.allfeedbackfragment.*

class AllFeedBackFragment : BaseFragment(), AboutView {
    override fun onSuccess(response: String?, flag: String?) {
        Log.i("AllFeedBackFragment", response)
        if (pageIndex == 0 && "refresh" == flag) {
            swipeLayout.finishRefresh()
            val view = View.inflate(mActivity, R.layout.emptyview, null)
            view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
            mAdapter?.emptyView = view
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
    var status = "-1" //null全部 -1 历史 0 申请 1已接受 2用户取消 3商户取消 4已过时
    var peresent: AboutPresent? = null
    var mAdapter: AllFeedBackAdapter? = null
    var lists = ArrayList<String>()


    override fun initView() {
        peresent = AboutPresent(mActivity, this)

        initRefreshLayout()
        initAdapter()
        swipeLayout.autoRefresh()


        mAdapter?.setOnItemClickListener { adapter, view, position ->
            start(FeedBackDetailsFragment())
        }
    }


    private fun initRefreshLayout() {
        swipeLayout.setRefreshHeader(PPWHeader2(context))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
        }
    }

    private fun initAdapter() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
        mAdapter?.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
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