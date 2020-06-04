package com.example.xavier.commodity

import android.os.Bundle
import com.example.xavier.R
import com.example.xavier.app.api.ConstantPool.Companion.COMMODITY
import com.example.xavier.app.api.ConstantPool.Companion.DETAIL
import com.example.xavier.app.api.ConstantPool.Companion.RECOMMEND
import com.example.xavier.app.api.ConstantPool.Companion.WORD_OF_MOUTH
import com.example.xavier.base.viewstratum.activity.SelfishnessActivity
import kotlinx.android.synthetic.main.commodity_details_toolbar.*

class CommodityDetailsActivity : SelfishnessActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commodity_details)
    }

    override fun immersionBar() {
        super.immersionBar()
        statusbar()
    }

    override fun init() {

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
}