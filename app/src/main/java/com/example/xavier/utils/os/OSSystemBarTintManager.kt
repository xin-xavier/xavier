package com.example.xavier.utils.os

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout

class OSSystemBarTintManager @TargetApi(19) constructor(activity: Activity) {
    private var mStatusBarAvailable = false
    private var mStatusBarTintEnabled = false
    private var mStatusBarTintView: View? = null

    /**
     * 初始化状态栏
     *
     * @param context
     * @param decorViewGroup
     */
    private fun setupStatusBarView(
        context: Activity,
        decorViewGroup: ViewGroup
    ) {
        mStatusBarTintView = View(context)
        //设置高度为Statusbar的高度
        val params =
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(context)
            )
        params.gravity = Gravity.TOP
        mStatusBarTintView!!.layoutParams = params
        mStatusBarTintView!!.setBackgroundColor(DEFAULT_TINT_COLOR)
        //默认不显示
        mStatusBarTintView!!.visibility = View.GONE
        //decorView添加状态栏高度的View
        decorViewGroup.addView(mStatusBarTintView)
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    private fun getStatusBarHeight(activity: Activity): Int {
        var statusBarHeight = 0
        val resourceId =
            activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = activity.resources.getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

    /**
     * 显示状态栏
     */
    fun setStatusBarTintEnabled(enabled: Boolean) {
        mStatusBarTintEnabled = enabled
        if (mStatusBarAvailable) {
            mStatusBarTintView!!.visibility = if (enabled) View.VISIBLE else View.GONE
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param color
     */
    fun setStatusBarTintColor(color: Int) {
        if (mStatusBarAvailable) {
            mStatusBarTintView!!.setBackgroundColor(color)
        }
    }

    companion object {
        const val DEFAULT_TINT_COLOR = -0x67000000
    }

    init {
        val win = activity.window
        //获取DecorView
        val decorViewGroup = win.decorView as ViewGroup
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 检查主题中是否有透明的状态栏
            val attrs = intArrayOf(android.R.attr.windowTranslucentStatus)
            val a = activity.obtainStyledAttributes(attrs)
            mStatusBarAvailable = try {
                a.getBoolean(0, false)
            } finally {
                a.recycle()
            }
            val winParams = win.attributes
            val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS //状态栏透明
            if (winParams.flags and bits != 0) {
                mStatusBarAvailable = true
            }
        }
        if (mStatusBarAvailable) {
            setupStatusBarView(activity, decorViewGroup)
        }
    }
}