package com.example.xavier.commodity

import android.os.Bundle
import com.example.xavier.R
import com.example.xavier.app.api.ConstantPool.Companion.COMMODITY
import com.example.xavier.app.api.ConstantPool.Companion.DETAIL
import com.example.xavier.app.api.ConstantPool.Companion.RECOMMEND
import com.example.xavier.app.api.ConstantPool.Companion.WORD_OF_MOUTH
import com.example.xavier.app.api.FieldConstant.Companion.GID
import com.example.xavier.base.viewstratum.activity.BaseSelfishnessActivity
import com.example.xavier.bean.result.dataclass.DetailsData
import com.example.xavier.commodity.contract.DetailsContract
import com.example.xavier.commodity.contract.DetailsPresenter
import com.example.xavier.utils.XavierLogUtils
import kotlinx.android.synthetic.main.commodity_details_toolbar.*

class CommodityDetailsActivity : BaseSelfishnessActivity<DetailsContract.Presenter<DetailsContract.View>>() ,DetailsContract.View {

    private var gid=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commodity_details)
    }

    override fun immersionBar() {
        super.immersionBar()
        statusbar()
    }

    override fun showDetails(detailsData: DetailsData) {
        XavierLogUtils.longInfo(TAG,detailsData.toString())
    }

    override fun showError(error: String) {

    }

    override fun init() {
        intent.apply {
            val extras = extras
            gid=extras?.getInt(GID)!!
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.let { presenter?.details(gid) }
    }

    override fun toolbarLayoutRes(): Int {
        return R.layout.commodity_details_toolbar
    }

    override fun onPrepare() {
        tabLayout.addTab(tabLayout.newTab().setText(COMMODITY))
        tabLayout.addTab(tabLayout.newTab().setText(WORD_OF_MOUTH))
        tabLayout.addTab(tabLayout.newTab().setText(DETAIL))
        tabLayout.addTab(tabLayout.newTab().setText(RECOMMEND))


        //toolbarLayout.background =ResourceUtils.getDrawable(R.drawable.b_14100e_r4)
        //toolbarLayout.background.alpha=225
        //toolbarLayout.background.alpha=0

        //toolbarHelper.rootView?.background = ResourceUtils.getDrawable(R.drawable.b_14100e_r4)
        //toolbarHelper.rootView?.background?.alpha=0

        toolbarLayout.getBackground().mutate().setAlpha(0)
    }

    override fun createPresenter(): DetailsContract.Presenter<DetailsContract.View>? {
        return DetailsPresenter(this)
    }
}