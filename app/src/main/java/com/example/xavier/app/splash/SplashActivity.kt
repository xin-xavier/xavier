package com.example.xavier.app.splash

import android.os.Bundle
import com.blankj.utilcode.util.SPStaticUtils
import com.example.xavier.MainActivity
import com.example.xavier.app.api.ConstantTransmit.Companion.SPLASH
import com.example.xavier.base.viewstratum.activity.SimpleActivty

class SplashActivity : SimpleActivty() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (SPStaticUtils.getBoolean(SPLASH)) {
            intent(MainActivity::class.java)
        } else {
            SPStaticUtils.put(SPLASH, true)
            intent(GuideActivity::class.java)
        }
        // 去掉 Android Activity 自带的原生进入进出动画
        // https://blog.csdn.net/wolfking0608/article/details/78967608
        activity.overridePendingTransition(0, 0)
        finish()
    }

    override fun init() {}

}
