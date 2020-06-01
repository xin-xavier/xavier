package com.example.xavier

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.CrashUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.example.xavier.app.api.ConstantPool.Companion.PUSH_STATUS
import com.example.xavier.app.notification.NotificationChannels
import com.example.xavier.http.client.XavierHttpClient
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

class XavierApplacation : Application() {

    private var refWatcher: RefWatcher? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        refWatcher = setupLeakCanary()

        // crash
        CrashUtils.init()

        if (!SPStaticUtils.contains(PUSH_STATUS)) {
            SPStaticUtils.put(PUSH_STATUS, true)
        }

        XavierHttpClient.getInstance().init(instance)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannels.createAllNotificationChannels(instance)
        }
    }

    // Error: null, Cannot fit requested classes in a single dex file (# methods: 66116 > 65536)
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }

    init {
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer { _, layout ->
            //开始设置全局的基本参数（可以被下面的DefaultRefreshHeaderCreator覆盖）
            //设置反弹时间
            //layout.setReboundDuration(1000)
            //设置反弹插值器
            //layout.setReboundInterpolator(DropBounceInterpolator())
            //设置页脚高度
            //layout.setFooterHeight(100)
            //设置加载时禁用内容
            //layout.setDisableContentWhenLoading(false)
            layout.setPrimaryColorsId(R.color.initial, R.color.black) //全局设置主题颜色
        }
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            ClassicsHeader(context)
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ -> //指定为经典Footer，默认是 BallPulseFooter
            ClassicsFooter(context)
        }
    }

    companion object {
        lateinit var instance: XavierApplacation
            private set

        fun getRefWatcher(context: Context): RefWatcher? {
            val treeApplication =
                context.applicationContext as XavierApplacation
            return treeApplication.refWatcher
        }
    }
}