package com.example.xavier.base.viewstratum.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentTransaction
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SizeUtils
import com.example.xavier.R
import com.example.xavier.app.api.ConstantPool.Companion.ACTION_BAR_SIZE
import com.example.xavier.base.viewstratum.presentation.OnPrepareListener
import com.example.xavier.widght.helper.AppbarHelper
import kotlinx.android.synthetic.main.layout_actionbar_view.*
import kotlinx.android.synthetic.main.layout_init_toobar_view.*


abstract class SimpleDecorViewActivity : SimpleActivity(), OnPrepareListener {

    private lateinit var inflater: LayoutInflater
    private lateinit var parentLinearLayout: LinearLayout
    private lateinit var appbarHelper: AppbarHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView(initLayoutRes())
    }

    /**
     * @param layoutResID layout id of sub-activity
     */
    override fun setContentView(@LayoutRes layoutResID: Int) {
        //  added the sub-activity layout id in parentLinearLayout
        inflater.inflate(layoutResID, parentLinearLayout, true)
        withImmersionBar()
        init()
    }

    /**
     * @param layoutResID layout id of sub-activity
     */
    private fun initContentView(@LayoutRes initLayoutResID: Int) {
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        viewGroup.removeAllViews()

        parentLinearLayout = LinearLayout(context)
        parentLinearLayout.orientation = LinearLayout.VERTICAL
        //  add parentLinearLayout in viewGroup
        viewGroup.addView(parentLinearLayout)
        //  add the layout of BaseDecorContentActivity in parentLinearLayout
        inflater = LayoutInflater.from(context)
        inflater.inflate(initLayoutResID, parentLinearLayout, true)

        val toolbarLayoutHeight = SizeUtils.dp2px(ACTION_BAR_SIZE)
        toolbarLayoutContent.layoutParams.height = toolbarLayoutHeight
        val statusBarHeight = BarUtils.getStatusBarHeight()
        //Log.i(TAG, "initContentView: statusBarHeight = ${SizeUtils.px2dp(statusBarHeight.toFloat())}")
        appbarLayout.layoutParams.height = statusBarHeight + toolbarLayoutHeight

        setToolbar(toolbarLayoutRes())
    }

    open fun setToolbar(@LayoutRes toolbarLayoutResID: Int) {
        val beginTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        appbarHelper =
            AppbarHelper(
                toolbarLayoutResID,
                this
            )
        beginTransaction.replace(R.id.toolbarLayoutContent, appbarHelper)
        beginTransaction.commit()
    }

    open fun initLayoutRes(): Int {
        return R.layout.layout_decor_view
    }

    open fun toolbarLayoutRes(): Int {
        return R.layout.layout_init_toobar_view
    }

    // 判断使用的是否是默认的 toolbarRes
    open fun isDefaultBar(): Boolean {
        return toolbarLayoutRes() == R.layout.layout_init_toobar_view
    }

    // 当页面使用的是否是默认的 toolbarRes 时，方便设置 title
    open fun setDefaultTitle(title: String) {
        if (isDefaultBar()) {
            appbarHelper.rootView?.let {
                appbarHelper.rootView?.findViewById<TextView>(R.id.toolbarTitle)
                toolbarTitle.text = title
            }
        }
    }

}