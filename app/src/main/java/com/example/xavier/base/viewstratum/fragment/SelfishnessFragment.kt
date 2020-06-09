package com.example.xavier.base.viewstratum.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import com.blankj.utilcode.util.BarUtils
import com.example.xavier.R
import com.example.xavier.base.viewstratum.presentation.OnPrepareListener
import com.example.xavier.widght.helper.AppbarHelper

abstract class SelfishnessFragment : SimpleFragment(), OnPrepareListener {

    protected lateinit var inflater: LayoutInflater
    protected lateinit var toolbarHelper: AppbarHelper

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

    override fun onPrepare() {
        rootView.let {
            rootView?.findViewById<FrameLayout>(R.id.toolbarLayoutContent)?.layoutParams?.height=toolbarHelper.rootHeight
            val appbarHeight: Int = BarUtils.getStatusBarHeight() + toolbarHelper.rootHeight
            //LogUtils.i(TAG+": appbarHeight = "+appbarHeight)
            view?.findViewById<FrameLayout>(R.id.appbarLayout)?.layoutParams?.height = appbarHeight
        }
    }

    private fun initLayout(@LayoutRes layoutResID: Int, container: ViewGroup?): View {
        val view = inflater.inflate(layoutResID, container, false)
        setToolbar(view,toolbarLayoutRes())
        return setContentView(view, contentLayoutRes(), container)
    }

    open fun setToolbar(
        view: View,
        @LayoutRes toolbarLayoutResID: Int
    ) {
        val beginTransaction = childFragmentManager.beginTransaction()
        toolbarHelper =
            AppbarHelper(
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

}