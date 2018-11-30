package com.yjhh.ppwbusiness.adapter

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils

class PhotoAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.photoadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {


        val iv = (helper?.getView<View>(R.id.iv) as ImageView)

        val iv_delete = (helper?.getView<View>(R.id.iv_delete) as ImageView)

        if ("EMPTY" == item) {

            ImageLoaderUtils.loadImgId(
                BaseApplication.context,
                iv,
                R.drawable.camera,
                R.drawable.camera,
                R.drawable.camera,
                0
            )

            iv_delete.visibility = View.GONE

        } else {
            ImageLoaderUtils.load(
                BaseApplication.context,
                iv,
                item,
                R.drawable.icon_place,
                R.drawable.icon_place,
                0
            )
            iv_delete.visibility = View.VISIBLE
        }


        helper.addOnClickListener(R.id.iv_delete)

    }
}