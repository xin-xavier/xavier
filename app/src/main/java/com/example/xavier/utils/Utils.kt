package com.example.xavier.utils

import android.content.Context
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.example.prepotency.app.http.observer.HttpDefaultObserver
import com.example.prepotency.bean.result.VersionUpdatingResult
import com.example.xavier.app.api.ConstantTransmit.Companion.USER_AGREEMENT
import com.example.xavier.app.api.FieldConstant.Companion.MODEL
import com.example.xavier.app.api.FieldConstant.Companion.VERSION
import com.example.xavier.http.client.RetrofitHelper
import com.example.xavier.widght.popup.UserAgreementPopup
import com.example.xavier.widght.popup.VersionUpdatingPopUp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

object Utils {

    @Volatile
    private var singleton: Utils? = null
    val instance: Utils?
        get() {
            if (singleton == null) {
                synchronized(Utils::class.java) {
                    if (singleton == null) {
                        singleton = Utils
                    }
                }
            }
            return singleton
        }

    // 版本更新
    fun versionUpdating(context: Context) {
        val map: MutableMap<String, String> =
            HashMap()
        map[MODEL] = DeviceUtils.getManufacturer()
        map[VERSION] = AppUtils.getAppVersionName()
        RetrofitHelper.getApiService()
            .versionUpdating(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<VersionUpdatingResult>() {
                override fun disposable(d: Disposable) {
                }
                override fun onSuccess(t: VersionUpdatingResult) {
                    val enforce: Int = t.getEnforce()
                    val content: String = t.getContent()
                    if (enforce == 0) {
                        // 强制
                        val versionUpdatingPopUp =
                            VersionUpdatingPopUp(
                                context
                            )
                        // 点击popupwindow背景部分不想让popupwindow隐藏怎么办
                        versionUpdatingPopUp.setOutSideDismiss(false)
                        // 如何点击back键不关闭BasePopup
                        versionUpdatingPopUp.setBackPressEnable(false)
                        versionUpdatingPopUp.setContent(content)
                        versionUpdatingPopUp.showPopupWindow()
                    } else if (enforce == 1) {
                        val versionUpdatingPopUp =
                            VersionUpdatingPopUp(
                                context
                            )
                        versionUpdatingPopUp.setContent(content)
                        versionUpdatingPopUp.showPopupWindow()
                    }
                }
                override fun onError(errorMsg: String) {
                }
            })
    }

    // 用户协议弹窗
    fun showUserAgreement(context: Context){
        if(SPStaticUtils.getBoolean(USER_AGREEMENT)){
            return
        }
        val popup = UserAgreementPopup(context)
        popup.showPopupWindow()
    }
}