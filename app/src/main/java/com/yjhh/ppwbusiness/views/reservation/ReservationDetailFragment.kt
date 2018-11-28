package com.yjhh.ppwbusiness.views.reservation

import android.Manifest
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import com.tbruyelle.rxpermissions2.RxPermissions


import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.layout.item
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.ReservDetailsBean
import com.yjhh.ppwbusiness.bean.ReservationBean
import com.yjhh.ppwbusiness.ipresent.ReservePresent
import com.yjhh.ppwbusiness.iview.ReservationDetailView
import com.yjhh.ppwbusiness.iview.ReserveView
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import com.yjhh.ppwbusiness.utils.TextStyleUtils.stytle
import com.yjhh.ppwbusiness.utils.TimeUtil
import com.yjhh.ppwbusiness.views.cui.TitleBarView
import kotlinx.android.synthetic.main.reservationdetailfragment.*
import java.lang.StringBuilder
import kotlin.text.Typography.times

class ReservationDetailFragment : BaseFragment(), View.OnClickListener, ReservationDetailView {
    override fun onSuccessReservDetail(model: ReservDetailsBean) {
        iev_1.setTextContent(model.shopName)
        iev_2.setTextContent("${TimeUtil.stampToDate2(model.acceptTime.toString())} 到店")
        iev_3.setTextContent("${model.userCount} 位")
        iev_4.setTextContent("${model.userName} ${model.userMobile}")
        iev_5.setTextContent(model.remark)


        val sb = StringBuilder()

        val times = TimeUtil.secondToTime(model.timeTotal)

        sb.append("预计还有 ")

        if (times.split("$")[0].toLong() > 0) {
            sb.append(times.split("$")[0]).append(" 天 ")
        }

        if (times.split("$")[1].toLong() >= 0) {
            sb.append(times.split("$")[1]).append(" 小时 ")
        }

        if (times.split("$")[2].toLong() >= 0) {
            sb.append(times.split("$")[2]).append(" 分钟到店")
        }


        tv_tips.text = TextStyleUtils.stytle(sb.toString(), mActivity)


    }


    override fun onFault(errorMsg: String?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mb_cancel -> {
                start(CancelReServationFragment())
            }
            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.reservationdetailfragment

    override fun initView() {
        val objectValue = arguments?.getString("objectValue")


        tbv_title.setOnRightClickListener(object : TitleBarView.OnRightClickListion {
            override fun setOnRightClick() {
                val dis = RxPermissions(this@ReservationDetailFragment)
                    .request(
                        Manifest.permission.CALL_PHONE
                    )
                    .subscribe {
                        if (it) {
                            Toast.makeText(mActivity, "拨打电话", Toast.LENGTH_SHORT).show()
                        } else {

                        }
                    }
                compositeDisposable.add(dis)
            }
        })



        when (objectValue) {
            "已接受预约" -> {
                ImageLoaderUtils.loadImgId(
                    mActivity,
                    iv_image,
                    R.drawable.icon_status1,
                    R.drawable.icon_status1,
                    R.drawable.icon_status1,
                    5
                )
                tv_status.text = "已接受预约"

                tv_tips.visibility = View.VISIBLE


                ll_control.visibility = View.GONE

            }
            "等待接受预约" -> {
                tv_status.text = "等待接受预约"

                ImageLoaderUtils.loadImgId(
                    mActivity,
                    iv_image,
                    R.drawable.icon_status2,
                    R.drawable.icon_status2,
                    R.drawable.icon_status2,
                    5
                )


                tv_tips.visibility = View.VISIBLE


            }
            "已过时" -> {
                tv_status.text = "已过时"

                ImageLoaderUtils.loadImgId(
                    mActivity,
                    iv_image,
                    R.drawable.icon_status3,
                    R.drawable.icon_status3,
                    R.drawable.icon_status3,
                    5
                )

                ll_control.visibility = View.GONE
                tv_tips.visibility = View.GONE

            }
            "已取消预约" -> {
                tv_status.text = "已取消预约"
                ImageLoaderUtils.loadImgId(
                    mActivity,
                    iv_image,
                    R.drawable.icon_status3,
                    R.drawable.icon_status3,
                    R.drawable.icon_status3,
                    5
                )

                ll_control.visibility = View.GONE
                tv_tips.visibility = View.GONE
                ll_cancel.visibility = View.GONE
            }
            else -> {
            }
        }


        arrayOf(mb_cancel).forEach {
            it.setOnClickListener(this)
        }





        ReservePresent(mActivity, this).reserveDetail(arguments?.getString("id"))


    }

    companion object {
        fun newInstance(objectValue: String, id: String): ReservationDetailFragment {
            val fragment = ReservationDetailFragment()
            val bundle = Bundle()

            bundle.putString("id", id)
            bundle.putString("objectValue", objectValue)
            fragment.arguments = bundle
            return fragment
        }
    }


}