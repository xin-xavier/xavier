package com.example.xavier.commodity.contract

import com.example.xavier.base.presenter.IBasePresenter
import com.example.xavier.base.viewstratum.presentation.ViewPresentation
import com.example.xavier.bean.result.DetailsResult

interface DetailsContract {

    interface View : ViewPresentation {
        fun showDetails(detailsResult: DetailsResult)
    }

    interface Presenter<V> : IBasePresenter<View> {
        fun details(gid: Int)
    }

}