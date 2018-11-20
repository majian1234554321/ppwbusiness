package com.yjhh.ppwbusiness.views.evaluate

import android.support.v4.util.ArrayMap
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.EvaluateManageAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionEvluateService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.EvaluateManageBean
import com.yjhh.ppwbusiness.views.cui.TabEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.evaluatemanagefragment.*
import java.util.*

class EvaluateManageFragment : BaseFragment() {

    private val mTitles = arrayOf("全部评价", "好评", "中评", "差评")
    private val mTabEntities = java.util.ArrayList<CustomTabEntity>()
    override fun getLayoutRes(): Int = R.layout.evaluatemanagefragment

    var type = "0"
    var startIndex = 0
    val pageSize = 15


    var mAdapter: EvaluateManageAdapter? = null

    val list = ArrayList<MultiItemEntity>()
    override fun initView() {
        recyclerView.layoutManager = GridLayoutManager(activity, 1)
        mAdapter = EvaluateManageAdapter(mActivity, list)
        recyclerView.adapter = mAdapter

        mAdapter?.expandAll()

        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i], R.mipmap.ic_launcher, R.mipmap.ic_launcher))
        }


        mTabLayout_7.setTabData(mTabEntities)


        mTabLayout_7.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                Log.i("EvaluateManageFragment", position.toString())

                type = position.toString()

            }

            override fun onTabReselect(position: Int) {

            }

        })


        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Log.i("EvaluateManageFragment", isChecked.toString())
            } else {
                Log.i("EvaluateManageFragment", isChecked.toString())
            }
        }


        val map = ArrayMap<String, String>()
        map["type"] = type//类别，默认null（null/0全部 1好评 2中评 3差评）
        map["isHasfile"] = "0"//是否包含附件，默认null（null/0 全部 1包含附件）
        map["pageIndex"] = startIndex.toString()
        map["pageSize"] = pageSize.toString()

        ApiServices.getInstance()
            .create(SectionEvluateService::class.java)
            .allcomments(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun onFault(message: String) {
                    Log.i("EvaluateManageFragment", message)
                }

                override fun processValue(response: String?) {
                    Log.i("EvaluateManageFragment", response)
                    val gson = Gson()
                    val bean = gson.fromJson<EvaluateManageBean>(response, EvaluateManageBean::class.java)


                    list.addAll(bean.items as List<MultiItemEntity>)

                    mAdapter?.setNewData(bean.items as List<MultiItemEntity>)

                }

            })



        mAdapter?.setOnItemClickListener { adapter, view, position ->
            start(EvaluateDetailsFragment((list[position] as EvaluateManageBean.ItemsBean).id))

        }

    }


}