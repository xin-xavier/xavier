package com.example.xavier.base.viewstratum.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.xavier.R
import com.example.xavier.app.AppManager
import com.example.xavier.base.viewstratum.presentation.UIPresentation
import com.example.xavier.login.LoginActivity
import com.gyf.immersionbar.ImmersionBar
import com.jaeger.library.StatusBarUtil

abstract class SimpleActivty() : LifeLinksBaseActivity(),
    UIPresentation {

    protected lateinit var activity: Activity
    protected lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        activity = this
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        immersionBar()
        init()
    }

    // 初始化状态栏
    protected open fun immersionBar() {
        ImmersionBar.with(activity)
            .transparentStatusBar() //透明状态栏
            .statusBarDarkFont(true) //状态栏字体是深色
            .navigationBarColor(R.color.white) //导航栏颜色
            .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
            .init() //通过上面配置后初始化后方可成功调用
    }

    // 状态栏
    protected open fun statusbar() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null)
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
    protected fun intentWhenLoggedin(clazz: Class<*>, bundle: Bundle) {
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