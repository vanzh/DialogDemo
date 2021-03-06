package com.vanzh.dialogdemo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView

/**
 * created by zp on 2019/3/13.
 */
@SuppressLint("AppCompatCustomView")
class MyTextView : TextView {
    val TAG = "MyTextView"

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        Log.i(TAG, "layout:$b - $t ,  ${b - t}")
        super.layout(l, t, r, b)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    //    Log.i(TAG, "onLayout:$bottom - $top ,  ${bottom - top}")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)
        Log.i(TAG, "onMeasure:$widthSize ,$heightSize")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}
