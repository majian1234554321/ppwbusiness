package com.yjhh.ppwbusiness.adapter

import android.content.Context
import android.opengl.Visibility
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseViewHolder

import com.yjhh.ppwbusiness.BaseApplication

import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.interfaces.OnRecycleViewItemChildClickListener
import com.yjhh.ppwbusiness.interfaces.OnRecycleViewItemClickListener
import kotlinx.android.synthetic.main.images.view.*

import com.yjhh.ppwbusiness.utils.ImageLoaderUtils

class ProductAdd(var context: Context, var lists: List<String>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<ProductAdd.ViewHolder>() {

    public var maxValue = 3


    constructor(context: Context, lists: List<String>, maxValue: Int) : this(context, lists) {
        this.maxValue = maxValue
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = View.inflate(context, R.layout.images, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = when {
        lists.size >= maxValue -> maxValue
        else -> 1 + lists.size
    }

    override fun onBindViewHolder(holder: ViewHolder, adapterPosition: Int) {


        if (holder.adapterPosition == lists.size) {
            ImageLoaderUtils.loadImgId(
                BaseApplication.context,
                holder.itemView.iv,
                R.drawable.ic_upload,
                R.drawable.ic_upload,
                R.drawable.ic_upload,
                0
            )
            holder.itemView.iv_zhutu.visibility = View.GONE
            holder.itemView.iv_delete.visibility = View.GONE

            holder.itemView.setOnClickListener { v ->
                mOnItemClickListener?.onRecycleViewItemClick(
                    v,
                    adapterPosition,
                    false
                )
            }
        } else {
            ImageLoaderUtils.load(
                BaseApplication.context,
                holder.itemView.iv,
                lists[adapterPosition],
                R.drawable.icon_place,
                R.drawable.icon_place,
                0
            )
            holder.itemView.iv_delete.visibility = View.VISIBLE
            if (adapterPosition == 0) {
                holder.itemView.iv_zhutu.visibility = View.VISIBLE
            } else {
                holder.itemView.iv_zhutu.visibility = View.GONE
            }



            holder.itemView.iv_delete.setOnClickListener { v ->
                mOnItemChildClickListener?.onItemChildClick(v, adapterPosition)
            }
            holder.itemView.setOnClickListener { v ->
                mOnItemClickListener?.onRecycleViewItemClick(
                    v,
                    adapterPosition,
                    true
                )
            }
        }


    }


    fun setOnItemClickListener(listener: OnRecycleViewItemClickListener?) {
        this.mOnItemClickListener = listener
    }


    fun setOnItemChildClickListener(listener: OnRecycleViewItemChildClickListener) {
        this.mOnItemChildClickListener = listener
    }

    private var mOnItemClickListener: OnRecycleViewItemClickListener? = null
    private var mOnItemChildClickListener: OnRecycleViewItemChildClickListener? = null

    class ViewHolder(itemView: View) : BaseViewHolder(itemView)


}









