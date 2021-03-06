package com.example.xavier.base.viewstratum.fragment

import android.os.Bundle
import com.example.xavier.base.presenter.IBasePresenter

abstract class BaseWithBarFragment<P : IBasePresenter<*>> : SimpleWithBarFragment() {

    protected var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter?.let { lifecycle.addObserver(it) }
    }

    protected abstract fun createPresenter(): P?

}