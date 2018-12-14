package com.yjhh.ppwbusiness.views.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.utils.APKVersionCodeUtils
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.backviewfragment.*

@SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface")
class BackViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_view2)


        val url = intent?.getStringExtra("url")




        mWebView.clearCache(true)

        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true

        webSettings.allowFileAccess = true// 设置允许访问文件数据 v

        webSettings.setSupportZoom(false)

        webSettings.builtInZoomControls = false

        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true

        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN

        webSettings.databaseEnabled = true
        webSettings.useWideViewPort = true

        mWebView.setInitialScale(25)


        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
//                    if (newProgress > 70 && ll != null) {
//                        ll.visibility = View.GONE
//                    }
            }

            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)

                if (!TextUtils.isEmpty(title) && tv_title != null) {
                    tv_title.setText(title)
                }
            }
        }
        iv_back.setOnClickListener { finish() }

        val map = HashMap<String, String?>()
        map.put("User-Agent", WebSettings.getDefaultUserAgent(BaseApplication.context) + "PPW_App")
        map.put("userAgent", "PPW_App")

        map.put("PPW-TERMINAL", "1") //（0 用户端 1商户端)
        map.put(
            "PPW-APP-VERSION",
            APKVersionCodeUtils.getVersionCode(
                BaseApplication.context

            ).toString()
        )
        //.header("PPW-SIGN", "XMLHttpRequest")
        map.put("PPW-TIMESTAMP", (System.currentTimeMillis() / 1000).toInt().toString())
        map.put("PPW-API-VERSION", "1.0")
        map.put("PPW-MARKET-ID", APKVersionCodeUtils.getChannelName(BaseApplication.context))

        map.put("PPW-DEVICE-ID", APKVersionCodeUtils.getChannelName(BaseApplication.context))

        map.put(
            "JSESSIONID",
            SharedPreferencesUtils.getParam(BaseApplication.context, "sessionId", "-1").toString()
        )


        mWebView.loadUrl(url, map)

      //  mWebView.loadUrl(url)
        mWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)

            }
        }

        // mWebView.addJavascriptInterface(MyJSInterface(context), "android")


        iv_back.setOnClickListener {
            if (mWebView.canGoBack()) {
                mWebView.goBack()
            } else {
                finish()
            }

        }


    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}
