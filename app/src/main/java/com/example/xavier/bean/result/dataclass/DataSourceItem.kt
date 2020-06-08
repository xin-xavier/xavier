package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class DataSourceItem(@SerializedName("value")
                          val value: List<String>?,
                          @SerializedName("key")
                          val key: String = "")