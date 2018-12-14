package com.yjhh.ppwbusiness.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.GameRecordAdapter
import com.yjhh.ppwbusiness.api.ActivityCenterService
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.ActivityCenterBean
import com.yjhh.ppwbusiness.bean.ActivityCenterBean2
import com.yjhh.ppwbusiness.utils.CommItemDecoration
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import com.yjhh.ppwbusiness.utils.TimeUtil
import com.yjhh.ppwbusiness.views.cui.GlideLoader
import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import com.youth.banner.Banner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.evaluatedetailsfragment.*
import kotlinx.android.synthetic.main.gamerecordheadview.*
import kotlinx.android.synthetic.main.productallfragment.*


class GameRecordFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.gamerecordfragment


    var mAdapter: GameRecordAdapter? = null
    val list = ArrayList<ActivityCenterBean2.ItemsBean.Regs>()
    override fun initView() {

        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)



        mAdapter = GameRecordAdapter(list)
        addHeadView()
        recyclerView.addItemDecoration(
            CommItemDecoration.createVertical(
                context,
                ContextCompat.getColor(mActivity, R.color.e6),
                1
            )
        )
        recyclerView.adapter = mAdapter

        val id = arguments?.getString("id")

        val map = ArrayMap<String, String>()
        map.put("itemId", id)

        ApiServices.getInstance()
            .create(ActivityCenterService::class.java)
            .detail(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("ActivityCenterFragment", response)

                    val model = Gson().fromJson<ActivityCenterBean2.ItemsBean>(
                        response,
                        ActivityCenterBean2.ItemsBean::class.java
                    )

                    banner?.setImages(model.files)
                        ?.setImageLoader(GlideLoader())
                        ?.setDelayTime(10000)
                        ?.start()

                    mAdapter?.setNewData(model.regs)
                    tbv_title.setTitle(model.name)
                    val stime = "开始时间:  ${TimeUtil.stampToDate(model.effectiveTime)}"
                    tv_sTime?.text = TextStyleUtils.changeTextColor(stime, 0, 5, Color.parseColor("#999999"))

                    val etime = "结束时间:  ${TimeUtil.stampToDate(model.expiredTime)}"
                    tv_eTime?.text = TextStyleUtils.changeTextColor(etime, 0, 5, Color.parseColor("#999999"))

                    tv_no.text = model.dateSign
                    tv_status.text = model.statusText

                    tv_yuser?.text = model.totalUserByYest
                    tv_tuser?.text = model.totalUserByToday
                }

                override fun onFault(message: String) {
                    Log.i("ActivityCenterFragment", message)
                }

            })


    }


    var iv_image: ImageView? = null

    var tv_sTime: TextView? = null
    var tv_eTime: TextView? = null
    var banner: Banner? = null

    var tv_yuser: TextView? = null
    var tv_tuser: TextView? = null

    fun addHeadView() {
        val gamerecordHeadview = View.inflate(mActivity, R.layout.gamerecordheadview, null)
        tv_sTime = gamerecordHeadview.findViewById<TextView>(R.id.tv_sTime)
        tv_eTime = gamerecordHeadview.findViewById<TextView>(R.id.tv_eTime)
        iv_image = gamerecordHeadview.findViewById<ImageView>(R.id.iv_image)
        banner = gamerecordHeadview.findViewById<Banner>(R.id.banner)

        tv_yuser = gamerecordHeadview.findViewById<TextView>(R.id.tv_yuser)
        tv_tuser = gamerecordHeadview.findViewById<TextView>(R.id.tv_tuser)



        mAdapter?.addHeaderView(gamerecordHeadview)
    }


    companion object {
        fun newInstance(id: String?): GameRecordFragment {
            val fragment = GameRecordFragment()
            val bundle = Bundle()

            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }


}