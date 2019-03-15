package com.vanzh.dialogdemo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout

/**
 * created by zp on 2019/3/13.
 */
class MyLinearLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    val  TAG = "MyLinearLayout"
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)
   //     Log.i(TAG, "onMeasure:$widthSize ,$heightSize")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun measureChildWithMargins(
        child: View?,
        parentWidthMeasureSpec: Int,
        widthUsed: Int,
        parentHeightMeasureSpec: Int,
        heightUsed: Int
    ) {
        if(child is MyTextView) {
            val specSize = View.MeasureSpec.getSize(parentHeightMeasureSpec)
            Log.i(TAG, "measureChildWithMargins: parentHeightMeasureSpec  $specSize")
        }
        super.measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed)
    }
}