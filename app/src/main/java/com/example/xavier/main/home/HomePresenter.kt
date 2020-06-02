package com.example.xavier.main.home

import com.example.prepotency.app.http.observer.HttpDefaultObserver
import com.example.prepotency.bean.result.TopClassResult
import com.example.xavier.base.presenter.BasePresenter
import com.example.xavier.http.client.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter (view: HomeContract.View): BasePresenter<HomeContract.View>(view)
    , HomeContract.Presenter<HomeContract.View>{
    override fun topClass() {
        RetrofitHelper.getApiService()
            .topClass()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<List<TopClassResult>>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }
                override fun onSuccess(t: List<TopClassResult>) {
                    view?.showTopClass(t)
                }
                override fun onError(errorMsg: String) {
                    view?.showError(errorMsg)
                }
            })
    }
}