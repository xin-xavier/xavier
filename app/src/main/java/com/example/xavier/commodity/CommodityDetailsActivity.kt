package com.example.xavier.commodity

import android.os.Bundle
import android.util.Log
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.xavier.R
import com.example.xavier.adapter.page.BannerHaveVideoAdapter
import com.example.xavier.app.api.ConstantPool.Companion.COMMODITY
import com.example.xavier.app.api.ConstantPool.Companion.DETAIL
import com.example.xavier.app.api.ConstantPool.Companion.RECOMMEND
import com.example.xavier.app.api.ConstantPool.Companion.WORD_OF_MOUTH
import com.example.xavier.app.api.FieldConstant.Companion.GID
import com.example.xavier.base.viewstratum.activity.BaseDecorViewActivity
import com.example.xavier.base.viewstratum.activity.BaseSelfishnessActivity
import com.example.xavier.bean.result.dataclass.DetailsData
import com.example.xavier.bean.viewholder.VideoHolder
import com.example.xavier.commodity.contract.DetailsContract
import com.example.xavier.commodity.contract.DetailsPresenter
import com.example.xavier.utils.XavierLogUtils
import com.example.xavier.widght.NumIndicator
import com.example.xavier.widght.helper.ViewGroupHelper
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.listener.OnPageChangeListener
import kotlinx.android.synthetic.main.details_toolbar.*

class CommodityDetailsActivity :
    BaseDecorViewActivity<DetailsContract.Presenter<DetailsContract.View>>(),
    DetailsContract.View {

    private var gid = -1
    val imageList = arrayListOf<String>()
    private lateinit var bannerAdapter:BannerHaveVideoAdapter

    var player: StandardGSYVideoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commodity_details)
    }

    override fun init() {
        intent.apply {
            val extras = extras
            gid = extras?.getInt(GID)!!
        }
        presenter.let { presenter?.details(gid) }
    }

    override fun onPrepare() {
        ViewGroupHelper.setTopMargin(returnPager, BarUtils.getStatusBarHeight())

        //bannerAdapter=BannerHaveVideoAdapter(context, imageList)

        /*banner.adapter=bannerAdapter
        banner.setIndicator(NumIndicator(this))
            .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
            .addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    Log.e(TAG, "onPageScrolled: position:$position")
                    if (player == null) {
                        val viewHolder =
                            banner.adapter.viewHolder
                        if (viewHolder is VideoHolder) {
                            val holder: VideoHolder = viewHolder as VideoHolder
                            player = holder.player
                        }
                        return
                    }
                    if (position != 0) {
                        player?.onVideoReset()
                    }
                }

                override fun onPageSelected(position: Int) {
                }

            })*/


        tabLayout.addTab(tabLayout.newTab().setText(COMMODITY))
        tabLayout.addTab(tabLayout.newTab().setText(WORD_OF_MOUTH))
        tabLayout.addTab(tabLayout.newTab().setText(DETAIL))
        tabLayout.addTab(tabLayout.newTab().setText(RECOMMEND))
    }

    override fun showDetails(detailsData: DetailsData) {
        XavierLogUtils.longInfo(TAG, detailsData.toString())
        val rows = detailsData.rows
        val images =rows.images
        val video = rows.video
        if(!StringUtils.isEmpty(video)){
            imageList.add(video)
        }
        if(images!=null && images.size >0){
            imageList.addAll(images)
        }
       // bannerAdapter.notifyDataSetChanged()
    }

    override fun showError(error: String) {
        ToastUtils.showShort(error)
    }

    override fun toolbarLayoutRes(): Int {
        return R.layout.commodity_toolbar
    }

    override fun createPresenter(): DetailsContract.Presenter<DetailsContract.View>? {
        return DetailsPresenter(this)
    }

}