package com.yjhh.ppwbusiness.fragments

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.tencent.mm.opensdk.utils.Log
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyMessageFragmentAdapter
import com.yjhh.ppwbusiness.adapter.MyPagerAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.MyMessageBean
import com.yjhh.ppwbusiness.ipresent.SectionUselessPresent
import com.yjhh.ppwbusiness.iview.MyMessageView

import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration

import kotlinx.android.synthetic.main.messagecenter1fragment.*


class MessageCenter2Fragment : BaseFragment(), MyMessageView {

    val array = arrayOf("我的消息", "系统消息")
    override fun getLayoutRes(): Int = R.layout.messagecenter1fragment


    var status = "-1"//状态，默认null(null/-1 全部 0未生效 1 有效的 2已过期的/失效的)
    override fun onSuccess(main1bean: MyMessageBean, flag: String) {
        if ("refresh" == flag) {
            if (startindex==0&&main1bean.items.isEmpty()) {
                val view = View.inflate(mActivity, R.layout.emptyview, null)
                view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                mRecyclerView.addItemDecoration(SpaceItemDecoration(0))
                mAdapter.emptyView = view
            }else{
                mAdapter.setNewData(main1bean.items as ArrayList<MyMessageBean.ItemsBean>)
            }

        } else {
            if (main1bean.items.size<pageSize){
                mAdapter.addData(main1bean.items as ArrayList<MyMessageBean.ItemsBean>)
                mAdapter.loadMoreEnd()
            }else{
                mAdapter.addData(main1bean.items as ArrayList<MyMessageBean.ItemsBean>)
                mAdapter.loadMoreComplete()
            }
        }
    }

    override fun onFault(errorMsg: String?) {
        val view = View.inflate(mActivity, R.layout.emptyview, null)
        view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
        mRecyclerView.addItemDecoration(SpaceItemDecoration(0))
        mAdapter.emptyView = view
    }

    var startindex = 0
    val pageSize = 15

    var share = "0"


    lateinit var sectionCouponPresent: SectionUselessPresent


    var list = ArrayList<MyMessageBean.ItemsBean>()

    lateinit var mAdapter: MyMessageFragmentAdapter

    override fun initView() {



        mRecyclerView.addItemDecoration(SpaceItemDecoration(30,"bottom"))
        mAdapter = MyMessageFragmentAdapter(list)
        sectionCouponPresent = SectionUselessPresent(context, this)
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()
        mAdapter.setOnItemClickListener { adapter, view, position ->


            (parentFragment as BaseFragment).start(
                MessageDetailFragment.newInstance(adapter.data[position] as MyMessageBean.ItemsBean)
            )

        }

    }


    private fun initAdapter() {

        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)



        mRecyclerView.adapter = mAdapter


    }


    private fun initRefreshLayout() {

        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
            refreshLayout.finishRefresh()
        }


    }


    private fun refresh() {

        startindex = 0
        sectionCouponPresent.usermessage(status, share, startindex, pageSize, "refresh")

    }


    private fun loadMore() {


        startindex++
        sectionCouponPresent.usermessage(status, share, startindex, pageSize, "load")

    }



}
