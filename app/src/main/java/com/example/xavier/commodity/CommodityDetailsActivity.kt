package com.example.xavier.commodity

import android.os.Bundle
import android.os.Handler
import com.example.xavier.R
import com.example.xavier.app.api.ConstantPool.Companion.COMMODITY
import com.example.xavier.app.api.ConstantPool.Companion.DETAIL
import com.example.xavier.app.api.ConstantPool.Companion.RECOMMEND
import com.example.xavier.app.api.ConstantPool.Companion.WORD_OF_MOUTH
import com.example.xavier.base.viewstratum.activity.SimpleDecorViewActivity
import kotlinx.android.synthetic.main.commodity_details_toolbar.*

class CommodityDetailsActivity : SimpleDecorViewActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commodity_details)
    }

    override fun init() {
        tabLayout.addTab(tabLayout.newTab().setText(COMMODITY))
        tabLayout.addTab(tabLayout.newTab().setText(WORD_OF_MOUTH))
        tabLayout.addTab(tabLayout.newTab().setText(DETAIL))
        tabLayout.addTab(tabLayout.newTab().setText(RECOMMEND))
    }

    override fun onPrepare() {
        setDefaultTitle("商品详情")
    }
}