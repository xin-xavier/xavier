package com.example.xavier.base.viewstratum.fragment

import com.example.xavier.base.presenter.IBasePresenter
import com.example.xavier.base.viewstratum.presentation.UIPresentationLazyLoad

abstract class BaseLazyFragment<P : IBasePresenter<*>>() : BaseFragment<P>(),
    UIPresentationLazyLoad {

    protected var isFirstVisible = true

    override fun onResume() {
        super.onResume()
        if(isFirstVisible){
            isFirstVisible=false
            onLazyLoad()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isFirstVisible=true
    }

}