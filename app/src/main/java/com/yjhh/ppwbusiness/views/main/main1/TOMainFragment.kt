package com.yjhh.ppwbusiness.views.main.main1

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyPagerAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.main1fragment.*

class TOMainFragment : BaseFragment() {

    private val mTitles = arrayOf("待接单", "待配送", "催单", "退款")

    override fun getLayoutRes(): Int = R.layout.tomainfragment


    override fun initView() {
        val fragments = ArrayList<BaseFragment>()
        fragments.add(TOMainFragment())
        fragments.add(PFMainFragment())
        viewPager.adapter = MyPagerAdapter(childFragmentManager, fragments, mTitles)
    }


}
