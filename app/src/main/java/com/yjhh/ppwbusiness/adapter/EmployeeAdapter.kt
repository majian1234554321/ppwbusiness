package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.MyMessageBean


class EmployeeAdapter(var data: ArrayList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.employeeadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {


    }


    override fun getItemCount(): Int {
        return if (data.size > 5) 5 else data.size
    }


}
