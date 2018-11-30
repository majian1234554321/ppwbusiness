package com.yjhh.ppwbusiness.views.writeoff

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.views.login.LoginFragment
import me.yokeyword.fragmentation.SupportHelper.findFragment
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

class CancellationBeforeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_detail)

        if (findFragment(CancellationDetailsFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, CancellationBeforeFragment.newInstance())
        }
    }


    override fun onBackPressedSupport() {

        super.onBackPressedSupport()
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {

        return DefaultHorizontalAnimator()
    }

}
