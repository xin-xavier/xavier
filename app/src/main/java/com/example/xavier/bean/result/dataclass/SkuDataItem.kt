package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class SkuDataItem(@SerializedName("preview")
                       val preview: String = "",
                       @SerializedName("purchase_num")
                       val purchaseNum: Int = 0,
                       @SerializedName("coding")
                       val coding: String = "",
                       @SerializedName("condition")
                       val condition: String = "",
                       @SerializedName("price")
                       val price: String = "",
                       @SerializedName("num")
                       val num: Int = 0,
                       @SerializedName("sku")
                       val sku: String = "",
                       @SerializedName("barcode")
                       val barcode: String = "")