package com.example.xavier.base.presenter

import com.example.xavier.base.viewstratum.presentation.ViewPresentation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : ViewPresentation>(view: V) :
    IBasePresenter<V> {

    protected var view: V? = view
    private var compositeDisposable: CompositeDisposable? = null

    init {
        compositeDisposable = CompositeDisposable()
    }

    override fun onCreate() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
        compositeDisposable?.clear()
    }

    protected fun addSubscribe(disposable: Disposable) {
        compositeDisposable?.add(disposable)
    }

}

