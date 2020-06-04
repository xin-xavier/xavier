package com.example.xavier.main.home.container

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.SizeUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.prepotency.bean.result.*
import com.example.xavier.R
import com.example.xavier.adapter.page.BannerImageAdapter
import com.example.xavier.app.api.ConstantPool.Companion.YANG
import com.example.xavier.app.api.Enumerations.Companion.peoplePay
import com.example.xavier.commodity.CommodityDetailsActivity
import com.example.xavier.utils.GlideEngineLoging
import com.example.xavier.utils.HorizontalItemDecoration
import com.example.xavier.utils.XavierItemDecoration
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter
import kotlinx.android.synthetic.main.fragment_tab.*


class TabFragment : HomeContainerFragment() {

    private val rowsList = ArrayList<HotResult.RowsBean>()

    // private val adapter = ProductListAdapter(R.layout.commodity_itme, rowsList)
    private val adapter = ProductListAdapter(R.layout.tiem3, rowsList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun init() {
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        /*  val stringIntegerHashMap: HashMap<String, Int?> = HashMap()
          stringIntegerHashMap[RecyclerViewSpacesItemDecoration.TOP_DECORATION] =
              SizeUtils.dp2px(12.toFloat())
          stringIntegerHashMap[RecyclerViewSpacesItemDecoration.LEFT_DECORATION] =
              SizeUtils.dp2px(6.toFloat())
          recyclerView.addItemDecoration(RecyclerViewSpacesItemDecoration(stringIntegerHashMap))*/
        /* val spanCount =100 // 3 columns
         val spacing = 12 // 50px
         val includeEdge = true
         recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, SizeUtils.dp2px(6f), includeEdge))*/
       // recyclerView.addItemDecoration(HorizontalItemDecoration(12, context)) //10表示10dp
        recyclerView.addItemDecoration(XavierItemDecoration(true)) //10表示10dp
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                intent(CommodityDetailsActivity::class.java)
            }
        })
        // 移除全部头部
        adapter.removeAllHeaderView()
        if (pageItem == 0) {
            adapter.setHeaderView(getEmptyHeaderView())
            adapter.setHeaderView(getEmptyHeaderView())
            //adapter.setHeaderView(getEmptyHeaderView())
        } else {
            adapter.setHeaderView(getEmptyHeaderView())
            //adapter.setHeaderView(getEmptyHeaderView())
        }
        super.init()
    }

    override fun onLazyLoad() {
        recyclerView.scrollToPosition(0)
    }

    private fun getEmptyHeaderView(): View {
        return View(context)
    }

    override fun showSlideShow(slideList: List<SlideShowResult>) {
        adapter.setHeaderView(getBannerHeaderView(slideList), 1)
    }

    override fun showSubClass(subclassList: List<SubClassResult>) {
    }

    override fun showAd(adList: List<HomeAdResult>) {
        adapter.setHeaderView(getAdHeaderView(adList), 2)
    }

    override fun showChoiceShop(choiceLists: List<List<ChoiceShopResult>>) {
    }

    override fun showHot(hotResult: HotResult) {
        val rows = hotResult.rows
        rowsList.clear()
        rowsList.addAll(rows)
        adapter.notifyDataSetChanged()

    }

    override fun showError(error: String) {
        ToastUtils.showShort(error)
    }

    private fun getBannerHeaderView(slideList: List<SlideShowResult>): View {
        val view = View.inflate(context, R.layout.banner, null)
        val imageAdapter = BannerImageAdapter(slideList);
        val banner =
            view.findViewById<Banner<SlideShowResult, BannerAdapter<SlideShowResult, BannerImageAdapter.BannerViewHolder>>>(
                R.id.banner
            )
        banner.adapter = imageAdapter
        banner.setBannerGalleryMZ(20);
        return view
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

    inner class ProductListAdapter(layoutResId: Int, data: MutableList<HotResult.RowsBean>?) :
        BaseQuickAdapter<HotResult.RowsBean, BaseViewHolder>(layoutResId, data) {

        override fun convert(holder: BaseViewHolder, item: HotResult.RowsBean) {
           /* val position = holder.layoutPosition
            Log.i(TAG, "convert: " + "position = " + position + " --- " + item.name)
            if (position > 0) {
                val oddEven = position%2
                val dp2px12: Int = SizeUtils.dp2px(12f);
                val dp2px6: Int = SizeUtils.dp2px(6f);
                if(oddEven==1){
                    holder.getView<LinearLayout>(R.id.layout).setPadding(dp2px12,dp2px12,dp2px6,0)
                }else{
                    holder.getView<LinearLayout>(R.id.layout).setPadding(dp2px6,dp2px12,dp2px12,0)
                }
            }*/
            GlideEngineLoging.createGlideEngine()
                .loadDefaultMapImage(context, item.image, holder.getView(R.id.image))
            SpanUtils.with(holder.getView(R.id.price)).append(YANG).setFontSize(10, true)
                .append(item.price).setFontSize(17, true).create()
            holder.setText(R.id.name, item.name)
                .setText(R.id.payments, item.sales.toString() + peoplePay)
        }
    }

}