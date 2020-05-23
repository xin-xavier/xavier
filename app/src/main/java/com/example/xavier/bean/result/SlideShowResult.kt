package com.example.prepotency.bean.result

import android.os.Parcel
import android.os.Parcelable

class SlideShowResult : Parcelable {
    /**
     * id : 6
     * name : 333
     * goods_id : 139
     * url :
     * image : http://admin.chengmeiyoupin.com/uploads/20191122/b9aa88c2daeeed962e83b802c92241a3.png
     * position_id : 0
     * mid : 50
     * shipping_method : 0
     */
    var id = 0
    var name: String? = null
    var goods_id = 0
    var url: String? = null
    var image: String = ""
    var position_id = 0
    var mid = 0
    var shipping_method = 0

    override fun toString(): String {
        return "SlideShowResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goods_id=" + goods_id +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", position_id=" + position_id +
                ", mid=" + mid +
                ", shipping_method=" + shipping_method +
                '}'
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeInt(goods_id)
        dest.writeString(url)
        dest.writeString(image)
        dest.writeInt(position_id)
        dest.writeInt(mid)
        dest.writeInt(shipping_method)
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        name = `in`.readString()
        goods_id = `in`.readInt()
        url = `in`.readString()
        image = `in`.readString().toString()
        position_id = `in`.readInt()
        mid = `in`.readInt()
        shipping_method = `in`.readInt()
    }

    companion object {
        val CREATOR: Parcelable.Creator<SlideShowResult?> =
            object : Parcelable.Creator<SlideShowResult?> {
                override fun createFromParcel(source: Parcel): SlideShowResult? {
                    return SlideShowResult(source)
                }

                override fun newArray(size: Int): Array<SlideShowResult?> {
                    return arrayOfNulls(size)
                }
            }
    }
}