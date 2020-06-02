package com.example.xavier.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.prepotency.bean.result.TopClassResult
import com.example.xavier.R
import com.example.xavier.app.api.FieldConstant.Companion.PAGE_NAME
import com.example.xavier.base.viewstratum.fragment.BaseSelfishnessFragment
import com.example.xavier.main.home.HomeContract
import com.example.xavier.main.home.HomePresenter
import com.example.xavier.main.home.container.HomeContainerFragment
import com.example.xavier.main.home.container.TabFragment
import kotlinx.android.synthetic.main.fragment_home.*
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView

class HomeFragment : BaseSelfishnessFragment<HomeContract.Presenter<HomeContract.View>>(),
    HomeContract.View {

    private var pageName: String? = null

    override fun getParameter() {
        super.getParameter()
        pageName = arguments?.getString(PAGE_NAME)
    }

    override fun onPrepare() {
    }

    override fun init() {
        presenter.let { presenter?.topClass() }
    }

    override fun contentLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun showTopClass(list: List<TopClassResult>) {
        setTopClass(list)
    }

    private fun setTopClass(list: List<TopClassResult>) {
        // 缓存
        //SPStaticUtils.put(TOP_CLASS, GsonUtils.toJson(list))
        val pagerAdapter: BlankPagerAdapter2 = BlankPagerAdapter2(this, list)
        viewPager.adapter = pagerAdapter
        val commonNavigator = CommonNavigator(context)
        commonNavigator.isAdjustMode = false
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            @SuppressLint("InflateParams")
            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                val commonPagerTitleView = CommonPagerTitleView(context)
                val customLayout: View = layoutInflater.inflate(R.layout.navigator_item, null)
                val title = customLayout.findViewById<TextView>(R.id.title)
                val indicator = customLayout.findViewById<ImageView>(R.id.indicator)
                title.text = list[index].name
                commonPagerTitleView.setContentView(customLayout)
                commonPagerTitleView.onPagerTitleChangeListener =
                    object : CommonPagerTitleView.OnPagerTitleChangeListener {

                        override fun onSelected(index: Int, totalCount: Int) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                title.setTextAppearance(R.style.SkinCompatTextAppearance_titleSelectColor)
                            } else {
                                title.setTextAppearance(
                                    context,
                                    R.style.SkinCompatTextAppearance_titleSelectColor
                                )
                            }
                            indicator.visibility = View.VISIBLE
                        }

                        override fun onDeselected(index: Int, totalCount: Int) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                title.setTextAppearance(R.style.SkinCompatTextAppearance_titleUnSelectColor)
                            } else {
                                title.setTextAppearance(
                                    context,
                                    R.style.SkinCompatTextAppearance_titleSelectColor
                                )
                            }
                            indicator.visibility = View.GONE
                        }

                        override fun onLeave(
                            index: Int,
                            totalCount: Int,
                            leavePercent: Float,
                            leftToRight: Boolean
                        ) {
                            title.setScaleX(1.4f + (1.1f - 1.4f) * leavePercent)
                            title.setScaleY(1.4f + (1.1f - 1.4f) * leavePercent)
                        }

                        override fun onEnter(
                            index: Int,
                            totalCount: Int,
                            enterPercent: Float,
                            leftToRight: Boolean
                        ) {
                            title.setScaleX(1.1f + (1.4f - 1.1f) * enterPercent)
                            title.setScaleY(1.1f + (1.4f - 1.1f) * enterPercent)
                        }
                    }

                commonPagerTitleView.setOnClickListener {
                    viewPager.currentItem = index
                }

                return commonPagerTitleView
            }

            override fun getCount(): Int {
                return list.size
            }

            override fun getIndicator(context: Context?): IPagerIndicator? {
                return null
            }

        }
        magicIndicator.setNavigator(commonNavigator)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                magicIndicator.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                magicIndicator.onPageSelected(position)
            }
        })
        //viewPager.offscreenPageLimit = 6
    }

    override fun showError(error: String) {
        Log.e(TAG, "onError: $error");
    }

    private inner class BlankPagerAdapter2(
        fragmentActivity: HomeFragment,
        var list: List<TopClassResult>
    ) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): TabFragment =
            HomeContainerFragment.newInstance(list.get(position).id, position)

        override fun getItemCount(): Int = list.size
    }

    override fun createPresenter(): HomeContract.Presenter<HomeContract.View>? {
        return HomePresenter(this)
    }

    companion object {
        @JvmStatic
        fun newInstance(pageName: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(PAGE_NAME, pageName)
                }
            }
    }
}