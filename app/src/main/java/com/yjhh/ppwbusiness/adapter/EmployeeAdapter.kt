package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.EmployeeBean
import com.yjhh.ppwbusiness.bean.MyMessageBean


class EmployeeAdapter(var data: ArrayList<EmployeeBean>, var display: Boolean) :
    BaseQuickAdapter<EmployeeBean, BaseViewHolder>(R.layout.employeeadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: EmployeeBean?) {

        helper?.setText(R.id.tv_name, item?.name)

        helper?.setVisible(R.id.tv_update, display)

        helper?.setText(R.id.tv_phone, item?.displayMobile)
    }


    override fun getItemCount(): Int {
        return if (data.size > 5) 5 else data.size
    }


}
