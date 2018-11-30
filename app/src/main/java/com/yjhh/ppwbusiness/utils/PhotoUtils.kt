package com.yjhh.ppwbusiness.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment
import androidx.core.content.FileProvider
import android.util.Log
import com.yjhh.ppwbusiness.BaseApplication.context
import com.yjhh.ppwbusiness.R
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.PicassoEngine
import com.zhihu.matisse.filter.Filter
import com.zhihu.matisse.internal.entity.CaptureStrategy
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object PhotoUtils {
    fun selectPhoto(fragment: androidx.fragment.app.Fragment, max:Int, code: Int) {

//        Matisse.from(fragment)
//            .choose(MimeType.ofAll(), false)
//            .countable(true)
//            .capture(true)
//            .captureStrategy(
//                CaptureStrategy(true, "com.yjhh.ppwcustomer.fileProvider")
//            )
//            .maxSelectable(1)
//            //.addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
//            .gridExpectedSize(
//                fragment.resources.getDimensionPixelSize(R.dimen.grid_expected_size)
//            )
//            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
//            .thumbnailScale(0.85f)
//            //                                            .imageEngine(new GlideEngine())  // for glide-V3
//            .imageEngine(Glide4Engine())    // for glide-V4
//
//            .originalEnable(true)
//            .maxOriginalSize(10)
//
//
//            .forResult(code)


        Matisse.from(fragment)
            .choose(MimeType.ofImage())
            .theme(R.style.Matisse_Dracula)
            .countable(false)
//            .captureStrategy(
//                CaptureStrategy(true, "com.yjhh.ppwcustomer.fileProvider")
//            )
            // .addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
            .maxSelectable(max)
            .originalEnable(true)
            .maxOriginalSize(10)
            .imageEngine(Glide4Engine())
            .forResult(code)

    }


    fun takePhote(fragment: androidx.fragment.app.Fragment, mActivity: Activity, code: Int): String? {


        var mPublicPhotoPath: String? = null
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //判断是否有相机应用
        if (takePictureIntent.resolveActivity(mActivity.packageManager) != null) {
            //创建临时图片文件
            var photoFile: File? = null
            try {


                val timeStamp =
                    SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(Date())
                //文件路径是公共的DCIM目录下的/camerademo目录
                var storagePath = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    .getAbsolutePath() + File.separator + "camerademo"

                var storageDir = File(storagePath)
                storageDir.mkdirs()
                photoFile = File.createTempFile(timeStamp, ".jpg", storageDir);
                mPublicPhotoPath = photoFile.absolutePath


            } catch (e: IOException) {
                e.printStackTrace()
            }

            //设置Action为拍照
            if (photoFile != null) {
                takePictureIntent.action = MediaStore.ACTION_IMAGE_CAPTURE
                //这里加入flag
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                val photoURI: Uri
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上   com.yjhh.ppwcustomer.fileProvider
                    photoURI = FileProvider.getUriForFile(
                        mActivity,
                        "${mActivity.packageName}.camera.fileProvider",
                        photoFile
                    )
                } else {
                    photoURI = Uri.fromFile(photoFile)
                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                fragment.startActivityForResult(takePictureIntent, code)

            }
        }
        //将照片添加到相册中
        if (mPublicPhotoPath != null) {
            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            val f = File(mPublicPhotoPath)
            val contentUri = Uri.fromFile(f)
            mediaScanIntent.data = contentUri
            context.sendBroadcast(mediaScanIntent)
        }

        return mPublicPhotoPath

    }


}
