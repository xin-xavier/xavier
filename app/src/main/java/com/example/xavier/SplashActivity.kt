package com.example.xavier

import android.content.Intent
import android.os.Bundle
import com.example.xavier.base.viewstratum.activity.SimpleActivty

class SplashActivity : SimpleActivty() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        // 去掉 Android Activity 自带的原生进入进出动画
        // https://blog.csdn.net/wolfking0608/article/details/78967608
        activity.overridePendingTransition(0, 0);
        finish()
    }
    override fun init() {}
}
