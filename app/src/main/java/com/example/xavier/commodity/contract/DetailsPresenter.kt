package com.example.xavier.commodity.contract

import com.example.xavier.app.http.observer.HttpDefaultObserver
import com.example.xavier.base.presenter.BasePresenter
import com.example.xavier.bean.result.dataclass.DetailsData
import com.example.xavier.http.client.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailsPresenter(view: DetailsContract.View) : BasePresenter<DetailsContract.View>(view)
    , DetailsContract.Presenter<DetailsContract.View> {
    override fun details(gid: Int) {
        RetrofitHelper.getApiService()
            .details(gid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<DetailsData>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: DetailsData) {
                    view?.showDetails(t)
                }

                override fun onError(errorMsg: String) {
                   view?.showError(errorMsg)
                }

            })
    }
}