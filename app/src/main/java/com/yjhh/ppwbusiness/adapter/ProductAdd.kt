package com.yjhh.ppwbusiness.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import com.yjhh.ppwbusiness.BaseApplication

import com.yjhh.ppwbusiness.R
import kotlinx.android.synthetic.main.images.view.*

import com.yjhh.ppwbusiness.utils.ImageLoaderUtils

class ProductAdd(var context: Context, var lists: List<String>) : RecyclerView.Adapter<ProductAdd.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = View.inflate(context, R.layout.images, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = when {
        lists.size >= 3 -> 3
        else -> 1 + lists.size
    }

    override fun onBindViewHolder(holder: ViewHolder, adapterPosition: Int) {


        if (holder.adapterPosition == lists.size) {
            ImageLoaderUtils.loadImgId(
                BaseApplication.context,
                holder.itemView.iv,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,
                0
            )
            holder.itemView.iv_delete.visibility = View.GONE


            if (holder.adapterPosition == 3) {

            } else {

            }

        } else {
            ImageLoaderUtils.load(
                BaseApplication.context,
                holder.itemView.iv,
                lists[adapterPosition],
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,
                0
            )
            holder.itemView.iv_delete.visibility = View.VISIBLE
        }


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}









