package com.yjhh.ppwbusiness.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.collection.ArrayMap
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUserService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.views.cui.TitleBarView
import com.yjhh.ppwbusiness.views.login.LoginFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.employeeadufragment.*

class EmployeeADUFragment : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mb_commit -> {


                if (!TextUtils.isEmpty(tv_name.text.toString()) && !TextUtils.isEmpty(tv_Phone.text.toString()) && tv_Phone.text.toString().length == 11) {

                    val map = ArrayMap<String, String>()
                    map.put("id", "")
                    map.put("name", tv_name.text.toString())
                    map.put("phone", tv_Phone.text.toString())
                    ApiServices.getInstance().create(SectionUserService::class.java)
                        .save(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : ProcessObserver2(mActivity) {
                            override fun processValue(response: String?) {

                                Log.i("EmployeeADUFragment", response)
                                mActivity.onBackPressed()
                            }

                            override fun onFault(message: String) {
                                Toast.makeText(_mActivity, message, Toast.LENGTH_SHORT).show()
                            }

                        })

                } else {
                    Toast.makeText(_mActivity, "请输入姓名和手机号", Toast.LENGTH_SHORT).show()
                }


            }


            else -> {
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.employeeadufragment

    override fun initView() {

        mb_commit.setOnClickListener(this)

        val type = arguments?.getString("type")
        val id = arguments?.getString("id")
        val phone = arguments?.getString("phone")
        val name = arguments?.getString("name")

        tv_name.setText(name)
        tv_Phone.setText(phone)



        tbv_title.setOnRightClickListener(object : TitleBarView.OnRightClickListion {
            override fun setOnRightClick() {
                val map = ArrayMap<String, String>()
                map["id"] = id
                ApiServices.getInstance().create(SectionUserService::class.java)
                    .del(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : ProcessObserver2(mActivity) {
                        override fun processValue(response: String?) {

                            Log.i("EmployeeADUFragment", response);
                            mActivity.onBackPressed()
                        }

                        override fun onFault(message: String) {
                            Toast.makeText(_mActivity, message, Toast.LENGTH_SHORT).show()
                        }

                    })
            }

        })



        if (type == "ADD") {
            tbv_title.setRightDisPlay(false)
            tbv_title.setTitle("店员添加")
            mb_commit.text = "确认添加"
        } else {
            tbv_title.setRightDisPlay(true)
            tbv_title.setTitle("店员修改")
            mb_commit.text = "确认修改"
        }

    }


    companion object {
        fun newInstance(type: String?, id: String?, phone: String?, name: String): EmployeeADUFragment {
            val fragment = EmployeeADUFragment()
            val args = Bundle()
            args.putString("type", type)
            args.putString("id", id)
            args.putString("phone", phone)
            args.putString("name", name)

            fragment.arguments = args
            return fragment
        }
    }


}