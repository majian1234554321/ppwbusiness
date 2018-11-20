package com.yjhh.ppwbusiness.views.evaluate

import android.annotation.SuppressLint
import android.support.v4.util.ArrayMap
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.layout.item
import com.yjhh.ppwbusiness.adapter.EvaluateDetailsAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionEvluateService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.EvaluateDetailsBean
import com.yjhh.ppwbusiness.bean.EvaluateManageBean
import com.yjhh.ppwbusiness.utils.TimeUtil
import com.yjhh.ppwbusiness.views.cui.RatingBar
import com.yjhh.ppwbusiness.views.evaluate.ninegrid.NineGridView
import com.yjhh.ppwbusiness.views.evaluate.ninegrid.NineGridViewClickAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.evaluatedetailsfragment.*


@SuppressLint("ValidFragment")
class EvaluateDetailsFragment(id: Int) : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.evaluatedetailsfragment

    var mAdapter: EvaluateDetailsAdapter? = null

    val list = ArrayList<EvaluateManageBean.ItemsBean>()
    override fun initView() {


        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        addHeadView()
        recyclerView.adapter = EvaluateDetailsAdapter(list)

        val map = ArrayMap<String, String>()
        map["id"] = id.toString()//类别，默认null（null/0全部 1好评 2中评 3差评）

        ApiServices.getInstance()
            .create(SectionEvluateService::class.java)
            .comment(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun onFault(message: String) {
                    Log.i("EvaluateDetailsFragment", message)
                }

                override fun processValue(response: String?) {
                    Log.i("EvaluateDetailsFragment", response)
                    val gson = Gson()
                    val bean = gson.fromJson<EvaluateDetailsBean>(response, EvaluateDetailsBean::class.java)

                    val url = ArrayList<String>()

                    val list1212 = bean.files
                    list1212.forEach {
                        url.add(it.url.toString())
                    }


                    nineGridView?.setAdapter(NineGridViewClickAdapter(context, url))




                    tv_username?.setText(bean?.nickName)
                    tv_content?.setText(bean?.content)
                    tv_time?.text = TimeUtil.stampToDate2(bean.time.toString())

                    id_ratingbar?.setStar(bean?.grade?.toFloat()!!)


                    mAdapter?.setNewData(list)

                }

            })








        mAdapter = EvaluateDetailsAdapter(list)
        addHeadView()
        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        recyclerView.adapter = mAdapter


    }


    var nineGridView: NineGridView? = null
    var tv_content: TextView? = null
    var tv_time: TextView? = null
    var tv_username: TextView? = null
    var id_ratingbar: RatingBar? = null
    private fun addHeadView() {
        val headView = View.inflate(mActivity, R.layout.evaluatedetailshead, null)
        nineGridView = headView.findViewById(R.id.nineGrid)
        tv_time = headView.findViewById(R.id.tv_time)
        tv_content = headView.findViewById(R.id.tv_content)
        tv_username = headView.findViewById(R.id.tv_username)
        id_ratingbar = headView.findViewById(R.id.id_ratingbar)

        mAdapter?.addHeaderView(headView)
    }
}