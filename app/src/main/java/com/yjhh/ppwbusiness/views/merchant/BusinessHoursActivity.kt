package com.yjhh.ppwbusiness.views.merchant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.BusinessHoursAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ShopSetServices
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.SETime

import com.yjhh.ppwbusiness.bean.ShopTimesModel
import com.yjhh.ppwbusiness.views.cui.AlertDialogFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_business_hours.*
import java.lang.StringBuilder

class BusinessHoursActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                this.finish()
            }

            R.id.tv_save -> {
                ApiServices.getInstance().create(ShopSetServices::class.java)
                    .editTimes(list.toArray(arrayOfNulls<ShopTimesModel>(list.size)))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object :ProcessObserver2(this@BusinessHoursActivity){
                        override fun processValue(response: String?) {
                            val intent = Intent()
                            intent.putExtra("time", list)
                            setResult(RESULT_OK, intent)
                            finish()
                        }

                        override fun onFault(message: String) {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }

                    })
            }

            else -> {
            }
        }
    }


    private val AValue = java.util.ArrayList<String>()
    private val BValue = java.util.ArrayList<String>()
    private val CValue = java.util.ArrayList<String>()


    var mAdapter: BusinessHoursAdapter? = null
    val list = ArrayList<ShopTimesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_hours)

        arrayOf(iv_back, tv_save).forEach {
            it.setOnClickListener(this)
        }

        getNoLinkData()
        list.clear()
        val timeList = intent.getParcelableArrayListExtra<ShopTimesModel>("time")
        list.addAll(timeList)


        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        recyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                this,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = BusinessHoursAdapter(list)

        recyclerView.adapter = mAdapter

        tv_add.setOnClickListener {
            if (list.size < 3) {
                list.add(ShopTimesModel("14:00", "18:00"))
                mAdapter?.setNewData(list)
            } else {
                Toast.makeText(this@BusinessHoursActivity, "营业时段最多添加3个", Toast.LENGTH_SHORT).show()
            }

        }

        mAdapter?.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.tv_sTime -> {
                    initNoLinkOptionsPicker(
                        0,
                        arrivePositionH(list[position].begin),
                        arrivePositionS(list[position].begin),
                        view,
                        position,
                        "sTime"
                    )
                    pvNoLinkOptions?.show()
                }

                R.id.tv_eTime -> {
                    initNoLinkOptionsPicker(
                        0,
                        arrivePositionH(list[position].end),
                        arrivePositionS(list[position].end),
                        view,
                        position,
                        "eTime"
                    )

                    pvNoLinkOptions?.show()
                }

                else -> {

                    AlertDialogFactory.createFactory(this).getAlertDialog(
                        null,
                        "确定删除该时间段吗?",
                        "确定", "取消",
                        AlertDialogFactory.OnClickListener { dlg, v ->
                            list.removeAt(position)
                            mAdapter?.notifyItemRemoved(position)
                        }, AlertDialogFactory.OnClickListener { dlg, v -> })


                }
            }
        }

    }


    fun arrivePositionH(value: String): Int {

        return BValue.indexOf(value.subSequence(0, 2))

    }

    fun arrivePositionS(value: String): Int {
        return BValue.indexOf(value.subSequence(3, 5))
    }


    private fun getNoLinkData() {
        AValue.add("")


        for (i in 0..23) {
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

        pvNoLinkOptions = OptionsPickerBuilder(this,
            OnOptionsSelectListener { options1, options2, options3, v ->
                //返回的分别是三个级别的选中位置
                //  Toast.makeText(this, "$options1,$options2,$options3", Toast.LENGTH_SHORT).show()


                val sb = StringBuilder()

                if (options1 == 0) {
                    sb.append(BValue[options2])

                } else {
                    sb.append(BValue[options2].toInt() + 12)
                }
                sb.append(":")
                sb.append(CValue[options3])

                if ("sTime" == flag) {
                    list[position].begin = sb.toString()
                } else {
                    list[position].end = sb.toString()
                }


                (view as TextView).text = sb.toString()


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
        pvNoLinkOptions?.setSelectOptions(a, b, c)
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
            pvNoLinkOptions?.dialogContainerLayout?.layoutParams = params

            val dialogWindow = mDialog.window
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim)//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM)//改成Bottom,底部显示
            }
        }


    }


}
