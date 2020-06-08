package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class RecommendedItem(@SerializedName("image")
                           val image: String = "",
                           @SerializedName("price")
                           val price: String = "",
                           @SerializedName("name")
                           val name: String = "",
                           @SerializedName("id")
                           val id: Int = 0,
                           @SerializedName("sales")
                           val sales: Int = 0)