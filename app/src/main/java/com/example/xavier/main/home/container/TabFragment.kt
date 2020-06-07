package com.example.xavier.main.home.container

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ScreenUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.prepotency.bean.result.*
import com.example.xavier.R
import com.example.xavier.adapter.list.ProductListAdapter
import com.example.xavier.adapter.page.BannerImageAdapter
import com.example.xavier.app.api.FieldConstant.Companion.GID
import com.example.xavier.bean.event.PassableFloatingActionButtonState
import com.example.xavier.bean.event.StickEvent
import com.example.xavier.commodity.CommodityDetailsActivity
import com.example.xavier.utils.GlideEngineLoging
import com.example.xavier.widght.helper.recyclerview.XavierGridItemDecoration
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter
import kotlinx.android.synthetic.main.fragment_tab.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class TabFragment : HomeContainerFragment() {

    private val rowsList = ArrayList<HotResult.RowsBean>()
    private lateinit var adapter: ProductListAdapter

    private var total = 1

    private var totalDy = 0
    private var fabStatus = View.INVISIBLE

    private var aDefault: EventBus? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun init() {
        if (aDefault == null) {
            aDefault = EventBus.getDefault()
        }
        if (!aDefault?.isRegistered(this)!!) {
            aDefault?.register(this)
        }

        if (!this::adapter.isInitialized) {
            adapter = ProductListAdapter(R.layout.commodity_itme, rowsList)
            recyclerView.layoutManager =
                    //StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            recyclerView.addItemDecoration(
                XavierGridItemDecoration(
                    true
                )
            )
            recyclerView.adapter = adapter
            adapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(
                    adapter: BaseQuickAdapter<*, *>,
                    view: View,
                    position: Int
                ) {
                    val bundle = Bundle()
                    bundle.putInt(GID, rowsList.get(position).id)

                    intent(CommodityDetailsActivity::class.java, bundle)
                }
            })
            // 移除全部头部
            adapter.removeAllHeaderView()
            adapter.addHeaderView(getBannerLayout(), 0)
            if (pageItem == 0) {
                adapter.addHeaderView(getAdLayout(), 1)
                //adapter.setHeaderView(getEmptyHeaderView())
            } else {
                adapter.addHeaderView(getEmptyHeader(), 1)
                //adapter.setHeaderView(getEmptyHeaderView())
            }

            val screenHeight: Int = ScreenUtils.getScreenHeight()
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    totalDy += dy
                    Log.i(
                        TAG,
                        "onScrolled: dy = " + dy + " --- totalDy = " + totalDy + " --- screenHeight = " + screenHeight
                    )
                    if (totalDy > screenHeight) {
                        if (fabStatus == View.INVISIBLE) {
                            fabStatus = View.VISIBLE
                            aDefault?.post(PassableFloatingActionButtonState(fabStatus))
                        }
                    } else {
                        if (fabStatus == View.VISIBLE) {
                            fabStatus = View.INVISIBLE
                            aDefault?.post(PassableFloatingActionButtonState(fabStatus))
                        }
                    }

                }
            })
        }
        refreshLayout.setOnRefreshListener {
            page = 1
            super.init()
        }
        refreshLayout.setOnLoadMoreListener {
            if (page < total) {
                page++
            }
            presenter?.hot(page)
        }

        super.init()
    }

    override fun onLazyLoad() {
        //recyclerView.scrollToPosition(0)
    }

    private fun getBannerLayout(): View {
        return View.inflate(context, R.layout.banner, null)
    }

    override fun showSlideShow(slideList: List<SlideShowResult>) {
        adapter.setHeaderView(getBannerHeader(slideList), 0)
    }

    private fun getBannerHeader(slideList: List<SlideShowResult>): View {
        val view = getBannerLayout()
        val imageAdapter = BannerImageAdapter(slideList);
        val banner =
            view.findViewById<Banner<SlideShowResult, BannerAdapter<SlideShowResult, BannerImageAdapter.BannerViewHolder>>>(
                R.id.banner
            )
        banner.adapter = imageAdapter
        banner.setBannerGalleryMZ(20);
        return view
    }

    override fun slideShowError(errorMsg: String) {
        adapter.setHeaderView(getEmptyHeader(), 0)
    }

    private fun getEmptyHeader(): View {
        return View(context)
    }

    private fun getAdLayout(): View {
        return View.inflate(context, R.layout.banner, null)
    }

    override fun showAd(adList: List<HomeAdResult>) {
        adapter.setHeaderView(getAdHeaderView(adList), 1)
    }

    override fun adError(errorMsg: String) {
        adapter.setHeaderView(getEmptyHeader(), 1)
    }

    override fun showSubClass(subclassList: List<SubClassResult>) {
    }

    override fun subClassError(errorMsg: String) {
    }


    override fun showChoiceShop(choiceLists: List<List<ChoiceShopResult>>) {
    }

    override fun choiceShopError(errorMsg: String) {
    }

    override fun showHot(hotResult: HotResult) {
        endRefreshing()
        val rows = hotResult.rows
        total = hotResult.total
        if (rows.isNotEmpty()) {
            if (page == 1) {
                rowsList.clear()
                rowsList.addAll(rows)
                adapter.notifyDataSetChanged()
            } else {
                adapter.addData(rows)
                //rowsList.addAll(rows)
                //adapter.notifyDataSetChanged()
            }
        }
    }

    private fun endRefreshing() {
        if (refreshLayout.isRefreshing) {
            refreshLayout.finishRefresh()
        }
        if (refreshLayout.isLoading) {
            refreshLayout.finishLoadMore()
        }
    }

    override fun hotError(errorMsg: String) {

    }

    override fun showError(error: String) {
        Log.e(TAG, "showError: $error")
    }

    private fun getAdHeaderView(adList: List<HomeAdResult>): View {
        val view = View.inflate(context, R.layout.advertising_space, null)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val imageView1 = view.findViewById<ImageView>(R.id.imageView1)
        val imageView2 = view.findViewById<ImageView>(R.id.imageView2)
        val imageView3 = view.findViewById<ImageView>(R.id.imageView3)
        val imageView4 = view.findViewById<ImageView>(R.id.imageView4)
        for (homeAdResult in adList) {
            when (adList.indexOf(homeAdResult)) {
                0 -> {
                    context?.let {
                        GlideEngineLoging.createGlideEngine()
                            .loadDefaultImage(it, homeAdResult.image, imageView)
                    }
                }
                1 -> {
                    context?.let {
                        GlideEngineLoging.createGlideEngine()
                            .loadImageAsResId(it, homeAdResult.image, R.drawable.ad_map, imageView1)
                    }
                }
                2 -> {
                    context?.let {
                        GlideEngineLoging.createGlideEngine()
                            .loadDefaultImage(it, homeAdResult.image, imageView2)
                    }
                }
                3 -> {
                    context?.let {
                        GlideEngineLoging.createGlideEngine()
                            .loadDefaultImage(it, homeAdResult.image, imageView3)
                    }
                }
                4 -> {
                    context?.let {
                        GlideEngineLoging.createGlideEngine().loadImageAsResRadiusId(
                            it,
                            homeAdResult.image,
                            R.drawable.ad4_map,
                            80,
                            imageView4
                        )
                    }
                }
                else -> {
                    return view
                }
            }
        }
        return view
    }

    override fun createPresenter(): TabContract.Presenter<TabContract.View>? {
        return TabPresenter(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun stickEvent(event: StickEvent) {
        //平滑滚动 会运行 onScrolled() 但是因为滑动太快，往往 totalDy 不能置0
        //recyclerView.smoothScrollToPosition(0)
        //非平滑滚动 使用此方法不会走 onScrolled() 所以手动置为0即可
        recyclerView.scrollToPosition(0)
        totalDy = 0
    }

}