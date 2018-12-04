package com.yjhh.ppwbusiness.views.writeoff

import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.gson.Gson
import com.jakewharton.rxbinding2.widget.RxTextView

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.WriteOffStoreAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.WriteOffStoreBean
import com.yjhh.ppwbusiness.ipresent.CancellationPresent

import com.yjhh.ppwbusiness.ipresent.ReservePresent
import com.yjhh.ppwbusiness.iview.CancellationView
import com.yjhh.ppwbusiness.utils.CommItemDecoration
import com.yjhh.ppwbusiness.views.cui.PPWHeader2
import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import kotlinx.android.synthetic.main.writeoffstorefragment.*

class WriteOffStoreFragment : BaseFragment(), CancellationView {
    override fun onSuccessCancellation(response: String?, flag: String?) {
        //

        var gson = Gson()

        val model = gson.fromJson<WriteOffStoreBean>(response, WriteOffStoreBean::class.java)

        if (model.item != null && model.item.isNotEmpty()) {

            listsAll.clear()

            listsAll.addAll(model.item)

            listSelect.addAll(model.item)
            mAdapter?.setNewData(model.item)
        }


    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.writeoffstorefragment


    val pageSize = 15
    var pageIndex = 0
    var status = "-1" //null全部 -1 历史 0 申请 1已接受 2用户取消 3商户取消 4已过时
    var peresent: CancellationPresent? = null
    var mAdapter: WriteOffStoreAdapter? = null

    var listsAll = ArrayList<WriteOffStoreBean.ItemBean>()
    var listSelect = ArrayList<WriteOffStoreBean.ItemBean>()

    override fun initView() {


        peresent = CancellationPresent(mActivity, this)
        peresent?.shopList("123")

        //initRefreshLayout()
        initAdapter()
        swipeLayout.setEnableRefresh(false)
        //  swipeLayout.autoRefresh()


        mAdapter?.setOnItemClickListener { adapter, view, position ->
            val bundle = Bundle()
            bundle.putString("name", listSelect[position].name)
            bundle.putString("ids", listSelect[position].id)

            setFragmentResult(RESULT_OK, bundle)

            mActivity.onBackPressed()

        }


        val dis = RxTextView.textChanges(et_search).subscribe {

            if (!TextUtils.isEmpty(it) && listsAll.size > 0) {
                listSelect.clear()
                for (list in listsAll) {
                    if (list.name.contains(it)) {
                        listSelect.add(list)
                    }
                }

                mAdapter?.setNewData(listSelect)

            } else {
                listSelect.addAll(listsAll)
                mAdapter?.setNewData(listSelect)
            }

        }

        compositeDisposable.addAll(dis)


    }


    private fun initAdapter() {


        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
        mAdapter?.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        mAdapter?.isFirstOnly(false)
        mAdapter = WriteOffStoreAdapter(listsAll)
        mRecyclerView.adapter = mAdapter

    }
/*
    private fun initRefreshLayout() {
        swipeLayout.setRefreshHeader(PPWHeader2(context))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
        }
    }

    private fun refresh() {
        pageIndex = 0
        peresent?.reserves(status, "", pageIndex, pageSize, "refresh")
    }

    private fun loadMore() {
        pageIndex++
        peresent?.reserves(status, "", pageIndex, pageSize, "load")

    }
*/

}