package com.yjhh.ppwbusiness.fragments

import android.Manifest
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.*
import com.yjhh.ppwbusiness.adapter.AboutAdapter
import com.yjhh.ppwbusiness.api.ApiServices
import com.yjhh.ppwbusiness.api.SectionUselessService
import com.yjhh.ppwbusiness.base.BaseFragment
import com.yjhh.ppwbusiness.base.ProcessObserver2
import com.yjhh.ppwbusiness.bean.AboutBean
import com.yjhh.ppwbusiness.utils.PhoneUtils
import com.yjhh.ppwbusiness.utils.PhotoUtils
import com.yjhh.ppwbusiness.views.cui.AlertDialogFactory
import com.yjhh.ppwbusiness.views.webview.BackViewFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.aboutfragment.*
import org.json.JSONObject

class AboutFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.aboutfragment


    var list = ArrayList<AboutBean.FunctionsBean>()
    var mAdapter: AboutAdapter? = null

    override fun initView() {


        recyclerView.layoutManager = LinearLayoutManager(mActivity)




        mAdapter = AboutAdapter(list)


        recyclerView.adapter = mAdapter

        ApiServices.getInstance().create(SectionUselessService::class.java)
            .about()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ProcessObserver2(mActivity) {
                override fun processValue(response: String?) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                    Log.i("AboutFragment", response)

                    val model = Gson().fromJson<AboutBean>(response, AboutBean::class.java)
                    list.addAll(model.functions)
                    mAdapter?.setNewData(model.functions)


                    tv_introduce.text = "\t\t\t\t${model.content}"


                    tv_servicePhone.text = "${model.companyName}\n${model.copyRight}"


                }

                override fun onFault(message: String) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    Log.i("AboutFragment", message)
                }

            })

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            when (list[position].name) {
                "协议中心" -> {
                    start(BackViewFragment.newInstance(list[position].linkUrl))
                }
                "帮助中心" -> {
                    start(BackViewFragment.newInstance(list[position].linkUrl))
                }
                "意见反馈" -> {
                    start(A_FeedBackFragment())
                }
                "客服电话" -> {
                    val disposable = RxPermissions(this)
                        .request(Manifest.permission.CALL_PHONE)
                        .subscribe {
                            if (it) {

                                AlertDialogFactory.createFactory(mActivity).getAlertDialog(
                                    "拨打服务热线",
                                    null,
                                    "确定", "取消",
                                    { dlg, v ->
                                        PhoneUtils.callPhone(mActivity, list.get(position).linkUrl.split("tels://")[1])
                                    }, { dlg, v ->
                                    })


                            } else {
                                Toast.makeText(mActivity, "请前往设置中心开启拨打电话", Toast.LENGTH_SHORT).show()
                            }
                        }

                    compositeDisposable.add(disposable)
                }
                "加盟合作" -> {
                    start(BackViewFragment.newInstance(list[position].linkUrl))
                }
                else -> {
                }
            }
        }


    }


}
