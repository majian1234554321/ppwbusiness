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
import com.yjhh.ppwbusiness.fragments.MessageDetailFragment
import com.yjhh.ppwbusiness.ipresent.SectionUselessPresent
import com.yjhh.ppwbusiness.views.cui.SpaceItemDecoration
import com.yjhh.ppwbusiness.views.product.twoview.BaseViewAdapter
import com.yjhh.ppwbusiness.views.product.twoview.BindData
import com.yjhh.ppwbusiness.views.product.twoview.LeftBean
import com.yjhh.ppwbusiness.views.product.twoview.RightBean

import kotlinx.android.synthetic.main.product1fragment.*
import kotlinx.android.synthetic.main.productallfragment.*
import java.util.ArrayList

class ProductAllFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_1 -> {

            }
            R.id.tv_2 -> {
                start(ProductAddFragment("ADD"))
            }
            R.id.tv_3 -> {

            }
            R.id.tv_4 -> {


            }
            else -> {
            }
        }
    }


    /*  var status = "-1"//状态，默认null(null/-1 全部 0未生效 1 有效的 2已过期的/失效的)
      override fun onSuccess(main1bean: MyMessageBean, flag: String) {
          if ("refresh" == flag) {
              swipeLayout.finishRefresh()
              mAdapter.setNewData(main1bean.items as ArrayList<MyMessageBean.ItemsBean>)

          } else {
              mAdapter.addData(main1bean.items as ArrayList<MyMessageBean.ItemsBean>)
              mAdapter.loadMoreComplete()
          }
      }

      override fun onFault(errorMsg: String?) {
          // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
      }*/

    var startindex = 0
    val pageSize = 10

    var share = ""
    var list = ArrayList<String>()

    lateinit var mAdapter: ProductAdapter


    lateinit var sectionCouponPresent: SectionUselessPresent


    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)
        Toast.makeText(mActivity, "0000", Toast.LENGTH_SHORT).show()
    }


    override fun getLayoutRes(): Int = R.layout.productallfragment

    override fun initView() {
        list.add("A")

        list.add("B")
        list.add("C")

        mRecyclerView.addItemDecoration(SpaceItemDecoration(30))
        mAdapter = ProductAdapter(list)
        // sectionCouponPresent = SectionUselessPresent(context, this)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        swipeLayout.setRefreshHeader(ClassicsHeader(context))
        initAdapter()
        initRefreshLayout()
        //  swipeLayout.autoRefresh()


        mAdapter.setOnItemClickListener { adapter, view, position ->

            //            (parentFragment as BaseFragment).start(
//                MessageDetailFragment()
//            )

        }


        mAdapter.setOnItemChildClickListener { adapter, view, position ->


            when (view.id) {
                R.id.tv_delete -> {
                    Toast.makeText(context, "删除$position", Toast.LENGTH_SHORT).show()
                }

                R.id.tv_stop -> {
                    Toast.makeText(context, "下架$position", Toast.LENGTH_SHORT).show()
                }

                R.id.iv_edit -> {
                    (parentFragment as BaseFragment).start(
                        ProductAddFragment("EDIT")
                    )
                }
                else -> {
                }
            }
        }


    }


    private fun initAdapter() {

        mAdapter.setOnLoadMoreListener({
            loadMore()
        }, mRecyclerView)
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)


        mRecyclerView.adapter = mAdapter


    }


    private fun initRefreshLayout() {
        swipeLayout.setOnRefreshListener { refreshLayout ->
            refresh()
        }
    }


    private fun refresh() {
        startindex = 0
        //sectionCouponPresent.usermessage(status, share, startindex, pageSize, "refresh")

    }


    private fun loadMore() {
        Toast.makeText(context, "onload", Toast.LENGTH_SHORT).show()
        startindex++
        //  sectionCouponPresent.usermessage(status, share, startindex, pageSize, "load")

    }


}