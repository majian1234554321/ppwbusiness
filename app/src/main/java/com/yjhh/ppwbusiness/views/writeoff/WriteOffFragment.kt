package com.yjhh.ppwbusiness.views.writeoff

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContextCompat
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.CancelationBeforeBean
import com.yjhh.ppwbusiness.ipresent.CancellationPresent
import com.yjhh.ppwbusiness.iview.CancellationView
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import com.yjhh.ppwbusiness.utils.TimeUtil
import com.yjhh.ppwbusiness.views.reservation.CancelReServationFragment
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.writeofffragment.*
import java.util.*

class WriteOffFragment : BaseFragment(), View.OnClickListener, CancellationView {

    var totleprice = 0f

    var discount = "0"


    var useRange: String? = "0"
    var types: String? = "0"
    var ids: String? = "0"
    var shopid: String? = null

    override fun onSuccessCancellation(response: String?, flag: String?) {

        val model = ProcessObserver2.constructor.gson.fromJson<CancelationBeforeBean.ItemsBean>(
            response,
            CancelationBeforeBean.ItemsBean::class.java
        )


        if (model != null) {
            val textNo = "券码  ${model.code}"

            tv_No.text = TextStyleUtils.changeTextColor(textNo, 0, 2, Color.parseColor("#99333333"))


            tv_count_tips.text = "未满${model.useRange},不享受优惠"

            types = model.type

            discount = model.value

            if ("0" == model.type) {
                val textprice = "¥ ${model.value}"
                tv_cardPrice.text = TextStyleUtils.changeTextAa(textprice, 0, 1, 10)
            } else {
                val textprice = "${model.value.toFloat() * 10} 折"
                tv_cardPrice.text = textprice
            }

            useRange = model.useRange
            tv_canUse.text = "限${model.useRange}元以上使用"

            tv_cardName.text = model.title


            iev1.setTextContent(model.userName)
            iev2.setTextContent(TimeUtil.stampToDate(model.expiredTime))

        }
    }

    override fun onFault(errorMsg: String?) {

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_select -> {
                startForResult(WriteOffStoreFragment(), 10086)
            }
            else -> {

                if (!TextUtils.isEmpty(shopid) && !TextUtils.isEmpty(et_totleprice.text.toString())) {
                        start(
                            ConfirmCancellationFragment.newInstance(
                                ids,
                                et_totleprice.text.toString(),
                                shopid,
                                discountNoPrice.text.toString()
                            )
                        )
                    } else {
                    Toast.makeText(mActivity, "请选择核销门店", Toast.LENGTH_SHORT).show()
                }


            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.writeofffragment


    @SuppressLint("SetTextI18n")
    override fun initView() {


        arrayOf(tv_select, mb_next).forEach {
            it.setOnClickListener(this)
        }


        val ids = arguments?.getString("ids")

        CancellationPresent(mActivity, this).detail(ids)


        /*   val dis2 = Observable.combineLatest(ob1, ob2,
               BiFunction<CharSequence, CharSequence, String> { t1, t2 ->
                   if (!TextUtils.isEmpty(t1) && !TextUtils.isEmpty(t2)) {
                       //calculation(t1.toString(),t2.toString(),)
                   } else if (!TextUtils.isEmpty(t1) && TextUtils.isEmpty(t2)) {
                       et_totleprice.visibility= View.VISIBLE
                       discountNoPrice.visibility = View.GONE
                   } else  {
                       et_totleprice.visibility= View.GONE
                   }
                   "1"
               })*/


        val dis1 = RxTextView.textChanges(et_totleprice).subscribe {
            if (!TextUtils.isEmpty(it)) {

                if (it.toString().toFloat() >= useRange?.toFloat()!!) {
                    ll.visibility = View.VISIBLE
                    tv_count_tips.text = "已满$useRange,享受优惠"
                    tv_count_tips.setBackgroundColor(Color.parseColor("#454545"))
                    tv_count_tips.setTextColor(getColor(mActivity, R.color.zthj))


                    if (!TextUtils.isEmpty(discountNoPrice.text.toString())) {
                        tv_finalprice.text =
                                calculation(it.toString(), discountNoPrice.text.toString(), types, discount).toString()
                    } else {
                        tv_finalprice.text = calculation(it.toString(), "0", types, discount).toString()
                    }


                } else {
                    tv_count_tips.setBackgroundColor(Color.parseColor("#f8f8f8"))
                    tv_count_tips.setTextColor(Color.parseColor("#F3DAAF"))
                    tv_count_tips.text = "未满$useRange,不享受优惠"
                    ll.visibility = View.GONE

                    tv_finalprice.text = calculation(it.toString(), "0", types, "0").toString()

                }

            } else {
                tv_count_tips.setBackgroundColor(Color.parseColor("#f8f8f8"))
                tv_count_tips.setTextColor(Color.parseColor("#F3DAAF"))
                tv_count_tips.text = "未满$useRange,不享受优惠"

                ll.visibility = View.GONE
                tv_finalprice.text = ""


            }
        }


        val dis2 = RxTextView.textChanges(discountNoPrice).subscribe {
            if (!TextUtils.isEmpty(it)) {
                if (!TextUtils.isEmpty(et_totleprice.text.toString())) {
                    tv_finalprice.text =
                            calculation(
                                et_totleprice.text.toString(),
                                discountNoPrice.text.toString(),
                                types,
                                discount
                            ).toString()
                } else {
                    tv_finalprice.text = ""

                }


            } else {
                if (!TextUtils.isEmpty(et_totleprice.text.toString())) {
                    tv_finalprice.text =
                            calculation(
                                et_totleprice.text.toString(),
                                "0",
                                types,
                                "0"
                            ).toString()
                }

            }
        }

        compositeDisposable.add(dis1)
        compositeDisposable.add(dis2)

    }


    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)
        if (requestCode == 10086) {


            val name = data?.getString("name")
            val ids = data?.getString("ids")
            if (name != null) {
                tv_select.text = name

                shopid = ids
            }


        }

    }


    fun calculation(totleprice: String, discountNoPrice: String, flag: String?, discountValue: String): Float {


        var price = 0f

        if ("1" == flag) {   //  类型（0满减（面值）1 抵扣（折扣百分比））
            if ("0" == discountValue) {  //“0” 不打折扣
                price = (totleprice.toFloat() - discountNoPrice.toFloat())
            } else {
                price = (totleprice.toFloat() - discountNoPrice.toFloat()) * discountValue.toFloat()
            }

        } else {
            price = totleprice.toFloat() - discountNoPrice.toFloat() - discountValue.toFloat()
        }


        return price
    }


    companion object {
        fun newInstance(ids: String): WriteOffFragment {
            val fragment = WriteOffFragment()
            val bundle = Bundle()

            bundle.putString("ids", ids)

            fragment.arguments = bundle
            return fragment
        }
    }

}