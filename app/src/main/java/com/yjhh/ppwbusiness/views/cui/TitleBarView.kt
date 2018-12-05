package com.yjhh.ppwbusiness.views.cui

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.appcompat.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.yjhh.ppwbusiness.R
import com.yjhh.ppwbusiness.R.id.iv_right


import kotlinx.android.synthetic.main.titlebarview.view.*
import java.nio.file.attribute.AclEntryFlag


class TitleBarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(
    context,
    attributeSet,
    defStyleAttr
) {


    fun setTitle(name: String) {
        tv_title.text = name
    }

    fun setRightDisPlay(flag: Boolean){
        iv_right.visibility = if (flag) {
            View.VISIBLE
        }else{
            View.GONE
        }
    }


    var onClickListener: OnRightClickListion? = null

    fun setOnRightClickListener(onClickListener: OnRightClickListion) {
        this.onClickListener = onClickListener
    }

    init {


        val type = context.theme.obtainStyledAttributes(attributeSet, R.styleable.TitleBarView, defStyleAttr, 0)
        val textValue = type.getString(R.styleable.TitleBarView_textValue)
        val textColor =
            type.getColor(R.styleable.TitleBarView_textColor, ContextCompat.getColor(context, R.color.colorPrimary))
        val textSize = type.getDimension(R.styleable.TitleBarView_textSize, 18f)

        val titleBarBackground = type.getColor(
            R.styleable.TitleBarView_titleBarBackground,
            ContextCompat.getColor(context, R.color.colorPrimary)
        )


        val imageSrc = type.getResourceId(R.styleable.TitleBarView_imageSrc, R.drawable.icon_place)

        val imageDis = type.getBoolean(R.styleable.TitleBarView_imageDis, false)

        type.recycle()

        val view = View.inflate(context, R.layout.titlebarview, this)
        val tv_title = view.findViewById<TextView>(R.id.tv_title)



        tv_title.text = textValue
        tv_title.setTextColor(textColor)
        tv_title.textSize = textSize


        val rl_background = view.findViewById<Toolbar>(R.id.rl_background)

        rl_background.setBackgroundColor(titleBarBackground)
        // iv_right.setImageResource(imageSrc)

        if (imageDis) {
            iv_right.visibility = View.VISIBLE
            iv_right.setBackgroundResource(imageSrc)
        } else {
            iv_right.visibility = View.GONE
        }


        iv_right.setOnClickListener {
            onClickListener?.setOnRightClick()
        }


        iv_back.setOnClickListener {
            (context as Activity).onBackPressed()
        }


    }


    interface OnRightClickListion {
        fun setOnRightClick()
    }

}
