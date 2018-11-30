package com.yjhh.ppwbusiness.views.main.main1

import androidx.recyclerview.widget.LinearLayoutManager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.PFMainAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.pfmainfragment.*
import androidx.recyclerview.widget.DividerItemDecoration
import com.yjhh.ppwbusiness.R.id.recyclerView



class PFMainFragment:BaseFragment(){
    override fun getLayoutRes(): Int  = R.layout.pfmainfragment


    override fun initView() {
       // sRefreshLayout
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
       val  list =   ArrayList<String>()
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")
        list.add("A")

        recyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                mActivity,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )

        recyclerView.adapter = PFMainAdapter(list)




    }

}
