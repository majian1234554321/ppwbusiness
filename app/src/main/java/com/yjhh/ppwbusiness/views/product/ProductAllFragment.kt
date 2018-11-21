package com.yjhh.ppwbusiness.views.product

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyMessageFragmentAdapter
import com.yjhh.ppwbusiness.adapter.ProductAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.MyMessageBean
import com.yjhh.ppwbusiness.bean.ProductBean
import com.yjhh.ppwbusiness.fragments.MessageDetailFragment
import com.yjhh.ppwbusiness.ipresent.ProductPresent
import com.yjhh.ppwbusiness.ipresent.SectionUselessPresent
import com.yjhh.ppwbusiness.iview.ProductView
import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import com.yjhh.ppwbusiness.views.product.twoview.BaseViewAdapter
import com.yjhh.ppwbusiness.views.product.twoview.BindData
import com.yjhh.ppwbusiness.views.product.twoview.LeftBean
import com.yjhh.ppwbusiness.views.product.twoview.RightBean

import kotlinx.android.synthetic.main.product1fragment.*
import kotlinx.android.synthetic.main.productallfragment.*
import java.util.ArrayList

class ProductAllFragment : BaseFragment(), ProductView {


    val categoryId = ""
    var status = ""//状态，默认null(null全部（不含已删除） 0 上架中 1已下架 3已删除)
    var startindex = 0
    val pageSize = 10


    override fun onSuccess(result: ProductBean?, flag: String) {

        when (flag) {
            "refresh" -> {
                swipeLayout.finishRefresh()
                mAdapter.setNewData(result?.items)
                swipeLayout.finishRefresh()
            }
            "DELETE" -> {
                result?.position?.let { mAdapter.data.removeAt(it) }
                result?.position?.let {
                    mAdapter.notifyItemRemoved(it)
                }
            }

            "load" -> {
                mAdapter.addData(result?.items!!)
                mAdapter.loadMoreComplete()
            }

            "UNSELL" -> {
                Toast.makeText(mActivity, "商品下架成功", Toast.LENGTH_SHORT).show()
            }

            "SELL" -> {
                Toast.makeText(mActivity, "商品上架成功", Toast.LENGTH_SHORT).show()
            }

            else -> {
            }
        }


    }

    override fun onFault(errorMsg: String?) {

    }


    var list = ArrayList<ProductBean.ItemsBean>()

    lateinit var mAdapter: ProductAdapter


    lateinit var present: ProductPresent


    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)
        Toast.makeText(mActivity, "0000", Toast.LENGTH_SHORT).show()
    }


    override fun getLayoutRes(): Int = R.layout.productallfragment

    override fun initView() {

        mRecyclerView.addItemDecoration(SpaceItemDecoration(30))
        mAdapter = ProductAdapter(list)

        present = ProductPresent(context, this)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()


        mAdapter.setOnItemClickListener { adapter, view, position ->

        }


        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.tv_delete -> {

                    present.delProduct(
                        (adapter.data[position] as ProductBean.ItemsBean).id.toString(),
                        position,
                        "DELETE"
                    )

                }

                R.id.tv_stop -> {

                    present.editSaleStatus("1", "0", position, "UNSELL")
                }

                R.id.iv_edit -> {
                    (parentFragment as BaseFragment).start(
                        ProductAddFragment.newInstance(adapter.data[position] as ProductBean.ItemsBean, "EDIT")
                    )
                }
                else -> {
                }
            }
        }


    }


    private fun initAdapter() {
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)

        mRecyclerView.adapter = mAdapter

        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mAdapter.disableLoadMoreIfNotFullPage(mRecyclerView)

    }


    private fun initRefreshLayout() {
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
        }
    }


    private fun refresh() {
        startindex = 0
        present.allproducts(categoryId, status, startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()
        startindex++
        present.allproducts(categoryId, status, startindex, pageSize, "load")

    }


}