package com.yjhh.ppwbusiness.views.reconciliation

import android.support.v7.widget.LinearLayoutManager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.PutForwardAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.putforwardfragment.*

class PutForwardFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.putforwardfragment

    override fun initView() {


        val list = ArrayList<String>()

        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")
        list.add("a")


        tv_apply.setOnClickListener {
            start(PutForwardSuccessFragment())
        }


        recyclerView.layoutManager = LinearLayoutManager(mActivity)

        recyclerView.adapter = PutForwardAdapter(list)


    }
}