package com.yjhh.ppwbusiness.views.writeoff

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.CancellationAdapter
import com.yjhh.ppwbusiness.adapter.WriteOffStoreAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.ipresent.ReservePresent
import kotlinx.android.synthetic.main.cancellationbeforefragment.*

class CancellationBeforeFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.cancellationbeforefragment

    val pageSize = 15
    var pageIndex = 0
    var status = "-1" //null全部 -1 历史 0 申请 1已接受 2用户取消 3商户取消 4已过时
    var peresent: ReservePresent? = null
    var mAdapter: CancellationAdapter? = null
    var lists = ArrayList<String>()

    override fun initView() {
        for (i in 1..9) {
            lists.add("A")
        }

        //initRefreshLayout()
        initAdapter()
        //  swipeLayout.autoRefresh()

        mAdapter?.setOnItemClickListener { adapter, view, position ->

            start(CancellationDetailsFragment())

        }
    }

    private fun initAdapter() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
        mAdapter?.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        mAdapter?.isFirstOnly(false)
        mAdapter = CancellationAdapter(lists)
        mRecyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mAdapter?.disableLoadMoreIfNotFullPage(mRecyclerView)
    }

    private fun initRefreshLayout() {
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
        }
    }

    private fun refresh() {
        pageIndex = 0
        peresent?.reserves(status, "", pageIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        pageIndex++
        peresent?.reserves(status, "", pageIndex, pageSize, "load")

    }


    companion object {
        fun newInstance(): CancellationBeforeFragment {

            val args = Bundle()

            val fragment = CancellationBeforeFragment()
            fragment.arguments = args
            return fragment
        }
    }


}