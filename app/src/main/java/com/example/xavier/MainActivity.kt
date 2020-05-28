package com.example.xavier

import android.os.Bundle
import com.example.xavier.base.viewstratum.activity.SimpleDecorViewActivity

class MainActivity : SimpleDecorViewActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun init() {
    }

    override fun onPrepare() {
        setDefaultTitle(TAG)
    }

}
