package com.yjhh.ppwbusiness.views.writeoff

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.CancellationAdapter
import com.yjhh.ppwbusiness.adapter.WriteOffStoreAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2.constructor.gson
import com.yjhh.ppwbusiness.bean.CancelationBeforeBean
import com.yjhh.ppwbusiness.ipresent.CancellationPresent
import com.yjhh.ppwbusiness.ipresent.ReservePresent
import com.yjhh.ppwbusiness.iview.CancellationView

import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import kotlinx.android.synthetic.main.cancellationbeforefragment.*


class CancellationBeforeFragment : BaseFragment(), CancellationView {
    override fun onSuccessCancellation(response: String?, flag: String?) {
        val model = gson.fromJson<CancelationBeforeBean>(
            response,
            CancelationBeforeBean::class.java
        )


        if (model.items != null) {

            if ("refresh" == flag) {
                swipeLayout.finishRefresh()
                mAdapter?.setNewData(model.items)
            } else {
                mAdapter?.addData(model.items)
                if (model.items.size < pageSize) {
                    mAdapter?.loadMoreEnd()
                } else {
                    mAdapter?.loadMoreComplete()
                }
            }


        }


    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.cancellationbeforefragment

    val pageSize = 15
    var pageIndex = 0
    var status = "-1" //null全部 -1 历史 0 申请 1已接受 2用户取消 3商户取消 4已过时
    var peresent: CancellationPresent? = null
    var mAdapter: CancellationAdapter? = null
    var lists = ArrayList<CancelationBeforeBean.ItemsBean>()

    override fun initView() {


        peresent = CancellationPresent(mActivity, this)

        initRefreshLayout()
        initAdapter()
        swipeLayout.autoRefresh()

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            start(CancellationDetailsFragment.newInstance((adapter.data[position] as CancelationBeforeBean.ItemsBean).id))
        }
    }

    private fun initAdapter() {


      //  mRecyclerView.addItemDecoration(SpaceItemDecoration(30, "bottom"))

        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)

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
        peresent?.history(pageIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        pageIndex++
        peresent?.history(pageIndex, pageSize, "load")

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