package com.yjhh.ppwbusiness.views.cui

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
import android.widget.LinearLayout
import android.widget.TextView
import com.yjhh.ppwbusiness.R
import kotlinx.android.synthetic.main.appupdatefragment.*
import me.jessyan.autosize.internal.CustomAdapt


@SuppressLint("ValidFragment")
class AppUpdateFragment(var flag: Boolean) : androidx.fragment.app.DialogFragment(), CustomAdapt {


    override fun isBaseOnWidth(): Boolean {
        return true
    }

    override fun getSizeInDp(): Float {
        return 375f
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.window.setGravity(Gravity.CENTER)
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    var mb: MaterialButton? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val view = LayoutInflater.from(activity).inflate(R.layout.appupdatefragment, null);
        val btn_ok = view.findViewById<TextView>(R.id.btn_ok);
        val btn_cancel = view.findViewById<TextView>(R.id.btn_cancel);
        val ll_pt = view.findViewById<LinearLayout>(R.id.ll_pt);

        mb = view.findViewById<MaterialButton>(R.id.mb)

        mb?.setOnClickListener { appUpdateListener?.onAppUpdate() }
        btn_ok.setOnClickListener { appUpdateListener?.onAppUpdate() }
        btn_cancel.setOnClickListener { dismiss() }
        builder.setView(view)

        val dialog = builder.create()

        if (flag) {
            ll_pt.visibility = View.GONE
            mb?.visibility = View.VISIBLE

            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            dialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return@OnKeyListener true
                }
                false
            })
        } else {
            ll_pt.visibility = View.VISIBLE
            mb?.visibility = View.GONE
        }



        return dialog
    }


    fun setTitle(title: String) {
        mb?.text = title
    }


    var appUpdateListener: AppUpdateListener? = null

    fun setOnAppUpdate(appUpdateListener: AppUpdateListener) {
        this.appUpdateListener = appUpdateListener
    }

    interface AppUpdateListener {
        fun onAppUpdate()
    }


}