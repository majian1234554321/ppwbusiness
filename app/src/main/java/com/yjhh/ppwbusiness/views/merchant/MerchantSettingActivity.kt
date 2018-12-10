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
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
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
import com.yjhh.ppwbusiness.bean.*
import com.yjhh.ppwbusiness.ipresent.ShopSetPresent
import com.yjhh.ppwbusiness.iview.ShopSetView
import com.yjhh.ppwbusiness.utils.Glide4Engine
import com.yjhh.ppwbusiness.utils.ImageLoaderUtils
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
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.text.Typography.times

class MerchantSettingActivity : BaseActivity(), View.OnClickListener, ShopSetView {

    var listHours = ArrayList<ShopTimesModel>()

    override fun AllShopInfoSuccess(model: AllShopInfo) {

        tv_name.text = model.name
        tv_shopTel.setText(model.mobile)
        tv_shopAddress.setText(model.address)
        tv_shopDesc.setText(model.content)


        tv_editOpen.isOpen = model.openStatus != 1

        if (model.times != null && model.times.size > 0) {

            listHours.clear()


            val sb = StringBuilder()
            model.times.forEach {
                sb.append(it.begin)
                    .append(" - ")
                    .append(it.end)
                    .append("   ")



                listHours.add(ShopTimesModel(it.begin, it.end))

            }
            tv_time.text = sb.toString()
        }

        ImageLoaderUtils.loadCircle(
            this,
            iv_image,
            model.logoUrl,
            R.drawable.icon_logoholder,
            R.drawable.icon_logoholder
        )


    }

    override fun onFault(errorMsg: String?) {

        Toast.makeText(this, "设置店铺信息失败", Toast.LENGTH_LONG).show()
    }

    override fun onSuccess() {

        //finish()
    }


    var typeStatus = "0"     //店铺状态(0正常营业，1打烊/休息)

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.tv_setTime -> {

                val intent = Intent(this, BusinessHoursActivity::class.java)
                intent.putParcelableArrayListExtra("time", listHours)

                startActivityForResult(intent, 10086)
            }

            R.id.iv_back -> {
                finish()
            }

            R.id.tv_save -> {
                if (!TextUtils.isEmpty(tv_shopTel.text.toString())) {
                    val map = SubmitShopAdminConfigModel()
                    map.address = tv_shopAddress.text.toString()
                    map.content = tv_shopDesc.text.toString()
                    map.mobile = tv_shopTel.text.toString()
                    map.status = typeStatus
                    map.times = listHours
                    present?.editConfig(map)
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


        present?.getAllInfo()

        arrayOf(iev_logo, tv_setTime, iv_back, tv_save).forEach {
            it.setOnClickListener(this)
        }


        tv_editOpen.isOpen = true

        tv_editOpen.setOnToggleListener {
            val map = ArrayMap<String, String>()
            map.clear()
            typeStatus = if (it) {
                Log.i("ProductAddFragment", it.toString())
                "0"

            } else {
                Log.i("ProductAddFragment", it.toString())
                "1"

            }

            present?.editOpen(typeStatus)


        }

        val dis = RxTextView.textChanges(tv_shopDesc).subscribe {
            text.text = "${it.toString().length}/80"
        }

        compositeDisposable.add(dis)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10086) {
            val timeList = data?.getParcelableArrayListExtra<ShopTimesModel>("time")
            if (timeList != null) {
                val sb = StringBuilder()
                listHours.clear()
                timeList.forEach {


                    sb.append(it.begin)
                        .append(" - ")
                        .append(it.end)
                        .append("   ")



                    listHours.add(ShopTimesModel(it.begin, it.end))

                }

                tv_time.text = sb.toString()
            }
        }
    }
}
