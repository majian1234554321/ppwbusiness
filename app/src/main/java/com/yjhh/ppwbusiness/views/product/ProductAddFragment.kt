package com.yjhh.ppwbusiness.views.product

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.ProductAdd
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.utils.Glide4Engine
import com.yjhh.ppwbusiness.views.cui.AbsSheetDialog
import com.yjhh.ppwbusiness.views.cui.AlertDialogFactory
import com.yjhh.ppwbusiness.views.cui.BottomVerSheetDialog
import com.yjhh.ppwbusiness.views.cui.GridRecyclerItemDecoration
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import kotlinx.android.synthetic.main.productadd.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ProductAddFragment : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.productadd

    var mAdapter: ProductAdd? = null
    val lists = ArrayList<String>()
    override fun initView() {
        recyclerView.layoutManager = GridLayoutManager(mActivity, 3)

        lists.add("EMPTY3")
        lists.add("EMPTY2")
        lists.add("EMPTY1")
        mAdapter = ProductAdd(lists)
        recyclerView.adapter = mAdapter

        recyclerView.addItemDecoration(GridRecyclerItemDecoration(40))

        mAdapter?.setOnItemClickListener { adapter, view, position ->

            if (lists[position] == "EMPTY") {
                //拍照或者选择照片
                photo()

            } else {

            }
        }



        mAdapter?.setOnItemChildClickListener { adapter, view, position ->
            Toast.makeText(mActivity, "" + "权限" + position + "申请失败", Toast.LENGTH_SHORT).show()


            lists.removeAt(position)
            if (lists.size == 0) {
                lists.add("EMPTY")
            }

            mAdapter?.setNewData(lists)


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

                    when (position) {
                        0 -> {
                            showTakePicture()
                        }
                        else -> {


                            Matisse.from(this@ProductAddFragment)
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
                    }

                }

                override fun onCancel(dlg: Dialog) {

                }
            })
    }


    //1111111111111111111111111111111111
    private fun showTakePicture() {
        //        判断手机版本,如果低于6.0 则不用申请权限,直接拍照
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//7.0及以上
            if (ContextCompat.checkSelfPermission(
                    mActivity,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        mActivity,
                        Manifest.permission.CAMERA
                    )
                ) {
                } else {
                    ActivityCompat.requestPermissions(
                        mActivity,
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ), 1
                    )
                }
            } else {
                startTake()
            }
        } else {
            startTake()
        }


    }


    //2222222222222222222222222222222222
    private fun startTake() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //判断是否有相机应用
        if (takePictureIntent.resolveActivity(mActivity.packageManager) != null) {
            //创建临时图片文件
            var photoFile: File? = null
            try {
                photoFile = createPublicImageFile()
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
                startActivityForResult(takePictureIntent, 10084)

            }
        }
        //将照片添加到相册中
        if (mPublicPhotoPath != null) {
            galleryAddPic(mPublicPhotoPath!!, mActivity)
        }

    }


    @Throws(IOException::class)
    private fun createPublicImageFile(): File {
        var imageFile: File
        var storagePath: String
        var storageDir: File
        val timeStamp =
            getTime(Date(), "yyyyMMdd_HHmmss", Locale.CHINA)
        //文件路径是公共的DCIM目录下的/camerademo目录
        storagePath = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            .getAbsolutePath() + File.separator + "camerademo"

        storageDir = File(storagePath)
        storageDir.mkdirs()
        imageFile = File.createTempFile(timeStamp, ".jpg", storageDir);
        mPublicPhotoPath = imageFile.getAbsolutePath()

        return imageFile

    }

    private var mPublicPhotoPath: String? = null

    fun galleryAddPic(mPublicPhotoPath: String, context: Context) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val f = File(mPublicPhotoPath)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        context.sendBroadcast(mediaScanIntent)
    }


    private fun getTime(date: Date, mode: String, locale: Locale): String {
        val format = SimpleDateFormat(mode, locale)
        return format.format(date)
    }

    //权限申请的回调
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    if (i == 0) {
                        startTake()
                    }
                } else {
                    Toast.makeText(mActivity, "" + "权限" + permissions[i] + "申请失败", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if (requestCode == 10085 && resultCode == BaseActivity.RESULT_OK) {
            val mSelected = Matisse.obtainResult(data)
            Matisse.obtainResult(data)
            val list = Matisse.obtainPathResult(data)
            Log.i("OnActivityResult ", list[0])

            val file = File(list[0])


            lists.add(0, file.path)

            while (lists.size > 3) {
                lists.removeAt(lists.lastIndex)
            }

            mAdapter?.setNewData(lists)

            /* val dis = present?.setAvatarUpLoadJoin(file)

             if (dis != null) {
                 compositeDisposable.add(dis)
             }*/
        }


        //拍照
        if (requestCode == 10084 && resultCode == BaseActivity.RESULT_OK) {
            if (resultCode != Activity.RESULT_OK) return
            val uri = Uri.parse(mPublicPhotoPath)
            val path = uri.path
            val file = File(path)
            lists.add(0, file.path)

            if (lists.size > 3) {

            }



            while (lists.size > 3) {
                lists.removeAt(lists.lastIndex)
            }

            mAdapter?.setNewData(lists)
            /* val dis = present?.setAvatarUpLoadJoin(file)
             if (dis != null) {
                 compositeDisposable.add(dis)
             }*/
        }


    }


}