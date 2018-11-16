package com.yjhh.ppwbusiness.views.merchant

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.views.cui.AbsSheetDialog
import com.yjhh.ppwbusiness.views.cui.AlertDialogFactory
import com.yjhh.ppwbusiness.views.cui.BottomVerSheetDialog
import kotlinx.android.synthetic.main.activity_merchant_setting.*
import java.util.*

class MerchantSettingActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iev_logo -> {
                AlertDialogFactory.createFactory(this).getBottomVerDialog(null,
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

                        }

                        override  fun onCancel(dlg: Dialog) {

                        }
                    })
            }
            else -> {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_setting)

        iev_logo.setOnClickListener(this)
    }
}
