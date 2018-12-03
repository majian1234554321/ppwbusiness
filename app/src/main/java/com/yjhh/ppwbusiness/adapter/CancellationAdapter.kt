package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.CancelationBeforeBean
import com.yjhh.ppwbusiness.views.cui.ItemEntryView2

class CancellationAdapter(data: List<CancelationBeforeBean.ItemsBean>) :
    BaseQuickAdapter<CancelationBeforeBean.ItemsBean, BaseViewHolder>(R.layout.cancellationadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: CancelationBeforeBean.ItemsBean?) {


        val iev1 = helper?.getView<ItemEntryView2>(R.id.iev1)
        val iev2 = helper?.getView<ItemEntryView2>(R.id.iev2)
        val iev3 = helper?.getView<ItemEntryView2>(R.id.iev3)



        item?.expiredTimeText?.let { iev1?.setTextContent(it) }

        item?.userName?.let { iev2?.setTextContent(it) }

        item?.shopName?.let { iev3?.setTextContent(it) }


    }
}