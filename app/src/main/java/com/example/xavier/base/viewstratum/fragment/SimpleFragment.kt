package com.example.xavier.base.viewstratum.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.BarUtils
import com.example.xavier.R
import com.example.xavier.app.AppManager
import com.example.xavier.base.viewstratum.presentation.UIPresentation
import com.example.xavier.login.LoginActivity
import com.gyf.immersionbar.ImmersionBar
import com.jaeger.library.StatusBarUtil

// dubug 阶段继承 LifeCycleBaseFragment 便于测试
// release 阶段可以直接继承 Fragment, UIPresentation 关闭相应的 log
abstract class SimpleFragment : LifeCycleBaseFragment, UIPresentation {

    protected var contentLayoutId = 0
    protected var activity: Activity? = null
    var rootView: View?=null

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
        if(rootView==null){
            rootView = view
        }
        isPrepare = true
        if(immersionBarEnabled()){
            immersionBar()
        }
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        contentLayoutId=0
        rootView=null
        isPrepare=false
    }

    override fun onDetach() {
        super.onDetach()
        activity=null
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

    /**
     * 是否可以实现沉浸式，当为true的时候才可以执行initImmersionBar方法
     * Immersion bar enabled boolean.
     *
     * @return the boolean
     */
    open fun immersionBarEnabled(): Boolean {
        return false
    }

    // 初始化状态栏
    protected open fun immersionBar() {
        activity?.let {
            StatusBarUtil.setTransparentForImageViewInFragment(it, null)
            if(BarUtils.isStatusBarLightMode(it) ){
                BarUtils.setStatusBarLightMode(it,false)
            }else{
                BarUtils.setStatusBarLightMode(it,true)
            }
        }
    }

}