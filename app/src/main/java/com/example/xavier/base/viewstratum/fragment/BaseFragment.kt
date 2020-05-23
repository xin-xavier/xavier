package com.example.xavier.base.viewstratum.fragment

import android.os.Bundle
import com.example.xavier.base.presenter.IBasePresenter

abstract class BaseFragment<P : IBasePresenter<*>> : SimpleFragment {

    protected var presenter: P? = null

    constructor() : super() {}
    constructor(contentLayoutId: Int) : super(contentLayoutId) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter?.let { lifecycle.addObserver(it) }
    }

    protected abstract fun createPresenter(): P?

}