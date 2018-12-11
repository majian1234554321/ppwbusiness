package com.yjhh.ppwbusiness.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter

import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.iv_image
import com.yjhh.ppwbusiness.bean.ActivityCenterBean
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils


class ActivityCenterAdapter(data: List<ActivityCenterBean>) :
    BaseMultiItemQuickAdapter<ActivityCenterBean, BaseViewHolder>(data) {
    override fun convert(helper: BaseViewHolder?, item: ActivityCenterBean?) {
        when (helper?.itemViewType) {
            ActivityCenterBean.TEXT -> helper.setText(R.id.tv, item?.content?.statusText)
            ActivityCenterBean.IMG -> {
                helper.setText(R.id.tv_No, "${item?.content?.dateSign} 期")

                if (item?.content != null && item?.content?.files != null && item?.content?.files?.isNotEmpty()!!) {
                    ImageLoaderUtils.load(
                        BaseApplication.context,
                        helper?.getView<ImageView>(R.id.iv_image),
                        item?.content?.files!![0].fileUrl,
                        R.drawable.icon_place,
                        R.drawable.icon_place,
                        0
                    )
                }

                helper.setVisible(R.id.tv_background, item?.content?.status != 1)


            }


        }

    }

    init {
        addItemType(ActivityCenterBean.TEXT, R.layout.item_text_view)
        addItemType(ActivityCenterBean.IMG, R.layout.item_image_view)

    }

}
