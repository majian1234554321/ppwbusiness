package com.yjhh.ppwbusiness.views.reservation

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.ReservationBeforeAdapter
import com.yjhh.ppwbusiness.adapter.ReservationOrderAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.DateBean
import com.yjhh.ppwbusiness.bean.ProductBean
import com.yjhh.ppwbusiness.bean.ReservationBean
import com.yjhh.ppwbusiness.ipresent.ReservePresent
import com.yjhh.ppwbusiness.iview.ReserveView
import com.yjhh.ppwbusiness.utils.DateUtil
import com.yjhh.ppwbusiness.utils.TimeUtil

import com.yjhh.ppwbusiness.views.product.ProductAddFragment
import kotlinx.android.synthetic.main.reservationorderfragment.*

class ReservationOrderFragment : BaseFragment(), View.OnClickListener, ReserveView {
    override fun onSuccessReserve(model: ReservationBean, flag: String) {


        when (flag) {
            "refresh" -> {
                mAdapter?.setNewData(model.items)
                if(swipeLayout!=null){
                    swipeLayout.finishRefresh()
                }



                if (pageIndex==0&&model.items.isEmpty()){
                    mAdapter?.setEmptyView(R.layout.emptyview, mRecyclerView.parent as ViewGroup)
                }

            }

            "accept" -> {
                Toast.makeText(mActivity, "接受订单成功", Toast.LENGTH_LONG).show()
                mAdapter?.data!![model.positions].status = 1
                mAdapter?.notifyDataSetChanged()
            }


            "load" -> {
                mAdapter?.addData(model.items)
                if (model.items.size < pageSize) {
                    mAdapter?.loadMoreEnd()
                } else {

                    mAdapter?.loadMoreComplete()
                }

            }


            else -> {
            }
        }
    }

    override fun onFault(errorMsg: String?) {
        if(swipeLayout!=null){
            swipeLayout.finishRefresh()
        }
        if (pageIndex==0){
            mAdapter?.setEmptyView(R.layout.emptyview, mRecyclerView.parent as ViewGroup)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                mActivity.onBackPressed()
            }

            R.id.tv_right -> {
                start(ReservationBeforeFragment())

            }
            else -> {
            }
        }
    }


    val pageSize = 15
    var pageIndex = 0
    var status = ""
    var peresent: ReservePresent? = null
    var mAdapter: ReservationOrderAdapter? = null
    val dateList = ArrayList<DateBean>()
    var lists = ArrayList<ReservationBean.ItemsBean>()

    var date: String? = null

    override fun getLayoutRes(): Int = R.layout.reservationorderfragment


    override fun initView() {

        peresent = ReservePresent(mActivity, this)



        arrayOf(iv_back, tv_right)
            .forEach {
                it.setOnClickListener(this)
            }


        for (i in 0 until 30) {
            val bean = DateBean()
            bean.YYMMDD = DateUtil.getFetureDate2(i, "YMD")
            bean.week = DateUtil.dayForWeek(DateUtil.getFetureDate2(i, "YMD"))
            bean.MMDD = DateUtil.getFetureDate(i, "MD")
            bean.timeStamp = TimeUtil.dateToStamp(DateUtil.getFetureDate2(i, "YMD"))
            dateList.add(bean)

        }
        val bean = DateBean()
        bean.MMDD = "即将到时"
        dateList.add(0, bean)



     val index =    arguments?.getInt("index",0)

        dateList.forEach {
            mTabLayout.addTab(mTabLayout.newTab().setText(it.MMDD))
        }
        mTabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        index?.let { mTabLayout.getTabAt(it)?.select() };
        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    date = dateList[p0.position].YYMMDD

                    refresh()
                }
            }

        })



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
        mAdapter?.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.mb_cancel -> {
                    startForResult(
                        CancelReServationFragment.newInstance(
                            (adapter.data[position] as ReservationBean.ItemsBean).id,
                            position.toString()
                        ), 10086
                    )
                }

                R.id.mb_accept -> {
                    //类型(0接受 1取消)

                    peresent?.acceptReserve(
                        (adapter.data[position] as ReservationBean.ItemsBean).id,
                        "0",
                        "",
                        "accept",
                        position
                    )
                }
                else -> {
                }
            }
        }


    }


    private fun initAdapter() {
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
        mAdapter = ReservationOrderAdapter(mActivity, lists)
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
        }
    }

    private fun refresh() {
        pageIndex = 0
        peresent?.reserves(status, date, pageIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        pageIndex++
        peresent?.reserves(status, date, pageIndex, pageSize, "load")

    }


    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)
        if (requestCode == 10086) {


            val position = data?.getInt("ids")
            if (position != null) {
                mAdapter?.data?.get(position)?.status = 3
                mAdapter?.notifyDataSetChanged()
            }


        }

    }


    companion object {
        fun newInstance( index: Int?): ReservationOrderFragment {
            val fragment = ReservationOrderFragment()
            val bundle = Bundle()
            index?.let { bundle.putInt("index", it) }

            fragment.arguments = bundle
            return fragment
        }
    }




}