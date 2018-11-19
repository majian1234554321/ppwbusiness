package com.yjhh.ppwbusiness.views.main.main2


import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.OrderAdapter
import com.yjhh.ppwbusiness.adapter.ProductAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.fragments.MessageDetailFragment
import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import kotlinx.android.synthetic.main.main2_1fragment.*
import java.util.ArrayList


class Main2_1Fragment : BaseFragment() {


    var startindex = 0
    val pageSize = 10

    var share = ""
    var list = ArrayList<String>()

    lateinit var mAdapter: OrderAdapter

    override fun getLayoutRes(): Int = R.layout.main2_1fragment

    override fun initView() {



        mRecyclerView.addItemDecoration(DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL))

        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        mAdapter = OrderAdapter(list)
        // sectionCouponPresent = SectionUselessPresent(context, this)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        //  swipeLayout.autoRefresh()


        mAdapter.setOnItemClickListener { adapter, view, position ->

            (parentFragment as BaseFragment).start(
                MessageDetailFragment()
            )

        }
    }


    private fun initAdapter() {

        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)


        mRecyclerView.adapter = mAdapter


    }


    private fun initRefreshLayout() {
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
        }
    }


    private fun refresh() {
        startindex = 0
        //sectionCouponPresent.usermessage(status, share, startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()
        startindex++
        //  sectionCouponPresent.usermessage(status, share, startindex, pageSize, "load")

    }

}