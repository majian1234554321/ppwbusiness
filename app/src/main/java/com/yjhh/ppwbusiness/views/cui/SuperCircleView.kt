package com.yjhh.ppwbusiness.views.cui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.yjhh.ppwbusiness.R

class SuperCircleView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
    context,
    attributeSet,
    defStyleAttr
) {


    var paint: Paint? = null
    val outsideCircleRadius: Float
    val outsideCircleColor: Int
    val outsideStrokeWidth: Float

    val insideCircleRadius: Float
    val insideCircleColor: Int
    val insideStrokeWidth: Float

    val CircleLTextSize: Float
    val CircleSTextSize: Float

    init {
        paint = Paint()

        val type = context.theme.obtainStyledAttributes(attributeSet, R.styleable.SuperCircleView, defStyleAttr, 0)

        outsideCircleRadius = type.getDimension(R.styleable.SuperCircleView_outsideCircleRadius, 18f)
        outsideCircleColor = type.getColor(R.styleable.SuperCircleView_outsideCircleColor, Color.RED)
        outsideStrokeWidth = type.getDimension(R.styleable.SuperCircleView_outsideStrokeWidth, 18f)


        insideCircleRadius = type.getDimension(R.styleable.SuperCircleView_insideCircleRadius, 18f)
        insideCircleColor = type.getColor(R.styleable.SuperCircleView_insideCircleColor, Color.RED)
        insideStrokeWidth = type.getDimension(R.styleable.SuperCircleView_insideStrokeWidth, 18f)


        CircleLTextSize = type.getDimension(R.styleable.SuperCircleView_CircleLTextSize, 18f)

        CircleSTextSize = type.getDimension(R.styleable.SuperCircleView_CircleSTextSize, 18f)

        type.recycle()


    }

    var content: String = ""

    fun setcontent(content: String) {
        this.content = content

        invalidate()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint?.reset()
        paint?.style = Paint.Style.STROKE
        paint?.isAntiAlias = true
        paint?.strokeWidth = outsideStrokeWidth
        paint?.color = outsideCircleColor
        canvas?.drawCircle(outsideCircleRadius, outsideCircleRadius, outsideCircleRadius, paint)


        paint?.reset()
        paint?.style = Paint.Style.STROKE
        paint?.isAntiAlias = true
        paint?.strokeWidth = insideStrokeWidth
        paint?.color = insideCircleColor
        canvas?.drawCircle(outsideCircleRadius, outsideCircleRadius, insideCircleRadius, paint)


        paint?.reset()
        paint?.style = Paint.Style.STROKE
        paint?.isAntiAlias = true
        paint?.color = Color.BLACK
        paint?.textSize = CircleLTextSize


//
//        canvas?.drawText(
//            content,
//
//            outsideCircleRadius / 2 + CircleSTextSize / 2,
//            outsideCircleRadius + CircleSTextSize / 2,
//            paint
//        )
//
//
//
//
//
//
//
//        paint?.textSize = 14f
//        paint?.textAlign = Paint.Align.CENTER
//        canvas?.drawText(
//            "单",
//            outsideCircleRadius / 2 + CircleSTextSize / 2+10f,
//            outsideCircleRadius + CircleSTextSize / 2,
//            paint
//        )
//
//        canvas.drawPaint()


    }
}
