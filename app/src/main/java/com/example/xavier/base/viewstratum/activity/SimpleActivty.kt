package com.example.xavier.base.viewstratum.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.xavier.app.AppManager
import com.example.xavier.app.ConstantPool
import com.example.xavier.base.viewstratum.presentation.UIPresentation
import com.example.xavier.login.LoginActivity
import com.example.xavier.utils.os.OSStatusBarUtil
import com.jaeger.library.StatusBarUtil
import kotlin.properties.Delegates

abstract class SimpleActivty() : LifeLinksBaseActivity(),
    UIPresentation {

    protected lateinit var activity: Activity
    protected lateinit var context: Context
    protected var statusBarHeight by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        activity = this

        statusBarHeight = getStatusBarHeight(this)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initStatusBar()
        init()
    }

    /**
     * @method 获取状态栏高度
     * @param context context
     * @return 状态栏高度
     */
    private fun getStatusBarHeight(context: Context): Int {
        val resourceId =
            context.resources.getIdentifier(
                ConstantPool.IDENTIFIER_NAME,
                ConstantPool.DEF_TYPE,
                ConstantPool.ANDROID
            )
        return context.resources.getDimensionPixelSize(resourceId)
    }

    // 初始化状态栏
    protected open fun initStatusBar() {
        StatusBarUtil.setTransparentForImageView(activity, null)
        OSStatusBarUtil.setImmersiveStatusBar(activity, true)
    }


    /**
     * @method 页面跳转
     * @param clazz clazz
     */
    protected fun intent(clazz: Class<*>) {
        startActivity(Intent(context, clazz))
    }

    /**
     * @method 携带bundle的页面跳转
     * @param clazz clazz
     * @param bundle bundle
     */
    protected fun intent(clazz: Class<*>, bundle: Bundle) {
        startActivity(Intent(context, clazz).apply {
            putExtras(bundle)
        })
    }

    /**
     * @method 跳转到需要登录的页面
     * @param clazz clazz
     */
    protected fun intentWhenLoggedin(clazz: Class<*>) {
        //需要登录&&未登录
        if (AppManager.isLogin()) {
            startActivity(Intent(context, clazz))
        } else {
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    /**
     * @method 携带bundle跳转到需要登录的页面
     * @param clazz clazz
     * @param bundle bundle
     */
    protected fun intentWhenLoggedin(clazz: Class<*>,bundle: Bundle) {
        //需要登录&&未登录
        if (AppManager.isLogin()) {
            startActivity(Intent(context, clazz))
        } else {
            startActivity(Intent(context, clazz).apply {
                putExtras(bundle)
            })
        }
    }


}