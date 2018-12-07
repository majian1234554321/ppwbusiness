package com.yjhh.ppwbusiness.views.reconciliation

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.OrderAdapter
import com.yjhh.ppwbusiness.adapter.ReconciliationAdapter
import com.yjhh.ppwbusiness.adapter.ReservationOrderAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.DateBean
import com.yjhh.ppwbusiness.bean.ReservationBean
import com.yjhh.ppwbusiness.fragments.MessageDetailFragment
import com.yjhh.ppwbusiness.ipresent.ReconciliationPresent
import com.yjhh.ppwbusiness.ipresent.ReservePresent
import com.yjhh.ppwbusiness.ipresent.SectionUselessPresent
import kotlinx.android.synthetic.main.reconciliation1fragment.*

class Reconciliation1Fragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.reconciliation1fragment


    val pageSize = 15
    var pageIndex = 0

    val type = ""////类型 null全部 1收入 2支出


    var mAdapter: ReconciliationAdapter? = null
    val lists = ArrayList<String>()
    var present: ReconciliationPresent? = null

    override fun initView() {


        mRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                mActivity,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        val list = ArrayList<String>()

        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        mAdapter = ReconciliationAdapter(list)
        present = ReconciliationPresent(context)
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)

        initAdapter()

        present?.logs(type, pageIndex, pageSize, "refresh")

        mAdapter?.setOnItemClickListener { adapter, view, position ->


        }

    }


    private fun initAdapter() {

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
        mAdapter?.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)


        mRecyclerView.adapter = mAdapter


    }


    private fun loadMore() {
        pageIndex++
        present?.logs(type, pageIndex, pageSize, "load")

    }


}