package com.example.xavier.main.home

import com.example.prepotency.bean.result.TopClassResult
import com.example.xavier.base.presenter.IBasePresenter
import com.example.xavier.base.viewstratum.presentation.ViewPresentation

interface HomeContract{

    interface View:ViewPresentation{
        fun showTopClass(list:List<TopClassResult>)
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun topClass()
    }
}