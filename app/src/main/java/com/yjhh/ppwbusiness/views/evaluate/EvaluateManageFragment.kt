package com.yjhh.ppwbusiness.views.evaluate

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.google.gson.Gson
import com.scwang.smartrefresh.layout.header.ClassicsHeader

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.*
import com.yjhh.ppwbusiness.adapter.EvaluateManageAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionEvluateService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.EvaluateManageBean
import com.yjhh.ppwbusiness.bean.EvaluateManageItemBean
import com.yjhh.ppwbusiness.bean.EvaluateManageNavBean
import com.yjhh.ppwbusiness.bean.SubCommentsBean
import com.yjhh.ppwbusiness.ipresent.EvaluatePresent
import com.yjhh.ppwbusiness.iview.EvaluateView

import com.yjhh.ppwbusiness.views.cui.TabEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.evaluatemanagefragment.*

import java.util.*

class EvaluateManageFragment : BaseFragment(), EvaluateView {

    override fun onFault(errorMsg: String?) {
        if (swipeLayout != null) {
            swipeLayout.finishRefresh()
        }

        if (startIndex == 0) {
            val view = View.inflate(mActivity, R.layout.emptyview, null)
            view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
            mAdapter?.emptyView = view
        }

    }

    override fun onSuccess(value: String?, flag: String) {


        val gson = Gson()
        val bean = gson.fromJson<EvaluateManageBean>(value, EvaluateManageBean::class.java)

        if (bean.items != null && bean.items.size > 0) {
            list.clear()
            for (i in 0 until bean.items.size) {
                val lv0 = bean.items[i]



                if (lv0.items != null && lv0.items.size > 0) {
                    lv0.items.forEachIndexed { index, subCommentsBean ->
                        if (index == lv0.items.size - 1) {
                            subCommentsBean.last = true
                            lv0.addSubItem(subCommentsBean)
                        } else {
                            subCommentsBean.last = false
                            lv0.addSubItem(subCommentsBean)
                        }
                    }
                } else {
                    val model = SubCommentsBean(
                        "", "", "", bean.items[i].id.toString(), 0,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                        , true
                    )
                    lv0.addSubItem(model)
                }
                list.add(lv0)
            }



            when (flag) {
                "refresh" -> {
                    listAll.clear()
                    listAll.addAll(list)
                    if (swipeLayout != null) {
                        swipeLayout.finishRefresh()
                    }

                    if (startIndex == 0 && bean.items != null && bean.items.size == 0) {
                        val view = View.inflate(mActivity, R.layout.emptyview, null)
                        view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                        mAdapter?.emptyView = view
                    } else {
                        mAdapter?.setNewData(list)
                    }

                }


                "load" -> {
                    if (bean.items != null && bean.items.size == pageSize) {
                        listAll.addAll(list)
                        mAdapter?.addData(listAll)
                        mAdapter?.loadMoreComplete()
                    } else {
                        listAll.addAll(list)
                        mAdapter?.addData(list)

                        mAdapter?.loadMoreEnd()
                    }


                }

            }

        } else {
            if (startIndex==0&&swipeLayout!=null){
                swipeLayout.finishRefresh()

                val view = View.inflate(mActivity, R.layout.emptyview, null)
                view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                mAdapter?.emptyView = view

            }

            if ("load" == flag) {
                mAdapter?.loadMoreEnd()
            }
        }


        mAdapter?.expandAll()


    }

    private val mTitles = arrayOf("全部评价", "好评", "中评", "差评")
    private val mTabEntities = java.util.ArrayList<CustomTabEntity>()
    override fun getLayoutRes(): Int = R.layout.evaluatemanagefragment

    var type = "0"//类别，默认null（null/0全部 1好评 2中评 3差评）
    var startIndex = 0
    val pageSize = 15
    var isHasfile = "0"

    var mAdapter: EvaluateManageAdapter? = null

    val list = ArrayList<MultiItemEntity>()
    val listAll = ArrayList<MultiItemEntity>()

    var present: EvaluatePresent? = null

    override fun initView() {

        present = EvaluatePresent(mActivity, this)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))

        mAdapter = EvaluateManageAdapter(mActivity, listAll)

//        for (i in mTitles.indices) {
//            mTabEntities.add(TabEntity(mTitles[i], R.mipmap.ic_launcher, R.mipmap.ic_launcher))
//        }

        initRefreshLayout()

        ApiServices.getInstance()
            .create(SectionEvluateService::class.java)
            .nav()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("EvaluateManageFragment", response)

                    //mTabLayout_7.

                    val model = Gson().fromJson<Array<EvaluateManageNavBean>>(
                        response,
                        Array<EvaluateManageNavBean>::class.java
                    )


                    for (i in model.indices) {
                        mTabEntities.add(TabEntity(model[i].title, R.mipmap.ic_launcher, R.mipmap.ic_launcher))
                    }

                    mTabLayout_7.setTabData(mTabEntities)
                }

                override fun onFault(message: String) {
                    Log.i("EvaluateManageFragment", message)
                }

            })





        initAdapter()

        swipeLayout.autoRefresh()



        mTabLayout_7.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                Log.i("EvaluateManageFragment", position.toString())

                type = position.toString()
                startIndex = 0
                swipeLayout.autoRefresh()

            }

            override fun onTabReselect(position: Int) {

            }

        })

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

                checkbox.setTextColor(Color.parseColor("#FF552E"))
                isHasfile = "1"
                startIndex = 0
                present?.allcomments(type, isHasfile, startIndex, pageSize, "refresh")
            } else {
                checkbox.setTextColor(Color.parseColor("#888888"))
                isHasfile = "0"
                startIndex = 0
                present?.allcomments(type, isHasfile, startIndex, pageSize, "refresh")

            }
        }

        mAdapter?.setOnItemClickListener { adapter, view, position ->

            if (adapter.data[position] is EvaluateManageItemBean) {
                start(EvaluateDetailsFragment.newInstance((adapter.data[position] as EvaluateManageItemBean).id.toString()))
            } else {
                start(EvaluateDetailsFragment.newInstance((adapter.data[position] as SubCommentsBean).id))
            }


        }

        mAdapter?.expandAll()

    }

    private fun initAdapter() {


        recyclerView.adapter = mAdapter

        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, recyclerView)

        mAdapter?.disableLoadMoreIfNotFullPage(recyclerView)

    }

    private fun initRefreshLayout() {
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
        }


    }

    private fun refresh() {
        startIndex = 0

        present?.allcomments(type, isHasfile, startIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        // Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()
        startIndex++

        present?.allcomments(type, isHasfile, startIndex, pageSize, "load")


    }

}