package com.yjhh.ppwbusiness.views.reservation

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.ReservationOrderAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.DateBean
import com.yjhh.ppwbusiness.bean.ReservationBean
import com.yjhh.ppwbusiness.ipresent.ReservePresent
import com.yjhh.ppwbusiness.iview.ReserveView
import com.yjhh.ppwbusiness.utils.DateUtil
import com.yjhh.ppwbusiness.utils.TimeUtil
import kotlinx.android.synthetic.main.reservationorderfragment.*

class ReservationOrderFragment : BaseFragment(), View.OnClickListener, ReserveView {
    override fun onSuccessReserve(model: ReservationBean, flag: String) {


        when (flag) {
            "refresh" -> {
                mAdapter?.setNewData(model.items)
            }

            "accept" -> {
                Toast.makeText(mActivity, "接受订单成功", Toast.LENGTH_LONG).show()
                mAdapter?.data!![model.positions].status = 1
                mAdapter?.notifyDataSetChanged()
            }


            else -> {
            }
        }
    }

    override fun onFault(errorMsg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    var lists = ArrayList<ReservationBean.ItemsBean>()
    override fun getLayoutRes(): Int = R.layout.reservationorderfragment


    override fun initView() {

        peresent = ReservePresent(mActivity, this)



        arrayOf(iv_back, tv_right)
            .forEach {
                it.setOnClickListener(this)
            }

        val dateList = ArrayList<DateBean>()

        for (i in 0 until 15) {
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


        dateList.forEach {

            mTabLayout.addTab(mTabLayout.newTab().setText(it.MMDD))
        }

        mTabLayout.tabMode = TabLayout.MODE_SCROLLABLE


        peresent?.reserves(status, dateList[2].YYMMDD, pageIndex, pageSize, "refresh")

        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = ReservationOrderAdapter(mActivity, lists)

        recyclerView.adapter = mAdapter









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
}