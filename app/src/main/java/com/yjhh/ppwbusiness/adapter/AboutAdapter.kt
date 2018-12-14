package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.AboutBean
import com.yjhh.ppwbusiness.bean.AllFeedBackBean
import com.yjhh.ppwbusiness.fragments.A_FeedBackFragment
import com.yjhh.ppwbusiness.utils.TimeUtil
import com.yjhh.ppwbusiness.views.cui.ItemEntryView
import com.yjhh.ppwbusiness.views.webview.BackViewFragment


class AboutAdapter(data: List<AboutBean.FunctionsBean>) :
    BaseQuickAdapter<AboutBean.FunctionsBean, BaseViewHolder>(R.layout.aboutadapter, data) {


    override fun convert(helper: BaseViewHolder?, item: AboutBean.FunctionsBean?) {





        when (item?.name) {

            "客服电话" -> {
                helper?.getView<ItemEntryView>(R.id.iev_1)?.setLeftTitle(item?.name)?.setTextContent(item?.linkUrl.split("tels://")[1])?.setArrow()
            }
            else -> {
                helper?.getView<ItemEntryView>(R.id.iev_1)?.setLeftTitle(item?.name)?.setTextContent("")
            }
        }


    }
}