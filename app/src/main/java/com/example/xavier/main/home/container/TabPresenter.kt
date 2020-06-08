package com.example.xavier.main.home.container

import com.example.xavier.app.http.observer.HttpDefaultObserver
import com.example.xavier.bean.result.*
import com.example.xavier.base.presenter.BasePresenter
import com.example.xavier.http.client.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TabPresenter(view: TabContract.View) : BasePresenter<TabContract.View>(view),
    TabContract.Presenter<TabContract.View> {

    override fun slideShow(pid: Int) {
        RetrofitHelper.getApiService()
            .slideShow(pid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<List<SlideShowResult>>() {
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }
                override fun onSuccess(t: List<SlideShowResult>) {
                    view?.showSlideShow(t)
                }
                override fun onError(errorMsg: String) {
                    view?.slideShowError(errorMsg)
                }
            })
    }

    override fun homeAd() {
        RetrofitHelper.getApiService()
            .homeAd()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<List<HomeAdResult>>() {
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: List<HomeAdResult>) {
                    view?.showAd(t)
                }

                override fun onError(errorMsg: String) {
                    view?.adError(errorMsg)
                }
            })
    }

    override fun subClass(pid: Int) {
        RetrofitHelper.getApiService()
            .subClass(pid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<List<SubClassResult>>() {
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: List<SubClassResult>) {
                    view?.showSubClass(t)
                }

                override fun onError(errorMsg: String) {
                    view?.subClassError(errorMsg)
                }
            })
    }


    override fun choiceShop() {
        RetrofitHelper.getApiService()
            .choiceShop()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<List<List<ChoiceShopResult>>>() {
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: List<List<ChoiceShopResult>>) {
                    view?.showChoiceShop(t)
                }

                override fun onError(errorMsg: String) {
                    view?.choiceShopError(errorMsg)
                }
            })
    }


    override fun hot(page: Int) {
        RetrofitHelper.getApiService()
            .hot(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<HotResult>() {
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: HotResult) {
                    view?.showHot(t)
                }

                override fun onError(errorMsg: String) {
                    view?.hotError(errorMsg)
                }
            })
    }


}