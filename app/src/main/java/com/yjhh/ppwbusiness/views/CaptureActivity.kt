package com.yjhh.ppwbusiness.views

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.uuzuche.lib_zxing.activity.CaptureFragment

import com.uuzuche.lib_zxing.activity.CodeUtils
import com.yjhh.ppwbusiness.R

class CaptureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera)
        val captureFragment = CaptureFragment()
        captureFragment.analyzeCallback = analyzeCallback
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_zxing_container, captureFragment).commit()
    }

    /**
     * 二维码解析回调函数
     */
    var analyzeCallback: CodeUtils.AnalyzeCallback = object : CodeUtils.AnalyzeCallback {
        override fun onAnalyzeSuccess(mBitmap: Bitmap, result: String) {
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS)
            bundle.putString(CodeUtils.RESULT_STRING, result)
            resultIntent.putExtras(bundle)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        override fun onAnalyzeFailed() {
            val resultIntent = Intent()
            val bundle = Bundle()
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED)
            bundle.putString(CodeUtils.RESULT_STRING, "")
            resultIntent.putExtras(bundle)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
