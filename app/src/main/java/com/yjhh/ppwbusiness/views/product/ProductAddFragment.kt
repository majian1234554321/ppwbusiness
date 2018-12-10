package com.yjhh.ppwbusiness.views.product

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.tbruyelle.rxpermissions2.RxPermissions

import com.yjhh.ppwbusiness.R

import com.yjhh.ppwbusiness.adapter.ProductAdd
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.ProductService
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.base.BaseActivity.requestRuntimePermission
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.PhotoBean
import com.yjhh.ppwbusiness.bean.ProductBean
import com.yjhh.ppwbusiness.bean.SETime
import com.yjhh.ppwbusiness.bean.SubmitProductInfoModel
import com.yjhh.ppwbusiness.fragments.PhotoFragment
import com.yjhh.ppwbusiness.interfaces.OnRecycleViewItemChildClickListener
import com.yjhh.ppwbusiness.interfaces.OnRecycleViewItemClickListener
import com.yjhh.ppwbusiness.interfaces.PermissionListener
import com.yjhh.ppwbusiness.ipresent.CommonPresent
import com.yjhh.ppwbusiness.iview.CommonView
import com.yjhh.ppwbusiness.utils.Glide4Engine
import com.yjhh.ppwbusiness.utils.PhotoUtils
import com.yjhh.ppwbusiness.utils.RxBus
import com.yjhh.ppwbusiness.utils.TextStyleUtils
import com.yjhh.ppwbusiness.views.cui.AbsSheetDialog
import com.yjhh.ppwbusiness.views.cui.AlertDialogFactory
import com.yjhh.ppwbusiness.views.cui.BottomVerSheetDialog
import com.yjhh.ppwbusiness.views.cui.GridRecyclerItemDecoration
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


import kotlinx.android.synthetic.main.productadd.*
import java.io.File
import java.lang.StringBuilder

import java.util.*
import kotlin.collections.ArrayList


class ProductAddFragment : BaseFragment(), CommonView {

    override fun onSuccess(value: String?) {
        val gson = Gson()
        val model = gson.fromJson<PhotoBean>(value, PhotoBean::class.java)


        model.item.forEach {
            listsId.add(it.fileId)
        }
    }

    override fun onFault(errorMsg: String?) {

    }


    override fun getLayoutRes(): Int = R.layout.productadd

    val imageId = ArrayList<String>()


    var toggleStatus = true

    var mAdapter: ProductAdd? = null
    val lists = ArrayList<String>()
    val listsId = java.util.ArrayList<String>()
    var present: CommonPresent? = null

