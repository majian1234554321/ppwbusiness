package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.BaseApplication
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.WriteOffStoreBean
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils
import kotlinx.android.synthetic.main.images.view.*

class WriteOffStoreAdapter(data: List<WriteOffStoreBean.ItemBean>) :
    BaseQuickAdapter<WriteOffStoreBean.ItemBean, BaseViewHolder>(R.layout.writeoffstoreadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: WriteOffStoreBean.ItemBean?) {


        helper?.setText(R.id.tv_name,item?.name)

        ImageLoaderUtils.load(
            BaseApplication.context,
            helper?.getView(R.id.iv_image),
            item?.logoUrl,
            R.drawable.icon_place,
            R.drawable.icon_place,
            0
        )

    }
}