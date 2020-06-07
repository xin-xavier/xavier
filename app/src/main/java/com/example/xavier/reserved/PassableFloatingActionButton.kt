package com.example.xavier.reserved

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable

/**
 * 序列化 FloatingActionButton
 * 可通过 Intent Bundle 传值的 FloatingActionButton
 */
class PassableFloatingActionButton :
    FloatingActionButton, Serializable {
    constructor(context: Context) : super(context) {}
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {}
}