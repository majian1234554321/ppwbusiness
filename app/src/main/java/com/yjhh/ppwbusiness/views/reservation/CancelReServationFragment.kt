package com.yjhh.ppwbusiness.views.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.MyMessageBean
import com.yjhh.ppwbusiness.bean.ReservationBean
import com.yjhh.ppwbusiness.fragments.MessageDetailFragment
import com.yjhh.ppwbusiness.ipresent.ReservePresent
import com.yjhh.ppwbusiness.iview.ReserveView
import com.yjhh.ppwbusiness.utils.RxBus
import kotlinx.android.synthetic.main.cancelreservationfragment.*
import me.yokeyword.fragmentation.ISupportFragment

class CancelReServationFragment : BaseFragment(), ReserveView {
    override fun onSuccessReserve(model: ReservationBean, flag: String) {
        Toast.makeText(mActivity, "取消订单成功", Toast.LENGTH_LONG).show()


        val bundle = Bundle()
        bundle.putInt("ids", model.positions)

        setFragmentResult(RESULT_OK, bundle)

        mActivity.onBackPressed()


    }

    override fun onFault(errorMsg: String?) {
        Toast.makeText(mActivity, "取消订单失败", Toast.LENGTH_LONG).show()
    }

    override fun getLayoutRes(): Int = R.layout.cancelreservationfragment

    override fun initView() {
        val drawable = getResources().getDrawable(R.drawable.icon_check)
        drawable.setBounds(0, 0, 28, 20)
        rb1.isChecked = true
        rb1.setCompoundDrawables(null, null, drawable, null)
        rb2.setCompoundDrawables(null, null, null, null);

        rg.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.rb1 -> {
                    rb1.setCompoundDrawables(null, null, drawable, null)
                    rb2.setCompoundDrawables(null, null, null, null);
                }

                R.id.rb2 -> {
                    rb1.setCompoundDrawables(null, null, null, null);
                    rb2.setCompoundDrawables(null, null, drawable, null);
                }

                else -> {
                }
            }

        }


        val id = arguments?.getString("id")
        val positions = arguments?.getString("positions")



        mb_cancel.setOnClickListener {

            ReservePresent(mActivity, this).acceptReserve(
                id,
                "1",
                tv_cause.text.toString(),
                "accept",
                positions?.toInt()!!
            )
        }

    }


    companion object {

        fun newInstance(id: String, positions: String): CancelReServationFragment {
            val fragment = CancelReServationFragment()
            val bundle = Bundle()

            bundle.putString("id", id)
            bundle.putString("positions", positions)
            fragment.arguments = bundle
            return fragment
        }


    }

}