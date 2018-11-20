package com.yjhh.ppwbusiness.views.merchant

import android.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.ArrayMap
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.baidu.android.bbalbs.common.a.a
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.CustomListener
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.BusinessHoursAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ShopSetServices
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.BusinessHoursBean
import com.yjhh.ppwbusiness.views.cui.AlertDialogFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.businesshoursfragment.*
import org.json.JSONArray
import java.lang.StringBuilder

class BusinessHoursFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.businesshoursfragment

    private val AValue = java.util.ArrayList<String>()
    private val BValue = java.util.ArrayList<String>()
    private val CValue = java.util.ArrayList<String>()


    var mAdapter: BusinessHoursAdapter? = null
    val list = ArrayList<BusinessHoursBean>()

    override fun initView() {


        getNoLinkData()




        recyclerView.layoutManager = LinearLayoutManager(mActivity)

        mAdapter = BusinessHoursAdapter(list)

        recyclerView.adapter = mAdapter

        tv_add.setOnClickListener {
            if (list.size < 3) {
                list.add(BusinessHoursBean("14:00", "18:00"))
                mAdapter?.setNewData(list)
            }

        }

        mAdapter?.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.tv_sTime -> {
                    initNoLinkOptionsPicker(0, 1, 1, view, position, "sTime")
                    pvNoLinkOptions?.show()
                }

                R.id.tv_eTime -> {
                    initNoLinkOptionsPicker(0, 1, 1, view, position, "eTime")

                    pvNoLinkOptions?.show()
                }

                else -> {


                    AlertDialogFactory.createFactory(mActivity).getAlertDialog(
                        null,
                        "确定删除该时间段吗?",
                        "确定", "取消",
                        { dlg, v ->
                            list.removeAt(position)
                            mAdapter?.notifyItemRemoved(position)
                        }, { dlg, v -> })


                }
            }
        }


    }


    private fun getNoLinkData() {
        AValue.add("上午")
        AValue.add("下午")

        for (i in 0..11) {
            if (i < 10) {
                BValue.add("0$i")
            } else {
                BValue.add("$i")
            }
        }

        for (i in 0..59) {
            if (i < 10) {
                CValue.add("0$i")
            } else {
                CValue.add("$i")
            }
        }


    }

    var pvNoLinkOptions: OptionsPickerView<String>? = null


    private fun initNoLinkOptionsPicker(a: Int, b: Int, c: Int, view: View, position: Int, flag: String) {

        pvNoLinkOptions = OptionsPickerBuilder(mActivity,
            OnOptionsSelectListener { options1, options2, options3, v ->
                //返回的分别是三个级别的选中位置
                Toast.makeText(mActivity, "$options1,$options2,$options3", Toast.LENGTH_SHORT).show()


                val sb = StringBuilder()

                if (options1 == 0) {
                    sb.append(BValue[options2])

                } else {
                    sb.append(BValue[options2].toInt() + 12)
                }
                sb.append(":")
                sb.append(CValue[options3])

                if ("sTime" == flag) {
                    list[position].sTime = sb.toString()
                } else {
                    list[position].eTime = sb.toString()
                }


                (view as TextView).text = sb.toString()



                val map = ArrayMap<String,String>()
                map["begin"]="00:00"
                map["begin"]="18:00"








            })
            .setLayoutRes(R.layout.pickerview_custom_options) { v ->
                val tvSubmit = v.findViewById(R.id.tv_finish) as TextView

                val ivCancel = v.findViewById(R.id.iv_cancel) as ImageView
                tvSubmit.setOnClickListener {
                    pvNoLinkOptions?.returnData()
                    pvNoLinkOptions?.dismiss()
                }

                ivCancel.setOnClickListener { pvNoLinkOptions?.dismiss() }


            }
            .isDialog(true)
            .build()

        pvNoLinkOptions?.setNPicker(AValue, BValue, CValue)
        pvNoLinkOptions?.setSelectOptions(0, 1, 1)
        // pvNoLinkOptions.show()

        val mDialog = pvNoLinkOptions?.getDialog()
        if (mDialog != null) {

            val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM
            )

            params.leftMargin = 0
            params.rightMargin = 0
            pvNoLinkOptions?.getDialogContainerLayout()?.setLayoutParams(params)

            val dialogWindow = mDialog!!.getWindow()
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim)//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM)//改成Bottom,底部显示
            }
        }


    }


}