package com.example.prepotency.bean.result

import android.os.Parcel
import android.os.Parcelable

class SubClassResult : Parcelable {
    /**
     * id : 16
     * name : 酒
     * image : http://admin.chengmeiyoupin.com/uploads/20191114/96acb293285104e3c0f82e4fd9da1738.jpg
     */
    var id = 0
    var name: String = ""
    var image: String = ""

    override fun toString(): String {
        return "SubClassResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}'
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(image)
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        name = `in`.readString().toString()
        image = `in`.readString().toString()
    }

    companion object {
        val CREATOR: Parcelable.Creator<SubClassResult?> =
            object : Parcelable.Creator<SubClassResult?> {
                override fun createFromParcel(source: Parcel): SubClassResult? {
                    return SubClassResult(source)
                }

                override fun newArray(size: Int): Array<SubClassResult?> {
                    return arrayOfNulls(size)
                }
            }
    }
}