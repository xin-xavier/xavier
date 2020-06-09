package com.example.xavier.widght.helper

import android.view.View
import android.view.ViewGroup.MarginLayoutParams

object ViewGroupHelper {
    fun setTopMargin(view: View, statusBarHeight: Int) {
        val layoutParams = view.layoutParams as MarginLayoutParams
        layoutParams.setMargins(0, statusBarHeight, 0, 0)
        view.layoutParams = layoutParams
    }
}