package com.example.xavier.base.viewstratum.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.xavier.app.AppManager
import com.example.xavier.base.viewstratum.presentation.UIPresentation
import com.example.xavier.login.LoginActivity

// dubug 阶段继承 LifeCycleBaseFragment 便于测试
// release 阶段可以直接继承 Fragment, UIPresentation 关闭相应的 log
abstract class SimpleFragment : LifeCycleBaseFragment, UIPresentation {

    protected var contentLayoutId = 0
    protected var activity: Activity? = null
    lateinit var rootView: View

    // 视图是否准备好了
    protected var isPrepare = false

    constructor() : super() {}
    constructor(contentLayoutId: Int) : super(contentLayoutId) {
        this.contentLayoutId = contentLayoutId
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            getParameter()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
        isPrepare = true
        init()
    }

    // 获取参数
    protected open fun getParameter() {}


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