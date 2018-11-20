package com.yjhh.ppwbusiness.views.main.main2


import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.ArrayMap
import android.util.Log
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.OrderAdapter
import com.yjhh.ppwbusiness.adapter.ProductAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionOrderService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.OrderBean
import com.yjhh.ppwbusiness.fragments.MessageDetailFragment
import com.yjhh.ppwbusiness.ipresent.OrderPresent
import com.yjhh.ppwbusiness.ipresent.SectionUselessPresent
import com.yjhh.ppwbusiness.iview.OrderView
import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main2_1fragment.*
import java.util.ArrayList


class Main2_1Fragment : BaseFragment(), OrderView {
    override fun onSuccess(model: OrderBean, flag: String) {
        if ("refresh" == flag) {
            mAdapter.setNewData(model.items)
            swipeLayout.finishRefresh()

        } else {
            mAdapter.addData(model.items)
            mAdapter.loadMoreComplete()
        }
    }

    override fun onFault(errorMsg: String?) {

    }


    var startindex = 0
    val pageSize = 15
    val status = "0"//状态，默认null（null/0 全部 1未付款 3已付款 4已完成）

    var list = ArrayList<OrderBean.ItemsBeanX>()

    lateinit var mAdapter: OrderAdapter

    override fun getLayoutRes(): Int = R.layout.main2_1fragment

    var present: OrderPresent? = null

    override fun initView() {

        mRecyclerView.addItemDecoration(DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL))
        mAdapter = OrderAdapter(list)
        present = OrderPresent(context, this)
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()


        mAdapter.setOnItemClickListener { adapter, view, position ->

//            start(
//                MessageDetailFragment()
//            )

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
        present?.orders(status, startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()
        startindex++
        present?.orders(status, startindex, pageSize, "load")

    }

}