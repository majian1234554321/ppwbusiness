package com.yjhh.ppwbusiness.views.writeoff

import android.os.Bundle
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseFragment


class CancellationDetailsFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.cancellationdetailsfragment


    companion object {
        fun newInstance(): CancellationDetailsFragment {

            val args = Bundle()

            val fragment = CancellationDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}