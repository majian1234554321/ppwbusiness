package com.yjhh.ppwbusiness.views.main.main1


import android.support.v4.view.ViewPager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.MyPagerAdapter
import com.yjhh.ppwbusiness.base.BaseFragment
import kotlinx.android.synthetic.main.main1fragment.*


class Main1Fragment : BaseFragment() {

    private val mTitles = arrayOf("首页", "消息")
    override fun getLayoutRes(): Int = R.layout.main1fragment


    override fun initView() {
        sTabLayout.setTabData(mTitles)


        


        val fragments = ArrayList<BaseFragment>()
        fragments.add(TOMainFragment())
        fragments.add(PFMainFragment())
        viewPager.adapter = MyPagerAdapter(childFragmentManager, fragments, mTitles)


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageSelected(p0: Int) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }


}