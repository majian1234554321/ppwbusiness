package com.yjhh.ppwbusiness.adapter

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import anet.channel.util.h
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.tv_count
import com.yjhh.ppwbusiness.bean.DateBean
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import com.yjhh.ppwbusiness.views.cui.ExpandLayout

class ReservationOrderAdapter(var context: Context, data: List<DateBean>) :
    BaseQuickAdapter<DateBean, BaseViewHolder>(R.layout.reservationorderadapter, data) {

    var list = ArrayList<Int>()

    override fun convert(helper: BaseViewHolder?, item: DateBean?) {
        //

        val mExpandLayout = helper?.getView<ExpandLayout>(R.id.expandLayout)


        if (data.get(helper?.adapterPosition!!).flag){
            mExpandLayout?.collapse()
        }else{
            mExpandLayout?.expand()
        }

        val text = "人数：5    预计还有 0 小时 59 分钟到点"


        helper?.getView<Button>(R.id.button)?.setOnClickListener {
           mExpandLayout?.toggleExpand()
            data.get(helper.adapterPosition).flag =!data.get(helper.adapterPosition).flag

        }



        text.split(" ")

        val textString = SpannableString(text)


        textString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.all_3)),
            0,
            5,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        textString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.sjred)),
            text.indexOf("有") + 1,
            text.indexOf("小"),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        textString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.sjred)),
            text.indexOf("时") + 1,
            text.indexOf("分"),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )



        textString.setSpan(
            AbsoluteSizeSpan(25, true),
            text.indexOf("有") + 1,
            text.indexOf("小"),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textString.setSpan(
            AbsoluteSizeSpan(25, true),
            text.indexOf("时") + 1,
            text.indexOf("分"),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )


    }
}