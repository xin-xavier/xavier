package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class DetailsData(@SerializedName("shop")
                         val shop: Shop,
                       @SerializedName("comment")
                         val comment: Comment,
                       @SerializedName("rows")
                         val rows: Rows,
                       @SerializedName("recommended")
                         val recommended: List<RecommendedItem>?)