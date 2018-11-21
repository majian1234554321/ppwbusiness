package com.yjhh.ppwbusiness.views

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.utils.ActivityCollector
import com.yjhh.ppwbusiness.utils.LogUtils
import com.yjhh.ppwbusiness.utils.RxCountDown
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import com.yjhh.ppwbusiness.views.login.LoginActivity
import com.yjhh.ppwbusiness.views.main.MainActivity
import io.reactivex.observers.DisposableObserver

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setStatusBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)






        RxCountDown.countdown(3)
            .subscribe(object : DisposableObserver<Int>() {
                override fun onNext(t: Int) {

                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                    if (!TextUtils.isEmpty(
                            SharedPreferencesUtils.getParam(
                                this@SplashActivity,
                                "sessionId",
                                ""
                            ) as String
                        )
                    ) {
                        ActivityCollector.JumpActivity(this@SplashActivity, MainActivity::class.java)
                    } else {

                        ActivityCollector.JumpActivity(this@SplashActivity, LoginActivity::class.java)
                    }

                    finish()
                }
            })


    }


    protected fun setStatusBar() {

        val decorView = window.decorView
        decorView.setBackgroundResource(R.mipmap.timg)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            val localLayoutParams = window.attributes
            localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
        }
        //修改字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}
