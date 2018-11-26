package com.yjhh.ppwbusiness.fragments

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.GameRecordAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.utils.CommItemDecoration
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import kotlinx.android.synthetic.main.evaluatedetailsfragment.*
import kotlinx.android.synthetic.main.productallfragment.*


class GameRecordFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.gamerecordfragment


    var mAdapter: GameRecordAdapter? = null
    override fun initView() {

        recyclerView.layoutManager = LinearLayoutManager(mActivity)

        val list = ArrayList<String>()

        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        mAdapter = GameRecordAdapter(list)
        addHeadView()


        recyclerView.addItemDecoration(CommItemDecoration.createVertical(context, ContextCompat.getColor(mActivity,R.color.e6),1))



        recyclerView.adapter = mAdapter
    }


    fun addHeadView() {
        val gamerecordHeadview = View.inflate(mActivity, R.layout.gamerecordheadview, null)
        var tv_sTime = gamerecordHeadview.findViewById<TextView>(R.id.tv_sTime)
        var tv_eTime = gamerecordHeadview.findViewById<TextView>(R.id.tv_eTime)

        val stime = "开始时间:"
        tv_sTime.text = TextStyleUtils.changeTextColor(stime, 0, 5, Color.parseColor("#999999"))


        val etime = "结束时间:"
        tv_eTime.text = TextStyleUtils.changeTextColor(stime, 0, 5, Color.parseColor("#999999"))

        mAdapter?.addHeaderView(gamerecordHeadview)
    }
}