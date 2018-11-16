package com.yjhh.ppwbusiness.views.main.main1



import com.flyco.tablayout.listener.OnTabSelectListener
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.sTabLayout

import com.yjhh.ppwbusiness.base.BaseMainFragment

import kotlinx.android.synthetic.main.main1fragment.*

class Main1Fragment : BaseMainFragment() {

    private val mTitles = arrayOf("首页", "消息")
    override fun getLayoutRes(): Int = R.layout.main1fragment


    override fun initView() {
        sTabLayout.setTabData(mTitles)



        childFragmentManager.beginTransaction().replace(R.id.f_content, TOMainFragment()).commit()

        sTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabReselect(position: Int) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTabSelect(position: Int) {
                when (position) {
                    0 -> {
                        childFragmentManager.beginTransaction().replace(R.id.f_content, TOMainFragment()).commit()
                    }
                    else -> {
                        childFragmentManager.beginTransaction().replace(R.id.f_content, PFMainFragment()).commit()
                    }
                }
            }

        })


    }


}