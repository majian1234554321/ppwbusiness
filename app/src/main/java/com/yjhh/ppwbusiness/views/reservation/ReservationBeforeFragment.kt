package com.yjhh.ppwbusiness.views.reservation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.EvaluateManageAdapter
import com.yjhh.ppwbusiness.adapter.ReservationBeforeAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.ReservationBean
import com.yjhh.ppwbusiness.ipresent.ReservePresent
import com.yjhh.ppwbusiness.iview.ReserveView

import com.yjhh.ppwbusiness.views.cui.TabEntity

import kotlinx.android.synthetic.main.reservationbeforefragment.*

class ReservationBeforeFragment : BaseFragment(), ReserveView {
    override fun onSuccessReserve(model: ReservationBean, flag: String) {
        if ("refresh" == flag) {

            if (swipeLayout!=null){
                swipeLayout.finishRefresh()
            }

            if (pageIndex==0&&model.items.size==0){

                val view = View.inflate(mActivity, R.layout.emptyview, null)
                view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                mAdapter?.emptyView = view
            }else{
                mAdapter?.setNewData(model.items)

            }




        } else {
            mAdapter?.addData(model.items)
            if (model.items.size<pageSize){
                mAdapter?.loadMoreEnd()
            }else{

                mAdapter?.loadMoreComplete()
            }
        }
    }

    override fun onFault(errorMsg: String?) {
        if (swipeLayout!=null){
            swipeLayout.finishRefresh()
        }
        val view = View.inflate(mActivity, R.layout.emptyview, null)
        view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
        mAdapter?.emptyView = view
    }

    override fun getLayoutRes(): Int = R.layout.reservationbeforefragment


    val pageSize = 15
    var pageIndex = 0
    var status = "-1" //null全部 -1 历史 0 申请 1已接受 2用户取消 3商户取消 4已过时
    var peresent: ReservePresent? = null
    var mAdapter: ReservationBeforeAdapter? = null
    var lists = ArrayList<ReservationBean.ItemsBean>()


    override fun initView() {

        peresent = ReservePresent(mActivity, this)

        initRefreshLayout()
        initAdapter()
        swipeLayout.autoRefresh()

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            when ((adapter.data[position] as ReservationBean.ItemsBean).status) {
                0 -> {
                    start(
                        ReservationDetailFragment.newInstance(
                            "等待接受预约",
                            (adapter.data[position] as ReservationBean.ItemsBean).id
                        )
                    )
                }
                1 -> {
                    start(
                        ReservationDetailFragment.newInstance(
                            "已接受预约",
                            (adapter.data[position] as ReservationBean.ItemsBean).id
                        )
                    )
                }
                2 -> {
                    start(
                        ReservationDetailFragment.newInstance(
                            "已取消预约",
                            (adapter.data[position] as ReservationBean.ItemsBean).id
                        )
                    )
                }
                3 -> {
                    start(
                        ReservationDetailFragment.newInstance(
                            "已取消预约",
                            (adapter.data[position] as ReservationBean.ItemsBean).id
                        )
                    )
                }


                else -> {
                    start(
                        ReservationDetailFragment.newInstance(
                            "已过时",
                            (adapter.data[position] as ReservationBean.ItemsBean).id
                        )
                    )
                }
            }
        }


    }

    private fun initAdapter() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)

        mAdapter?.isFirstOnly(false)
        mAdapter = ReservationBeforeAdapter(lists)
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
            swipeLayout.finishRefresh()
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