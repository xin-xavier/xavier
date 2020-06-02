package com.example.xavier.main.home.container

import com.example.prepotency.bean.result.*
import com.example.xavier.base.presenter.IBasePresenter
import com.example.xavier.base.viewstratum.presentation.ViewPresentation

interface TabContract {

    interface View : ViewPresentation {
        fun showSlideShow(slideList: List<SlideShowResult>)
        fun showAd(adList: List<HomeAdResult>)
        fun showSubClass(subclassList : List<SubClassResult>)
        fun showChoiceShop(choiceLists : List<List<ChoiceShopResult>>)
        fun showHot(hotResult: HotResult)
    }

    interface Presenter<T> : IBasePresenter<View> {
        fun slideShow(pid: Int)
        fun homeAd()
        fun subClass(pid: Int)
        fun choiceShop()
        fun hot(page: Int)
    }
}