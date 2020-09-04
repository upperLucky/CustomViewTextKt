package com.upperlucky.customviewtextkt

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat

/**
 * created by yunKun.wen on 2020/9/3
 * desc:
 */
private val CIRCLE_SIZE = 120.dp
private val TEXT_SIZE = 80.dp

class SportView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 15.dp
        textAlign = Paint.Align.CENTER
        typeface = ResourcesCompat.getFont(context, R.font.font)
    }

    private val bounds: Rect = Rect()
    private val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 画圆
        paint.color = Color.GRAY
        canvas.drawOval(
            width / 2f - CIRCLE_SIZE,
            height / 2f - CIRCLE_SIZE,
            width / 2f + CIRCLE_SIZE,
            height / 2f + CIRCLE_SIZE, paint
        )


        // 画弧
        paint.color = Color.BLUE
        canvas.drawArc(
            width / 2f - CIRCLE_SIZE,
            height / 2f - CIRCLE_SIZE,
            width / 2f + CIRCLE_SIZE,
            height / 2f + CIRCLE_SIZE, -90f, 225f, false, paint
        )

        // 文字
        paint.textSize = TEXT_SIZE
        paint.style = Paint.Style.FILL
        val text = "abab"
        paint.getTextBounds(text, 0, text.length, bounds)
        paint.getFontMetrics(fontMetrics)
        canvas.drawText(
            text,
            width / 2f,
            height / 2f - (fontMetrics.descent + fontMetrics.ascent) / 2f,
            paint
        )

        // 文字的左对齐()
        paint.textSize = 50.dp
        paint.textAlign = Paint.Align.LEFT
        paint.getTextBounds(text,0,text.length,bounds)
        paint.getFontMetrics(fontMetrics)
        canvas.drawText(text,0f - bounds.left ,0f - fontMetrics.top,paint)
    }

}