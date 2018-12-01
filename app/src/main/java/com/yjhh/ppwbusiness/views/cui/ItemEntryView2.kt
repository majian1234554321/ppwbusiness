package com.yjhh.ppwbusiness.views.cui

import android.content.Context
import android.graphics.drawable.Drawable

import androidx.core.content.ContextCompat

import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.yjhh.ppwbusiness.R
import kotlinx.android.synthetic.main.itementryview2.view.*


class ItemEntryView2 @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(
    context,
    attributeSet,
    defStyleAttr
) {

    fun setTextContent(name: String) {
        tv_right.text = name
    }

    fun getTextContent(): String {
        return tv_right.text.toString()
    }


    fun setLeftContent(value: String) {
        tv_left.text = value
    }


    init {


        val type = context.theme.obtainStyledAttributes(attributeSet, R.styleable.ItemEntryView2, defStyleAttr, 0)
        val textValue = type.getString(R.styleable.ItemEntryView2_ievtextValue2)
        val ievLeftTextColor =
            type.getColor(
                R.styleable.ItemEntryView2_ievtextColor2,
                ContextCompat.getColor(context, R.color.colorPrimary)
            )


        val ievRightTextColor =
            type.getColor(
                R.styleable.ItemEntryView2_ievRightTextColor,
                ContextCompat.getColor(context, R.color.colorPrimary)
            )


        type.recycle()

        val view = View.inflate(context, R.layout.itementryview2, this)

        tv_left.text = textValue
        tv_left.setTextColor(ievLeftTextColor)

        tv_right.setTextColor(ievRightTextColor)


    }


}
