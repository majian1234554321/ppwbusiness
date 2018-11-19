package com.yjhh.ppwbusiness.views.product

import android.Manifest
import android.annotation.SuppressLint
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
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.ProductAdd
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.base.BaseActivity.requestRuntimePermission
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.interfaces.PermissionListener
import com.yjhh.ppwbusiness.utils.Glide4Engine
import com.yjhh.ppwbusiness.utils.PhotoUtils
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

@SuppressLint("ValidFragment")
class ProductAddFragment(var string: String) : BaseFragment() {
    override fun getLayoutRes(): Int = R.layout.productadd


    var mAdapter: ProductAdd? = null
    val lists = ArrayList<String>()
    override fun initView() {


        if ("ADD" == string) {
            tbv_title.setTitle("新建商品")
        } else {
            tbv_title.setTitle("编辑商品")
        }


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

            if (lists.size < 3 && !lists.contains("EMPTY")) {
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
                            requestPermission("photo")
                        }
                        else -> {


                            requestPermission("select")

                        }
                    }

                }

                override fun onCancel(dlg: Dialog) {

                }
            })
    }


    var permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE

    )


    var mPublicPhotoPath: String? = null
    //1111111111111111111111111111111111
    private fun requestPermission(string: String) {
        //        判断手机版本,如果低于6.0 则不用申请权限,直接拍照
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            requestRuntimePermission(permissions, object : PermissionListener {
                override fun onGranted() {

                    if ("photo" == string) {
                        mPublicPhotoPath = PhotoUtils.takePhote(this@ProductAddFragment, mActivity, 10084)
                    } else {
                        PhotoUtils.selectPhoto(this@ProductAddFragment, 10085)
                    }


                    Log.i("requestRuntime", "onGranted")

                }

                override fun onDenied(deniedPermission: List<String>) {
                    for (i in deniedPermission.indices) {
                        Log.i("requestRuntime", deniedPermission[i])
                    }

                    Toast.makeText(mActivity, "请先设置权限", Toast.LENGTH_SHORT).show()
                    val intent = Intent(Settings.ACTION_SEARCH_SETTINGS);
                    startActivity(intent)

                }
            })

        } else {
            if ("photo" == string) {
                mPublicPhotoPath = PhotoUtils.takePhote(this@ProductAddFragment, mActivity, 10084)
            } else {
                PhotoUtils.selectPhoto(this@ProductAddFragment, 10085)
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