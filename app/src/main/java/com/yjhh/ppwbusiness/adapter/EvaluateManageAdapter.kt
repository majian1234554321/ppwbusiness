package com.yjhh.ppwbusiness.adapter

import android.content.Context
import android.opengl.Visibility
import android.util.Log
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.yjhh.ppwbusiness.BaseApplication.context
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.*
import com.yjhh.ppwbusiness.R.layout.item
import com.yjhh.ppwbusiness.bean.EvaluateManageBean
import com.yjhh.ppwbusiness.bean.EvaluateManageItemBean
import com.yjhh.ppwbusiness.bean.SubCommentsBean

import com.yjhh.ppwbusiness.utils.TimeUtil
import com.yjhh.ppwbusiness.views.cui.RatingBar

import com.yjhh.ppwbusiness.views.evaluate.ninegrid.NineGridView
import com.yjhh.ppwbusiness.views.evaluate.ninegrid.NineGridViewClickAdapter


class EvaluateManageAdapter(var context: Context, data: List<MultiItemEntity>) :
    BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(data) {


    init {
        addItemType(TYPE_LEVEL_0, R.layout.evaluatemanageadapter)
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1)

    }


    override fun convert(helper: BaseViewHolder?, item22: MultiItemEntity?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


        Log.i("EvaluateManageAdapter", helper?.itemViewType.toString())

        when (helper?.itemViewType) {
            TYPE_LEVEL_0 -> {

                val item = item22 as EvaluateManageItemBean

                val list = ArrayList<String>()

                if (item.files != null) {
                    item.files.forEach {
                        list.add(it.url)
                    }

                    val view = helper.getView<NineGridView>(R.id.nineGrid)

                    view?.setAdapter(NineGridViewClickAdapter(context, list))
                    helper.setVisible(R.id.nineGrid, true)
                } else {
                    helper.setVisible(R.id.nineGrid, false)
                }



                helper.setText(R.id.tv_username, item?.nickName)
                helper.setText(R.id.tv_content, item?.content)
                helper.setText(R.id.tv_time, TimeUtil.stampToDate2(item?.time.toString()))


                helper.getView<RatingBar>(R.id.id_ratingbar)?.setStar(item?.grade?.toFloat()!!)

            }

            TYPE_LEVEL_1 -> {

                val item = item22 as SubCommentsBean


                helper.setText(R.id.tv_reply22, item.nickName)

                helper.setText(R.id.tv_content22, item.content)

                helper.setText(R.id.tv_time22, TimeUtil.stampToDate2(item.time.toString()))


            }

        }


    }


    companion object {


        const val TYPE_LEVEL_0 = 0
        const val TYPE_LEVEL_1 = 1

    }
}