    override fun initView() {


        val bundle = arguments

        val objectValue = bundle?.getSerializable("objectValue") as ProductBean.ItemsBean
        val type = bundle?.getString("type")


        val tips = "商品主图（注：默认第一张为主图，最多添加3张）"
        tv_tip.text = TextStyleUtils.changeTextColor(
            tips,
            0,
            4,
            Color.parseColor("#333333")
        )



        present = CommonPresent(mActivity, this)

        if ("ADD" == type) {
            toggle.isOpen = true
            toggleStatus = true
            tbv_title.setTitle("新建商品")
            bt_add.text = "新建商品"
        } else {
            tbv_title.setTitle("编辑商品")
            et_name.setText(objectValue.name)
            et_price.setText(objectValue.price.toString())
            et_desc.setText(objectValue.describe)


            toggle.isOpen = objectValue.saleStatus != 1

            objectValue.images.forEach {
                listsId.add(it.fileId)
                lists.add(it.fileUrl)
            }





            bt_add.text = "编辑完成"
        }


        //recyclerView.addItemDecoration(GridRecyclerItemDecoration(20))
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(mActivity, 3)
        mAdapter = ProductAdd(mActivity, lists)
        recyclerView.adapter = mAdapter


        toggle.setOnToggleListener { }



        mAdapter?.setOnItemClickListener(OnRecycleViewItemClickListener { view, position, flag ->
            if (flag) {
               // start(PhotoFragment(lists[position]))

                val dialog = PhotoFragment(lists)
                dialog?.show(childFragmentManager, "TAG")
            } else {
                photo()
            }

        })




        mAdapter?.setOnItemChildClickListener(OnRecycleViewItemChildClickListener { view, position ->
            lists.removeAt(position)
            listsId.removeAt(position)
            mAdapter?.notifyItemRemoved(position)
            mAdapter?.notifyDataSetChanged()
        })



        bt_add.setOnClickListener {


            if (!TextUtils.isEmpty(et_name.text.toString()) &&
                !TextUtils.isEmpty(et_price.text.toString())
            ) {
                val aa = SubmitProductInfoModel()
                if (!TextUtils.isEmpty(objectValue.id)) {
                    aa.id = objectValue.id
                    aa.itemId = objectValue.itemId

                }
                aa.name = et_name.text.toString()
                aa.saleStatus = if (toggle.isOpen) {
                    "0"
                } else {
                    "1"
                }
                aa.describe = et_desc.text.toString()
                aa.price = et_price.text.toString()


                val arrayimageIds = listsId.toArray(arrayOfNulls<String>(listsId.size))

                aa.imageIds = arrayimageIds



                ApiServices.getInstance()
                    .create(ProductService::class.java)
                    .editProducts(aa)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : ProcessObserver2(mActivity) {
                        override fun processValue(response: String?) {
                            Log.i("ProductAddFragment", response)


                            val intent = Intent()
                            intent.putExtra("TYPE", "ProductAddFragment")
                            RxBus.default.post(intent)


                            if ("ADD" == type) {
                                AlertDialogFactory.createFactory(mActivity).getAlertDialog(
                                    "添加商品成功",
                                    "继续添加?",
                                    "确定", "取消",
                                    { dlg, v ->

                                        et_name.text.clear()
                                        et_price.text.clear()
                                        et_desc.text.clear()
                                        lists.clear()
                                        listsId.clear()
                                        mAdapter?.notifyDataSetChanged()
                                    }, { dlg, v ->
                                        mActivity.onBackPressed()
                                    })
                            } else {
                                Toast.makeText(mActivity, "商品修改成功", Toast.LENGTH_SHORT).show()
                                mActivity.onBackPressed()
                            }

                        }

                        override fun onFault(message: String) {
                            Log.i("ProductAddFragment", message)
                        }

                    })
            } else {
                Toast.makeText(mActivity, "商品名称和价格不能为空", Toast.LENGTH_SHORT).show()
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


    var mPublicPhotoPath: String? = null

    private fun requestPermission(string: String) {


        var disposable: Disposable? = null

        if ("photo" == string) {

            disposable = RxPermissions(this)
                .request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe {
                    if (it) {
                        mPublicPhotoPath = PhotoUtils.takePhote(this@ProductAddFragment, mActivity, 10084)
                    } else {
                        Toast.makeText(mActivity, "请前往设置中心开启照相机权限", Toast.LENGTH_SHORT).show()
                    }
                }

        } else {

            disposable = RxPermissions(this)
                .request(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .subscribe {
                    if (it) {
                        PhotoUtils.selectPhoto(this@ProductAddFragment, 3 - lists.size, 10085)
                    } else {
                        Toast.makeText(mActivity, "请前往设置中心开启照相机权限", Toast.LENGTH_SHORT).show()
                    }
                }


        }


        compositeDisposable.add(disposable)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10085 && resultCode == BaseActivity.RESULT_OK) {

            val list = Matisse.obtainPathResult(data)


//            val file = File(list[0])
//
//            lists.add(file.path)
//
//            while (lists.size > 3) {
//                lists.removeAt(lists.lastIndex)
//            }
//
//
//            mAdapter?.notifyDataSetChanged()
//            present?.UpLoadFile(file)

            val listFiles = ArrayList<File>()

            lists.addAll(list)
            list.forEach {
                val file = File(it)
                listFiles.add(file)
            }
            present?.UpLoadFiles(listFiles)

            mAdapter?.notifyDataSetChanged()


        }
        //拍照
        if (requestCode == 10084 && resultCode == BaseActivity.RESULT_OK) {
            if (resultCode != Activity.RESULT_OK) return

            if (!TextUtils.isEmpty(mPublicPhotoPath)) {
                val uri = Uri.parse(mPublicPhotoPath)
                val path = uri.path
                val file = File(path)
                lists.add(file.path)
                val listFiles = ArrayList<File>()

                listFiles.add(file)
                while (lists.size > 3) {
                    lists.removeAt(lists.lastIndex)
                }

                mAdapter?.notifyDataSetChanged()

                present?.UpLoadFiles(listFiles)
            } else {

            }


        }


    }


    companion object {

        fun newInstance(objectValue: ProductBean.ItemsBean, type: String): ProductAddFragment {
            val fragment = ProductAddFragment()
            val bundle = Bundle()
            bundle.putString("type", type)
            bundle.putSerializable("objectValue", objectValue)
            fragment.arguments = bundle
            return fragment
        }


    }


}