package com.yjhh.ppwbusiness.adapter

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.BaseApplication.context
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.layout.item
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils

class ProductAdd(var lists: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.images, lists) {
    override fun convert(helper: BaseViewHolder?, item: String?) {

        helper?.addOnClickListener(R.id.iv_delete)

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)


        val iv = (holder?.getView<View>(R.id.iv) as ImageView)

        val iv_delete = (holder.getView<View>(R.id.iv_delete) as ImageView)

        if (holder.adapterPosition == itemCount) {
            ImageLoaderUtils.loadImgId(
                context,
                iv,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,
                0
            )
            iv_delete.visibility = View.GONE


            if (holder.adapterPosition == 3) {

            } else {

            }

        } else {
            ImageLoaderUtils.load(context, iv, item, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, 0)
            iv_delete.visibility = View.VISIBLE
        }



    }



    override fun getItemCount(): Int {
        return when {
            lists.size >= 3 -> 3
            else -> 1 + lists.size
        }
    }






}