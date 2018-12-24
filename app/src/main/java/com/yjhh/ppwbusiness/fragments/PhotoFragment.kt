package com.yjhh.ppwbusiness.fragments


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import androidx.fragment.app.DialogFragment
import android.view.*
import com.yjhh.ppwbusiness.R


import com.yjhh.ppwbusiness.views.cui.GlideLoader
import com.youth.banner.Banner
import me.jessyan.autosize.internal.CustomAdapt
import androidx.constraintlayout.widget.ConstraintAttribute.setAttributes
import android.view.ViewGroup
import android.view.Gravity
import android.R.attr.gravity

import android.view.WindowManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.Window.FEATURE_NO_TITLE
import android.view.LayoutInflater
import android.widget.*
import androidx.annotation.Nullable
import androidx.viewpager.widget.ViewPager
import com.yjhh.ppwbusiness.BaseApplication
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener


@SuppressLint("ValidFragment")
class PhotoFragment(var list: List<String>,var index : Int) : androidx.fragment.app.DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val view = LayoutInflater.from(activity).inflate(R.layout.photofragment, null);

        val tv_title = view.findViewById<TextView>(R.id.tv_title)


        val rl = view.findViewById<RelativeLayout>(R.id.rl)
        rl.setOnClickListener { dismiss() }


        tv_title.text = "${index+1}/${list.size}"

       val iv_back = view.findViewById<ImageView>(R.id.iv_back)

        val banner = view.findViewById<Banner>(R.id.banner)
        iv_back.setOnClickListener {
            dismiss()
        }

        banner.setBannerStyle(BannerConfig.NOT_INDICATOR)

        banner.setImages(list)
            .setImageLoader(GlideLoader())
            .setDelayTime(10000000)
            .start()




        banner.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {


                    tv_title.text = "${position+1}/${list.size}"



                Log.i("PhotoFragment",position.toString())
            }


            override fun onPageScrollStateChanged(state: Int) {

            }

        })







        builder.setView(view)
        val dialog = builder.create()
        return dialog
    }


    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        // dialog.window!!.attributes.windowAnimations = R.style.dialogAnim
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val win = dialog.window
        // 一定要设置Background，如果不设置，window属性设置无效
        win!!.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        val dm = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(dm)

        val params = win.attributes
        params.gravity = Gravity.CENTER
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        win.attributes = params
    }


}