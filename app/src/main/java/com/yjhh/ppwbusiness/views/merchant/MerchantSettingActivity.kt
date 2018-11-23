package com.yjhh.ppwbusiness.views.merchant

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.text.TextUtils
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ShopSetServices
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.LoginBean
import com.yjhh.ppwbusiness.ipresent.ShopSetPresent
import com.yjhh.ppwbusiness.iview.ShopSetView
import com.yjhh.ppwbusiness.utils.Glide4Engine
import com.yjhh.ppwbusiness.utils.RxBus
import com.yjhh.ppwbusiness.utils.SharedPreferencesUtils
import com.yjhh.ppwbusiness.views.cui.AbsSheetDialog
import com.yjhh.ppwbusiness.views.cui.AlertDialogFactory
import com.yjhh.ppwbusiness.views.cui.BottomVerSheetDialog
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import com.zhihu.matisse.listener.OnCheckedListener
import com.zhihu.matisse.listener.OnSelectedListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_merchant_setting.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MerchantSettingActivity : BaseActivity(), View.OnClickListener, ShopSetView {
    override fun onFault(errorMsg: String?) {

        Toast.makeText(this, "设置店铺信息成功", Toast.LENGTH_LONG).show()
    }

    override fun onSuccess() {
        Toast.makeText(this, "设置店铺信息失败", Toast.LENGTH_LONG).show()
    }


    var typeStatus = "0"     //店铺状态(0正常营业，1打烊/休息)

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.tv_setTime -> {
                startActivityForResult(Intent(this, BusinessHoursActivity::class.java), 10086)
            }

            R.id.iv_back -> {
                finish()
            }

            R.id.tv_save -> {

                if (!TextUtils.isEmpty(tv_shopTel.text.toString())) {
                    present?.editConfig(
                        tv_shopAddress.text.toString(),
                        tv_shopDesc.text.toString(),
                        tv_shopTel.text.toString(),
                        typeStatus
                    )
                } else {
                    Toast.makeText(this, "商家的联系电话不能为空", Toast.LENGTH_SHORT).show()
                }


            }

            else -> {
            }
        }
    }

    var present: ShopSetPresent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_setting)

        present = ShopSetPresent(this, this)

        arrayOf(iev_logo, tv_setTime, iv_back, tv_save).forEach {
            it.setOnClickListener(this)
        }


        tv_editOpen.isOpen = true

        tv_editOpen.setOnToggleListener {
            val map = ArrayMap<String, String>()
            map.clear()
            if (it) {
                Log.i("ProductAddFragment", it.toString())
                typeStatus = "0"

            } else {
                Log.i("ProductAddFragment", it.toString())
                typeStatus = "1"

            }

            present?.editOpen(typeStatus)


        }

        RxTextView.textChanges(tv_shopDesc).subscribe {
            text.text = "${it.toString().length}/80"
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }
}
