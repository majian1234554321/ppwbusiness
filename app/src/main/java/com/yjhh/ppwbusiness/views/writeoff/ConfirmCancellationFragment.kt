package com.yjhh.ppwbusiness.views.writeoff

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.azhon.appupdate.utils.PermissionUtil.requestPermission
import com.google.gson.Gson
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.ProductAdd
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.bean.PhotoBean
import com.yjhh.ppwbusiness.fragments.PhotoFragment
import com.yjhh.ppwbusiness.interfaces.OnRecycleViewItemChildClickListener
import com.yjhh.ppwbusiness.interfaces.OnRecycleViewItemClickListener
import com.yjhh.ppwbusiness.ipresent.CommonPresent
import com.yjhh.ppwbusiness.iview.CommonView
import com.yjhh.ppwbusiness.utils.PhotoUtils
import com.yjhh.ppwbusiness.views.cui.AbsSheetDialog
import com.yjhh.ppwbusiness.views.cui.AlertDialogFactory
import com.yjhh.ppwbusiness.views.cui.BottomVerSheetDialog
import com.yjhh.ppwbusiness.views.cui.GridRecyclerItemDecoration
import com.zhihu.matisse.Matisse
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.confirmcancellationfragment.*
import java.io.File
import java.util.*

class ConfirmCancellationFragment : BaseFragment(), CommonView {


    override fun onSuccess(value: String?) {
        val gson = Gson()
        val model = gson.fromJson<PhotoBean>(value, PhotoBean::class.java)


        model.item.forEach {
            listsId.add(it.id)
        }
    }

    override fun onFault(errorMsg: String?) {

    }

    override fun getLayoutRes(): Int = R.layout.confirmcancellationfragment

    val lists = ArrayList<String>()
    val listsId = java.util.ArrayList<String>()
    var mAdapter: ProductAdd? = null
    var present: CommonPresent? = null

    override fun initView() {

        present = CommonPresent(mActivity, this)
        recyclerView.layoutManager = GridLayoutManager(mActivity, 4)
        recyclerView.addItemDecoration(GridRecyclerItemDecoration(40))

        mAdapter = ProductAdd(mActivity, lists)
        recyclerView.adapter = mAdapter

        mb_commit.setOnClickListener { }

        mAdapter?.setOnItemClickListener(OnRecycleViewItemClickListener { view, position, flag ->
            if (flag) {
                start(PhotoFragment(lists[position]))
            } else {
                photo()
            }

        })

        mAdapter?.setOnItemChildClickListener(OnRecycleViewItemChildClickListener { view, position ->
            lists.removeAt(position)
            listsId.removeAt(position)
            mAdapter?.notifyItemRemoved(position)
        })
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
                        PhotoUtils.takePhote(this@ConfirmCancellationFragment, mActivity, 10084)
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
                        PhotoUtils.selectPhoto(this@ConfirmCancellationFragment, 3 - lists.size, 10085)
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
            val uri = Uri.parse(mPublicPhotoPath)
            val path = uri.path
            val file = File(path)
            lists.add(file.path)

            mAdapter?.notifyDataSetChanged()
            present?.UpLoadFile(file)

        }


    }


}