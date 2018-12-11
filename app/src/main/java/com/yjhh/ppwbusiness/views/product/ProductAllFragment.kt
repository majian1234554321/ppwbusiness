package com.yjhh.ppwbusiness.views.product

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
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
import com.yjhh.ppwbusiness.bean.rxbean.RxUserInfo
import com.yjhh.ppwbusiness.fragments.MessageDetailFragment
import com.yjhh.ppwbusiness.fragments.PhotoFragment
import com.yjhh.ppwbusiness.ipresent.ProductPresent
import com.yjhh.ppwbusiness.ipresent.SectionUselessPresent
import com.yjhh.ppwbusiness.iview.ProductView
import com.yjhh.ppwbusiness.utils.RxBus

import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import com.yjhh.ppwbusiness.views.product.twoview.BaseViewAdapter
import com.yjhh.ppwbusiness.views.product.twoview.BindData
import com.yjhh.ppwbusiness.views.product.twoview.LeftBean
import com.yjhh.ppwbusiness.views.product.twoview.RightBean
import kotlinx.android.synthetic.main.pickerview_custom_time.*

import kotlinx.android.synthetic.main.product1fragment.*
import kotlinx.android.synthetic.main.productallfragment.*
import java.util.ArrayList

class ProductAllFragment : BaseFragment(), ProductView {


    val categoryId = ""
    var status = ""//状态，默认null(null全部（不含已删除） 0 上架中 1已下架 3已删除)
    var startindex = 0
    val pageSize = 15
    lateinit var mAdapter: ProductAdapter
    lateinit var present: ProductPresent

    var order = "0"   //排序,0（0 时间排序 1价格排序）
    var orderType = "1" //排序方式，0(0升序 1倒叙)
    var list = ArrayList<ProductBean.ItemsBean>()

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
                if (result?.items != null && result?.items.size < pageSize) {
                    mAdapter.loadMoreEnd()
                } else {
                    mAdapter.addData(result?.items!!)
                    mAdapter.loadMoreComplete()
                }

            }

            "UNSELL" -> {
                Toast.makeText(mActivity, "商品下架成功", Toast.LENGTH_SHORT).show()
                mAdapter.getItem(result?.position!!)?.saleStatus = 1
                mAdapter.notifyDataSetChanged()
            }

            "SELL" -> {
                Toast.makeText(mActivity, "商品上架成功", Toast.LENGTH_SHORT).show()

                mAdapter.getItem(result?.position!!)?.saleStatus = 0

                mAdapter.notifyDataSetChanged()
            }

            else -> {
            }
        }


    }

    override fun onFault(errorMsg: String?) {
        swipeLayout.finishRefresh()

        if (startindex == 0) {
            val view = View.inflate(mActivity, R.layout.emptyview, null)
            view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
            mAdapter?.emptyView = view
        }

    }

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)
        Toast.makeText(mActivity, "0000", Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutRes(): Int = R.layout.productallfragment

    override fun initView() {

        mRecyclerView.addItemDecoration(SpaceItemDecoration(30, "bottom"))
        mAdapter = ProductAdapter(list)
        present = ProductPresent(context, this)

        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)

        initAdapter()
        initRefreshLayout()
        swipeLayout.autoRefresh()


        val dis = RxBus.default.toFlowable(Intent::class.java).subscribe {
            if (it != null && "ProductAddFragment" == it.getStringExtra("TYPE")) {
                swipeLayout.autoRefresh()
            }
        }


        mAdapter.setOnItemClickListener { adapter, view, position ->


        }
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.tv_delete -> {

                    present.delProduct(
                        (adapter.data[position] as ProductBean.ItemsBean).id,
                        (adapter.data[position] as ProductBean.ItemsBean).itemId,


                        position,
                        "DELETE"
                    )

                }

                R.id.tv_stop -> {


                    //	上下架状态(0上 1下)
                    if ((adapter.data[position] as ProductBean.ItemsBean).saleStatus == 0) {
                        present.editSaleStatus(
                            (adapter.data[position] as ProductBean.ItemsBean).id.toString(),
                            (adapter.data[position] as ProductBean.ItemsBean).itemId,
                            "1",
                            position,
                            "UNSELL"
                        )
                    } else {
                        present.editSaleStatus(
                            (adapter.data[position] as ProductBean.ItemsBean).id.toString(),
                            (adapter.data[position] as ProductBean.ItemsBean).itemId,
                            "0",
                            position,
                            "SELL"
                        )
                    }


                }

                R.id.iv_edit -> {
                    (parentFragment as BaseFragment).start(
                        ProductAddFragment.newInstance(adapter.data[position] as ProductBean.ItemsBean, "EDIT")
                    )
                }

                R.id.iv_image -> {

                    val list = ArrayList<String>()
                    list.add((adapter.data[position] as ProductBean.ItemsBean).logoUrl)

                    val dialog = PhotoFragment(list)
                    dialog?.show(childFragmentManager, "TAG")
                }

                else -> {

                }
            }
        }


    }


    private fun initAdapter() {


        mRecyclerView.adapter = mAdapter

        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)

        mAdapter.disableLoadMoreIfNotFullPage(mRecyclerView)

    }

    private fun initRefreshLayout() {
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
        }
    }

    private fun refresh() {
        startindex = 0
        present.allproducts(categoryId, order, orderType, status, startindex, pageSize, "refresh")

    }

    private fun loadMore() {

        startindex++
        present.allproducts(categoryId, order, orderType, status, startindex, pageSize, "load")

    }

    public fun sortType(order: String, orderType: String) {
        this.order = order
        this.orderType = orderType
        if (swipeLayout != null)
            swipeLayout.autoRefresh()

    }

}