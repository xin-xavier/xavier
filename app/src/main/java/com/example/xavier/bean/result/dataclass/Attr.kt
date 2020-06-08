package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class Attr(@SerializedName("skuData")
                val skuData: List<SkuDataItem>?,
                @SerializedName("dataSource")
                val dataSource: List<DataSourceItem>?)