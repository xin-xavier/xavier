package com.example.xavier.commodity.contract

import com.example.xavier.base.presenter.IBasePresenter
import com.example.xavier.base.viewstratum.presentation.ViewPresentation
import com.example.xavier.bean.result.dataclass.DetailsData

interface DetailsContract {

    interface View : ViewPresentation {
        fun showDetails(detailsData: DetailsData)
    }

    interface Presenter<V> : IBasePresenter<View> {
        fun details(gid: Int)
    }

}