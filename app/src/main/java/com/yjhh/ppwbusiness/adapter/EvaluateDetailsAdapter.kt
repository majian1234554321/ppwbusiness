package com.yjhh.ppwbusiness.adapter

import android.support.v4.app.SupportActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.EvaluateManageBean
import com.yjhh.ppwbusiness.bean.EvaluateManageItemBean

class EvaluateDetailsAdapter(data: List<EvaluateManageItemBean>):BaseQuickAdapter<EvaluateManageItemBean,BaseViewHolder>(R.layout.evaluatedetailsadapter,data){
    override fun convert(helper: BaseViewHolder?, item: EvaluateManageItemBean?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}