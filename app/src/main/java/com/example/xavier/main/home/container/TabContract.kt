package com.example.xavier.main.home.container

import com.example.prepotency.bean.result.*
import com.example.xavier.base.presenter.IBasePresenter
import com.example.xavier.base.viewstratum.presentation.ViewPresentation

interface TabContract {

    interface View : ViewPresentation {
        fun showSlideShow(slideList: List<SlideShowResult>)
        fun slideShowError(errorMsg: String)
        fun showAd(adList: List<HomeAdResult>)
        fun adError(errorMsg: String)
        fun showSubClass(subclassList : List<SubClassResult>)
        fun subClassError(errorMsg: String)
        fun showChoiceShop(choiceLists : List<List<ChoiceShopResult>>)
        fun choiceShopError(errorMsg: String)
        fun showHot(hotResult: HotResult)
        fun hotError(errorMsg: String)
    }

    interface Presenter<V> : IBasePresenter<View> {
        fun slideShow(pid: Int)
        fun homeAd()
        fun subClass(pid: Int)
        fun choiceShop()
        fun hot(page: Int)
    }
}