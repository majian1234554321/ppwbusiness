package com.yjhh.ppwbusiness.views

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.uuzuche.lib_zxing.activity.CaptureFragment
import com.uuzuche.lib_zxing.activity.CodeUtils
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.views.cui.CustomPopWindow
import com.yjhh.ppwbusiness.views.writeoff.CancellationBeforeActivity

import kotlinx.android.synthetic.main.activity_capture2.*

class CaptureActivity2 : BaseActivity(), View.OnClickListener {

    var mCustomPopWindow: CustomPopWindow? = null

    override fun onClick(v: View?) = when (v?.id) {
        R.id.iv_back -> {
            finish()
        }
        R.id.tv_save -> {


            val contentView = LayoutInflater.from(this).inflate(R.layout.pop_layout, null)
            contentView.findViewById<TextView>(R.id.tv)
                .setOnClickListener {
                   startActivity(Intent(this@CaptureActivity2, CancellationBeforeActivity::class.java))

                    mCustomPopWindow?.dissmiss()
                }

            mCustomPopWindow = CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .create()
                .showAsDropDown(v, -160, 10);


        }

        else -> {
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture2)

        val captureFragment = CaptureFragment()
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera)
        captureFragment.analyzeCallback = analyzeCallback



        supportFragmentManager.beginTransaction().replace(R.id.fl_my_container, captureFragment).commit()

        initView()
    }

    var isOpen = false

    private fun initView() {
        val linearLayout = findViewById<LinearLayout>(R.id.linear1)

        arrayOf(iv_back, tv_save).forEach {
            it.setOnClickListener(this)
        }

        linearLayout.setOnClickListener {
            if (!isOpen) {
                CodeUtils.isLightEnable(true)
                isOpen = true
            } else {
                CodeUtils.isLightEnable(false)
                isOpen = false
            }
        }
    }

    /**
     * 二维码解析回调函数
     */
    internal var analyzeCallback: CodeUtils.AnalyzeCallback = object : CodeUtils.AnalyzeCallback {
        override fun onAnalyzeSuccess(mBitmap: Bitmap, result: String) {
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS)
            bundle.putString(CodeUtils.RESULT_STRING, result)
            resultIntent.putExtras(bundle)
            this@CaptureActivity2.setResult(RESULT_OK, resultIntent)
            this@CaptureActivity2.finish()
        }

        override fun onAnalyzeFailed() {
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED)
            bundle.putString(CodeUtils.RESULT_STRING, "")
            resultIntent.putExtras(bundle)
            this@CaptureActivity2.setResult(RESULT_OK, resultIntent)
            this@CaptureActivity2.finish()
        }
    }


}