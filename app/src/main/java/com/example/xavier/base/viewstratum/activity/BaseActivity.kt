package com.example.xavier.base.viewstratum.activity

import com.example.xavier.base.presenter.IBasePresenter

abstract class BaseActivity<P: IBasePresenter<*>>  : SimpleActivty(){

    protected var presenter:P? = null

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        presenter = createPresenter()
        presenter?.let {
            lifecycle.addObserver(it)
        }
    }

    protected abstract fun createPresenter(): P?

}