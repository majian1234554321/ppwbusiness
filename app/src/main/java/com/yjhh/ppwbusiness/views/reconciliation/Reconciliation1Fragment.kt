package com.yjhh.ppwbusiness.views.reconciliation

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.OrderAdapter
import com.yjhh.ppwbusiness.adapter.ReconciliationAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.fragments.MessageDetailFragment
import kotlinx.android.synthetic.main.reconciliation1fragment.*

class Reconciliation1Fragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.reconciliation1fragment


    var startindex = 0;

    var mAdapter: ReconciliationAdapter? = null
    val lists = ArrayList<String>()

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
        // sectionCouponPresent = SectionUselessPresent(context, this)
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)

        initAdapter()
        //  swipeLayout.autoRefresh()


        mAdapter?.setOnItemClickListener { adapter, view, position ->

            (parentFragment as BaseFragment).start(
                MessageDetailFragment()
            )

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
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()
        startindex++
        //  sectionCouponPresent.usermessage(status, share, startindex, pageSize, "load")

    }


}