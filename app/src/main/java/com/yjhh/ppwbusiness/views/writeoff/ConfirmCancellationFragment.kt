package com.yjhh.ppwbusiness.views.writeoff

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.recyclerview.widget.GridLayoutManager
import android.widget.Toast
import androidx.lifecycle.Transformations.map
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
import com.yjhh.ppwbusiness.ipresent.CancellationPresent
import com.yjhh.ppwbusiness.ipresent.CommonPresent
import com.yjhh.ppwbusiness.iview.CancellationView
import com.yjhh.ppwbusiness.iview.CommonView
import com.yjhh.ppwbusiness.utils.PhotoUtils
import com.yjhh.ppwbusiness.views.cui.AbsSheetDialog
import com.yjhh.ppwbusiness.views.cui.AlertDialogFactory
import com.yjhh.ppwbusiness.views.cui.BottomVerSheetDialog
import com.yjhh.ppwbusiness.views.cui.GridRecyclerItemDecoration
import com.yjhh.ppwbusiness.views.main.MainActivity
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils.getPath
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.confirmcancellationfragment.*
import top.zibin.luban.CompressionPredicate
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File
import java.util.*
import java.util.function.BiConsumer
import java.util.function.BiFunction
import java.util.function.Function

class ConfirmCancellationFragment : BaseFragment(), CommonView, CancellationView {
    override fun onSuccessCancellation(response: String?, flag: String?) {
        Toast.makeText(mActivity, "核销凭据上传成功", Toast.LENGTH_SHORT).show()
        mActivity.onBackPressed()
    }


    override fun onSuccess(value: String?) {
        val gson = Gson()
        val model = gson.fromJson<PhotoBean>(value, PhotoBean::class.java)


        model.item.forEach {
            listsId.add(it.fileId)
            lists.add(it.fileUrl)
        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onFault(errorMsg: String?) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutRes(): Int = R.layout.confirmcancellationfragment

    val lists = ArrayList<String>()
    val listsId = java.util.ArrayList<String>()
    var mAdapter: ProductAdd? = null
    var present: CommonPresent? = null


    companion object {
        fun newInstance(
            ids: String?,
            realMoney: String?,
            shopId: String?,
            unDisMoney: String?
        ): ConfirmCancellationFragment {
            val fragment = ConfirmCancellationFragment()
            val bundle = Bundle()

            bundle.putString("ids", ids)
            bundle.putString("realMoney", realMoney)
            bundle.putString("shopId", shopId)
            bundle.putString("unDisMoney", unDisMoney)

            fragment.arguments = bundle
            return fragment
        }
    }


    override fun initView() {

        present = CommonPresent(mActivity, this)
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(mActivity, 4)

        mAdapter = ProductAdd(mActivity, lists, Int.MAX_VALUE)
        recyclerView.adapter = mAdapter

        val id = arguments?.getString("ids")
        val realMoney = arguments?.getString("realMoney")
        val shopId = arguments?.getString("shopId")
        val unDisMoney = arguments?.getString("unDisMoney")


        mb_commit.setOnClickListener {


            CancellationPresent(mActivity, this)
                .review(id, realMoney, shopId, unDisMoney, listsId, tv_remark.text.toString())

        }

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
            mAdapter?.notifyDataSetChanged()
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
                        mPublicPhotoPath = PhotoUtils.takePhote(this@ConfirmCancellationFragment, mActivity, 10084)
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
                        PhotoUtils.selectPhoto(this@ConfirmCancellationFragment, Int.MAX_VALUE, 10085)
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

            val listFiles = ArrayList<File?>()
            Luban.with(mActivity)
                .load(list)
                .ignoreBy(100)
                .filter { path -> !(TextUtils.isEmpty(path) || path?.toLowerCase()?.endsWith(".gif")!!) }
                .setCompressListener(object : OnCompressListener {
                    override fun onSuccess(file: File?) {
                        listFiles.add(file)
                    }

                    override fun onError(e: Throwable?) {

                    }

                    override fun onStart() {

                    }

                })

                .launch();


//            val dis = Flowable.just(list)
//                .map( BiFunction<List<String>, List<File>> { t1->
//
//                })




            present?.UpLoadFiles(listFiles)


        }
        //拍照
        if (requestCode == 10084 && resultCode == BaseActivity.RESULT_OK) {
            if (resultCode != Activity.RESULT_OK) return
            val uri = Uri.parse(mPublicPhotoPath)
            val path = uri.path
            val file = File(path)
            val listFiles = ArrayList<File>()
            listFiles.add(file)
            present?.UpLoadFiles(listFiles)

        }


    }


}