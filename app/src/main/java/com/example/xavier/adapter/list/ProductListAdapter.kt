package com.example.xavier.adapter.list

import android.util.Log
import com.blankj.utilcode.util.SpanUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.prepotency.bean.result.HotResult
import com.example.xavier.R
import com.example.xavier.app.api.ConstantPool
import com.example.xavier.app.api.Enumerations
import com.example.xavier.utils.GlideEngineLoging

class ProductListAdapter(layoutResId: Int, data: MutableList<HotResult.RowsBean>?) :
    BaseQuickAdapter<HotResult.RowsBean, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: HotResult.RowsBean) {
        Log.i("ProductListAdapter", "convert: " + holder.layoutPosition + " name = " + item.name)
        GlideEngineLoging.createGlideEngine()
            .loadDefaultMapImage(context, item.image, holder.getView(R.id.image))
        SpanUtils.with(holder.getView(R.id.price)).append(ConstantPool.YANG).setFontSize(10, true)
            .append(item.price).setFontSize(17, true).create()
        holder.setText(R.id.name, item.name)
            .setText(R.id.payments, item.sales.toString() + Enumerations.peoplePay)
    }
}