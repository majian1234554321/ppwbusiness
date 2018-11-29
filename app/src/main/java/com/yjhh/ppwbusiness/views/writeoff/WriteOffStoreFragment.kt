package com.yjhh.ppwbusiness.views.writeoff

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jakewharton.rxbinding2.widget.RxTextView
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.WriteOffStoreAdapter
import com.yjhh.ppwbusiness.base.BaseFragment

import com.yjhh.ppwbusiness.ipresent.ReservePresent
import kotlinx.android.synthetic.main.writeoffstorefragment.*

class WriteOffStoreFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.writeoffstorefragment


    val pageSize = 15
    var pageIndex = 0
    var status = "-1" //null全部 -1 历史 0 申请 1已接受 2用户取消 3商户取消 4已过时
    var peresent: ReservePresent? = null
    var mAdapter: WriteOffStoreAdapter? = null
    var lists = ArrayList<String>()

    override fun initView() {

        for (i in 1..9) {
            lists.add("A")
        }

        //initRefreshLayout()
        initAdapter()
        //  swipeLayout.autoRefresh()


        mAdapter?.setOnItemClickListener { adapter, view, position ->
            val bundle = Bundle()
            bundle.putString("ids", lists[position])

            setFragmentResult(RESULT_OK, bundle)

            mActivity.onBackPressed()

        }


        val dis = RxTextView.textChanges(et_search).subscribe {

        }


    }


    private fun initAdapter() {
        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        mAdapter?.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        mAdapter?.isFirstOnly(false)
        mAdapter = WriteOffStoreAdapter(lists)
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


}