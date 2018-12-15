package com.yjhh.ppwbusiness.fragments

import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.ActivityCenterAdapter
import com.yjhh.ppwbusiness.api.ActivityCenterService
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.ActivityCenterBean
import com.yjhh.ppwbusiness.bean.ActivityCenterBean2
import com.yjhh.ppwbusiness.ipresent.ActivityCenterPrenent
import com.yjhh.ppwbusiness.iview.ActivityCenterView

import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activitycenterfragment.*

class ActivityCenterFragment : BaseFragment(), ActivityCenterView {
    override fun onFault(errorMsg: String?) {

    }

    override fun onSuccessView(response: String?, flag: String) {
        Log.i("ActivityCenterFragment", response)
        val model = Gson().fromJson<ActivityCenterBean2>(response, ActivityCenterBean2::class.java)

        when (flag) {
            "refresh" -> {

                    if (pageIndex == 0&&model.items.isEmpty()) {
                        val view = View.inflate(mActivity, R.layout.emptyview, null)
                        view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                        mAdapter?.emptyView = view
                    }else{
                        list.clear()
                        listAll.clear()
                        model.items.forEach {

                            list.add(ActivityCenterBean(1, it))
                        }
                        listAll.addAll(list)

                        mAdapter?.setNewData(listAll)
                    }






            }
            else -> {
                if (model.items != null) {
                    if (model.items.isNotEmpty() && model.items.size == pageSize) {
                        mAdapter?.loadMoreComplete()
                    } else {
                        mAdapter?.loadMoreEnd()

                    }

                    list.clear()
                    model.items.forEach {
                        //                        if (it.status == 1) {
//                            list.add(ActivityCenterBean(1, it))
//                        } else {
//                            list.add(ActivityCenterBean(0, it))
//                        }
                        list.add(ActivityCenterBean(1, it))
                    }
                    listAll.addAll(list)

                    mAdapter?.addData(list)
                }
            }
        }


    }

    val pageSize = 15
    var pageIndex = 0
    var mAdapter: ActivityCenterAdapter? = null
    val listAll = ArrayList<ActivityCenterBean>()

    val list = ArrayList<ActivityCenterBean>()

    var peresent: ActivityCenterPrenent? = null

    override fun getLayoutRes(): Int = R.layout.activitycenterfragment
    override fun initView() {

        peresent = ActivityCenterPrenent(mActivity, this)


//        list.add(ActivityCenterBean(1, "1"))
//        list.add(ActivityCenterBean(0, "已结束"))
//        list.add(ActivityCenterBean(1, "1"))
//        list.add(ActivityCenterBean(1, "1"))
//        list.add(ActivityCenterBean(1, "1"))
//        list.add(ActivityCenterBean(1, "1"))


        initRefreshLayout()
        initAdapter()
        swipeLayout.autoRefresh()



        mAdapter?.setOnItemClickListener { adapter, view, position ->
            start(GameRecordFragment.newInstance(listAll[position].content?.id))
        }
    }


    private fun initAdapter() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
        mRecyclerView.addItemDecoration(SpaceItemDecoration(30, "bottom"))
        mAdapter = ActivityCenterAdapter(listAll)
        mRecyclerView.adapter = mAdapter

        mAdapter?.isFirstOnly(false)
        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
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
        peresent?.ShopAdminActivity(pageIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        pageIndex++
        peresent?.ShopAdminActivity(pageIndex, pageSize, "load")

    }


}