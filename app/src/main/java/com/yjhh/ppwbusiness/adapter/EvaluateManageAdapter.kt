package com.yjhh.ppwbusiness.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.BaseApplication.context
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.views.evaluate.ninegrid.NineGridView
import com.yjhh.ppwbusiness.views.evaluate.ninegrid.NineGridViewAdapter
import com.yjhh.ppwbusiness.views.evaluate.ninegrid.NineGridViewClickAdapter

class EvaluateManageAdapter(var context: Context, data: List<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.evaluatemanageadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        helper?.getView<NineGridView>(R.id.nineGrid)?.setAdapter(NineGridViewClickAdapter(context, data))


    }
}