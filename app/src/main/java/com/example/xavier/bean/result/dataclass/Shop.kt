package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class Shop(@SerializedName("headImage")
                val headImage: String = "",
                @SerializedName("count")
                val count: Int = 0,
                @SerializedName("store_name")
                val storeName: String = "",
                @SerializedName("goods")
                val goods: List<GoodsItem>?)