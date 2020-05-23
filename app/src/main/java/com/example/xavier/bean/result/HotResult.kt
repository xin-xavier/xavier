package com.example.prepotency.bean.result

import android.os.Parcel
import android.os.Parcelable
import com.example.prepotency.bean.result.HotResult.RowsBean
import java.util.*
import kotlin.collections.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class HotResult : Parcelable {
    /**
     * rows : [{"id":4,"shop_id":1,"images":"https://goss.veer.com/creative/vcg/veer/612/veer-165164710.jpg","name":"虚拟自动","num":100,"price":"0.10","sales":13,"type":0,"shipping_method":2},{"id":3,"shop_id":1,"images":"https://goss.veer.com/creative/vcg/veer/612/veer-165164710.jpg","name":"虚拟手动","num":100,"price":"0.10","sales":12,"type":0,"shipping_method":1},{"id":1,"shop_id":1,"images":"https://goss.veer.com/creative/vcg/veer/612/veer-165164710.jpg","name":"实物","num":100,"price":"0.10","sales":11,"type":1,"shipping_method":0}]
     * total : 3
     */
    var total = 0
    var rows: List<RowsBean> = ArrayList()

    class RowsBean : Parcelable {
        /**
         * id : 4
         * shop_id : 1
         * images : https://goss.veer.com/creative/vcg/veer/612/veer-165164710.jpg
         * name : 虚拟自动
         * num : 100
         * price : 0.10
         * sales : 13
         * type : 0
         * shipping_method : 2
         */
        var id = 0
        var shop_id = 0
        var image: String = ""
        var images: String? = null
        var name: String = ""
        var num = 0
        var price: String = ""
        var sales = 0
        var type = 0
        var shipping_method = 0

        override fun toString(): String {
            return "RowsBean{" +
                    "id=" + id +
                    ", shop_id=" + shop_id +
                    ", images='" + images + '\'' +
                    ", name='" + name + '\'' +
                    ", num=" + num +
                    ", price='" + price + '\'' +
                    ", sales=" + sales +
                    ", type=" + type +
                    ", shipping_method=" + shipping_method +
                    '}'
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(id)
            dest.writeInt(shop_id)
            dest.writeString(images)
            dest.writeString(name)
            dest.writeInt(num)
            dest.writeString(price)
            dest.writeInt(sales)
            dest.writeInt(type)
            dest.writeInt(shipping_method)
        }

        constructor() {}
        protected constructor(`in`: Parcel) {
            id = `in`.readInt()
            shop_id = `in`.readInt()
            images = `in`.readString()
            name = `in`.readString().toString()
            num = `in`.readInt()
            price = `in`.readString().toString()
            sales = `in`.readInt()
            type = `in`.readInt()
            shipping_method = `in`.readInt()
        }

        companion object {
            val CREATOR: Parcelable.Creator<RowsBean?> = object : Parcelable.Creator<RowsBean?> {
                override fun createFromParcel(source: Parcel): RowsBean? {
                    return RowsBean(source)
                }

                override fun newArray(size: Int): Array<RowsBean?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

    override fun toString(): String {
        return "HotResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}'
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(total)
        dest.writeList(rows)
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        total = `in`.readInt()
        rows = ArrayList()
        `in`.readList(rows, RowsBean::class.java.classLoader)
    }

    companion object {
        val CREATOR: Parcelable.Creator<HotResult?> = object : Parcelable.Creator<HotResult?> {
            override fun createFromParcel(source: Parcel): HotResult? {
                return HotResult(source)
            }

            override fun newArray(size: Int): Array<HotResult?> {
                return arrayOfNulls(size)
            }
        }
    }
}