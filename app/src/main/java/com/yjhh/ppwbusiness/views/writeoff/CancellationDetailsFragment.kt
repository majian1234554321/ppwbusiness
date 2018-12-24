package com.yjhh.ppwbusiness.views.writeoff

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.PhotoAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2.constructor.gson
import com.yjhh.ppwbusiness.bean.CancelationBeforeBean
import com.yjhh.ppwbusiness.fragments.PhotoFragment
import com.yjhh.ppwbusiness.ipresent.CancellationPresent
import com.yjhh.ppwbusiness.iview.CancellationView
import com.yjhh.ppwbusiness.utils.TimeUtil
import kotlinx.android.synthetic.main.cancellationdetailsfragment.*


class CancellationDetailsFragment : BaseFragment(), CancellationView {
    override fun onSuccessCancellation(response: String?, flag: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        val model = gson.fromJson<CancelationBeforeBean.ItemsBean>(
            response,
            CancelationBeforeBean.ItemsBean::class.java
        )


        if (model != null) {
            iev1.setTextContent(getString(R.string.rmb_price_double2, model.money))
            iev2.setTextContent(model.title)
            iev3.setTextContent(getString(R.string.rmb_price_double2, model.totalMoney))
            iev4.setTextContent(model.code)
            iev5.setTextContent(TimeUtil.stampToDate(model.useTime))
            iev6.setTextContent(model.reviewUserName)
            iev7.setTextContent(model.shopName)
            iev8.setTextContent(model.userName)
            iev9.setTextContent(TimeUtil.stampToDate(model.expiredTime))
            iev10.setTextContent(model.remark)




            if (model.files != null && model.files.size > 0) {
                recyclerView.layoutManager = GridLayoutManager(mActivity, 3)


                val list = ArrayList<String>()

                model.files.forEach {
                    list.add(it.fileUrl)
                }


                val mAdapter = PhotoAdapter(list, true)

                recyclerView.adapter = mAdapter

                mAdapter.setOnItemClickListener { adapter, view, position ->
                    val dialog = PhotoFragment(list,position)
                    dialog?.show(childFragmentManager, "TAG")
                }

            }


        }


    }

    override fun onFault(errorMsg: String?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutRes(): Int = R.layout.cancellationdetailsfragment


    override fun initView() {

        val ids = arguments?.getString("ids")

        CancellationPresent(mActivity, this).detail(ids)
        iv_back.setOnClickListener {
            mActivity.onBackPressed()
        }

    }


    companion object {
        fun newInstance(ids: String): CancellationDetailsFragment {

            val bundle = Bundle()

            bundle.putString("ids", ids)

            val fragment = CancellationDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}