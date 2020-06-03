package com.example.xavier.utils.helper

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.xavier.R
import com.example.xavier.app.api.ConstantPool
import com.example.xavier.app.api.ConstantPool.Companion.APP_NAME
import com.example.xavier.base.viewstratum.fragment.SimpleFragment
import com.example.xavier.base.viewstratum.presentation.OnPrepareListener


class ToolbarHelper : SimpleFragment, View.OnClickListener {

    var rootHeight : Int=0

    private lateinit var onPrepareListener: OnPrepareListener

    constructor() : super() {}

    constructor(
        contentLayoutId: Int,
        onPrepareListener: OnPrepareListener
    ) : super(contentLayoutId) {
        this.onPrepareListener = onPrepareListener
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        rootView.let {
            rootHeight = rootView?.layoutParams?.height!!
        }
        if (contentLayoutId != 0) {
            when (contentLayoutId) {
                R.layout.layout_init_toobar_view -> {
                    Log.w(TAG, "onViewCreated: contentLayoutId = $contentLayoutId")
                    val returnPager = view.findViewById<ImageView>(R.id.returnPager)
                    val navMenu = view.findViewById<ImageView>(R.id.navMenu)
                    returnPager.setOnClickListener(this)
                    navMenu.setOnClickListener(this)
                    // 这种方法也能将点击事件回调到指定页面里
                    // 但最好使用下面的回调
                    /* if (activity is MainActivity) {
                         returnPager.setOnClickListener(activity as MainActivity)
                     }*/
                }
                R.layout.layout_searchbox_btn -> {
                }
                else -> {
                }
            }
        }
        /**
         * 在默认的判断和监听后面回调,如果相应的 activity 设置监听
         * 将以 activity 里面设置的监听为准
         */
        onPrepareListener.onPrepare()
    }

    override fun init() {}

    override fun onClick(v: View?) {
        val id = v?.id
        when (id) {
            R.id.returnPager -> {
                activity?.finish()
            }
            R.id.navMenu -> {
                ToastUtils.showShort(APP_NAME)
            }
            else -> {
            }
        }
    }

}
