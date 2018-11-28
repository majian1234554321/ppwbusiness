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
import com.yjhh.ppwbusiness.R.id.tv_status
import com.yjhh.ppwbusiness.bean.DateBean
import com.yjhh.ppwbusiness.bean.ReservationBean
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import com.yjhh.ppwbusiness.utils.TimeUtil
import com.yjhh.ppwbusiness.views.cui.ExpandLayout
import java.lang.StringBuilder

class ReservationOrderAdapter(var context: Context, data: List<ReservationBean.ItemsBean>) :
    BaseQuickAdapter<ReservationBean.ItemsBean, BaseViewHolder>(R.layout.reservationorderadapter, data) {

    var list = ArrayList<Int>()


    override fun convert(helper: BaseViewHolder?, item: ReservationBean.ItemsBean?) {


        val times = TimeUtil.secondToTime(item?.timeTotal!!)


        val sb = StringBuilder()
        sb.append(" 人数：${item?.userCount} 预计还有 ")

        if (times.split("$")[0].toLong() > 0) {
            sb.append(times.split("$")[0]).append(" 天 ")
        }

        if (times.split("$")[1].toLong() >= 0) {
            sb.append(times.split("$")[1]).append(" 小时 ")
        }

        if (times.split("$")[2].toLong() >= 0) {
            sb.append(times.split("$")[2]).append(" 分钟到店")
        }



        helper?.setText(R.id.tv_count, TextStyleUtils.stytle(sb.toString(), context))

        helper?.setText(R.id.tv_phone, item.userMobile)

        helper?.setText(R.id.tv_name, item?.userName)

        helper?.addOnClickListener(R.id.mb_cancel)?.addOnClickListener(R.id.mb_accept)


        when (item.status) {
            0 -> {
                helper?.setVisible(R.id.mb_cancel, true)
                helper?.setVisible(R.id.mb_accept, true)
                helper?.setVisible(R.id.tv_status, false)
                helper?.setText(R.id.tv_status, "")
            }

            1 -> {
                helper?.setVisible(R.id.mb_cancel, false)
                helper?.setVisible(R.id.mb_accept, false)
                helper?.setVisible(R.id.tv_status, true)
                helper?.setTextColor(R.id.tv_status, Color.parseColor("#54B36D"))
                helper?.setText(R.id.tv_status, item.statusText)
            }


            else -> {
                helper?.setVisible(R.id.mb_cancel, false)
                helper?.setVisible(R.id.mb_accept, false)
                helper?.setVisible(R.id.tv_status, true)
                helper?.setTextColor(R.id.tv_status, Color.parseColor("#B5B5B5"))
                helper?.setText(R.id.tv_status, item.statusText)
            }
        }


    }
}