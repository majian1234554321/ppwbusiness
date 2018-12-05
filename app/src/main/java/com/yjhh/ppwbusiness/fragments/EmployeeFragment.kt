package com.yjhh.ppwbusiness.fragments

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.Log

import android.view.View
import androidx.collection.ArrayMap
import androidx.recyclerview.widget.LinearLayoutManager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.EmployeeAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUserService

import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import com.yjhh.ppwbusiness.views.cui.TitleBarView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.employeefragment.*

class EmployeeFragment : BaseFragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                mActivity.onBackPressed()
            }

            else -> {

            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.employeefragment


    val lists = ArrayList<String>()
    var mAdapter: EmployeeAdapter? = null
    override fun initView() {
        tbv_title.setOnRightClickListener(object : TitleBarView.OnRightClickListion {
            override fun setOnRightClick() {
                start(EmployeeADUFragment.newInstance("ADD", "", "", ""))
            }

        })

        val textValue = "店员 上限：5"
        val spannableString = SpannableString(textValue)
        spannableString.setSpan(AbsoluteSizeSpan(12, true), 2, textValue.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF552E")),
            2,
            textValue.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv_tips.text = spannableString



        initAdapter()

        ApiServices.getInstance().create(
            SectionUserService::class.java
        ).shopAdminUser(ArrayMap<String, String>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    Log.i("TGA", response);
                }

                override fun onFault(message: String) {
                    Log.i("TGA", message);
                }

            })


    }

    private fun initAdapter() {

        for (i in 0..9) {
            lists.add("AAAA")
        }

        recyclerView.layoutManager = LinearLayoutManager(mActivity)

        mAdapter = EmployeeAdapter(lists)

        recyclerView.adapter = mAdapter

        mAdapter?.setOnItemClickListener { adapter, view, position ->

            start(EmployeeADUFragment.newInstance("DELETE", "", "", ""))
        }


    }

}