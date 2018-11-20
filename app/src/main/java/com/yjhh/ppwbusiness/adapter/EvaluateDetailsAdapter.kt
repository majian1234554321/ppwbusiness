package com.yjhh.ppwbusiness.adapter

import android.support.v4.app.SupportActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.EvaluateManageBean

class EvaluateDetailsAdapter(data: List<EvaluateManageBean.ItemsBean>):BaseQuickAdapter<EvaluateManageBean.ItemsBean,BaseViewHolder>(R.layout.evaluatedetailsadapter,data){
    override fun convert(helper: BaseViewHolder?, item: EvaluateManageBean.ItemsBean?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}