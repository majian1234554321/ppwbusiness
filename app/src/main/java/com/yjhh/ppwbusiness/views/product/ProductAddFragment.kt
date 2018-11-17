package com.yjhh.ppwbusiness.views.product

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.ProductAdd
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.utils.Glide4Engine
import com.yjhh.ppwbusiness.views.cui.AbsSheetDialog
import com.yjhh.ppwbusiness.views.cui.AlertDialogFactory
import com.yjhh.ppwbusiness.views.cui.BottomVerSheetDialog
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import kotlinx.android.synthetic.main.productadd.*
import java.util.*

class ProductAddFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.productadd

    var mAdapter: ProductAdd? = null

    override fun initView() {
        recyclerView.layoutManager = GridLayoutManager(mActivity, 3)
        val lists = ArrayList<String>()
        lists.add("EMPTY")
        mAdapter = ProductAdd(lists)
        recyclerView.adapter = mAdapter



        mAdapter?.setOnItemChildClickListener { adapter, view, position ->

            if (lists[position] == "EMPTY") {
                //拍照

                photo()

            } else {

            }

        }

    }

    private fun photo() {
        AlertDialogFactory.createFactory(mActivity).getBottomVerDialog(null,
            Arrays.asList<BottomVerSheetDialog.Bean>(
                BottomVerSheetDialog.Bean(
                    "拍照",
                    R.color.lib_pub_color_text_main,
                    false
                ),
                BottomVerSheetDialog.Bean(
                    "从手机相册选择",
                    R.color.lib_pub_color_text_main,
                    false
                )
            ),
            object : AbsSheetDialog.OnItemClickListener<BottomVerSheetDialog.Bean> {
                override fun onClick(dlg: Dialog, position: Int, item: BottomVerSheetDialog.Bean) {

                    /*when (position) {
                        0 -> {
                            showTakePicture()
                        }
                        else -> {


                            Matisse.from(this@MerchantSettingActivity)
                                .choose(MimeType.ofAll(), false)
                                .countable(true)
                                .capture(true)
                                .captureStrategy(
                                    CaptureStrategy(true, "com.yjhh.ppwcustomer.fileProvider")
                                )
                                .maxSelectable(1)
                                //.addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(
                                    resources.getDimensionPixelSize(R.dimen.grid_expected_size)
                                )
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                //                                            .imageEngine(new GlideEngine())  // for glide-V3
                                .imageEngine(Glide4Engine())    // for glide-V4
                                .setOnSelectedListener { uriList, pathList ->
                                    // DO SOMETHING IMMEDIATELY HERE
                                    Log.e("onSelected", "onSelected: pathList=$pathList")
                                }
                                .originalEnable(true)
                                .maxOriginalSize(10)
                                //.autoHideToolbarOnSingleTap(true)
                                .setOnCheckedListener { isChecked ->
                                    // DO SOMETHING IMMEDIATELY HERE
                                    Log.e("isChecked", "onCheck: isChecked=$isChecked")
                                }
                                .forResult(10085)

                        }
                    }*/

                }

                override fun onCancel(dlg: Dialog) {

                }
            })
    }


}