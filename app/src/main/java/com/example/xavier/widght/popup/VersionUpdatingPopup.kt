package com.example.xavier.widght.popup

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.xavier.R
import com.example.xavier.utils.subutil.AppStoreUtils
import razerdp.basepopup.BasePopupWindow

class VersionUpdatingPopUp(private val context: Context) : BasePopupWindow(context),
    View.OnClickListener {

    private var image: ImageView? = null
    private var title: TextView? = null
    private var content: TextView? = null
    private var upgradeBtn: TextView? = null

    // onCreateContentView() 是在 BasePopupWindow 的构造里调用的
    // 也就是说是在 VersionUpdatingPopUp() 主构造器中的调用的
    // 而 init{} 也被包含在主构造器调用
    // 调用顺序为
    // 主构造器 -> init{} -> 次构造器
    override fun onCreateContentView(): View {
        return createPopupById(R.layout.popup_version_updating)
    }

    init {
        popupGravity = Gravity.CENTER
        findbyid()
    }

    private fun findbyid() {
        image = findViewById(R.id.image)
        title = findViewById(R.id.title)
        content = findViewById(R.id.content)
        upgradeBtn = findViewById(R.id.upgradeBtn)
        upgradeBtn!!.setOnClickListener(this)
    }

    fun setContent(content: String?) {
        this.content!!.text = content
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.upgradeBtn -> {
                val intent = AppStoreUtils.getAppStoreIntent()
                intent.let {
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
            }
            else -> {
            }
        }
    }



}
