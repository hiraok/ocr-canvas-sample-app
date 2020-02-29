package com.khirao.ocr_canvas_sample_app

import android.R.attr.bitmap
import android.app.ActionBar
import android.content.Context
import android.graphics.*
import android.graphics.Bitmap.CompressFormat
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class CanvasView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0
) : View(
    context,
    attr,
    defStyle
) {
    private val pathList = arrayListOf<Path>()
    private val paint = Paint()
    private var drawingPath: Path? = null

    init {
        paint.color = (Color.BLACK)
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.strokeWidth = 10.0f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let { c ->
            pathList.forEach {
                c.drawPath(it, paint)
            }
        } ?: run {
            Log.println(Log.ERROR, CanvasView::class.java.simpleName, "Null Canvas")
        }
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                drawingPath = Path()
                drawingPath?.moveTo(event.x, event.y)
                drawingPath?.let {
                    pathList.add(it)
                }
            }
            MotionEvent.ACTION_UP -> {
                drawingPath?.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                drawingPath?.lineTo(event.x, event.y)
            }
        }
        return true
    }




}