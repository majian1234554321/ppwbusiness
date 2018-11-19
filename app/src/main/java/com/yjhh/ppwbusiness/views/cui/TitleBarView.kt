package com.yjhh.ppwbusiness.views.cui

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.yjhh.ppwbusiness.R


import kotlinx.android.synthetic.main.titlebarview.view.*


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

        type.recycle()

        val view = View.inflate(context, R.layout.titlebarview, this)
        val tv_title = view.findViewById<TextView>(R.id.tv_title)



        tv_title.text = textValue
        tv_title.setTextColor(textColor)
        tv_title.textSize = textSize


        val rl_background = view.findViewById<Toolbar>(R.id.rl_background)

        rl_background.setBackgroundColor(titleBarBackground)


        iv_right.setOnClickListener {

        }


        iv_back.setOnClickListener {
            (context as Activity).onBackPressed()
        }


    }


}
