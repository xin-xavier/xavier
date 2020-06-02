package com.example.xavier

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.xavier.app.api.ConstantPool.Companion.ICON_SELECT_IDS
import com.example.xavier.app.api.ConstantPool.Companion.ICON_UNSELECT_IDS
import com.example.xavier.app.api.ConstantPool.Companion.TABS
import com.example.xavier.base.viewstratum.activity.SimpleActivty
import com.example.xavier.base.viewstratum.activity.SimpleDecorViewActivity
import com.example.xavier.bean.entity.TabEntity
import com.example.xavier.main.HomeFragment
import com.example.xavier.utils.Utils
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_actionbar_view.*
import java.util.*

class MainActivity : SimpleActivty() {

    private val tabEntities = ArrayList<CustomTabEntity>()

    private var pressedTime: Long = 0
    private var pageItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun init() {
        Utils.instance?.showUserAgreement(context)

        // 检查版本更新
        //Utils.instance?.versionUpdating(context)

        initData()

        settingUpTheNavigator()
    }

    /*override fun onPrepare() {
        if (isDefaultBar()) {
            val returnPager =
                toolbarManagerFragment.rootView.findViewById<ImageView>(R.id.returnPager)
            val toolbarTitle =
                toolbarManagerFragment.rootView.findViewById<TextView>(R.id.toolbarTitle)
            toolbarTitle.text = TABS[pageItem]
            returnPager.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun toolbarLayoutRes(): Int {
        return when (pageItem) {
            0 -> {
                //appbarBg.setImageResource(R.mipmap.sona_buvelle)
                R.layout.layout_no_space_view
            }
            else -> {
                appbarBg.setImageResource(R.color.white)
                R.layout.layout_init_toobar_view
            }
        }
    }*/

    private fun initData() {
        for (tab in TABS) {
            val indexOf = TABS.indexOf(tab)
            tabEntities.add(
                TabEntity(
                    tab,
                    ICON_SELECT_IDS[indexOf],
                    ICON_UNSELECT_IDS[indexOf]
                )
            )
        }
    }

    private fun settingUpTheNavigator() {
        val pagerAdapter = TabsPagerAdapter2(this)
        carousel.adapter = pagerAdapter
        carousel.isUserInputEnabled = false
        navigator.setTabData(tabEntities)

        navigator.setOnTabSelectListener(object : OnTabSelectListener {
            // 点击时运行
            override fun onTabSelect(position: Int) {
                carousel.currentItem = position
            }

            // 选项卡选中时运行
            override fun onTabReselect(position: Int) {}
        })

        // viewpager2
        carousel.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position in 2..3) {
                    floatingActionButton.visibility = View.INVISIBLE
                }
                pageItem = position
                navigator.currentTab = position
                //setToolbar(toolbarLayoutRes())
            }
        })
    }


    override fun onBackPressed() {
        if (carousel.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            val nowTime = System.currentTimeMillis() //获取第一次按键时间
            if (nowTime - pressedTime > 2000) { //比较两次按键时间差
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                pressedTime = nowTime
            } else { //退出程序
                /*finish()
                System.exit(0)*/
                super.onBackPressed()
            }
        } else {
            // Otherwise, select the previous step.
            carousel.currentItem = 0
        }
    }


    private inner class TabsPagerAdapter2(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = TABS.size
        override fun createFragment(position: Int): Fragment =
            when (position) {
                0 -> {
                    HomeFragment.newInstance(TABS[position])
                }
                1 -> {
                    BlankFragment.newInstance("", "")
                }
                2 -> {
                    BlankFragment.newInstance("", "")
                }
                3 -> {
                    BlankFragment.newInstance("", "")
                }
                else -> {
                    BlankFragment.newInstance("", "")
                }
            }
    }


}
