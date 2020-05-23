package com.example.xavier.utils.os

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.annotation.IntDef

object OSStatusBarUtil {
    const val TYPE_MIUI = 0
    const val TYPE_FLYME = 1
    const val TYPE_M = 3 //6.0

    /**
     * 修改状态栏颜色，支持4.4以上版本
     * @param colorId 颜色
     */
    fun setStatusBarColor(activity: Activity, colorId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.statusBarColor = colorId
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //使用SystemBarTintManager,需要先将状态栏设置为透明
            setTranslucentStatus(activity)
            val systemBarTintManager = OSSystemBarTintManager(activity)
            systemBarTintManager.setStatusBarTintEnabled(true) //显示状态栏
            systemBarTintManager.setStatusBarTintColor(colorId) //设置状态栏颜色
        }
    }

    /**
     * 设置状态栏透明
     */
    @TargetApi(19)
    fun setTranslucentStatus(activity: Activity) {

        // 5.0以上系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            //清除透明状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            //设置状态栏颜色必须添加
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT //设置透明
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //19
            activity.window
                .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 设置沉浸式状态栏
     *
     * @param fontIconDark 状态栏字体和图标颜色是否为深色
     */
    fun setImmersiveStatusBar(
        activity: Activity,
        fontIconDark: Boolean
    ) {
        setTranslucentStatus(activity)
        if (fontIconDark) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setStatusBarFontIconDark(activity, TYPE_M)
            } else if (OSUtils.isMiui()) {
                setStatusBarFontIconDark(activity, TYPE_MIUI)
            } else if (OSUtils.isFlyme()) {
                setStatusBarFontIconDark(activity, TYPE_FLYME)
            } else { //其他情况下我们将状态栏设置为灰色，就不会看不见字体
                setStatusBarColor(activity, Color.LTGRAY) //灰色
            }
        }
    }

    /**
     * 设置文字颜色
     */
    fun setStatusBarFontIconDark(activity: Activity, @ViewType type: Int) {
        when (type) {
            TYPE_MIUI -> setMiuiUI(activity, true)
            TYPE_M -> setCommonUI(activity)
            TYPE_FLYME -> setFlymeUI(activity, true)
        }
    }

    //设置6.0的字体
    fun setCommonUI(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }
    }

    //设置Flyme的字体
    fun setFlymeUI(activity: Activity, dark: Boolean) {
        try {
            val window = activity.window
            val lp = window.attributes
            val darkFlag =
                WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
            val meizuFlags =
                WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
            darkFlag.isAccessible = true
            meizuFlags.isAccessible = true
            val bit = darkFlag.getInt(null)
            var value = meizuFlags.getInt(lp)
            value = if (dark) {
                value or bit
            } else {
                value and bit.inv()
            }
            meizuFlags.setInt(lp, value)
            window.attributes = lp
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //设置MIUI字体
    @SuppressLint("PrivateApi")
    fun setMiuiUI(activity: Activity, dark: Boolean) {
        try {
            val window = activity.window
            val clazz: Class<*> = activity.window.javaClass
            val layoutParams =
                Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field =
                layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            val darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod(
                "setExtraFlags",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            if (dark) {    //状态栏亮色且黑色字体
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag)
            } else {
                extraFlagField.invoke(window, 0, darkModeFlag)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @IntDef(TYPE_MIUI, TYPE_FLYME, TYPE_M)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    internal annotation class ViewType
}