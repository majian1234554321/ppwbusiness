package com.yjhh.ppwbusiness.views.reconciliation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.OrderAdapter
import com.yjhh.ppwbusiness.adapter.ReconciliationAdapter

import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.DateBean
import com.yjhh.ppwbusiness.bean.ReconciliationItemBean

import com.yjhh.ppwbusiness.ipresent.WithDrawPresent
import com.yjhh.ppwbusiness.iview.WithDrowView
import kotlinx.android.synthetic.main.reconciliation1fragment.*
import java.nio.file.attribute.AclEntryFlag

class Reconciliation1Fragment : BaseFragment(), WithDrowView {
    override fun onSuccessView(response: String?, flag: String) {


        val model = Gson().fromJson<ReconciliationItemBean>(response, ReconciliationItemBean::class.java)



        when (flag) {
            "refresh" -> {
                if (pageIndex == 0 && model.items.size == 0) {


                    val view = View.inflate(mActivity, R.layout.emptyview, null)
                    view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                    mAdapter?.emptyView = view


                } else {
                    mAdapter?.setNewData(model.items)
                }
            }
            else -> {
                if (pageSize > model.items.size) {
                    mAdapter?.loadMoreEnd()
                } else {
                    mAdapter?.addData(model.items)
                    mAdapter?.loadMoreComplete()
                }
            }
        }

    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.reconciliation1fragment


    val pageSize = 15
    var pageIndex = 0

    val type = ""////类型 null全部 1收入 2支出


    var mAdapter: ReconciliationAdapter? = null
    val lists = ArrayList<ReconciliationItemBean.ItemsBean>()
    var present: WithDrawPresent? = null

    override fun initView() {

        mRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                mActivity,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )

        present = WithDrawPresent(mActivity, this)
        present?.shopAdminWithdrawLogs(type, pageIndex, pageSize, "refresh")
        initAdapter()
        mAdapter?.setOnItemClickListener { adapter, view, position ->


        }

    }


    private fun initAdapter() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        mAdapter = ReconciliationAdapter(lists)
        mRecyclerView.adapter = mAdapter
        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
    }


    private fun loadMore() {
        pageIndex++
        present?.shopAdminWithdrawLogs(type, pageIndex, pageSize, "load")

    }


}