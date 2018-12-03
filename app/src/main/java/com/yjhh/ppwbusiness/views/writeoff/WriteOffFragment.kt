package com.yjhh.ppwbusiness.views.writeoff

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContextCompat
import android.view.View
import android.widget.TextView
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

    var finalPayPrice = 0f


    var useRange: String? = null

    override fun onSuccessCancellation(response: String?, flag: String?) {

        val model = ProcessObserver2.constructor.gson.fromJson<CancelationBeforeBean.ItemsBean>(
            response,
            CancelationBeforeBean.ItemsBean::class.java
        )


        if (model != null) {
            val textNo = "券码  ${model.code}"

            tv_No.text = TextStyleUtils.changeTextColor(textNo, 0, 2, Color.parseColor("#99333333"))


            //  类型（0满减（面值）1 抵扣（折扣百分比））


            model.value;
            tv_count_tips.text = "未满${model.useRange},不享受优惠"
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

                start(ConfirmCancellationFragment())

            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.writeofffragment


    override fun initView() {


        arrayOf(tv_select, mb_next).forEach {
            it.setOnClickListener(this)
        }


        val ids = arguments?.getString("ids")

        CancellationPresent(mActivity, this).detail(ids)


        val ob1 = RxTextView.textChanges(et_totleprice)

        val ob2 = RxTextView.textChanges(discountNoPrice)


        val dis2 = Observable.combineLatest(ob1, ob2,
            BiFunction<CharSequence, CharSequence, String> { t1, t2 ->
                if (!TextUtils.isEmpty(t1) && !TextUtils.isEmpty(t2)) {
                    Observable.just("1")
                } else {
                    if (!TextUtils.isEmpty(t1)) {

                    }
                }


                "1"
            })


        val dis = RxTextView.textChanges(et_totleprice)
            .flatMap {
                if (!TextUtils.isEmpty(it)) {
                    discountNoPrice.visibility = View.VISIBLE
                    Observable.just(it)

                } else {
                    discountNoPrice.visibility = View.GONE
                    Observable.just(0)
                }
            }

    }


    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)
        if (requestCode == 10086) {


            val ids = data?.getString("ids")
            if (ids != null) {
                tv_select.text = ids
            }


        }

    }


    fun calculation(totleprice: String, discountNoPrice: String, flag: String?, discountValue: String): Float {


        var price = 0f

        if ("折扣" == flag) {
            price = (totleprice.toFloat() - discountNoPrice.toFloat()) * discountValue.toFloat()
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