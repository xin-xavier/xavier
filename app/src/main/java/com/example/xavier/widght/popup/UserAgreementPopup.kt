package com.example.xavier.widght.popup

import android.content.Context
import android.view.Gravity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.blankj.utilcode.util.SPStaticUtils
import com.example.xavier.R
import com.example.xavier.app.api.ConstantTransmit
import com.example.xavier.http.DNSConfig
import com.example.xavier.http.url.ConstantUrl
import com.example.xavier.widght.LollipopFixedWebView
import razerdp.basepopup.BasePopupWindow

class UserAgreementPopup(private val context: Context) : BasePopupWindow(context),View.OnClickListener {

    override fun onCreateContentView(): View {
        return createPopupById(R.layout.user_agreement)
    }

    init {
        popupGravity = Gravity.CENTER
        setOutSideDismiss(false)
        setBackPressEnable(false)

        val webView: LollipopFixedWebView = findViewById(R.id.webView)
        val disagree: TextView = findViewById(R.id.disagree)
        val agree: TextView = findViewById(R.id.agree)
        disagree.setOnClickListener(this)
        agree.setOnClickListener(this)
        webView.loadUrl(
            DNSConfig.getInstance().apiServerUrl +
                    ConstantUrl.variousPrivacy
        )
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.disagree ->  dismiss()
            R.id.agree -> {
                SPStaticUtils.put(
                    ConstantTransmit.USER_AGREEMENT,
                    true
                )
                dismiss()
            }
            else -> {
            }
        }
    }

}