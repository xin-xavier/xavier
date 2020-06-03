package com.example.xavier.base.viewstratum.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.blankj.utilcode.util.BarUtils
import com.example.xavier.R
import com.example.xavier.base.viewstratum.presentation.OnPrepareListener
import com.example.xavier.utils.helper.ToolbarHelper
import kotlinx.android.synthetic.main.layout_init_toobar_view.*

abstract class SimpleDecorViewFragment : SimpleFragment(), OnPrepareListener {

    protected lateinit var inflater: LayoutInflater
    protected lateinit var toolbarHelper: ToolbarHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflater = inflater
        rootView = initLayout(layoutRes(), container)
        return rootView
    }

    override fun immersionBarEnabled(): Boolean {
        return true
    }

    private fun initLayout(@LayoutRes layoutResID: Int, container: ViewGroup?): View {
        val view = inflater.inflate(layoutResID, container, false)

        val height = view.findViewById<FrameLayout>(R.id.toolbarLayoutContent).layoutParams.height
        val appbarHeight: Int = BarUtils.getStatusBarHeight() + height
        view.findViewById<FrameLayout>(R.id.appbarLayout).layoutParams.height = appbarHeight

        setToolbar(toolbarLayoutRes())

        return setContentView(view, contentLayoutRes(), container)
    }

    open fun setToolbar(@LayoutRes toolbarLayoutResID: Int) {
        val beginTransaction = childFragmentManager.beginTransaction()
        toolbarHelper =
            ToolbarHelper(
                toolbarLayoutResID,
                this
            )
        beginTransaction.replace(R.id.toolbarLayoutContent, toolbarHelper)
        beginTransaction.commit()
    }

    /**
     * @param layoutResID layout id of sub-activity
     */
    private fun setContentView(
        view: View,
        @LayoutRes contentLayoutResID: Int,
        container: ViewGroup?
    ): View {
        //  added the sub-activity layout id in parentLinearLayout
        val decorView: LinearLayout = view.findViewById<LinearLayout>(R.id.decorView)
        decorView.addView(inflater.inflate(contentLayoutResID, container, false))
        return view
    }

    open fun layoutRes(): Int {
        return R.layout.layout_decor_view
    }

    open fun toolbarLayoutRes(): Int {
        return R.layout.layout_init_toobar_view
    }

    abstract fun contentLayoutRes(): Int

    // 判断使用的是否是默认的 toolbarRes
    open fun isDefaultBar(): Boolean {
        return toolbarLayoutRes() == R.layout.layout_init_toobar_view
    }

    // 当页面使用的是否是默认的 toolbarRes 时，方便设置 title
    open fun setDefaultTitle(title: String) {
        if (isDefaultBar()) {
            toolbarHelper.rootView?.let {
                toolbarHelper.rootView?.findViewById<TextView>(R.id.toolbarTitle)
                toolbarTitle.text = title
            }
        }
    }

}