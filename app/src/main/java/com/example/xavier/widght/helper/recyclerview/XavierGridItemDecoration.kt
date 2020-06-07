package com.example.xavier.widght.helper.recyclerview

import android.annotation.SuppressLint
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.blankj.utilcode.util.SizeUtils
import com.example.xavier.app.api.ConstantPool

class XavierGridItemDecoration : ItemDecoration {
    private val TAG = "XavierGridItemDecoration"
    private var longDivide =
        SizeUtils.dp2px(ConstantPool.EDGE_DISTANCE)
    private var shortDivide =
        SizeUtils.dp2px(ConstantPool.DIVIDE_DISTANCE)

    // false == 无 Header
    // true  == 有 Header
    private var existHeader = false
    private var begin = 0

    constructor() {}
    constructor(longEdge: Int) {
        longDivide = SizeUtils.dp2px(longEdge.toFloat())
        shortDivide = longDivide / 2
    }

    constructor(existHeader: Boolean) {
        this.existHeader = existHeader
    }

    constructor(existHeader: Boolean, longEdge: Int) : this(longEdge) {
        this.existHeader = existHeader
    }

    constructor(longDivide: Int, shortDivide: Int, existHeader: Boolean) {
        this.longDivide = longDivide
        this.shortDivide = shortDivide
        this.existHeader = existHeader
    }

    @SuppressLint("LongLogTag")
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        //super.getItemOffsets(outRect, view, parent, state);
        val position =
            (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
        val oddEven = position % 2
        val itemCount = parent.adapter!!.itemCount

        Log.i(
            TAG,
            "getItemOffsets: position = $position --- itemCount = $itemCount"
        )

        begin = if (existHeader) 1 else 0

        if (position < begin) {
            return
        }

        if (position == begin) {
            firstDivide(outRect)
        } else if (position == itemCount - 1) {
            endDivide(outRect, oddEven)
        } else {
            usualDivide(outRect, oddEven)
        }

    }

    private fun firstDivide(outRect: Rect) {
        outRect.left = longDivide
        outRect.top = longDivide
        outRect.right = shortDivide
        outRect.bottom = 0
    }

    private fun endDivide(outRect: Rect, oddEven: Int) {
        if (existHeader && oddEven == 1) {
            leftEndDivide(outRect)
        } else {
            rightEndDivide(outRect)
        }
    }

    private fun leftEndDivide(outRect: Rect) {
        outRect.left = longDivide
        outRect.top = longDivide
        outRect.right = shortDivide
        outRect.bottom = longDivide
    }

    private fun rightEndDivide(outRect: Rect) {
        outRect.left = shortDivide
        outRect.top = longDivide
        outRect.right = longDivide
        outRect.bottom = 0
    }

    private fun usualDivide(outRect: Rect, oddEven: Int) {
        if (existHeader && oddEven == 1) {
            leftUsualDivide(outRect)
        } else {
            rightUsualDivide(outRect)
        }
    }

    private fun leftUsualDivide(outRect: Rect) {
        outRect.left = longDivide
        outRect.top = longDivide
        outRect.right = shortDivide
        outRect.bottom = 0
    }

    private fun rightUsualDivide(outRect: Rect) {
        outRect.left = shortDivide
        outRect.top = longDivide
        outRect.right = longDivide
        outRect.bottom = 0
    }
}