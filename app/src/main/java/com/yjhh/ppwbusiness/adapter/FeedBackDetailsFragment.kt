package com.yjhh.ppwbusiness.adapter

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.AccountBean
import com.yjhh.ppwbusiness.bean.AllFeedBackBean
import com.yjhh.ppwbusiness.ipresent.AboutPresent
import com.yjhh.ppwbusiness.iview.AboutView
import com.yjhh.ppwbusiness.views.reservation.ReservationDetailFragment
import kotlinx.android.synthetic.main.feedbackdetailsfragment.*

class FeedBackDetailsFragment : BaseFragment(), AboutView {
    override fun onSuccess(response: String?, flag: String?) {

        Log.i("FeedBackDetailsFragment", response)


        val modle = Gson().fromJson<AllFeedBackBean.ItemsBean>(response, AllFeedBackBean.ItemsBean::class.java)


        tv_title22.text = modle.title


        tv_status.text = modle.statusText
        tv_content.text = modle.cause

        val list = ArrayList<String>()
        if (modle.images != null && modle.images.isNotEmpty()) {
            list.clear()
            modle.images.forEach {
                list.add(it.fileUrl)
            }
            recyclerView.adapter = PhotoAdapter(list, false)
        }


    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.feedbackdetailsfragment


    override fun initView() {

        val id = arguments?.getString("id")

        recyclerView.layoutManager = GridLayoutManager(mActivity, 3)
        AboutPresent(mActivity, this).feedbackDetail(id)

    }


    companion object {
        fun newInstance(id: String): FeedBackDetailsFragment {
            val fragment = FeedBackDetailsFragment()
            val bundle = Bundle()

            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }

}