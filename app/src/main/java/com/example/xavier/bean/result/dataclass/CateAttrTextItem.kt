package com.example.xavier.bean.result.dataclass

import com.google.gson.annotations.SerializedName

data class CateAttrTextItem(@SerializedName("v")
                            val v: String = "",
                            @SerializedName("n")
                            val n: String = "")