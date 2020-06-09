package com.example.xavier.adapter.page

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.xavier.R
import com.example.xavier.bean.viewholder.ImageHolder
import com.example.xavier.bean.viewholder.VideoHolder
import com.example.xavier.utils.GlideEngineLoging
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.util.BannerUtils

class BannerHaveVideoAdapter(private val context: Context, datas: List<String>) :
    BannerAdapter<String, RecyclerView.ViewHolder>(datas) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return ImageHolder(BannerUtils.getView(parent!!, R.layout.image))
        }
        return VideoHolder(BannerUtils.getView(parent!!, R.layout.video))
    }

    override fun onBindView(
        holder: RecyclerView.ViewHolder?,
        data: String?,
        position: Int,
        size: Int
    ) {
        val itemViewType = holder?.itemViewType
        if (itemViewType == 1) {
            val imageHolder = holder as ImageHolder
            if (data != null) {
                GlideEngineLoging.createGlideEngine()
                    .loadAsBitmapImage(context, data, imageHolder.imageView)
            }
        } else {
            val videoHolder = holder as VideoHolder
            videoHolder.player.setUp(data, true, null)
            videoHolder.player.backButton.visibility = View.GONE
            //增加封面
            val imageView = ImageView(context)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            if (data != null) {
                GlideEngineLoging.createGlideEngine().loadAsBitmapImage(context, data, imageView)
            }
            videoHolder.player.thumbImageView = imageView
        }
    }


    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 2
        }
        return 1
    }
}