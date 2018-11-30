package com.yjhh.ppwbusiness.views.writeoff

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import kotlinx.android.synthetic.main.writeofffragment.*

class WriteOffFragment : BaseFragment(), View.OnClickListener {
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
        val textNo = "券码  8888888888888"

        tv_No.text = TextStyleUtils.changeTextColor(textNo, 0, 2, Color.parseColor("#99333333"))

        val textprice = "¥ 200"
        tv_cardPrice.text = TextStyleUtils.changeTextAa(textprice, 0, 1, 10)


        arrayOf(tv_select,mb_next).forEach {
            it.setOnClickListener(this)
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

}