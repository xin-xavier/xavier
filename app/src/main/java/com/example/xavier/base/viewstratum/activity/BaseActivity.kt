package com.example.xavier.base.viewstratum.activity

import com.example.xavier.base.presenter.IBasePresenter

abstract class BaseActivity<P: IBasePresenter<*>>  : SimpleActivity(){

    protected var presenter:P? = null

    override fun setContentView(layoutResID: Int) {
        presenter = createPresenter()
        presenter?.let {
            lifecycle.addObserver(it)
        }
        super.setContentView(layoutResID)
    }

    protected abstract fun createPresenter(): P?

}