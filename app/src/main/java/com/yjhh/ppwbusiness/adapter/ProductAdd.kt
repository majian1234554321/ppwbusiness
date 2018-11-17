package com.yjhh.ppwbusiness.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.BaseApplication.context
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.iv
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils
import kotlinx.android.synthetic.main.images.view.*

class ProductAdd( var lists: List<String>) : BaseQuickAdapter<String,BaseViewHolder>(R.layout.images,lists) {
    override fun convert(helper: BaseViewHolder?, item: String?) {



      val iv =  (helper?.getView<View>(R.id.iv) as ImageView)

        if ("EMPTY" == item) {

            ImageLoaderUtils.loadImgId(
                context,
                iv,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,
                0
            )
        } else {
            ImageLoaderUtils.load(context, iv, item, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, 0)
        }


    }


}