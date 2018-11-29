package com.yjhh.ppwbusiness.views.cui

import com.azhon.appupdate.listener.OnDownloadListener

import java.io.File

class AA {
    internal var on: OnDownloadListener = object : OnDownloadListener {
        override fun start() {

        }

        override fun downloading(max: Int, progress: Int) {

        }

        override fun done(apk: File) {

        }

        override fun error(e: Exception) {

        }
    }
}
