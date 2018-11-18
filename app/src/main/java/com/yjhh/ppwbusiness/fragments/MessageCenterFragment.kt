package com.yjhh.ppwbusiness.fragments

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyPagerAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.messagecenterfragment.*

class MessageCenterFragment : BaseFragment() {

    val array = arrayOf("我的消息", "系统消息")
    override fun getLayoutRes(): Int = R.layout.messagecenterfragment

    override fun initView() {
        val mFragments = ArrayList<BaseFragment>()
        mFragments.add(MessageCenter1Fragment())
        mFragments.add(MessageCenter2Fragment())


        mViewPager.adapter = MyPagerAdapter(childFragmentManager, mFragments, array)
        mTabLayout.setViewPager(mViewPager)
    }
}
