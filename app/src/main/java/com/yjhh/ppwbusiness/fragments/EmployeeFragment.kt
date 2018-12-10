package com.yjhh.ppwbusiness.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.Log

import android.view.View
import android.widget.Toast
import androidx.collection.ArrayMap
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.EmployeeAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUserService

import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.AccountBean
import com.yjhh.ppwbusiness.bean.EmployeeBean
import com.yjhh.ppwbusiness.bean.ProductBean

import com.yjhh.ppwbusiness.views.cui.TitleBarView
import com.yjhh.ppwbusiness.views.product.ProductAddFragment
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


    val lists = ArrayList<EmployeeBean>()
    var mAdapter: EmployeeAdapter? = null
    override fun initView() {
        tbv_title.setOnRightClickListener(object : TitleBarView.OnRightClickListion {
            override fun setOnRightClick() {
                if (lists.size < 5) {
                    startForResult(EmployeeADUFragment.newInstance("ADD", "", "", "", -1), 10086)
                } else {
                    Toast.makeText(_mActivity, "最多只能添加5位店员", Toast.LENGTH_SHORT).show()
                }
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

        val model = arguments?.getSerializable("objectValue")
        if (model != null) {
            model as AccountBean
            tv_name.text = model.roleName
            tv_mobile.text = model.displayMobile
            tbv_title.setRightDisPlay(model.role == 0)
            mAdapter = EmployeeAdapter(lists, model.role == 0)
        } else {
            mAdapter = EmployeeAdapter(lists, false)
        }


        recyclerView.layoutManager = LinearLayoutManager(mActivity)



        recyclerView.adapter = mAdapter

        mAdapter?.setOnItemClickListener { adapter, view, position ->

            if (lists.size < 5) {

                if (model != null) {
                    model as AccountBean
                    if (model.role == 0) {
                        startForResult(
                            EmployeeADUFragment.newInstance(
                                "DELETE",
                                lists[position].id,
                                lists[position].mobile,
                                lists[position].name,
                                position
                            ), 10086
                        )
                    }

                } else {

                }


            } else {
                Toast.makeText(_mActivity, "最多只能添加5位店员", Toast.LENGTH_SHORT).show()
            }
        }



        loadNetData()
    }


    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)

        if (requestCode == 10086 && resultCode == RESULT_OK) {
            loadNetData()
        }


    }


    companion object {
        fun newInstance(objectValue: AccountBean?): EmployeeFragment {
            val fragment = EmployeeFragment()
            val bundle = Bundle()

            bundle.putSerializable("objectValue", objectValue)
            fragment.arguments = bundle
            return fragment
        }
    }


    private fun loadNetData() {
        ApiServices.getInstance().create(
            SectionUserService::class.java
        ).shopAdminUser(ArrayMap<String, String>())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    if (!TextUtils.isEmpty("response")) {
                        val model = Gson().fromJson<Array<EmployeeBean>>(response, Array<EmployeeBean>::class.java)
                        lists.clear()
                        lists.addAll(model.asList())
                        mAdapter?.setNewData(lists)
                        mAdapter?.notifyDataSetChanged()

                    } else {

                    }

                    Log.i("TGA", response)
                }

                override fun onFault(message: String) {
                    Log.i("TGA", message)
                }

            })
    }


}