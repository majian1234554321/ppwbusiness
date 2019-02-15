package com.yjhh.ppwbusiness.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.bean.SwitchShopBean
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils

class SwitchShopAdapter(data: List<SwitchShopBean.ShopsBean>) :
    BaseQuickAdapter<SwitchShopBean.ShopsBean, BaseViewHolder>(R.layout.switchshopadapter, data) {
    override fun convert(helper: BaseViewHolder?, item: SwitchShopBean.ShopsBean?) {


        ImageLoaderUtils.loadCircle(
            mContext,
            helper?.getView(R.id.iv_image),
            item?.logoUrl,
            R.drawable.icon_place,
            R.drawable.icon_place
        )
        helper?.setText(R.id.tv_text, item?.name)


    }

}