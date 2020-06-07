package com.example.xavier.main.home.container

import android.os.Bundle
import com.example.xavier.app.api.FieldConstant.Companion.CID
import com.example.xavier.app.api.FieldConstant.Companion.PAGE_ITEM
import com.example.xavier.base.viewstratum.fragment.BaseLazyFragment

abstract class HomeContainerFragment :
    BaseLazyFragment<TabContract.Presenter<TabContract.View>>(), TabContract.View {

    private var cid: Int = 0
    protected var pageItem: Int = 0

    protected var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cid = it.getInt(CID)
            pageItem = it.getInt(PAGE_ITEM)
        }
    }

    override fun init() {
        presenter.let {
            presenter?.slideShow(cid)
            if (pageItem == 0) {
                presenter?.homeAd()
                presenter?.choiceShop()
            } else {
                presenter?.subClass(cid)
            }
            presenter?.hot(page)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            cid: Int,
            pagItem: Int
        ) =
            TabFragment().apply {
                arguments = Bundle().apply {
                    putInt(CID, cid)
                    putInt(PAGE_ITEM, pagItem)
                }
            }
    }
}
