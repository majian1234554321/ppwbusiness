package com.yjhh.ppwbusiness.views.evaluate

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.google.gson.Gson

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.EvaluateManageAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.EvaluateManageBean
import com.yjhh.ppwbusiness.bean.EvaluateManageItemBean
import com.yjhh.ppwbusiness.bean.SubCommentsBean
import com.yjhh.ppwbusiness.ipresent.EvaluatePresent
import com.yjhh.ppwbusiness.iview.EvaluateView
import com.yjhh.ppwbusiness.views.cui.PPWHeader2
import com.yjhh.ppwbusiness.views.cui.TabEntity
import kotlinx.android.synthetic.main.evaluatemanagefragment.*

import java.util.*

class EvaluateManageFragment : BaseFragment(), EvaluateView {
    override fun onFault(errorMsg: String?) {

    }

    override fun onSuccess(value: String?, flag: String) {


        val gson = Gson()
        val bean = gson.fromJson<EvaluateManageBean>(value, EvaluateManageBean::class.java)
        for (i in 0 until bean.items.size) {
            val lv0 = bean.items[i]

            var lv1: SubCommentsBean? = null
            if (bean.items[i].subComments != null) {
                for (j in 0 until bean.items[i].subComments.size) {
                    val modle = bean.items[i].subComments[j]
                    if (j == bean.items[i].subComments.size - 1) {

                        lv1 = SubCommentsBean(
                            modle.content,
                            modle.ifFile,
                            modle.ifShop,
                            modle.nickName,
                            modle.time,
                            bean.items[i].id.toString(),
                            false
                        )
                    } else {
                        lv1 =
                                SubCommentsBean(
                                    modle.content,
                                    modle.ifFile,
                                    modle.ifShop,
                                    modle.nickName,
                                    modle.time,
                                    bean.items[i].id.toString(),
                                    true
                                )
                    }
                    lv0.addSubItem(lv1)
                }
            } else {
                lv1 =
                        SubCommentsBean(
                            "", false, false, "", 0,
                            bean.items[i].id.toString(), false
                        )
                lv0.addSubItem(lv1)
            }


            list.add(lv0)
        }

        when (flag) {
            "refresh" -> {
                swipeLayout.finishRefresh()
                mAdapter?.setNewData(list)
                swipeLayout.finishRefresh()
            }


            "load" -> {
                mAdapter?.addData(list)
                mAdapter?.loadMoreComplete()
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

    var present: EvaluatePresent? = null

    override fun initView() {

        present = EvaluatePresent(mActivity, this)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        swipeLayout.setRefreshHeader(PPWHeader2(context))

        mAdapter = EvaluateManageAdapter(mActivity, list)




        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(mTitles[i], R.mipmap.ic_launcher, R.mipmap.ic_launcher))
        }

        initRefreshLayout()

        initAdapter()
        swipeLayout.autoRefresh()



        mTabLayout_7.setTabData(mTabEntities)


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

            if (list[position] is EvaluateManageItemBean) {
                start(EvaluateDetailsFragment.newInstance((list[position] as EvaluateManageItemBean).id.toString()))
            } else {
                start(EvaluateDetailsFragment.newInstance((list[position] as SubCommentsBean).id))
            }


        }

        mAdapter?.expandAll()

    }


    private fun initAdapter() {
        mAdapter?.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)

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