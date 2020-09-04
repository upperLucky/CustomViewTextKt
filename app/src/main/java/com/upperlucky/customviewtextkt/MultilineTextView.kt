package com.upperlucky.customviewtextkt

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * created by yunKun.wen on 2020/9/3
 * desc:
 */
private val BITMAP_SIZE = 200.dp
private val BITMAP_PADDING = 50.dp
private val TEXT_SIZE = 20.dp

class MultilineTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private val text =
        "Lorem ipsum dolor sit amet, consectetur adipisicing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint " +
                "occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum" +
                "unt in culpa qui officia deserunt mollit anim id est laborum" +
                "unt in culpa qui officia deserunt mollit anim id est laborum."

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 20.dp
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 使用StaticLayout绘制多行文字
//        val staticLayout =
//            StaticLayout(text, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false)
//        staticLayout.draw(canvas)
//

        canvas.drawBitmap(
            getAvator(BITMAP_SIZE.toInt()),
            width - BITMAP_SIZE,
            BITMAP_PADDING,
            paint
        )

        paint.textSize = TEXT_SIZE
        paint.getFontMetrics(fontMetrics)
        var floatArray = floatArrayOf(0f)

        var startIndex = 0
        var count = 0
        var verticalOffset = -fontMetrics.top
        var maxWidth = 0f
        while (startIndex < text.length) {
            maxWidth = if (verticalOffset + fontMetrics.bottom < BITMAP_PADDING ||
                verticalOffset + fontMetrics.top > BITMAP_PADDING + BITMAP_SIZE
            ) {
                width.toFloat()
            } else {
                width.toFloat() - BITMAP_SIZE
            }
            // 返回测量的字符数
            count = paint.breakText(text, startIndex, text.length, true, maxWidth, floatArray)
            canvas.drawText(text, startIndex, startIndex + count, 0f, verticalOffset, paint)
            startIndex += count
            verticalOffset += paint.fontSpacing
        }
    }

    fun getAvator(width: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.icon, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.icon, options)
    }
}