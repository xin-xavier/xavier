package com.example.xavier.base.viewstratum.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentTransaction
import com.blankj.utilcode.util.BarUtils
import com.example.xavier.R
import com.example.xavier.base.viewstratum.presentation.OnPrepareListener
import com.example.xavier.utils.helper.ToolbarHelper
import kotlinx.android.synthetic.main.layout_actionbar_view.*

abstract class SelfishnessActivity : SimpleActivty(), OnPrepareListener {

    protected lateinit var inflater: LayoutInflater
    protected lateinit var parentLinearLayout: LinearLayout
    protected lateinit var toolbarHelper: ToolbarHelper

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

    override fun onPrepare() {
        toolbarLayoutContent.layoutParams.height = toolbarHelper.rootHeight
        val appbarHeight: Int = BarUtils.getStatusBarHeight() + toolbarHelper.rootHeight
        appbarLayout.layoutParams.height = appbarHeight
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

        setToolbar(toolbarLayoutRes())
    }

    open fun setToolbar(@LayoutRes toolbarLayoutResID: Int) {
        val beginTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        toolbarHelper =
            ToolbarHelper(
                toolbarLayoutResID,
                this
            )
        beginTransaction.replace(R.id.toolbarLayoutContent, toolbarHelper)
        beginTransaction.commit()
    }

    open fun initLayoutRes(): Int {
        return R.layout.layout_decor_view
    }

    open fun toolbarLayoutRes(): Int {
        return R.layout.layout_init_toobar_view
    }

}