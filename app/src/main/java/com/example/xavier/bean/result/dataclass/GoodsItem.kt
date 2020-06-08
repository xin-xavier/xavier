package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class GoodsItem(@SerializedName("image")
                     val image: String = "",
                     @SerializedName("price")
                     val price: String = "",
                     @SerializedName("shipping_method")
                     val shippingMethod: Int = 0,
                     @SerializedName("name")
                     val name: String = "",
                     @SerializedName("id")
                     val id: Int = 0)