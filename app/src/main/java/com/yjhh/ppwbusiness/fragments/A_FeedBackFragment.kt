package com.yjhh.ppwbusiness.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.jakewharton.rxbinding2.widget.RxTextView
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.adapter.PhotoAdapter
import com.yjhh.ppwbusiness.adapter.ProductAdd
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUselessService
import com.yjhh.ppwbusiness.base.BaseActivity
import com.yjhh.ppwbusiness.base.BaseActivity.requestRuntimePermission
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.PhotoBean
import com.yjhh.ppwbusiness.bean.SubmitFeedbackModel
import com.yjhh.ppwbusiness.interfaces.PermissionListener
import com.yjhh.ppwbusiness.ipresent.CommonPresent
import com.yjhh.ppwbusiness.iview.CommonView
import com.yjhh.ppwbusiness.utils.PhotoUtils
import com.yjhh.ppwbusiness.views.cui.AbsSheetDialog
import com.yjhh.ppwbusiness.views.cui.AlertDialogFactory
import com.yjhh.ppwbusiness.views.cui.BottomVerSheetDialog
import com.zhihu.matisse.Matisse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.a_feedbackfragment.*
import top.zibin.luban.CompressionPredicate
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File
import java.lang.StringBuilder
import java.util.*

class A_FeedBackFragment : BaseFragment(), View.OnClickListener, CommonView {
    override fun onSuccess(value: String?) {


        val gson = Gson()
        val model = gson.fromJson<PhotoBean>(value, PhotoBean::class.java)


        model.item.forEach {
            listsId.add(it.fileId)
        }

    }

    override fun onFault(errorMsg: String?) {
        Toast.makeText(mActivity, errorMsg, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_commit -> {


                if (!TextUtils.isEmpty(et_1.text.toString()) && !TextUtils.isEmpty(et_5.text.toString())) {


                    val model = SubmitFeedbackModel()



                    model.cause = et_5.text.toString()
                    model.mobile = et_1.text.toString()
                    model.name = et_3.text.toString()
                    model.title = et_4.text.toString()
                    model.email = et_2.text.toString()

                    if (listsId.contains("EMPTY")) {
                        listsId.remove("EMPTY")
                    }


                    val arrayimageIds = listsId.toArray(arrayOfNulls<String>(listsId.size))

                    model.fileIds = arrayimageIds



                    ApiServices.getInstance()
                        .create(SectionUselessService::class.java)
                        .feedback(model)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : ProcessObserver2(mActivity) {
                            override fun processValue(response: String?) {

                                Toast.makeText(mActivity, "反馈提交成功", Toast.LENGTH_SHORT).show()
                                et_1.text.clear()
                                et_2.text.clear()
                                et_3.text.clear()
                                et_4.text.clear()
                                et_5.text.clear()

                                lists.clear()
                                listsId.clear()

                                lists.add("EMPTY")
                                listsId.add("EMPTY")

                                mAdapter?.notifyDataSetChanged()

                                // mActivity.onBackPressed()
                            }

                            override fun onFault(message: String) {
                                Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show()
                            }

                        })

                } else {
                    Toast.makeText(mActivity, "联系方式和反馈内容不能为空", Toast.LENGTH_SHORT).show()
                }


            }

            R.id.tv_see -> {
                start(AllFeedBackFragment())
            }

            else -> {

            }
        }
    }

    var mAdapter: PhotoAdapter? = null
    override fun getLayoutRes(): Int = R.layout.a_feedbackfragment
    val lists = ArrayList<String>()

    val listsId = ArrayList<String>()
    @SuppressLint("CheckResult")
    override fun initView() {
        arrayOf(tv_commit, tv_see)
            .forEach {
                it.setOnClickListener(this)
            }

        present = CommonPresent(mActivity, this)

        lists.add("EMPTY")
        listsId.add("EMPTY")
        recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 3)

        mAdapter = PhotoAdapter(lists)

        recyclerView.adapter = mAdapter


        mAdapter?.setOnItemClickListener { adapter, view, position ->


            if (lists[position] == "EMPTY") {
                //拍照或者选择照片
                photo()

            } else {
                // start(PhotoFragment(lists[position]))

                val dialog = PhotoFragment(lists)
                dialog?.show(childFragmentManager, "TAG")
            }

        }



        mAdapter?.setOnItemChildClickListener { adapter, view, position ->


            lists.removeAt(position)
            if (lists.size == 0) {
                lists.add("EMPTY")
                listsId.add("EMPTY")
            }

            if (lists.size < 3 && !lists.contains("EMPTY")) {
                lists.add("EMPTY")
                listsId.add("EMPTY")
            }

            mAdapter?.setNewData(lists)

        }




        RxTextView.textChanges(et_5).subscribe {
            text.text = "${it.toString().length}/200"
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

    private fun requestPermission(string: String) {
        //        判断手机版本,如果低于6.0 则不用申请权限,直接拍照
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            requestRuntimePermission(permissions, object : PermissionListener {
                override fun onGranted() {
                    if ("photo" == string) {
                        mPublicPhotoPath = PhotoUtils.takePhote(this@A_FeedBackFragment, mActivity, 10084)
                    } else {
                        PhotoUtils.selectPhoto(this@A_FeedBackFragment, 9 - lists.size, 10085)
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
                mPublicPhotoPath = PhotoUtils.takePhote(this@A_FeedBackFragment, mActivity, 10084)
            } else {
                PhotoUtils.selectPhoto(this@A_FeedBackFragment, 9 - lists.size, 10085)
            }

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10085 && resultCode == BaseActivity.RESULT_OK) {

            val list = Matisse.obtainPathResult(data)


            val listFiles = ArrayList<File>()

            lists.addAll(0, list)
            list.forEach {
                val file = File(it)
                listFiles.add(file)
            }


            val dis = Flowable.just(listFiles).map {
                Luban.with(mActivity).load(list).get()
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.i("onActivityResult", "${it.size}1212");
                    present?.UpLoadFiles(it)
                }

            compositeDisposable.add(dis)

            mAdapter?.notifyDataSetChanged()


        }
        //拍照
        if (requestCode == 10084 && resultCode == BaseActivity.RESULT_OK) {
            if (resultCode != Activity.RESULT_OK) return
            val uri = Uri.parse(mPublicPhotoPath)
            val path = uri.path
            val file = File(path)
            lists.add(0, file.path)



            while (lists.size > 3) {
                lists.removeAt(lists.lastIndex)
            }

            mAdapter?.setNewData(lists)




            Luban.with(mActivity)
                .load(file)
                .ignoreBy(100)
                .filter(object : CompressionPredicate {
                    override fun apply(path: String?): Boolean {
                        return !(TextUtils.isEmpty(path) || path?.toLowerCase()?.endsWith(".gif")!!)
                    }

                }).setCompressListener(object : OnCompressListener {
                    override fun onSuccess(filevalue: File?) {

                        val listfilevalue = ArrayList<File?>();
                        listfilevalue.add(filevalue)

                        present?.UpLoadFiles(listfilevalue)
                    }

                    override fun onError(e: Throwable?) {

                    }

                    override fun onStart() {

                    }

                }).launch()


        }


    }


    var present: CommonPresent? = null

}