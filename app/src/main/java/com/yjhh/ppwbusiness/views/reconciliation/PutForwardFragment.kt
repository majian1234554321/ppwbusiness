package com.yjhh.ppwbusiness.views.reconciliation

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Half.toFloat
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.PutForwardAdapter
import com.yjhh.ppwbusiness.adapter.ReconciliationAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.ReconciliationBean
import com.yjhh.ppwbusiness.bean.ReconciliationItemBean
import com.yjhh.ppwbusiness.bean.WithDrawBean
import com.yjhh.ppwbusiness.ipresent.WithDrawPresent
import com.yjhh.ppwbusiness.iview.WithDrowView
import kotlinx.android.synthetic.main.putforwardfragment.*

class PutForwardFragment : BaseFragment(), WithDrowView, View.OnClickListener {

    var selectPosition = 0


    var payType: List<WithDrawBean.BindsBean>? = null


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_wechat2 -> {


                if ("未绑定" != tv_wechat2.text.toString()) {

                    selectPosition = 0
                    tv_wechat2.compoundDrawablePadding = 8
                    val drawable = context.resources.getDrawable(
                        R.drawable.icon_check
                    )
                    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight);
                    tv_wechat2.setCompoundDrawables(null, null, drawable, null)
                    tv_alipay2.setCompoundDrawables(null, null, null, null)
                    tv_bank2.setCompoundDrawables(null, null, null, null)


                    tv_wechat1.setTextColor(Color.parseColor("#2C85FF"))
                    tv_wechat2.setTextColor(Color.parseColor("#2C85FF"))


                    tv_alipay1.setTextColor(Color.parseColor("#333333"))
                    tv_alipay2.setTextColor(Color.parseColor("#333333"))

                    tv_bank1.setTextColor(Color.parseColor("#333333"))
                    tv_bank2.setTextColor(Color.parseColor("#333333"))

                }


            }
            R.id.tv_alipay2 -> {

                if ("未绑定" != tv_alipay2.text.toString()) {

                    selectPosition = 1

                    val drawable = context.resources.getDrawable(
                        R.drawable.icon_check
                    )
                    tv_alipay2.compoundDrawablePadding = 8
                    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight);
                    tv_wechat2.setCompoundDrawables(null, null, null, null)
                    tv_alipay2.setCompoundDrawables(null, null, drawable, null)
                    tv_bank2.setCompoundDrawables(null, null, null, null)


                    tv_wechat1.setTextColor(Color.parseColor("#333333"))
                    tv_wechat2.setTextColor(Color.parseColor("#333333"))

                    tv_alipay1.setTextColor(Color.parseColor("#2C85FF"))
                    tv_alipay2.setTextColor(Color.parseColor("#2C85FF"))

                    tv_bank1.setTextColor(Color.parseColor("#333333"))
                    tv_bank2.setTextColor(Color.parseColor("#333333"))
                }
            }

            R.id.tv_bank2 -> {

                if ("未绑定" != tv_bank2.text.toString()) {

                    selectPosition = 2

                    tv_bank2.compoundDrawablePadding = 8
                    val drawable = context.resources.getDrawable(
                        R.drawable.icon_check
                    )
                    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight);
                    tv_wechat2.setCompoundDrawables(null, null, null, null)
                    tv_alipay2.setCompoundDrawables(null, null, null, null)
                    tv_bank2.setCompoundDrawables(null, null, drawable, null)

                    tv_wechat1.setTextColor(Color.parseColor("#333333"))
                    tv_wechat2.setTextColor(Color.parseColor("#333333"))

                    tv_alipay1.setTextColor(Color.parseColor("#333333"))
                    tv_alipay2.setTextColor(Color.parseColor("#333333"))

                    tv_bank1.setTextColor(Color.parseColor("#2C85FF"))
                    tv_bank2.setTextColor(Color.parseColor("#2C85FF"))
                }
            }


            else -> {
            }
        }
    }

    var balance = 0f
    var minRange = 0f

    val pageSize = 15
    var pageIndex = 0


    var mAdapter: PutForwardAdapter? = null
    val lists = ArrayList<ReconciliationItemBean.ItemsBean>()
    var present: WithDrawPresent? = null

    override fun onSuccessView(response: String?, flag: String) {


        when (flag) {
            "refresh" -> {
                val model2 = Gson().fromJson<ReconciliationItemBean>(response, ReconciliationItemBean::class.java)
                if (pageIndex == 0 && model2.items.size == 0) {

                    val view = View.inflate(mActivity, R.layout.emptyview, null)
                    view.findViewById<TextView>(R.id.tv_tips).text = "暂无数据"
                    mAdapter?.emptyView = view


                } else {
                    mAdapter?.setNewData(model2.items)
                }
            }

            "load" -> {
                val model2 = Gson().fromJson<ReconciliationItemBean>(response, ReconciliationItemBean::class.java)
                if (pageSize > model2.items.size) {
                    mAdapter?.loadMoreEnd()
                } else {
                    mAdapter?.addData(model2.items)
                    mAdapter?.loadMoreComplete()
                }
            }

            "wirDraw" -> {


                start(PutForwardSuccessFragment.newInstance(response))

            }

            else -> {
                val model = Gson().fromJson<WithDrawBean>(response, WithDrawBean::class.java)
                balance = model.balance;
                minRange = model.minRange;


                payType = model.binds


                tv_totalPrice.text = BaseApplication.getIns().getString(R.string.rmb_price_double2, model.balance)

                if (model.binds != null && model.binds.size >= 1 && model.binds[0] != null) {
                    tv_wechat2.text = model.binds[0].text
                }

                if (model.binds != null && model.binds.size >= 2 && model.binds[1] != null) {
                    tv_alipay2.text = model.binds[1].text
                }

                if (model.binds != null && model.binds.size >= 3 && model.binds[2] != null) {
                    tv_bank2.text = model.binds[2].text
                }


            }
        }


    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.putforwardfragment


    override fun initView() {


        present = WithDrawPresent(mActivity, this)

        present?.shopAdminWithdraw()

        present?.logs(pageIndex, pageSize, "refresh")


        tv_apply.setOnClickListener {
            if (!TextUtils.isEmpty(et_price.text.toString()) && et_price.text.toString().toFloat() >= minRange && et_price.text.toString().toFloat() <= balance) {

                if (payType != null && payType?.isNotEmpty()!! && payType!!.size > selectPosition) {
                    present?.apply(et_price.text.toString(), payType!![selectPosition].id, "wirDraw")
                } else {
                    Toast.makeText(context, "提现方式增加修改请联系运营人员", Toast.LENGTH_SHORT).show()
                }


            } else {
                Toast.makeText(context, "请输入正确的提现金额", Toast.LENGTH_SHORT).show()
            }


        }


        arrayOf(tv_wechat2, tv_alipay2, tv_bank2).forEach {
            it.setOnClickListener(this)
        }


        iv_back.setOnClickListener {
            mActivity.onBackPressed()
        }


        val dis = RxTextView.textChanges(et_price).subscribe {
            if (!TextUtils.isEmpty(it) && it.toString().toFloat() > balance) {
                tv_tips1.visibility = View.VISIBLE
                tv_tips1.text = "超出账户可提现金额,请核实后输入"
            } else if (!TextUtils.isEmpty(it) && it.toString().toFloat() < minRange) {
                tv_tips1.visibility = View.VISIBLE
                tv_tips1.text = "最低提现${minRange}元"
            } else {
                tv_tips1.visibility = View.GONE
            }
        }

        compositeDisposable.add(dis)



        initAdapter()


    }


    private fun initAdapter() {
        mAdapter = PutForwardAdapter(lists)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(mActivity)
        recyclerView.adapter = mAdapter
        mAdapter?.setOnLoadMoreListener({
            loadMore()
        }, recyclerView)
    }

    private fun loadMore() {
        pageIndex++
        present?.logs(pageIndex, pageSize, "load")

    }
}