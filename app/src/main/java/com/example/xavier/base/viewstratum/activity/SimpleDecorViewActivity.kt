package com.example.xavier.base.viewstratum.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentTransaction
import com.blankj.utilcode.util.BarUtils
import com.example.xavier.R
import com.example.xavier.base.viewstratum.presentation.OnPrepareListener
import com.example.xavier.utils.helper.ToolbarHelper
import kotlinx.android.synthetic.main.layout_actionbar_view.*


abstract class SimpleDecorViewActivity : SimpleActivty(), OnPrepareListener {

    protected lateinit var inflater: LayoutInflater
    protected lateinit var parentLinearLayout: LinearLayout
    protected lateinit var beginTransaction: FragmentTransaction
    protected lateinit var toolbarManagerFragment: ToolbarHelper

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
        immersionBar()
        init()
    }

    /**
     * @param layoutResID layout id of sub-activity
     */
    private fun initContentView(@LayoutRes layoutResID: Int) {
        val viewGroup = findViewById(android.R.id.content) as ViewGroup
        viewGroup.removeAllViews()
        parentLinearLayout = LinearLayout(this)
        parentLinearLayout.orientation = LinearLayout.VERTICAL
        //  add parentLinearLayout in viewGroup
        viewGroup.addView(parentLinearLayout)
        //  add the layout of BaseDecorContentActivity in parentLinearLayout
        inflater = LayoutInflater.from(this)
        inflater.inflate(layoutResID, parentLinearLayout, true)

        // toolbarLayoutContent 一般为 40dp
        // 通过LayoutParams获取 这是获取的就是在 xml 里明确定义的高度
        val height = toolbarLayoutContent.layoutParams.height
        // 此时在 xml 设置 width 为 match_parent 而获取到的是 -1
        // xincaution 当没有设置具体宽高的获取到的值为0
        //val width = toolbarLayoutContent.layoutParams.width
        //Log.i(TAG, "onCreate: height = " + height + " == " + SizeUtils.dp2px(ConstantPool._40.toFloat()))
        val appbarHeight: Int = BarUtils.getStatusBarHeight() + height
        appbarLayout.layoutParams.height = appbarHeight

        setToolbarResID(layoutToolbarResID())
    }

    open fun setToolbarResID(@LayoutRes layoutToolbarID: Int) {
        beginTransaction = supportFragmentManager.beginTransaction()
        toolbarManagerFragment =
            ToolbarHelper(
                layoutToolbarID,
                this
            )
        beginTransaction.replace(R.id.toolbarLayoutContent, toolbarManagerFragment)
        beginTransaction.commit()
    }

    open fun initLayoutRes(): Int {
        return R.layout.layout_decor_view
    }

    open fun layoutToolbarResID(): Int {
        return R.layout.layout_init_toobar_view
    }

    // 判断使用的是否是默认的 toolbarRes
    open fun isDefaultBar(): Boolean {
        return layoutToolbarResID() == R.layout.layout_init_toobar_view
    }

    // 当页面使用的是否是默认的 toolbarRes 时，方便设置 title
    open fun setDefaultTitle(title: String) {
        if (isDefaultBar()) {
            val toolbarTitle =
                toolbarManagerFragment.rootView.findViewById<TextView>(R.id.toolbarTitle)
            toolbarTitle.text = title
        }
    }



}